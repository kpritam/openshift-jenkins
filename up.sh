#!/usr/bin/env bash

oc new-project cicd --display-name="CI/CD"

oc policy add-role-to-user edit system:serviceaccount:cicd:jenkins -n cicd

oc create -f volumes/coursier_pv.yaml  
oc create -f volumes/ivy2_pv.yaml  
oc create -f volumes/ws_pv.yaml  
oc create -f volumes/coursier_pvc.yaml  
oc create -f volumes/ivy2_pvc.yaml  
oc create -f volumes/ws_pvc.yaml 

oc process -f jenkins.yaml | oc create -f - -n cicd