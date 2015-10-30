import swagger_client

swagger_client.configuration.username = 'akita'
swagger_client.configuration.password = 'akita'

apiclient = swagger_client.ApiClient('http://localhost:8080/v0', header_name="Authorization", header_value=swagger_client.configuration.get_basic_auth_token())

serviceapi = swagger_client.ServiceApi(apiclient)
typeclassesapi = swagger_client.TypeclassesApi(apiclient)
individualsapi = swagger_client.IndividualsApi(apiclient)

print ("config")
print (serviceapi.config())

# print ("errorcodes")
# print (serviceapi.errorcodes())

print (typeclassesapi.list())
print (typeclassesapi.read(12))

# print (individualsapi.read_individual(53))
#
# print (individualsapi.get_emails(53)[0].emailaddress)
#
# # given an email address, find a user's display name
# print (individualsapi.find_individuals(entity='email', attribute='emailaddress', value='jdoe415a4f3e@foo.com')[0].individualprofiles[0].displayname)
