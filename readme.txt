javac -cp junit-4.12.jar:. resource/JUnit1.java
javac -cp junit-4.12.jar:. resource/JUnit2.java
javac -cp junit-4.12.jar:. resource/JunitTestSuite.java
javac -cp junit-4.12.jar:. resource/TestRunner.java
java -cp junit-4.12.jar:hamcrest-core-1.3.jar:. resource.TestRunner