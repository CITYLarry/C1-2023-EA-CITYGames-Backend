FROM amazoncorretto:17-alpine
WORKDIR /app
COPY . /app
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "applications/app-service/build/libs/CITYGames.jar"]
