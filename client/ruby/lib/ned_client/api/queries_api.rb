require "uri"

module NedClient
  class QueriesApi
    attr_accessor :api_client

    def initialize(api_client = nil)
      @api_client = api_client || Configuration.api_client
    end

    # Get a list of search alerts by type
    # 
    # @param frequency 
    # @param [Hash] opts the optional parameters
    # @return [Array<Alert>]
    def get_alerts(frequency, opts = {})
      if Configuration.debugging
        Configuration.logger.debug "Calling API: QueriesApi#get_alerts ..."
      end
      
      # verify the required parameter 'frequency' is set
      fail "Missing the required parameter 'frequency' when calling get_alerts" if frequency.nil?
      
      # resource path
      path = "/queries/alerts/{frequency}".sub('{format}','json').sub('{' + 'frequency' + '}', frequency.to_s)

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
      

      auth_names = ['basic']
      result = @api_client.call_api(:GET, path,
        :header_params => header_params,
        :query_params => query_params,
        :form_params => form_params,
        :body => post_body,
        :auth_names => auth_names,
        :return_type => 'Array<Alert>')
      if Configuration.debugging
        Configuration.logger.debug "API called: QueriesApi#get_alerts. Result: #{result.inspect}"
      end
      return result
    end
  end
end




