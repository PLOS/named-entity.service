#!/bin/bash

mysql -u root < src/main/resources/ned-schema.mysql.sql 
mysql -u root < src/main/resources/ned-data.mysql.sql 

