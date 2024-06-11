FROM openjdk:17-alpine
ARG JAR_FILE=ccs/batch/build/libs/batch-0.0.1-SNAPSHOT.jar
ARG DB_URL
ENV DB_URL=${DB_URL}
COPY ${JAR_FILE} /app.jar
ENTRYPOINT ["java","-Duser.timezone=Asia/Seoul","-jar","/app.jar"]
