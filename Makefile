build:
	javac src/com/rekaerst/pong/*.java -d build
	javac src/com/rekaerst/pong/gameObjects/*.java -d build
run: build
	java -cp build com.rekaerst.pong.App
