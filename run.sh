#!/bin/bash

javac */*.java
jar cvf balls.jar */*.class

# with & run in background
rmiregistry &
pid_reg=$!
echo
echo "rmiregistry started with pid" $pid_reg

java -classpath balls.jar -Djava.security.policy=security.policy balls.Player 2 &
pid_1=$!
echo "balls.jar started with pid" $pid_1
# wait a moment to let BallBoss register
sleep 3
java -classpath balls.jar -Djava.security.policy=security.policy balls.Player localhost Bill &
pid_2=$!
echo "balls.jar started with pid" $pid_2
# wait another moment
sleep 3
java -classpath balls.jar -Djava.security.policy=security.policy balls.Player localhost Steve &
pid_3=$!
echo "balls.jar started with pid" $pid_3

# now everything goes to the same console

echo
echo "Press any key to leave and end started processes..."
echo
read -p ""

kill $pid_reg
echo "rmiregistry" $pid_reg "terminated"
kill $pid_1
echo "balls.jar" $pid_1 "terminated"
kill $pid_2
echo "balls.jar" $pid_2 "terminated"
kill $pid_3
echo "balls.jar" $pid_3 "terminated"