FROM amazoncorretto:17-alpine
#VOLUME /tmp
#COPY *.jar CITYGames.jar
#ENV JAVA_OPTS=" -Xshareclasses:name=cacheapp,cacheDir=/cache,nonfatal -XX:+UseContainerSupport -XX:MaxRAMPercentage=70 -Djava.security.egd=file:/dev/./urandom"
# Replace with a non-root user to avoid running the container with excessive privileges
#USER CITYLarry
#ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS  -jar CITYGames.jar" ]

WORKDIR /app
COPY . /app
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "applications/app-service/build/libs/CITYGames.jar"]
