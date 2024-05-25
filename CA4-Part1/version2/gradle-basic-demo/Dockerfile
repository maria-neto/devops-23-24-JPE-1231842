FROM gradle:jdk21

WORKDIR /ca4-part1-v2

COPY build/libs/basic_demo-0.1.0.jar /ca4-part1-v2

ENTRYPOINT ["java", "-cp", "basic_demo-0.1.0.jar", "basic_demo.ChatServerApp", "59001"]