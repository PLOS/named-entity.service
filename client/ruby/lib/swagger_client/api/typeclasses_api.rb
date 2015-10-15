require "uri"

module SwaggerClient
  class TypeclassesApi
    attr_accessor :api_client

    def initialize(api_client = nil)
      @api_client = api_client || Configuration.api_client
    end

    # List
    # 
    # @param [Hash] opts the optional parameters
    # @option opts [Integer] :offset 
    # @option opts [Integer] :limit 
    # @return [Array<Typedescription>]
    def list(opts = {})
      if Configuration.debugging
        Configuration.logger.debug "Calling API: TypeclassesApi#list ..."
      end
      
      # resource path
      path = "/typeclasses".sub('{format}','json')

      # query parameters
      query_params = {}
      query_params[:'offset'] = opts[:'offset'] if opts[:'offset']
      query_params[:'limit'] = opts[:'limit'] if opts[:'limit']

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
        :return_type => 'Array<Typedescription>')
      if Configuration.debugging
        Configuration.logger.debug "API called: TypeclassesApi#list. Result: #{result.inspect}"
      end
      return result
    end

    # Create
    # 
    # @param [Hash] opts the optional parameters
    # @option opts [Typedescription] :body 
    # @return [Typedescription]
    def create(opts = {})
      if Configuration.debugging
        Configuration.logger.debug "Calling API: TypeclassesApi#create ..."
      end
      
      # resource path
      path = "/typeclasses".sub('{format}','json')

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
      post_body = @api_client.object_to_http_body(opts[:'body'])
      

      auth_names = []
      result = @api_client.call_api(:POST, path,
        :header_params => header_params,
        :query_params => query_params,
        :form_params => form_params,
        :body => post_body,
        :auth_names => auth_names,
        :return_type => 'Typedescription')
      if Configuration.debugging
        Configuration.logger.debug "API called: TypeclassesApi#create. Result: #{result.inspect}"
      end
      return result
    end

    # Read
    # 
    # @param id 
    # @param [Hash] opts the optional parameters
    # @return [Typedescription]
    def read(id, opts = {})
      if Configuration.debugging
        Configuration.logger.debug "Calling API: TypeclassesApi#read ..."
      end
      
      # verify the required parameter 'id' is set
      fail "Missing the required parameter 'id' when calling read" if id.nil?
      
      # resource path
      path = "/typeclasses/{id}".sub('{format}','json').sub('{' + 'id' + '}', id.to_s)

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
        :return_type => 'Typedescription')
      if Configuration.debugging
        Configuration.logger.debug "API called: TypeclassesApi#read. Result: #{result.inspect}"
      end
      return result
    end

    # Update
    # 
    # @param [Hash] opts the optional parameters
    # @option opts [Typedescription] :body 
    # @return [Typedescription]
    def update(opts = {})
      if Configuration.debugging
        Configuration.logger.debug "Calling API: TypeclassesApi#update ..."
      end
      
      # resource path
      path = "/typeclasses/{id}".sub('{format}','json')

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
      post_body = @api_client.object_to_http_body(opts[:'body'])
      

      auth_names = []
      result = @api_client.call_api(:PUT, path,
        :header_params => header_params,
        :query_params => query_params,
        :form_params => form_params,
        :body => post_body,
        :auth_names => auth_names,
        :return_type => 'Typedescription')
      if Configuration.debugging
        Configuration.logger.debug "API called: TypeclassesApi#update. Result: #{result.inspect}"
      end
      return result
    end

    # Delete
    # 
    # @param id 
    # @param [Hash] opts the optional parameters
    # @return [nil]
    def delete(id, opts = {})
      if Configuration.debugging
        Configuration.logger.debug "Calling API: TypeclassesApi#delete ..."
      end
      
      # verify the required parameter 'id' is set
      fail "Missing the required parameter 'id' when calling delete" if id.nil?
      
      # resource path
      path = "/typeclasses/{id}".sub('{format}','json').sub('{' + 'id' + '}', id.to_s)

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
      @api_client.call_api(:DELETE, path,
        :header_params => header_params,
        :query_params => query_params,
        :form_params => form_params,
        :body => post_body,
        :auth_names => auth_names)
      if Configuration.debugging
        Configuration.logger.debug "API called: TypeclassesApi#delete"
      end
      return nil
    end

    # List global types
    # 
    # @param typeclassid 
    # @param [Hash] opts the optional parameters
    # @return [nil]
    def get_global_type_for_type_class(typeclassid, opts = {})
      if Configuration.debugging
        Configuration.logger.debug "Calling API: TypeclassesApi#get_global_type_for_type_class ..."
      end
      
      # verify the required parameter 'typeclassid' is set
      fail "Missing the required parameter 'typeclassid' when calling get_global_type_for_type_class" if typeclassid.nil?
      
      # resource path
      path = "/typeclasses/{typeclassid}/typevalues".sub('{format}','json').sub('{' + 'typeclassid' + '}', typeclassid.to_s)

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
      @api_client.call_api(:GET, path,
        :header_params => header_params,
        :query_params => query_params,
        :form_params => form_params,
        :body => post_body,
        :auth_names => auth_names)
      if Configuration.debugging
        Configuration.logger.debug "API called: TypeclassesApi#get_global_type_for_type_class"
      end
      return nil
    end

    # Create global type
    # 
    # @param typeclassid 
    # @param [Hash] opts the optional parameters
    # @option opts [Globaltype] :body 
    # @return [Globaltype]
    def create_global_type(typeclassid, opts = {})
      if Configuration.debugging
        Configuration.logger.debug "Calling API: TypeclassesApi#create_global_type ..."
      end
      
      # verify the required parameter 'typeclassid' is set
      fail "Missing the required parameter 'typeclassid' when calling create_global_type" if typeclassid.nil?
      
      # resource path
      path = "/typeclasses/{typeclassid}/typevalues".sub('{format}','json').sub('{' + 'typeclassid' + '}', typeclassid.to_s)

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
      post_body = @api_client.object_to_http_body(opts[:'body'])
      

      auth_names = []
      result = @api_client.call_api(:POST, path,
        :header_params => header_params,
        :query_params => query_params,
        :form_params => form_params,
        :body => post_body,
        :auth_names => auth_names,
        :return_type => 'Globaltype')
      if Configuration.debugging
        Configuration.logger.debug "API called: TypeclassesApi#create_global_type. Result: #{result.inspect}"
      end
      return result
    end

    # Read global type
    # 
    # @param typeclassid 
    # @param typevalueid 
    # @param [Hash] opts the optional parameters
    # @return [Globaltype]
    def get_global_type(typeclassid, typevalueid, opts = {})
      if Configuration.debugging
        Configuration.logger.debug "Calling API: TypeclassesApi#get_global_type ..."
      end
      
      # verify the required parameter 'typeclassid' is set
      fail "Missing the required parameter 'typeclassid' when calling get_global_type" if typeclassid.nil?
      
      # verify the required parameter 'typevalueid' is set
      fail "Missing the required parameter 'typevalueid' when calling get_global_type" if typevalueid.nil?
      
      # resource path
      path = "/typeclasses/{typeclassid}/typevalues/{typevalueid}".sub('{format}','json').sub('{' + 'typeclassid' + '}', typeclassid.to_s).sub('{' + 'typevalueid' + '}', typevalueid.to_s)

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
        :return_type => 'Globaltype')
      if Configuration.debugging
        Configuration.logger.debug "API called: TypeclassesApi#get_global_type. Result: #{result.inspect}"
      end
      return result
    end

    # Update global type
    # 
    # @param typeclassid 
    # @param typevalueid 
    # @param [Hash] opts the optional parameters
    # @option opts [Globaltype] :body 
    # @return [Globaltype]
    def update_global_type(typeclassid, typevalueid, opts = {})
      if Configuration.debugging
        Configuration.logger.debug "Calling API: TypeclassesApi#update_global_type ..."
      end
      
      # verify the required parameter 'typeclassid' is set
      fail "Missing the required parameter 'typeclassid' when calling update_global_type" if typeclassid.nil?
      
      # verify the required parameter 'typevalueid' is set
      fail "Missing the required parameter 'typevalueid' when calling update_global_type" if typevalueid.nil?
      
      # resource path
      path = "/typeclasses/{typeclassid}/typevalues/{typevalueid}".sub('{format}','json').sub('{' + 'typeclassid' + '}', typeclassid.to_s).sub('{' + 'typevalueid' + '}', typevalueid.to_s)

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
      post_body = @api_client.object_to_http_body(opts[:'body'])
      

      auth_names = []
      result = @api_client.call_api(:PUT, path,
        :header_params => header_params,
        :query_params => query_params,
        :form_params => form_params,
        :body => post_body,
        :auth_names => auth_names,
        :return_type => 'Globaltype')
      if Configuration.debugging
        Configuration.logger.debug "API called: TypeclassesApi#update_global_type. Result: #{result.inspect}"
      end
      return result
    end

    # Delete global type
    # 
    # @param typeclassid 
    # @param typevalueid 
    # @param [Hash] opts the optional parameters
    # @return [nil]
    def delete_global_type(typeclassid, typevalueid, opts = {})
      if Configuration.debugging
        Configuration.logger.debug "Calling API: TypeclassesApi#delete_global_type ..."
      end
      
      # verify the required parameter 'typeclassid' is set
      fail "Missing the required parameter 'typeclassid' when calling delete_global_type" if typeclassid.nil?
      
      # verify the required parameter 'typevalueid' is set
      fail "Missing the required parameter 'typevalueid' when calling delete_global_type" if typevalueid.nil?
      
      # resource path
      path = "/typeclasses/{typeclassid}/typevalues/{typevalueid}".sub('{format}','json').sub('{' + 'typeclassid' + '}', typeclassid.to_s).sub('{' + 'typevalueid' + '}', typevalueid.to_s)

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
      @api_client.call_api(:DELETE, path,
        :header_params => header_params,
        :query_params => query_params,
        :form_params => form_params,
        :body => post_body,
        :auth_names => auth_names)
      if Configuration.debugging
        Configuration.logger.debug "API called: TypeclassesApi#delete_global_type"
      end
      return nil
    end
  end
end




