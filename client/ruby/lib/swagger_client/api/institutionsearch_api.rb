require "uri"

module SwaggerClient
  class InstitutionsearchApi
    attr_accessor :api_client

    def initialize(api_client = nil)
      @api_client = api_client || Configuration.api_client
    end

    # Find institution(s) by name fragment (wildcard search).
    # 
    # @param [Hash] opts the optional parameters
    # @option opts [String] :substring 
    # @return [nil]
    def find_institutions_by_name(opts = {})
      if Configuration.debugging
        Configuration.logger.debug "Calling API: InstitutionsearchApi#find_institutions_by_name ..."
      end
      
      # resource path
      path = "/institutionsearch".sub('{format}','json')

      # query parameters
      query_params = {}
      query_params[:'substring'] = opts[:'substring'] if opts[:'substring']

      # header parameters
      header_params = {}

      # HTTP header 'Accept' (if needed)
      _header_accept = ['application/json', 'application/xml']
      _header_accept_result = @api_client.select_header_accept(_header_accept) and header_params['Accept'] = _header_accept_result

      # HTTP header 'Content-Type'
      _header_content_type = []
      header_params['Content-Type'] = @api_client.select_header_content_type(_header_content_type)

      # form parameters
      form_params = {}

      # http body (model)
      post_body = nil
      

      auth_names = []
      @api_client.call_api(:GET, path,
        :header_params => header_params,
        :query_params => query_params,
        :form_params => form_params,
        :body => post_body,
        :auth_names => auth_names)
      if Configuration.debugging
        Configuration.logger.debug "API called: InstitutionsearchApi#find_institutions_by_name"
      end
      return nil
    end
  end
end




