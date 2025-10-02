#!/bin/bash

MAIN_CLASS="EditorFormas"

# Compilar todas as classes
javac --module-path lib --add-modules javafx.controls -d out src/*.java

# Executar a aplicação
java --module-path lib --add-modules javafx.controls -cp out $MAIN_CLASS
