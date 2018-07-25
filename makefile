JAVA_FILES=Main.java BinarySearchTree.java TreeNode.java
JAVA_ASPECT=AspectSynch.java

ASPECTJRT=/usr/share/java/aspectjrt.jar

compile: ${JAVA_FILES} ${JAVA_ASPECT}
	@ajc -source 1.8 ${JAVA_FILES} ${JAVA_ASPECT}

run: compile
	@java -cp ${ASPECTJRT}:. Main

clean:
	rm -f *.class