#!/bin/bash

echo "Starting MySQL Server"
/usr/sbin/mysqld &
sleep 5

echo -e "\nCreating DB User"
echo "CREATE USER dummyuser IDENTIFIED BY 'password'" | mysql --default-character-set=utf8
echo "GRANT ALL PRIVILEGES ON *.* TO 'dummyuser'@'%' WITH GRANT OPTION; FLUSH PRIVILEGES" | mysql --default-character-set=utf8
echo "Finished creating user."

tail -f /var/log/mysql/*

