#FROM hseeberger/scala-sbt:graalvm-ce-19.3.0-java11_1.3.7_2.13.1
#WORKDIR /cards
#ADD . /cards
#CMD sbt run
#EXPOSE 80


FROM openjdk:11
ARG SBT_VERSION=1.3.7

# Install sbt
RUN \
  curl -L -o sbt-$SBT_VERSION.deb https://dl.bintray.com/sbt/debian/sbt-$SBT_VERSION.deb && \
  dpkg -i sbt-$SBT_VERSION.deb && \
  rm sbt-$SBT_VERSION.deb && \
  apt-get update && \
  apt-get install -y sbt libxrender1 libxtst6 libxi6
EXPOSE 80
WORKDIR /Sources
ADD . /Sources
CMD sbt run