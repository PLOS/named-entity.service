# Common files
require 'swagger_client/api_client'
require 'swagger_client/api_error'
require 'swagger_client/version'
require 'swagger_client/configuration'

# Models
require 'swagger_client/models/base_object'
require 'swagger_client/models/group'
require 'swagger_client/models/email'
require 'swagger_client/models/address'
require 'swagger_client/models/degree'
require 'swagger_client/models/organization_composite'
require 'swagger_client/models/config_info'
require 'swagger_client/models/uniqueidentifier'
require 'swagger_client/models/globaltype'
require 'swagger_client/models/url'
require 'swagger_client/models/ned_error_response'
require 'swagger_client/models/typedescription'
require 'swagger_client/models/auth'
require 'swagger_client/models/relationship'
require 'swagger_client/models/individual_composite'
require 'swagger_client/models/phonenumber'
require 'swagger_client/models/individualprofile'

# APIs
require 'swagger_client/api/institutionsearch_api'
require 'swagger_client/api/typeclasses_api'
require 'swagger_client/api/organizations_api'
require 'swagger_client/api/service_api'
require 'swagger_client/api/individuals_api'

module SwaggerClient
  class << self
    # Configure sdk using block.
    # SwaggerClient.configure do |config|
    #   config.username = "xxx"
    #   config.password = "xxx"
    # end
    # If no block given, return the configuration singleton instance.
    def configure
      if block_given?
        yield Configuration.instance
      else
        Configuration.instance
      end
    end
  end
end
