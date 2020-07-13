FROM adoptopenjdk/openjdk8-openj9:x86_64-alpine-jdk8u202-b08_openj9-0.12.1-slim
ARG JAR_FILE=target/product-service.jar

WORKDIR /opt/app
# cp spring-boot-web.jar /opt/app/app.jar
COPY ${JAR_FILE} app.jar
# java -jar /opt/app/app.jar
ENTRYPOINT ["java","-jar","app.jar"]