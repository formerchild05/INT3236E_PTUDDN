# ====== Stage 1: Build ======
FROM maven:3.9.9-eclipse-temurin-17 AS build
WORKDIR /app

# Copy source code
COPY pom.xml .
COPY src ./src

# Build jar file
RUN mvn clean package -DskipTests

# ====== Stage 2: Run ======
FROM eclipse-temurin:17-jdk-jammy
WORKDIR /app

# Copy jar từ stage build sang
COPY --from=build /app/target/*.jar app.jar

# Mở port cho app
EXPOSE 8080

# Chạy ứng dụng
ENTRYPOINT ["java", "-jar", "app.jar"]