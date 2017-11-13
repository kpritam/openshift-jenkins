# Openshift Jenkins Setup

## Start Openshift Jenkins

    1. oc new-project cicd --display-name="CI/CD"
    2. oc policy add-role-to-user edit system:serviceaccount:cicd:jenkins -n cicd
    3. oc process -f cicd.yaml | oc create -f - -n cicd

## Push Docker Image to OS Docker Registry & ImageStream

    1. docker tag twtmt/openshift/jenkins-2.89 <docker-registry-ip>:5000/openshift/jenkins-2.89
    2. oc whoami -t
    3. docker login --username=pritam --email=phkadam2008@gmail.com --password=<token> <docker-registry-ip>:5000
    4. docker push <docker-registry-ip>:5000/openshift/jenkins-2.89

## Linux Set UID and GID

    - groupadd -g 100011000 default
    - useradd -g 100011000 -u 100011000 default
    - usermod
    - groupmod
    - userdel
    - groupdel