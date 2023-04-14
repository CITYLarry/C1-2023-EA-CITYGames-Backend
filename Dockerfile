FROM amazoncorretto:17-alpine
WORKDIR /app
RUN ./gradlew assemble
COPY . /app
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "applications/app-service/build/libs/CITYGames.jar"]
