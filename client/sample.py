import ned_client

ned_client.configuration.username = 'akita'
ned_client.configuration.password = 'akita'
# ned_client.configuration.debug = True

apiclient = ned_client.ApiClient('http://localhost:8080/v0')
# apiclient = ned_client.ApiClient('http://localhost:8080/v0', header_name="Authorization", header_value=ned_client.configuration.get_basic_auth_token())

serviceapi = ned_client.ServiceApi(apiclient)
typeclassesapi = ned_client.TypeclassesApi(apiclient)
individualsapi = ned_client.IndividualsApi(apiclient)

print (serviceapi.config())

# print ("errorcodes")
# print (serviceapi.errorcodes())

# print (typeclassesapi.list())
print (typeclassesapi.read(12))

# print (individualsapi.read_individual(53))
#
# print (individualsapi.get_emails(53)[0].emailaddress)
#
# # given an email address, find a user's display name
# print (individualsapi.find_individuals(entity='email', attribute='emailaddress', value='jdoe415a4f3e@foo.com')[0].individualprofiles[0].displayname)
