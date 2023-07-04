#!/bin/bash

mvn exec:java -Dexec.mainClass="back.App"

# Check the Maven execution status
if [ $? -eq 0 ]; then
    echo "Maven execution successful"
else
    echo "Maven execution failed"
fi