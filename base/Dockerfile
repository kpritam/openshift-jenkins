FROM openshift/jenkins-slave-base-centos7

MAINTAINER Pritam Kadam <phkadam2008@gmail.com>

# Env variables
ENV SCALA_VERSION 2.12.4
ENV SBT_VERSION 1.0.3

# Install Scala
## Piping curl directly in tar
RUN \
  curl -fsL https://downloads.typesafe.com/scala/$SCALA_VERSION/scala-$SCALA_VERSION.tgz | tar xfz - -C $HOME/ && \
  echo >> $HOME/.bashrc && \
  echo "export PATH=~/scala-$SCALA_VERSION/bin:$PATH" >> $HOME/.bashrc

# Install sbt
RUN curl -L -o sbt-$SBT_VERSION.tgz "https://github.com/sbt/sbt/releases/download/v${SBT_VERSION}/sbt-${SBT_VERSION}.tgz" | gunzip | tar -x -C /usr/local && \
    ln -s /usr/local/sbt/bin/sbt /usr/local/bin && \
    rm sbt-$SBT_VERSION.tgz && \
    mkdir -p $HOME/.sbt && \
    mkdir -p $HOME/.ivy2 && \
    mkdir -p $HOME/.coursier && \
    sbt sbtVersion 

RUN chown -R 1001:0 $HOME && \
    chmod -R g+rw $HOME

USER 1001