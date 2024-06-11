FROM openjdk:11
ARG JAR_FILE=ccs/news-api/build/libs/news-api-0.0.1-SNAPSHOT.jar
ARG DB_URL
ENV DB_URL=${DB_URL}
COPY ${JAR_FILE} /app.jar
ENTRYPOINT ["java","-Duser.timezone=Asia/Seoul","-jar","/app.jar"]
