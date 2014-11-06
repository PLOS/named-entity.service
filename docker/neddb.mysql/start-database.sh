#!/bin/bash

echo "Starting MySQL Server"
/usr/sbin/mysqld &
sleep 5

echo -e "\nCreating NED Schema"
mysql --default-character-set=utf8 < /mysql_schema/ned-schema.mysql.sql

echo -e "\nSeeding NED Schema"
mysql --default-character-set=utf8 < /mysql_schema/ned-data.mysql.sql

echo -e "\nCreating DB User (ned)"
echo "CREATE USER 'ned' IDENTIFIED BY ''" | mysql --default-character-set=utf8
echo "GRANT ALL PRIVILEGES ON *.* TO 'ned'@'%' WITH GRANT OPTION; FLUSH PRIVILEGES" | mysql --default-character-set=utf8
echo "Finished creating user."

tail -f /var/log/mysql/*
