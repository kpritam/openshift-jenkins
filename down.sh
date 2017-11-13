#!/usr/bin/env bash

oc delete -f volumes/

oc delete template/csw-jenkins-persistent -n openshift

oc delete project cicd