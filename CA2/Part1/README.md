# Class Assignment 2, Part 1 : Build Tools with Gradle

## Introduction: What is Gradle?
Gradle is a build automation tool that is based on the concepts of Apache Ant and Apache Maven and introduces a Groovy-based domain-specific
language (DSL) instead of the XML form used by Apache Maven for declaring the project configuration. Gradle uses a directed acyclic graph ("DAG")
to determine the order in which tasks can be run. Gradle was designed for multi-project builds, which can become quite large, supporting
incremental builds by intelligently determining which parts of the build tree are updated, and is capable of reusing outputs from previous builds.

### Assignment Overview
For this assignment, the goal was to use a basic Gradle application to introduce some new and simple features, which would help to understand 
how the gradle wrapper works and how to edit the build.gradle file to add new tasks and dependencies. For each new task or feature added, issues were created 
in the DevOps repository, which were then associated with the commits made to implement said feature.

## First Steps

 1. Cloning the repository: in order to complete this assignment, the first step is to clone the repository from this [link](https://bitbucket.org/pssmatos/gradle_basic_demo/) to the local machine,
as it will provide the basic structure of the project and the necessary files to work on.


 2. In the DevOps private repository, a new directory was created to store the cloned repository.

```bash
mkdir CA2
cd CA2
mkdir Part1
mkdir Part2
```

Once the directories were created, the repository was copied into the Part1 directory.

```bash
cp -r /path/to/gradle_basic_demo/ CA2/Part1
```

 3. Commit the changes to the DevOps repository

After copying the repository, the changes were committed to the DevOps repository.

```bash
git add .
git commit -m "gradle-basic-demo added to CA2/Part1, closes #9"
git push origin main
```

### Exploring the application
The application used to complete this assignment implements a basic multithreaded chat room server, which supports several
simultaneous clients. When a client connects to the server, it is required to choose a screen name until a unique one is chosen.
After the name is acknowledged by the server, the client can start sending messages to the chat room. The server will broadcast
the messages to all connected clients.

#### Prerequisites
 - Java JDK 8 (or newer)
 - Apache Log4J 2
 - Gradle 7.4.1 (if gradle wrapper is not used in the project)

#### Build
To build a .jar file with the application:

```gradle
    % ./gradlew build
```

#### Run the server
To run the server, open a terminal and execute the following command from the project's root directory:

```gradle
    % java -cp build/libs/basic_demo-0.1.0.jar basic_demo.ChatServerApp <server port>
```

Substitute *server port* by a valid por number, e.g. 59001.

#### Run a client
To run a client, open a terminal and execute the following command from the project's root directory:

```gradle
 % ./gradlew runClient
```

The above task assumes the chat server's IP is "localhost" and its port is "59001". To use other parameters, the runClient 
task must be edited in the "build.gradle" file in the project's root directory.
To run several clients at the same time, more terminals should be open and the invocation of the runClient task should be repeated.

## Adding new tasks

In order to create and build the project using the newly implemented tasks, the build.gradle file was edited to include the new tasks
and dependencies. Also, the gradle wrapper version was updated to 8.6 in the gradle-wrapper.properties file.

### Task 1: Addition of a task to run the server

The first task created was to run the server. The task was named "runServer" and it was added to the build.gradle file as follows:

```groovy
task runServer(type:JavaExec, dependsOn: classes){
    group = "DevOps"
    description = "Launches a chat server that listens on port 59001"

    classpath = sourceSets.main.runtimeClasspath

    mainClass = 'basic_demo.ChatServerApp'

    args '59001'
}
```

This task is of type JavaExec, which means it will run a Java application. The task depends on the classes task, a lifecycle task added 
by the Java plugin, which ensures that the classes are compiled before the server is run. The classpath is set to the runtime classpath, 
which includes the compiled class files and the runtime dependencies. The main class is set to the ChatServerApp class, which specifies 
the name of the Java class to be executed with the task, in this case, it's the ChatServerApp class in the basic_demo package.

In order to run the server and compile the project with the new task included, the project was built again.

```gradle
 % ./gradlew build
```

With the addition of the new runServer task, the newly added changes were committed and pushed to the remote repository.

```bash
git add .
git commit -m "closes #10 - added runServer task and updated gradle version in gradle-wrapper properties"
git push origin main
```

### Addition of test class and unit test
As required, a new test class was added to the project and a unit test was developed. The class was named *AppTest* and it was added to the
src/test/java/basic_demo package. In order to do so, the following commands were executed:

```bash
mkdir -p src/test/java/basic_demo
touch src/test/java/basic_demo/AppTest.java
```

To set up the test class and add the unit test, the following code was added to the AppTest.java file:

```java
package basic_demo;

import org.junit.Test;
import static org.junit.Assert.*;

public class AppTest {
    @Test public void testAppHasAGreeting() {
        App classUnderTest = new App();
        assertNotNull("app should have a greeting", classUnderTest.getGreeting());
    }
}
```

In order to run the unit test, the dependencies were updated in the build.gradle file to include the JUnit library.

```groovy
dependencies {
    // Use Apache Log4J for logging
    implementation group: 'org.apache.logging.log4j', name: 'log4j-api', version: '2.11.2'
    implementation group: 'org.apache.logging.log4j', name: 'log4j-core', version: '2.11.2'
    testImplementation group: 'junit', name: 'junit', version: '4.12'
}
```

After updating the dependencies, the project could be built again.

```gradle
 % ./gradlew build
```
With the new features implemented, the changes were committed and pushed to the remote repository.

```bash
git add .
git commit -m "closes #11 created a test class and unit test, dependencies updated in build.gradle to include JUnit 4.12"
git push origin main
``` 

### Task 2: Addition of a task to copy source files
The class assignment required the addition of a new task to copy the source files to a new directory named *'backup'*,
in the application's root directory.

```groovy
task backupSourceFiles(type:Copy){
    group = "DevOps"
    description = "Copy the source code to the backup directory"

    from '/src'
    into '/backup'
}
```

The task was named *backupSourceFiles* and it was of type Copy, which is a task type provided by the Gradle build system to copy files.
The task was then run independently to check if the backup directory was created and the source files were successfully copied.

```gradle
 % ./gradlew backupSource
```

After running the task, the changes were committed and pushed to the remote repository.

```bash
git add .
git commit -m "closes #12 - added task to copy application source and created CA2/Part1/backup"
git push origin main
```

### Task 3: Addition of a task to zip the source files
The last task added to the project was to create an archive of the source files for the application. The task was named *zipSourceFiles* and it
was of type Zip, which is a task type provided by the Gradle build system to create zip files.

```groovy
task zipSourceFiles(type:Zip){
    group = "DevOps"
    description = "Create an archive of the source code"

    from '/src'
    archiveBaseName.set("source")
    archiveExtension.set("zip")
}

```

The task was then run independently to check if the zip file was created successfully.

```gradle
 % ./gradlew zipSource
```

The zip file was created in the project's root directory (CA2/Part1/build/distributions/source-0.1.0.zip) and the changes
were committed and pushed to the remote repository.

```bash
git add .
git commit -m "closes #13 - added task to zip application source"
git push origin main
```

Finally, as the implementation of this task marked the completion of the first part of this class assignment, a new tag was created.
```bash
git tag -a ca2-part1 -m "CA2 Part1 completed"
git push origin ca2-part1
```


