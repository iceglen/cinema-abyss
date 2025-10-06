FROM maven:3.9-eclipse-temurin-24 AS builder
WORKDIR /build
COPY . /build
RUN mvn clean package -DskipTests

FROM eclipse-temurin:24-jre-alpine
WORKDIR /app
COPY --from=builder /build/target/proxy-service-0.0.1.jar /app/app.jar
ENTRYPOINT ["java","-jar","/app/app.jar"]