FROM maven:3.3-jdk-8

WORKDIR /src

RUN mkdir /build

ADD docker_runscript.bash /docker_runscript.bash

CMD ["bash", "/docker_runscript.bash"]
