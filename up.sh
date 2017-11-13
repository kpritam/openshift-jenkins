#!/usr/bin/env bash

oc new-project cicd --display-name="CI/CD"

oc policy add-role-to-user edit system:serviceaccount:cicd:jenkins -n cicd

oc create -f volumes/

oc create -f csw-jenkins-persistent.yml -n openshift

oc process csw-jenkins-persistent -n openshift | oc create -f - -n cicd

oc process -f jenkins.yml | oc create -f - -n cicd
