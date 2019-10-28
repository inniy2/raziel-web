#!/bin/bash

export T_HOST=test-bae-server2.testdb

mvn clean package;

scp target/raziel-0.0.1-SNAPSHOT.jar $T_HOST:/home/sangsun/raziel/

scp -r src/main/js/react-app-1/build $T_HOST:/home/sangsun/raziel/
