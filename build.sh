#!/usr/bin/bash

javac *.java
jar -cvfm SessionPatcher.jar manifest.inf *.class
