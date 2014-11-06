NED Integration tests in Docker
===============================

To run these tests cd into this directory and run ./run_tests.py

Make sure you have built the service war file BEFORE running these tests. If you use the ned.sh script in the above directory, it will automatically build the war before running the docker tests.

This python script will spin up docker containers of mysql and the ned service. It will then make some rest level requests, validate the responses, tear down the containers and quit.

Requirements
------------
* docker >= 1.3 (www.docker.com)
* fig >= 0.5.2 (www.fig.sh)
* python 2.7 (see script imports for required libraries)

Notes
-----
The first time you run these tests it can take a while. This is because it has to download a base Linux image the first time, and then it has to build the docker images. These will be caches so successive runs have an overhead of under a minute (not including the tests themselves) to start and stop the containers.
