# Common files
require 'ned_client/api_client'
require 'ned_client/api_error'
require 'ned_client/version'
require 'ned_client/configuration'

# Models
require 'ned_client/models/base_object'
require 'ned_client/models/group'
require 'ned_client/models/email'
require 'ned_client/models/address'
require 'ned_client/models/degree'
require 'ned_client/models/organization_composite'
require 'ned_client/models/config_info'
require 'ned_client/models/uniqueidentifier'
require 'ned_client/models/globaltype'
require 'ned_client/models/url'
require 'ned_client/models/ned_error_response'
require 'ned_client/models/typedescription'
require 'ned_client/models/auth'
require 'ned_client/models/relationship'
require 'ned_client/models/individual_composite'
require 'ned_client/models/phonenumber'
require 'ned_client/models/individualprofile'

# APIs
require 'ned_client/api/institutionsearch_api'
require 'ned_client/api/typeclasses_api'
require 'ned_client/api/organizations_api'
require 'ned_client/api/service_api'
require 'ned_client/api/individuals_api'

module NedClient
  class << self
    # Configure sdk using block.
    # NedClient.configure do |config|
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
