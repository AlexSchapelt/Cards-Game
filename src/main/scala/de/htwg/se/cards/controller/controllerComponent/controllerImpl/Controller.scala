package de.htwg.se.cards.controller.controllerComponent.controllerImpl

import com.google.inject.{Guice, Injector}
import de.htwg.se.cards.CardsModule
import de.htwg.se.cards.controller.controllerComponent._
import de.htwg.se.cards.model.fileIOComponent.FileIOInterface
import de.htwg.se.cards.model.playerComponent.PlayerInterface
import de.htwg.se.cards.model.statusComponent.StatusInterface
import de.htwg.se.cards.util.{RuleStrategy, UndoManager}
import javax.inject.Inject

class Controller @Inject()(var status: StatusInterface) extends ControllerInterface {
  private val undoManager = new UndoManager

  override def preInit(): Unit = {
    publish(new Init)
  }

  override def init(player: List[PlayerInterface], rule: RuleStrategy): Unit = {
    status = status.copyS(queue = player, rule = rule).init
    publish(new StatusChanged)
  }

  override def nextPlayer(): Unit = {
    status = status.nextPlayer
    undoManager.reset()
    publish(new PlayerChanged)
  }

  override def draw(): Unit = {
    status = if (status == status.draw) status.rule.fillTalon(status).draw else status.draw
    publish(new CardsChanged)
  }

  override def shuffle(): Unit = {
    status = status.shuffle
    publish(new TalonChanged)
  }

  override def play(i: List[Int]): Unit = {
    undoManager.doStep(new PlayCommand(this, i))
    if (status.rule.won(status)) {
      publish(new PlayerWon)
    } else {
      publish(new CardsChanged)
      publish(new DiscardChanged)
    }
  }

  override def undo(): Unit = {
    undoManager.undoStep()
    publish(new StatusChanged)
  }

  override def redo(): Unit = {
    undoManager.redoStep()
    publish(new StatusChanged)
  }

  override def statusToString: String = status.toString

  val injector: Injector = Guice.createInjector(new CardsModule)
  val fileIO: FileIOInterface = injector.getInstance(classOf[FileIOInterface])

  override def load(): Unit = {
    status = fileIO.load
    undoManager.reset()
    publish(new StatusChanged)
  }

  override def save(): Unit = fileIO.save(this.status)
}
