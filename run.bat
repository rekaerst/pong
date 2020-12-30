cd %~dp0
mvn clean package
%JAVA_HOME%\bin\java -jar target\pong-1.0.0.jar
