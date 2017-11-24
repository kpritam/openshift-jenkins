FROM openshift/jenkins-slave-maven-centos7

MAINTAINER Pritam Kadam <phkadam2008@gmail.com>

USER root

# Env variables
ENV SBT_VERSION 1.0.3

# Install sbt
RUN curl -Lo sbt-$SBT_VERSION.tgz  https://github.com/sbt/sbt/releases/download/v$SBT_VERSION/sbt-$SBT_VERSION.tgz
# RUN curl -Lo sbt-$SBT_VERSION.rpm https://dl.bintray.com/sbt/rpm/sbt-$SBT_VERSION.rpm
# RUN rpm -i sbt-$SBT_VERSION.rpm

RUN gunzip sbt-$SBT_VERSION.tgz | tar xfz sbt-$SBT_VERSION.tgz -C /usr/local && \
    ln -s /usr/local/sbt/bin/sbt /usr/local/bin && \
    mkdir -p $HOME/.sbt && \
    mkdir -p $HOME/.ivy2 && \
    mkdir -p $HOME/.coursier \
    mkdir -p $HOME/workspace

RUN chown -R 1001:0 $HOME && \
    chmod -R g+rw $HOME

USER 1001
