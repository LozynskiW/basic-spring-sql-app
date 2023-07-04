#!/bin/sh

project_dir=""
java_files_dir="src/main/java/back/"
app_file="App.java"
output_dir="$project_dir/target/classes"

mvn clean compile

# Check if the compilation was successful
if [ $? -eq 0 ]; then
    echo "Compilation successful"
else
    echo "Compilation failed"
fi