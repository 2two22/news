FROM openjdk:11
ARG DB_URL
ENV DB_URL=jdbc:mysql://twotwo-db.c7iaym6c0pb0.ap-northeast-2.rds.amazonaws.com:3306/news


COPY build/libs/user-api-0.0.1-SNAPSHOT.jar user-api-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","user-api-0.0.1-SNAPSHOT.jar"]