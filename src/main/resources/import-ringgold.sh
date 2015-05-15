#!/bin/bash

shopt -s nullglob

cd "$(dirname "$0")"

rm -f PLOS_Identify_*_utf8.sql
rm -f PLOS_Identify_*_counts.txt

ringgold_zip=(PLOS_Identify_*.zip)

if [ ${#ringgold_zip[@]} -ne 1 ]; then
    echo -e "\nUnexpected # of Ringgold zip's found (expected:1 found:${#ringgold_zip[@]}). Aborting.\n"
    exit 1
fi

unzip -o $ringgold_zip

ringgold_sql=(PLOS_Identify_*_utf8.sql)

# rename ringgold schema: identify_test -> ringgold
sed -i 's/identify_test/ringgold/g' $ringgold_sql

# remove "_new" from table names
sed -i 's/_new / /g' $ringgold_sql
