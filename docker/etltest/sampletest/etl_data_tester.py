#!/usr/bin/python
__author__ = 'jfinger'

import json
import requests
import subprocess
import MySQLdb as mdb


def cmd_read(command):
  print (command)
  return subprocess.Popen(command, shell=True, stdout=subprocess.PIPE).stdout.read().strip()


class NedEtlDataTester:

  def __init__(self, mysql_ambra_host, mysql_ambra_db, mysql_ambra_user, mysql_ambra_pass, mysql_ambra_port, etl_jar, ned_url):

    self.mysql_ambra_host = mysql_ambra_host
    self.mysql_ambra_db = mysql_ambra_db 
    self.mysql_ambra_user = mysql_ambra_user
    self.mysql_ambra_pass = mysql_ambra_pass
    self.mysql_ambra_port = mysql_ambra_port
    self.etl_jar = etl_jar
    self.ned_url = ned_url.strip().rstrip('/')
    
    self.base_mysql_linux = 'mysql -h '+self.mysql_ambra_host+' -u '+ self.mysql_ambra_user +' --password='+self.mysql_ambra_pass + ' ';

    self.ambra_jdbc = "jdbc:mysql://%s:%s/%s?user=%s&password=%s" % (mysql_ambra_host, mysql_ambra_port, mysql_ambra_db, mysql_ambra_user, mysql_ambra_pass)


  # ENTRY POINT FOR TESTS

  def run(self):

    print ("WARNING: Running sample data tests")

    # make sure the ned service is accessible
    r = requests.get("%s/service/config" % self.ned_url)
    data = json.loads(r.text)
    print (data)
    assert(data['version'])

    self.test_ambra_happy_row()
    print ("FINISHED test_ambra_happy_row")

    self.test_ambra_bad_email()
    print ("FINISHED test_ambra_bad_email")

    self.test_ambra_json_data()
    print ("FNISHED test_ambra_json_data")


  # TESTS

  def test_ambra_json_data(self):

    self.create_db()
    self.load_json_data('data/ambra_test_data.json')

    log = self.run_etl()

    # TODO: assert things

    self.ambra_db_conn.close()


  def test_ambra_happy_row(self):
    
    self.create_db()
    self.load_sql_data('data/happy_row.sql')
    log = self.run_etl()

    assert (log == "")  # since there should not be errors

    r = requests.get("%s/individuals/Editorial Manager/PONE-1111" % self.ned_url)
    assert(r.status_code == requests.codes.ok)
    self.ambra_db_conn.close()

  def test_ambra_bad_email(self):
    
    self.create_db()
    self.load_sql_data('data/bad_email.sql')
    log = self.run_etl()

    # TODO: assert grep for a line with both "Skipping Push" and "Email not valid" and "bad@email"

    r = requests.get("%s/individuals/Editorial Manager/PONE-5555" % self.ned_url)
    assert(r.status_code == requests.codes.bad_request, r.json())
    assert(r.json()[u'errorCode'] == 1000)
    self.ambra_db_conn.close()
    
    

  # HELPER FUNCTIONS

  def create_db(self):

    cmd_read(self.base_mysql_linux + ' -e \"create database if not exists '+ self.mysql_ambra_db + '\"')
    cmd_read(self.base_mysql_linux + self.mysql_ambra_db +' < data/ambra_schema.sql')
    cmd_read(self.base_mysql_linux + self.mysql_ambra_db +' < data/base_data.sql')

    self.ambra_db_conn = mdb.connect(self.mysql_ambra_host, self.mysql_ambra_user, self.mysql_ambra_pass, self.mysql_ambra_db)

    self.ambra_db_cursor = self.ambra_db_conn.cursor()

    
  def load_sql_data(self, sql_file):
    cmd_read(self.base_mysql_linux + self.mysql_ambra_db +' < '+ sql_file)

  def load_json_data(self, json_file):

    with open(json_file) as json_file:
      json_data = json.load(json_file)

      for r in json_data:
        print ("inserting " + r["displayName"])

        self.ambra_db_cursor.execute("insert into userProfile (authId, userProfileURI, realName, givenNames, surName, email, displayName, created, lastModified, password) values('%s','%s','%s','%s','%s','%s','%s',0,0,'0')" % (r["displayName"], 'http://example.com/'+r["displayName"], r["displayName"], r["firstName"], r["lastName"], r["email"], r["displayName"]))

        # TODO: insert into userOrcid, userProfileMetaData, userProfileMetaData, userProfileRoleJoinTable

      self.ambra_db_conn.commit()

  def run_etl(self):
    return cmd_read('java -DAMBRA_JDBC_URL="'+ self.ambra_jdbc +'" -DNED_URL="'+ self.ned_url +'/" -jar '+ self.etl_jar + ' /dev/null ambra | grep Skipping')
