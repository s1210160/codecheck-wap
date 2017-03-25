#!/bin/bash

ROOT=$(cd $(dirname $0) && pwd)

### Java ###
java -jar $(ls $ROOT/java/ai/target/exam2a-ai-*.jar) "$@"
echo $?

### Python ###
# python $ROOT/python/src/exam2a-ai.pyc "$@"

### NodeJS ###
# cd $(dirname $0)/js
# node $ROOT/nodejs/src/exam2a-ai.js "$@"