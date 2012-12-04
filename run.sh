#!/bin/bash

javac */*.java
jar cvf balls.jar */*.class

rmiregistry

java -classpath balls.jar -Djava.security.policy=security.policy balls.Player 2
java -classpath balls.jar -Djava.security.policy=security.policy balls.Player localhost Bill
java -classpath balls.jar -Djava.security.policy=security.policy balls.Player localhost Steve
