#!/bin/bash

java -jar dist/Compiler.jar -target dot $1 > dotout
dot -Tpng dotout > dot.png
rm dotout
