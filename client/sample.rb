require 'swagger_client'

SwaggerClient.configure { |c| [
   c.debugging = true, c.host='http://localhost:8080',
   c.username = 'akita', c.password = 'akita', c.base_path = '/'] }

apiclient = SwaggerClient::ApiClient.new

serviceapi = SwaggerClient::ServiceApi.new(apiclient)

# TODO: figure out an exctra slash is being added GET //service/config
serviceapi.config
