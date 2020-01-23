[![Build Status](https://travis-ci.org/AlexSchapelt/Cards-Game.svg?branch=final)](https://travis-ci.org/AlexSchapelt/Cards-Game)
[![Coverage Status](https://coveralls.io/repos/github/AlexSchapelt/Cards-Game/badge.svg?branch=final)](https://coveralls.io/github/AlexSchapelt/Cards-Game?branch=master)


# Cards 
=====================================================
This repo is a game to apply the leaned Software Engineering styles from the Software-
Engineering class of the University of applied Science HTWG-Konstanz.

It has a MVC-Layout and is compatible with the Docker "scala-sbt" image from hseeberger


It is the used to be a Cards-Game Engine.
In this release only Mau is implemented, but this will change in further patches.

####How to run
#####Docker:
run your container with following flags:
-it --rm  -v /tmp/.X11-unix:/tmp/.X11-unix -e DISPLAY=unix$DISPLAY 

you may have to execute

xhost +local:$(id -un) 

first


The presentation for this project can be found here:
https://docs.google.com/presentation/d/1J6Qn5GGaf19PQWvK_TDqHCSGwhvDFqD_/edit#slide=id.p1