FROM openjdk:11
ARG DB_URL
ENV DB_URL=${DB_URL}
COPY /build/libs/news-api-0.0.1-SNAPSHOT.jar /app.jar
ENTRYPOINT ["java","-Duser.timezone=Asia/Seoul","-jar","/app.jar"]
