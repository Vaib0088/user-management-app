FROM eclipse-temurin:17

LABEL mentainer="patilvaibhavanil88@gmail.com"

WORKDIR /app

COPY target/user-management-app-0.0.1-SNAPSHOT.jar /app/user-management-app.jar

ENTRYPOINT ["java", "-jar", "user-management-app.jar"]
