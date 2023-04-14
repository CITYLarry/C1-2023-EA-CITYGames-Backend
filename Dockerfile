FROM amazoncorretto:17-alpine
WORKDIR /app
COPY . /app
RUN ./gradlew assemble
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "applications/app-service/build/libs/CITYGames.jar"]
