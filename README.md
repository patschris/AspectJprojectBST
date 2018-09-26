# Synchonize threads using AspectJ
In this project, I use AspectJ to synchronize threads. I constructed a Binary Search Tree (BST - see [[1]](
http://algorithms.tutorialhorizon.com/binary-search-tree-complete-implementation)) with some additions. Then, in my main function 
I created a number of threads that execute randomly a number of commands (insert, remove, lookup). The threads try to to access 
simultaneously the BST, so we have to ensure that we don't read and write at the same time, but many threads can read the same
content at the same time. We use AspectJ in Java with annotations (@before, @after) to synchronize the threads.

Written in Java 1.8, Ubuntu 16.04.3 LTS. 

## Installation
It's easy to install AspectJ on Ubuntu using just a simple command as follows:<br/> 
*sudo apt-get install aspectj*

## AspectJ
 AspectJ is a very nice MOP/general compositional semantic extensibility facility for Java:
- used entirely for interposing code, not changing how the object system works
- AspectJ is a transparent extension of Java, comes with IDE support (for easier editing, inspection of aspect code)
- Can declare members and supertypes for existing classes
- Nice tool to add external functionality without changing the initial code itself, for instance test events, debugging methods etc.

## Makefile
- `make run` to execute the program
- `make clean` to remove class files.

## References
[1] http://algorithms.tutorialhorizon.com/binary-search-tree-complete-implementation 
