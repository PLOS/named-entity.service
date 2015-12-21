require "uri"

module NedClient
  class TypeclassesApi
    attr_accessor :api_client

    def initialize(api_client = ApiClient.default)
      @api_client = api_client
    end

    # List
    # 
    # @param [Hash] opts the optional parameters
    # @option opts [Integer] :offset 
    # @option opts [Integer] :limit 
    # @return [Array<Typedescription>]
    def list(opts = {})
      data, status_code, headers = list_with_http_info(opts)
      return data
    end

    # List
    # 
    # @param [Hash] opts the optional parameters
    # @option opts [Integer] :offset 
    # @option opts [Integer] :limit 
    # @return [Array<(Array<Typedescription>, Fixnum, Hash)>] Array<Typedescription> data, response status code and response headers
    def list_with_http_info(opts = {})
      if @api_client.config.debugging
        @api_client.config.logger.debug "Calling API: TypeclassesApi#list ..."
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
      _header_accept = ['application/json']
      _header_accept_result = @api_client.select_header_accept(_header_accept) and header_params['Accept'] = _header_accept_result

      # HTTP header 'Content-Type'
      _header_content_type = []
      header_params['Content-Type'] = @api_client.select_header_content_type(_header_content_type)

      # form parameters
      form_params = {}

      # http body (model)
      post_body = nil
      

      auth_names = ['basic']
      data, status_code, headers = @api_client.call_api(:GET, path,
        :header_params => header_params,
        :query_params => query_params,
        :form_params => form_params,
        :body => post_body,
        :auth_names => auth_names,
        :return_type => 'Array<Typedescription>')
      if @api_client.config.debugging
        @api_client.config.logger.debug "API called: TypeclassesApi#list\nData: #{data.inspect}\nStatus code: #{status_code}\nHeaders: #{headers}"
      end
      return data, status_code, headers
    end

    # Create
    # 
    # @param [Hash] opts the optional parameters
    # @option opts [Typedescription] :body 
    # @return [Typedescription]
    def create(opts = {})
      data, status_code, headers = create_with_http_info(opts)
      return data
    end

    # Create
    # 
    # @param [Hash] opts the optional parameters
    # @option opts [Typedescription] :body 
    # @return [Array<(Typedescription, Fixnum, Hash)>] Typedescription data, response status code and response headers
    def create_with_http_info(opts = {})
      if @api_client.config.debugging
        @api_client.config.logger.debug "Calling API: TypeclassesApi#create ..."
      end
      
      # resource path
      path = "/typeclasses".sub('{format}','json')

      # query parameters
      query_params = {}

      # header parameters
      header_params = {}

      # HTTP header 'Accept' (if needed)
      _header_accept = ['application/json']
      _header_accept_result = @api_client.select_header_accept(_header_accept) and header_params['Accept'] = _header_accept_result

      # HTTP header 'Content-Type'
      _header_content_type = []
      header_params['Content-Type'] = @api_client.select_header_content_type(_header_content_type)

      # form parameters
      form_params = {}

      # http body (model)
      post_body = @api_client.object_to_http_body(opts[:'body'])
      

      auth_names = ['basic']
      data, status_code, headers = @api_client.call_api(:POST, path,
        :header_params => header_params,
        :query_params => query_params,
        :form_params => form_params,
        :body => post_body,
        :auth_names => auth_names,
        :return_type => 'Typedescription')
      if @api_client.config.debugging
        @api_client.config.logger.debug "API called: TypeclassesApi#create\nData: #{data.inspect}\nStatus code: #{status_code}\nHeaders: #{headers}"
      end
      return data, status_code, headers
    end

    # Read
    # 
    # @param id 
    # @param [Hash] opts the optional parameters
    # @return [Typedescription]
    def read(id, opts = {})
      data, status_code, headers = read_with_http_info(id, opts)
      return data
    end

    # Read
    # 
    # @param id 
    # @param [Hash] opts the optional parameters
    # @return [Array<(Typedescription, Fixnum, Hash)>] Typedescription data, response status code and response headers
    def read_with_http_info(id, opts = {})
      if @api_client.config.debugging
        @api_client.config.logger.debug "Calling API: TypeclassesApi#read ..."
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
      _header_accept = ['application/json']
      _header_accept_result = @api_client.select_header_accept(_header_accept) and header_params['Accept'] = _header_accept_result

      # HTTP header 'Content-Type'
      _header_content_type = []
      header_params['Content-Type'] = @api_client.select_header_content_type(_header_content_type)

      # form parameters
      form_params = {}

      # http body (model)
      post_body = nil
      

      auth_names = ['basic']
      data, status_code, headers = @api_client.call_api(:GET, path,
        :header_params => header_params,
        :query_params => query_params,
        :form_params => form_params,
        :body => post_body,
        :auth_names => auth_names,
        :return_type => 'Typedescription')
      if @api_client.config.debugging
        @api_client.config.logger.debug "API called: TypeclassesApi#read\nData: #{data.inspect}\nStatus code: #{status_code}\nHeaders: #{headers}"
      end
      return data, status_code, headers
    end

    # Update
    # 
    # @param [Hash] opts the optional parameters
    # @option opts [Typedescription] :body 
    # @return [Typedescription]
    def update(opts = {})
      data, status_code, headers = update_with_http_info(opts)
      return data
    end

    # Update
    # 
    # @param [Hash] opts the optional parameters
    # @option opts [Typedescription] :body 
    # @return [Array<(Typedescription, Fixnum, Hash)>] Typedescription data, response status code and response headers
    def update_with_http_info(opts = {})
      if @api_client.config.debugging
        @api_client.config.logger.debug "Calling API: TypeclassesApi#update ..."
      end
      
      # resource path
      path = "/typeclasses/{id}".sub('{format}','json')

      # query parameters
      query_params = {}

      # header parameters
      header_params = {}

      # HTTP header 'Accept' (if needed)
      _header_accept = ['application/json']
      _header_accept_result = @api_client.select_header_accept(_header_accept) and header_params['Accept'] = _header_accept_result

      # HTTP header 'Content-Type'
      _header_content_type = []
      header_params['Content-Type'] = @api_client.select_header_content_type(_header_content_type)

      # form parameters
      form_params = {}

      # http body (model)
      post_body = @api_client.object_to_http_body(opts[:'body'])
      

      auth_names = ['basic']
      data, status_code, headers = @api_client.call_api(:PUT, path,
        :header_params => header_params,
        :query_params => query_params,
        :form_params => form_params,
        :body => post_body,
        :auth_names => auth_names,
        :return_type => 'Typedescription')
      if @api_client.config.debugging
        @api_client.config.logger.debug "API called: TypeclassesApi#update\nData: #{data.inspect}\nStatus code: #{status_code}\nHeaders: #{headers}"
      end
      return data, status_code, headers
    end

    # Delete
    # 
    # @param id 
    # @param [Hash] opts the optional parameters
    # @return [nil]
    def delete(id, opts = {})
      delete_with_http_info(id, opts)
      return nil
    end

    # Delete
    # 
    # @param id 
    # @param [Hash] opts the optional parameters
    # @return [Array<(nil, Fixnum, Hash)>] nil, response status code and response headers
    def delete_with_http_info(id, opts = {})
      if @api_client.config.debugging
        @api_client.config.logger.debug "Calling API: TypeclassesApi#delete ..."
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
      _header_accept = ['application/json']
      _header_accept_result = @api_client.select_header_accept(_header_accept) and header_params['Accept'] = _header_accept_result

      # HTTP header 'Content-Type'
      _header_content_type = []
      header_params['Content-Type'] = @api_client.select_header_content_type(_header_content_type)

      # form parameters
      form_params = {}

      # http body (model)
      post_body = nil
      

      auth_names = ['basic']
      data, status_code, headers = @api_client.call_api(:DELETE, path,
        :header_params => header_params,
        :query_params => query_params,
        :form_params => form_params,
        :body => post_body,
        :auth_names => auth_names)
      if @api_client.config.debugging
        @api_client.config.logger.debug "API called: TypeclassesApi#delete\nData: #{data.inspect}\nStatus code: #{status_code}\nHeaders: #{headers}"
      end
      return data, status_code, headers
    end

    # List global types
    # 
    # @param typeclassid 
    # @param [Hash] opts the optional parameters
    # @return [nil]
    def get_global_type_for_type_class(typeclassid, opts = {})
      get_global_type_for_type_class_with_http_info(typeclassid, opts)
      return nil
    end

    # List global types
    # 
    # @param typeclassid 
    # @param [Hash] opts the optional parameters
    # @return [Array<(nil, Fixnum, Hash)>] nil, response status code and response headers
    def get_global_type_for_type_class_with_http_info(typeclassid, opts = {})
      if @api_client.config.debugging
        @api_client.config.logger.debug "Calling API: TypeclassesApi#get_global_type_for_type_class ..."
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
      _header_accept = ['application/json']
      _header_accept_result = @api_client.select_header_accept(_header_accept) and header_params['Accept'] = _header_accept_result

      # HTTP header 'Content-Type'
      _header_content_type = []
      header_params['Content-Type'] = @api_client.select_header_content_type(_header_content_type)

      # form parameters
      form_params = {}

      # http body (model)
      post_body = nil
      

      auth_names = ['basic']
      data, status_code, headers = @api_client.call_api(:GET, path,
        :header_params => header_params,
        :query_params => query_params,
        :form_params => form_params,
        :body => post_body,
        :auth_names => auth_names)
      if @api_client.config.debugging
        @api_client.config.logger.debug "API called: TypeclassesApi#get_global_type_for_type_class\nData: #{data.inspect}\nStatus code: #{status_code}\nHeaders: #{headers}"
      end
      return data, status_code, headers
    end

    # Create global type
    # 
    # @param typeclassid 
    # @param [Hash] opts the optional parameters
    # @option opts [Globaltype] :body 
    # @return [Globaltype]
    def create_global_type(typeclassid, opts = {})
      data, status_code, headers = create_global_type_with_http_info(typeclassid, opts)
      return data
    end

    # Create global type
    # 
    # @param typeclassid 
    # @param [Hash] opts the optional parameters
    # @option opts [Globaltype] :body 
    # @return [Array<(Globaltype, Fixnum, Hash)>] Globaltype data, response status code and response headers
    def create_global_type_with_http_info(typeclassid, opts = {})
      if @api_client.config.debugging
        @api_client.config.logger.debug "Calling API: TypeclassesApi#create_global_type ..."
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
      _header_accept = ['application/json']
      _header_accept_result = @api_client.select_header_accept(_header_accept) and header_params['Accept'] = _header_accept_result

      # HTTP header 'Content-Type'
      _header_content_type = []
      header_params['Content-Type'] = @api_client.select_header_content_type(_header_content_type)

      # form parameters
      form_params = {}

      # http body (model)
      post_body = @api_client.object_to_http_body(opts[:'body'])
      

      auth_names = ['basic']
      data, status_code, headers = @api_client.call_api(:POST, path,
        :header_params => header_params,
        :query_params => query_params,
        :form_params => form_params,
        :body => post_body,
        :auth_names => auth_names,
        :return_type => 'Globaltype')
      if @api_client.config.debugging
        @api_client.config.logger.debug "API called: TypeclassesApi#create_global_type\nData: #{data.inspect}\nStatus code: #{status_code}\nHeaders: #{headers}"
      end
      return data, status_code, headers
    end

    # Read global type
    # 
    # @param typeclassid 
    # @param typevalueid 
    # @param [Hash] opts the optional parameters
    # @return [Globaltype]
    def get_global_type(typeclassid, typevalueid, opts = {})
      data, status_code, headers = get_global_type_with_http_info(typeclassid, typevalueid, opts)
      return data
    end

    # Read global type
    # 
    # @param typeclassid 
    # @param typevalueid 
    # @param [Hash] opts the optional parameters
    # @return [Array<(Globaltype, Fixnum, Hash)>] Globaltype data, response status code and response headers
    def get_global_type_with_http_info(typeclassid, typevalueid, opts = {})
      if @api_client.config.debugging
        @api_client.config.logger.debug "Calling API: TypeclassesApi#get_global_type ..."
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
      _header_accept = ['application/json']
      _header_accept_result = @api_client.select_header_accept(_header_accept) and header_params['Accept'] = _header_accept_result

      # HTTP header 'Content-Type'
      _header_content_type = []
      header_params['Content-Type'] = @api_client.select_header_content_type(_header_content_type)

      # form parameters
      form_params = {}

      # http body (model)
      post_body = nil
      

      auth_names = ['basic']
      data, status_code, headers = @api_client.call_api(:GET, path,
        :header_params => header_params,
        :query_params => query_params,
        :form_params => form_params,
        :body => post_body,
        :auth_names => auth_names,
        :return_type => 'Globaltype')
      if @api_client.config.debugging
        @api_client.config.logger.debug "API called: TypeclassesApi#get_global_type\nData: #{data.inspect}\nStatus code: #{status_code}\nHeaders: #{headers}"
      end
      return data, status_code, headers
    end

    # Update global type
    # 
    # @param typeclassid 
    # @param typevalueid 
    # @param [Hash] opts the optional parameters
    # @option opts [Globaltype] :body 
    # @return [Globaltype]
    def update_global_type(typeclassid, typevalueid, opts = {})
      data, status_code, headers = update_global_type_with_http_info(typeclassid, typevalueid, opts)
      return data
    end

    # Update global type
    # 
    # @param typeclassid 
    # @param typevalueid 
    # @param [Hash] opts the optional parameters
    # @option opts [Globaltype] :body 
    # @return [Array<(Globaltype, Fixnum, Hash)>] Globaltype data, response status code and response headers
    def update_global_type_with_http_info(typeclassid, typevalueid, opts = {})
      if @api_client.config.debugging
        @api_client.config.logger.debug "Calling API: TypeclassesApi#update_global_type ..."
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
      _header_accept = ['application/json']
      _header_accept_result = @api_client.select_header_accept(_header_accept) and header_params['Accept'] = _header_accept_result

      # HTTP header 'Content-Type'
      _header_content_type = []
      header_params['Content-Type'] = @api_client.select_header_content_type(_header_content_type)

      # form parameters
      form_params = {}

      # http body (model)
      post_body = @api_client.object_to_http_body(opts[:'body'])
      

      auth_names = ['basic']
      data, status_code, headers = @api_client.call_api(:PUT, path,
        :header_params => header_params,
        :query_params => query_params,
        :form_params => form_params,
        :body => post_body,
        :auth_names => auth_names,
        :return_type => 'Globaltype')
      if @api_client.config.debugging
        @api_client.config.logger.debug "API called: TypeclassesApi#update_global_type\nData: #{data.inspect}\nStatus code: #{status_code}\nHeaders: #{headers}"
      end
      return data, status_code, headers
    end

    # Delete global type
    # 
    # @param typeclassid 
    # @param typevalueid 
    # @param [Hash] opts the optional parameters
    # @return [nil]
    def delete_global_type(typeclassid, typevalueid, opts = {})
      delete_global_type_with_http_info(typeclassid, typevalueid, opts)
      return nil
    end

    # Delete global type
    # 
    # @param typeclassid 
    # @param typevalueid 
    # @param [Hash] opts the optional parameters
    # @return [Array<(nil, Fixnum, Hash)>] nil, response status code and response headers
    def delete_global_type_with_http_info(typeclassid, typevalueid, opts = {})
      if @api_client.config.debugging
        @api_client.config.logger.debug "Calling API: TypeclassesApi#delete_global_type ..."
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
      _header_accept = ['application/json']
      _header_accept_result = @api_client.select_header_accept(_header_accept) and header_params['Accept'] = _header_accept_result

      # HTTP header 'Content-Type'
      _header_content_type = []
      header_params['Content-Type'] = @api_client.select_header_content_type(_header_content_type)

      # form parameters
      form_params = {}

      # http body (model)
      post_body = nil
      

      auth_names = ['basic']
      data, status_code, headers = @api_client.call_api(:DELETE, path,
        :header_params => header_params,
        :query_params => query_params,
        :form_params => form_params,
        :body => post_body,
        :auth_names => auth_names)
      if @api_client.config.debugging
        @api_client.config.logger.debug "API called: TypeclassesApi#delete_global_type\nData: #{data.inspect}\nStatus code: #{status_code}\nHeaders: #{headers}"
      end
      return data, status_code, headers
    end
  end
end




