require 'swagger_client'

SwaggerClient.configure { |c| [
   c.debugging = false, c.host='http://localhost:8081',
   c.username = 'dev', c.password = 'dev'] }

header = {'Authorization'=> SwaggerClient.configure.basic_auth_token}

apiclient = SwaggerClient::ApiClient.new

apiclient.default_headers = header

serviceapi = SwaggerClient::ServiceApi.new(apiclient)
individualsapi = SwaggerClient::IndividualsApi.new(apiclient)
typeclassesapi = SwaggerClient::TypeclassesApi.new(apiclient)

print serviceapi.config

# serviceapi.errorcodes

print typeclassesapi.list

# print individualsapi.read_individual(53).emails[0].emailaddress
