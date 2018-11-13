javac Node.java
javac Initiator.java

for /f %%i in (D:\GitHubProjekte\VAA2018\inputFiles\inputTextFile) do (
start cmd /K java Node %%i
)

start cmd /K java Initiator