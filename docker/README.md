NED Integration tests in Docker
==============================

To run these tests cd into this directory and run ./run_tests.py

This python script will spin up docker containers of mysql and the ned service. It will then make some rest level requests, validate the responses, tear down the containers and quit.

Requirements
------------
* docker >= 1.3
* fig >= 0.5.2
* python >= 2.7
