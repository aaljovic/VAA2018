:: The first parametar is the full path to the file with the information for the nodes. This file starts all Nodes written in the file.
@ECHO OFF

:: Removes all class files
rm *.class

:: Compiles java files
javac Node.java
javac Initiator.java

:: Reads every line of the file
:: For every Node ID (written in each line) the java programm Node is started with the corresponding ID as parameter
for /f %%i in (%1) do (
start cmd /K java Node %%i
)

:: Starts the java Initiator programm once
start cmd /K java Initiator