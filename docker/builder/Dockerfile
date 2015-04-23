FROM maven:3.3-jdk-8

WORKDIR /src

CMD mkdir build

CMD ["./ned.sh", "package"]

CMD ["copy", "target/*.?ar", "build"]
