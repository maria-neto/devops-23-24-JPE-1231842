# Class Assignment 4 - Part 1 : Containers with Docker

## Introduction
The goal of this class assignment is to explore the functionalities of Docker containers and images, creating images and running 
containers using a simple chat application as an example. 
Docker is a platform that allows the user to develop, ship, and run applications in containers. Containers allow a developer to 
package up an application with all parts it needs, such as libraries and other dependencies, and ship it all out as one package.
In this part of the class assignment, there will be to different approaches to create a Docker image, one where the chat server is built
inside the Docker image, and another where the chat server is built in the host machine and the resulting jar file is copied to the Docker image.

## Building the Docker Image for the Chat Server (Version 1)

In order to build the Docker image, a Dockerfile must be created. The Dockerfile is a text document that contains all the commands a user
could call on the command line to assemble an image. The Dockerfile used in the first version of this assignment, where the chat server is
built directly inside the Docker image, is shown below:

```Dockerfile
FROM gradle:jdk21

WORKDIR /ca4-part1-v1

COPY . /ca4-part1-v1

RUN gradle clean build

ENTRYPOINT ["java", "-cp", "build/libs/basic_demo-0.1.0.jar", "basic_demo.ChatServerApp", "59001"]
```

The provided Dockerfile has all the requirements to build the Docker image for the chat server.
 - The Dockerfile starts by using the gradle:jdk21 image as the base image, which is an image that already has the Gradle and JDK 21 installed. 
 - The *WORKDIR* command sets the working directory inside the Docker image to /ca4-part1-v1, which means all subsequent commands will be executed from this directory. 
 - The *COPY* command copies all the files from the current directory (where the Dockerfile is located) to the working directory inside the Docker image. 
 - The *RUN* command executes the gradle clean build command, which will build the chat server inside the Docker image, generating basic_demo-0.1.0.jar file inside the build/libs directory.
 - Finally, the *ENTRYPOINT* command specifies the command that will be executed when the container is started from the Docker image. In this case, the command is 
```java -cp build/libs/basic_demo-0.1.0.jar basic_demo.ChatServerApp 59001```, which will start the chat server on port 59001.
As the project that is being used in this assignment is a Gradle project that includes a task to run the chat server, the command to run this task could be ```gradle runServer ``` as is defined in 
the build.gradle file. This command will start the chat server on port 59001.

 - To build the Docker image, the following command must be executed in the same directory where the Dockerfile is located:

```bash
docker build -t chat-server:version1 .
```

 - This will build the Docker image with the name chat-server and the tag version1.


 - After the Docker image is built, the chat server can be started by running the following command:

```bash
docker run -p 59001:59001 chat-server:version1
```
 - This command will start the chat server on port 59001, and the chat server can be accessed by connecting to the port 59001 using a chat client.

 - To start the chat client, the user should run the following command in the host machine:

```bash
./gradlew runClient
```
 - This command will execute a task that is defined within the build.gradle file, which will start the chat client and connect to the chat server running on port 59001.

## Building the Docker Image for the Chat Server (Version 2)

In the second version of this assignment, the chat server is built in the host machine, and the resulting jar file is copied into the Docker image. So, in this version, to
ensure that the chat server is built in the host machine and generates the required jar file, the following command must be executed in the project directory where the build.gradle file is located:

```bash
./gradlew clean build
```

The Dockerfile used in this version is shown below: 

```Dockerfile
FROM gradle:jdk21

WORKDIR /ca4-part1-v2

COPY build/libs/basic_demo-0.1.0.jar /ca4-part1-v2

ENTRYPOINT ["java", "-cp", "basic_demo-0.1.0.jar", "basic_demo.ChatServerApp", "59001"]
```

The provided Dockerfile has all the requirements to build the Docker image for the chat server.
 - The Dockerfile starts by using the gradle:jdk21 image as the base image, which is an image that already has the Gradle and JDK 21 installed. 
 - The *WORKDIR* command sets the working directory inside the Docker image to /ca4-part1-v2, which means all subsequent commands will be executed from this directory. 
 - The *COPY* command copies the basic_demo-0.1.0.jar file from the build/libs directory in the host machine to the working directory inside the Docker image. 
 - Finally, the *ENTRYPOINT* command specifies the command that will be executed when the container is started from the Docker image. In this case, the command is 
```java -cp basic_demo-0.1.0.jar basic_demo.ChatServerApp 59001```, which will start the chat server on port 59001.

 - As it happened in the first version, to build the Docker image and run it, the following commands must be executed in the same directory where the Dockerfile is located:
 - To build the Docker image:
```bash
docker build -t chat-server:version2 .
```
 - This will build the Docker image with the name chat-server and the tag version2.

 - To run the chat server:
```bash
docker run -p 59001:59001 chat-server:version2
```
 - This command will start the chat server on port 59001, and the chat server can be accessed by connecting to the port 59001 using a chat client.

 - To start the chat client, the following command will execute the runClient task defined in the build.gradle file.

```bash
./gradlew runClient
```

## Conclusion

In this assignment, the functionalities of Docker containers and images were explored, creating images and running containers using a simple
chat application as an example. There were two different approaches to create a Docker image, one where the chat server is built inside the Docker image,
and another where the chat server is built in the host machine and the resulting jar file is copied into the Docker image.
This assignment emphasized the benefits of Docker in software development, such as packaging an application with all its dependencies into a single package, 
ensuring consistent operation across different environments.







