#!/bin/sh

set -eu

SCRIPT_DIR=$(CDPATH= cd -- "$(dirname -- "$0")" && pwd)
cd "$SCRIPT_DIR"

if ! command -v kotlinc >/dev/null 2>&1; then
    echo "Error: Kotlin compiler (kotlinc) is not installed or not in PATH."
    exit 1
fi

if ! command -v java >/dev/null 2>&1; then
    echo "Error: Java is not installed or not in PATH."
    exit 1
fi

echo "Compiling Kotlin files..."
kotlinc ./*.kt -include-runtime -d assignment.jar

echo "Running assignment..."
java -jar assignment.jar
