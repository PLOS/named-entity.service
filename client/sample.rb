require 'swagger_client'

SwaggerClient.configure { |c| [
   c.debugging = false, c.host='http://akita:akita@localhost:8080',
   c.username = 'akita', c.password = 'akita'] }

# TODO: get basic auth to work in the header https://github.com/swagger-api/swagger-codegen/issues/1407
header = {'Authorization'=> SwaggerClient.configure.basic_auth_token}

print header

apiclient = SwaggerClient::ApiClient.new

apiclient.update_params_for_auth!(header, {}, ['header'])

serviceapi = SwaggerClient::ServiceApi.new(apiclient)
individualsapi = SwaggerClient::IndividualsApi.new(apiclient)
typeclassesapi = SwaggerClient::TypeclassesApi.new(apiclient)

print serviceapi.config

# serviceapi.errorcodes

typeclassesapi.list

print individualsapi.read_individual(53).emails[0].emailaddress
