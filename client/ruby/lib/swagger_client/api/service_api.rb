require "uri"

module SwaggerClient
  class ServiceApi
    attr_accessor :api_client

    def initialize(api_client = nil)
      @api_client = api_client || Configuration.api_client
    end

    # Config info
    # 
    # @param [Hash] opts the optional parameters
    # @return [ConfigInfo]
    def config(opts = {})
      if Configuration.debugging
        Configuration.logger.debug "Calling API: ServiceApi#config ..."
      end
      
      # resource path
      path = "/service/config".sub('{format}','json')

      # query parameters
      query_params = {}

      # header parameters
      header_params = {}

      # HTTP header 'Accept' (if needed)
      _header_accept = []
      _header_accept_result = @api_client.select_header_accept(_header_accept) and header_params['Accept'] = _header_accept_result

      # HTTP header 'Content-Type'
      _header_content_type = []
      header_params['Content-Type'] = @api_client.select_header_content_type(_header_content_type)

      # form parameters
      form_params = {}

      # http body (model)
      post_body = nil
      

      auth_names = []
      result = @api_client.call_api(:GET, path,
        :header_params => header_params,
        :query_params => query_params,
        :form_params => form_params,
        :body => post_body,
        :auth_names => auth_names,
        :return_type => 'ConfigInfo')
      if Configuration.debugging
        Configuration.logger.debug "API called: ServiceApi#config. Result: #{result.inspect}"
      end
      return result
    end

    # List possible error codes
    # 
    # @param [Hash] opts the optional parameters
    # @return [Array<NedErrorResponse>]
    def errorcodes(opts = {})
      if Configuration.debugging
        Configuration.logger.debug "Calling API: ServiceApi#errorcodes ..."
      end
      
      # resource path
      path = "/service/errorcodes".sub('{format}','json')

      # query parameters
      query_params = {}

      # header parameters
      header_params = {}

      # HTTP header 'Accept' (if needed)
      _header_accept = []
      _header_accept_result = @api_client.select_header_accept(_header_accept) and header_params['Accept'] = _header_accept_result

      # HTTP header 'Content-Type'
      _header_content_type = []
      header_params['Content-Type'] = @api_client.select_header_content_type(_header_content_type)

      # form parameters
      form_params = {}

      # http body (model)
      post_body = nil
      

      auth_names = []
      result = @api_client.call_api(:GET, path,
        :header_params => header_params,
        :query_params => query_params,
        :form_params => form_params,
        :body => post_body,
        :auth_names => auth_names,
        :return_type => 'Array<NedErrorResponse>')
      if Configuration.debugging
        Configuration.logger.debug "API called: ServiceApi#errorcodes. Result: #{result.inspect}"
      end
      return result
    end
  end
end




