FROM openjdk:8u201-jdk-alpine3.9

ENV TZ=Asia/Shanghai
# 设置alpine操作系统字符编码
ENV LANG=zh_CN.UTF-8
ENV LANGUAGE=zh_CN:zh
ENV LC_ALL=zh_CN.UTF-8

VOLUME /tmp

ADD target/server_project-1.0-SNAPSHOT.jar app.jar

ENTRYPOINT ["java","-jar","/app.jar"]

EXPOSE 8080

