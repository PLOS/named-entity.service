NED Integration tests in Docker
==============================

To run these tests cd into this directory and run ./run_tests.py

This python script will spin up docker containers of mysql and the ned service. It will then make some rest level requests, validate the responses, tear down the containers and quit.

Requirements
------------
* docker >= 1.3
* fig >= 0.5.2
* python >= 2.7

Make sure you have built the service war file before doing these tests.

Notes
-----
My tests have shown the first time run can take 5 to 10 minutes since the docker images have to be built. But successive runs have an overhead of under a minute (not including the tests themselves) to start and stop the containers.
