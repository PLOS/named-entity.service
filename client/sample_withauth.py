import swagger_client

swagger_client.configuration.username = 'akita'
swagger_client.configuration.password = 'akita'
swagger_client.configuration.debug = True

apiclient = swagger_client.ApiClient('http://localhost:8080')

serviceapi = swagger_client.ServiceApi(apiclient)
typeclassesapi = swagger_client.TypeclassesApi(apiclient)
individualsapi = swagger_client.IndividualsApi(apiclient)

print ("config")
print (serviceapi.config())

# print ("errorcodes")
# print (serviceapi.errorcodes())

print (typeclassesapi.read(12))
