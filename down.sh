#!/usr/bin/env bash

oc delete -f volumes/coursier_pv.yaml  
oc delete -f volumes/ivy2_pv.yaml  
oc delete -f volumes/ws_pv.yaml  
oc delete -f volumes/coursier_pvc.yaml  
oc delete -f volumes/ivy2_pvc.yaml  
oc delete -f volumes/ws_pvc.yaml 

oc delete project cicd