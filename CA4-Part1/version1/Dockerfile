FROM gradle:jdk21

WORKDIR /ca4-part1-v1

COPY . /ca4-part1-v1

RUN gradle clean build

ENTRYPOINT ["java", "-cp", "build/libs/basic_demo-0.1.0.jar", "basic_demo.ChatServerApp", "59001"]