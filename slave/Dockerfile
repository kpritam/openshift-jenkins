FROM openshift/jenkins-slave-maven-centos7

MAINTAINER Pritam Kadam <phkadam2008@gmail.com>

USER root

# Env variables
ENV SBT_VERSION 1.0.3

COPY sbt-1.0.3.tgz sbt-1.0.3.tgz

# Install sbt
RUN  gunzip sbt-1.0.3.tgz | tar xfz sbt-1.0.3.tgz -C /usr/local && \
    ln -s /usr/local/sbt/bin/sbt /usr/local/bin && \
    mkdir -p $HOME/.sbt && \
    mkdir -p $HOME/.ivy2 && \
    mkdir -p $HOME/.coursier

RUN chown -R 1001:0 $HOME && \
    chmod -R g+rw $HOME

USER 1001
