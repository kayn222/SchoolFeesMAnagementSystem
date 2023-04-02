#!/bin/sh
exec java -cp "elki/elki-0.8.0.jar:elki/*:dependency/*" elki.application.ELKILauncher "$@"
