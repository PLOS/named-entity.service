NED Integration tests in Docker
===============================

To run these tests cd into apitester directory and run ./ned-test.sh

This apitest container will use an internal python script against running docker containers of mysql and the ned service. It will then make some rest level requests, validate the responses, tear down the containers and quit.

Requirements
------------
* docker >= 1.3 (www.docker.com)
* docker-compose

It is recommended that you set up docker to run as a user instead of requiring sudo. See documentation (https://docs.docker.com/installation/ubuntulinux/#giving-non-root-access).


Notes
-----
The first time you run these tests it can take a while. This is because it has to download a base Linux image the first time, and then it has to build the docker images. These will be cached so successive runs have an overhead of under a minute (not including the tests themselves) to start and stop the containers.
