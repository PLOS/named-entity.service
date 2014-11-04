#!/bin/bash

# Import database if provided via 'docker run --env url="http:/ex.org/db.sql"'
/usr/sbin/mysqld &
sleep 5

echo -e "\nCreating NED Schema"
mysql --default-character-set=utf8 < /mysql_schema/ned-schema.mysql.sql
sleep 1

echo -e "\nSeeding NED Schema"
mysql --default-character-set=utf8 < /mysql_schema/ned-data.mysql.sql
mysqladmin shutdown
echo -e "\nFinished creating schema."

/usr/sbin/mysqld &
sleep 5
echo -e "\nCreating DB User ($DB_USER)"
echo "CREATE USER '$DB_USER' IDENTIFIED BY ''" | mysql --default-character-set=utf8
echo "GRANT ALL PRIVILEGES ON *.* TO '$DB_USER'@'%' WITH GRANT OPTION; FLUSH PRIVILEGES" | mysql --default-character-set=utf8
echo "Finished creating user."

# restart the server to go operational
mysqladmin shutdown
echo "Starting MySQL Server"
/usr/sbin/mysqld

