#!/bin/bash

cd `dirname $0`

java -jar ~/.m2/repository/org/apache/avro/avro-tools/1.7.7/avro-tools-1.7.7.jar compile schema   src/main/resources/avro/   src/main/java
