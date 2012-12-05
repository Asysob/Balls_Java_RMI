#!/bin/bash

javac */*.java
jar cvf balls.jar */*.class

# with & run in background
rmiregistry &

java -classpath balls.jar -Djava.security.policy=security.policy balls.Player 2 &
# wait a moment to let BallBoss register
sleep 5
java -classpath balls.jar -Djava.security.policy=security.policy balls.Player localhost Bill &
# wait another moment
sleep 5
java -classpath balls.jar -Djava.security.policy=security.policy balls.Player localhost Steve &

# now everything goes to the same console
