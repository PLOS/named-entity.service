require 'swagger_client'

SwaggerClient.configure { |c| [
   c.debugging = true, c.host='http://localhost:8080',
   c.username = 'akita', c.password = 'akita'] }

# TODO: get basic auth to work  https://github.com/swagger-api/swagger-codegen/issues/1407
header = {'Authorization'=> SwaggerClient.configure.basic_auth_token}

print header

apiclient = SwaggerClient::ApiClient.new

apiclient.update_params_for_auth!(header, {}, ['header'])

serviceapi = SwaggerClient::ServiceApi.new(apiclient)
individualsapi = SwaggerClient::IndividualsApi.new(apiclient)
typeclassesapi = SwaggerClient::TypeclassesApi.new(apiclient)

serviceapi.config

# serviceapi.errorcodes

typeclassesapi.list
