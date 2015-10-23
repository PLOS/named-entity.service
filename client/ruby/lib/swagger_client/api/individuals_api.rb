require "uri"

module SwaggerClient
  class IndividualsApi
    attr_accessor :api_client

    def initialize(api_client = nil)
      @api_client = api_client || Configuration.api_client
    end

    # Find individual matching specified attribute.
    # 
    # @param [Hash] opts the optional parameters
    # @option opts [String] :entity 
    # @option opts [String] :attribute 
    # @option opts [String] :value 
    # @return [Array<IndividualComposite>]
    def find_individuals(opts = {})
      if Configuration.debugging
        Configuration.logger.debug "Calling API: IndividualsApi#find_individuals ..."
      end
      
      # resource path
      path = "/individuals".sub('{format}','json')

      # query parameters
      query_params = {}
      query_params[:'entity'] = opts[:'entity'] if opts[:'entity']
      query_params[:'attribute'] = opts[:'attribute'] if opts[:'attribute']
      query_params[:'value'] = opts[:'value'] if opts[:'value']

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
      result = @api_client.call_api(:GET, path,
        :header_params => header_params,
        :query_params => query_params,
        :form_params => form_params,
        :body => post_body,
        :auth_names => auth_names,
        :return_type => 'Array<IndividualComposite>')
      if Configuration.debugging
        Configuration.logger.debug "API called: IndividualsApi#find_individuals. Result: #{result.inspect}"
      end
      return result
    end

    # Create individual
    # 
    # @param [Hash] opts the optional parameters
    # @option opts [IndividualComposite] :body 
    # @option opts [String] :authorization 
    # @return [IndividualComposite]
    def create_individual(opts = {})
      if Configuration.debugging
        Configuration.logger.debug "Calling API: IndividualsApi#create_individual ..."
      end
      
      # resource path
      path = "/individuals".sub('{format}','json')

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
      header_params[:'Authorization'] = opts[:'authorization'] if opts[:'authorization']

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
        :return_type => 'IndividualComposite')
      if Configuration.debugging
        Configuration.logger.debug "API called: IndividualsApi#create_individual. Result: #{result.inspect}"
      end
      return result
    end

    # Read individual by CAS ID
    # 
    # @param cas_id 
    # @param [Hash] opts the optional parameters
    # @return [IndividualComposite]
    def read_individual_by_cas_id(cas_id, opts = {})
      if Configuration.debugging
        Configuration.logger.debug "Calling API: IndividualsApi#read_individual_by_cas_id ..."
      end
      
      # verify the required parameter 'cas_id' is set
      fail "Missing the required parameter 'cas_id' when calling read_individual_by_cas_id" if cas_id.nil?
      
      # resource path
      path = "/individuals/CAS/{casId}".sub('{format}','json').sub('{' + 'casId' + '}', cas_id.to_s)

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
        :return_type => 'IndividualComposite')
      if Configuration.debugging
        Configuration.logger.debug "API called: IndividualsApi#read_individual_by_cas_id. Result: #{result.inspect}"
      end
      return result
    end

    # Read individual by Ned ID
    # 
    # @param ned_id 
    # @param [Hash] opts the optional parameters
    # @return [IndividualComposite]
    def read_individual(ned_id, opts = {})
      if Configuration.debugging
        Configuration.logger.debug "Calling API: IndividualsApi#read_individual ..."
      end
      
      # verify the required parameter 'ned_id' is set
      fail "Missing the required parameter 'ned_id' when calling read_individual" if ned_id.nil?
      
      # resource path
      path = "/individuals/{nedId}".sub('{format}','json').sub('{' + 'nedId' + '}', ned_id.to_s)

      # query parameters
      query_params = {}

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
      result = @api_client.call_api(:GET, path,
        :header_params => header_params,
        :query_params => query_params,
        :form_params => form_params,
        :body => post_body,
        :auth_names => auth_names,
        :return_type => 'IndividualComposite')
      if Configuration.debugging
        Configuration.logger.debug "API called: IndividualsApi#read_individual. Result: #{result.inspect}"
      end
      return result
    end

    # List addresses
    # 
    # @param ned_id 
    # @param [Hash] opts the optional parameters
    # @return [Array<Address>]
    def get_addresses(ned_id, opts = {})
      if Configuration.debugging
        Configuration.logger.debug "Calling API: IndividualsApi#get_addresses ..."
      end
      
      # verify the required parameter 'ned_id' is set
      fail "Missing the required parameter 'ned_id' when calling get_addresses" if ned_id.nil?
      
      # resource path
      path = "/individuals/{nedId}/addresses".sub('{format}','json').sub('{' + 'nedId' + '}', ned_id.to_s)

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
        :return_type => 'Array<Address>')
      if Configuration.debugging
        Configuration.logger.debug "API called: IndividualsApi#get_addresses. Result: #{result.inspect}"
      end
      return result
    end

    # Create address
    # 
    # @param ned_id 
    # @param [Hash] opts the optional parameters
    # @option opts [Address] :body 
    # @option opts [String] :authorization 
    # @return [Address]
    def create_address(ned_id, opts = {})
      if Configuration.debugging
        Configuration.logger.debug "Calling API: IndividualsApi#create_address ..."
      end
      
      # verify the required parameter 'ned_id' is set
      fail "Missing the required parameter 'ned_id' when calling create_address" if ned_id.nil?
      
      # resource path
      path = "/individuals/{nedId}/addresses".sub('{format}','json').sub('{' + 'nedId' + '}', ned_id.to_s)

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
      header_params[:'Authorization'] = opts[:'authorization'] if opts[:'authorization']

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
        :return_type => 'Address')
      if Configuration.debugging
        Configuration.logger.debug "API called: IndividualsApi#create_address. Result: #{result.inspect}"
      end
      return result
    end

    # Read address
    # 
    # @param ned_id 
    # @param address_id 
    # @param [Hash] opts the optional parameters
    # @return [Address]
    def get_address(ned_id, address_id, opts = {})
      if Configuration.debugging
        Configuration.logger.debug "Calling API: IndividualsApi#get_address ..."
      end
      
      # verify the required parameter 'ned_id' is set
      fail "Missing the required parameter 'ned_id' when calling get_address" if ned_id.nil?
      
      # verify the required parameter 'address_id' is set
      fail "Missing the required parameter 'address_id' when calling get_address" if address_id.nil?
      
      # resource path
      path = "/individuals/{nedId}/addresses/{addressId}".sub('{format}','json').sub('{' + 'nedId' + '}', ned_id.to_s).sub('{' + 'addressId' + '}', address_id.to_s)

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
        :return_type => 'Address')
      if Configuration.debugging
        Configuration.logger.debug "API called: IndividualsApi#get_address. Result: #{result.inspect}"
      end
      return result
    end

    # Update address
    # 
    # @param ned_id 
    # @param address_id 
    # @param [Hash] opts the optional parameters
    # @option opts [Address] :body 
    # @option opts [String] :authorization 
    # @return [Address]
    def update_address(ned_id, address_id, opts = {})
      if Configuration.debugging
        Configuration.logger.debug "Calling API: IndividualsApi#update_address ..."
      end
      
      # verify the required parameter 'ned_id' is set
      fail "Missing the required parameter 'ned_id' when calling update_address" if ned_id.nil?
      
      # verify the required parameter 'address_id' is set
      fail "Missing the required parameter 'address_id' when calling update_address" if address_id.nil?
      
      # resource path
      path = "/individuals/{nedId}/addresses/{addressId}".sub('{format}','json').sub('{' + 'nedId' + '}', ned_id.to_s).sub('{' + 'addressId' + '}', address_id.to_s)

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
      header_params[:'Authorization'] = opts[:'authorization'] if opts[:'authorization']

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
        :return_type => 'Address')
      if Configuration.debugging
        Configuration.logger.debug "API called: IndividualsApi#update_address. Result: #{result.inspect}"
      end
      return result
    end

    # Delete address
    # 
    # @param ned_id 
    # @param address_id 
    # @param [Hash] opts the optional parameters
    # @option opts [String] :authorization 
    # @return [nil]
    def delete_address(ned_id, address_id, opts = {})
      if Configuration.debugging
        Configuration.logger.debug "Calling API: IndividualsApi#delete_address ..."
      end
      
      # verify the required parameter 'ned_id' is set
      fail "Missing the required parameter 'ned_id' when calling delete_address" if ned_id.nil?
      
      # verify the required parameter 'address_id' is set
      fail "Missing the required parameter 'address_id' when calling delete_address" if address_id.nil?
      
      # resource path
      path = "/individuals/{nedId}/addresses/{addressId}".sub('{format}','json').sub('{' + 'nedId' + '}', ned_id.to_s).sub('{' + 'addressId' + '}', address_id.to_s)

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
      header_params[:'Authorization'] = opts[:'authorization'] if opts[:'authorization']

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
        Configuration.logger.debug "API called: IndividualsApi#delete_address"
      end
      return nil
    end

    # List auth record(s)
    # 
    # @param ned_id 
    # @param [Hash] opts the optional parameters
    # @return [nil]
    def get_auth_record(ned_id, opts = {})
      if Configuration.debugging
        Configuration.logger.debug "Calling API: IndividualsApi#get_auth_record ..."
      end
      
      # verify the required parameter 'ned_id' is set
      fail "Missing the required parameter 'ned_id' when calling get_auth_record" if ned_id.nil?
      
      # resource path
      path = "/individuals/{nedId}/auth".sub('{format}','json').sub('{' + 'nedId' + '}', ned_id.to_s)

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
        Configuration.logger.debug "API called: IndividualsApi#get_auth_record"
      end
      return nil
    end

    # Validate password
    # 
    # @param ned_id 
    # @param [Hash] opts the optional parameters
    # @option opts [Auth] :body 
    # @return [nil]
    def check_password(ned_id, opts = {})
      if Configuration.debugging
        Configuration.logger.debug "Calling API: IndividualsApi#check_password ..."
      end
      
      # verify the required parameter 'ned_id' is set
      fail "Missing the required parameter 'ned_id' when calling check_password" if ned_id.nil?
      
      # resource path
      path = "/individuals/{nedId}/auth/checkpassword".sub('{format}','json').sub('{' + 'nedId' + '}', ned_id.to_s)

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
      @api_client.call_api(:POST, path,
        :header_params => header_params,
        :query_params => query_params,
        :form_params => form_params,
        :body => post_body,
        :auth_names => auth_names)
      if Configuration.debugging
        Configuration.logger.debug "API called: IndividualsApi#check_password"
      end
      return nil
    end

    # Update auth record
    # 
    # @param ned_id 
    # @param auth_id 
    # @param [Hash] opts the optional parameters
    # @option opts [String] :authorization 
    # @option opts [Auth] :body 
    # @return [Auth]
    def update_auth_record(ned_id, auth_id, opts = {})
      if Configuration.debugging
        Configuration.logger.debug "Calling API: IndividualsApi#update_auth_record ..."
      end
      
      # verify the required parameter 'ned_id' is set
      fail "Missing the required parameter 'ned_id' when calling update_auth_record" if ned_id.nil?
      
      # verify the required parameter 'auth_id' is set
      fail "Missing the required parameter 'auth_id' when calling update_auth_record" if auth_id.nil?
      
      # resource path
      path = "/individuals/{nedId}/auth/{authId}".sub('{format}','json').sub('{' + 'nedId' + '}', ned_id.to_s).sub('{' + 'authId' + '}', auth_id.to_s)

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
      header_params[:'Authorization'] = opts[:'authorization'] if opts[:'authorization']

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
        :return_type => 'Auth')
      if Configuration.debugging
        Configuration.logger.debug "API called: IndividualsApi#update_auth_record. Result: #{result.inspect}"
      end
      return result
    end

    # List degrees
    # 
    # @param ned_id 
    # @param [Hash] opts the optional parameters
    # @return [Array<Degree>]
    def get_degrees(ned_id, opts = {})
      if Configuration.debugging
        Configuration.logger.debug "Calling API: IndividualsApi#get_degrees ..."
      end
      
      # verify the required parameter 'ned_id' is set
      fail "Missing the required parameter 'ned_id' when calling get_degrees" if ned_id.nil?
      
      # resource path
      path = "/individuals/{nedId}/degrees".sub('{format}','json').sub('{' + 'nedId' + '}', ned_id.to_s)

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
        :return_type => 'Array<Degree>')
      if Configuration.debugging
        Configuration.logger.debug "API called: IndividualsApi#get_degrees. Result: #{result.inspect}"
      end
      return result
    end

    # List emails
    # 
    # @param ned_id 
    # @param [Hash] opts the optional parameters
    # @return [Array<Email>]
    def get_emails(ned_id, opts = {})
      if Configuration.debugging
        Configuration.logger.debug "Calling API: IndividualsApi#get_emails ..."
      end
      
      # verify the required parameter 'ned_id' is set
      fail "Missing the required parameter 'ned_id' when calling get_emails" if ned_id.nil?
      
      # resource path
      path = "/individuals/{nedId}/emails".sub('{format}','json').sub('{' + 'nedId' + '}', ned_id.to_s)

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
        :return_type => 'Array<Email>')
      if Configuration.debugging
        Configuration.logger.debug "API called: IndividualsApi#get_emails. Result: #{result.inspect}"
      end
      return result
    end

    # Create email
    # 
    # @param ned_id 
    # @param [Hash] opts the optional parameters
    # @option opts [Email] :body 
    # @option opts [String] :authorization 
    # @return [Email]
    def create_email(ned_id, opts = {})
      if Configuration.debugging
        Configuration.logger.debug "Calling API: IndividualsApi#create_email ..."
      end
      
      # verify the required parameter 'ned_id' is set
      fail "Missing the required parameter 'ned_id' when calling create_email" if ned_id.nil?
      
      # resource path
      path = "/individuals/{nedId}/emails".sub('{format}','json').sub('{' + 'nedId' + '}', ned_id.to_s)

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
      header_params[:'Authorization'] = opts[:'authorization'] if opts[:'authorization']

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
        :return_type => 'Email')
      if Configuration.debugging
        Configuration.logger.debug "API called: IndividualsApi#create_email. Result: #{result.inspect}"
      end
      return result
    end

    # Read email
    # 
    # @param ned_id 
    # @param email_id 
    # @param [Hash] opts the optional parameters
    # @return [Email]
    def get_email(ned_id, email_id, opts = {})
      if Configuration.debugging
        Configuration.logger.debug "Calling API: IndividualsApi#get_email ..."
      end
      
      # verify the required parameter 'ned_id' is set
      fail "Missing the required parameter 'ned_id' when calling get_email" if ned_id.nil?
      
      # verify the required parameter 'email_id' is set
      fail "Missing the required parameter 'email_id' when calling get_email" if email_id.nil?
      
      # resource path
      path = "/individuals/{nedId}/emails/{emailId}".sub('{format}','json').sub('{' + 'nedId' + '}', ned_id.to_s).sub('{' + 'emailId' + '}', email_id.to_s)

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
        :return_type => 'Email')
      if Configuration.debugging
        Configuration.logger.debug "API called: IndividualsApi#get_email. Result: #{result.inspect}"
      end
      return result
    end

    # Update email
    # 
    # @param ned_id 
    # @param email_id 
    # @param [Hash] opts the optional parameters
    # @option opts [Email] :body 
    # @option opts [String] :authorization 
    # @return [Email]
    def update_email(ned_id, email_id, opts = {})
      if Configuration.debugging
        Configuration.logger.debug "Calling API: IndividualsApi#update_email ..."
      end
      
      # verify the required parameter 'ned_id' is set
      fail "Missing the required parameter 'ned_id' when calling update_email" if ned_id.nil?
      
      # verify the required parameter 'email_id' is set
      fail "Missing the required parameter 'email_id' when calling update_email" if email_id.nil?
      
      # resource path
      path = "/individuals/{nedId}/emails/{emailId}".sub('{format}','json').sub('{' + 'nedId' + '}', ned_id.to_s).sub('{' + 'emailId' + '}', email_id.to_s)

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
      header_params[:'Authorization'] = opts[:'authorization'] if opts[:'authorization']

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
        :return_type => 'Email')
      if Configuration.debugging
        Configuration.logger.debug "API called: IndividualsApi#update_email. Result: #{result.inspect}"
      end
      return result
    end

    # Delete email
    # 
    # @param ned_id 
    # @param email_id 
    # @param [Hash] opts the optional parameters
    # @option opts [String] :authorization 
    # @return [nil]
    def delete_email(ned_id, email_id, opts = {})
      if Configuration.debugging
        Configuration.logger.debug "Calling API: IndividualsApi#delete_email ..."
      end
      
      # verify the required parameter 'ned_id' is set
      fail "Missing the required parameter 'ned_id' when calling delete_email" if ned_id.nil?
      
      # verify the required parameter 'email_id' is set
      fail "Missing the required parameter 'email_id' when calling delete_email" if email_id.nil?
      
      # resource path
      path = "/individuals/{nedId}/emails/{emailId}".sub('{format}','json').sub('{' + 'nedId' + '}', ned_id.to_s).sub('{' + 'emailId' + '}', email_id.to_s)

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
      header_params[:'Authorization'] = opts[:'authorization'] if opts[:'authorization']

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
        Configuration.logger.debug "API called: IndividualsApi#delete_email"
      end
      return nil
    end

    # List groups
    # 
    # @param ned_id 
    # @param [Hash] opts the optional parameters
    # @return [Array<Group>]
    def get_groups(ned_id, opts = {})
      if Configuration.debugging
        Configuration.logger.debug "Calling API: IndividualsApi#get_groups ..."
      end
      
      # verify the required parameter 'ned_id' is set
      fail "Missing the required parameter 'ned_id' when calling get_groups" if ned_id.nil?
      
      # resource path
      path = "/individuals/{nedId}/groups".sub('{format}','json').sub('{' + 'nedId' + '}', ned_id.to_s)

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
        :return_type => 'Array<Group>')
      if Configuration.debugging
        Configuration.logger.debug "API called: IndividualsApi#get_groups. Result: #{result.inspect}"
      end
      return result
    end

    # Create group
    # 
    # @param ned_id 
    # @param [Hash] opts the optional parameters
    # @option opts [Group] :body 
    # @option opts [String] :authorization 
    # @return [Group]
    def create_group(ned_id, opts = {})
      if Configuration.debugging
        Configuration.logger.debug "Calling API: IndividualsApi#create_group ..."
      end
      
      # verify the required parameter 'ned_id' is set
      fail "Missing the required parameter 'ned_id' when calling create_group" if ned_id.nil?
      
      # resource path
      path = "/individuals/{nedId}/groups".sub('{format}','json').sub('{' + 'nedId' + '}', ned_id.to_s)

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
      header_params[:'Authorization'] = opts[:'authorization'] if opts[:'authorization']

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
        :return_type => 'Group')
      if Configuration.debugging
        Configuration.logger.debug "API called: IndividualsApi#create_group. Result: #{result.inspect}"
      end
      return result
    end

    # Read group
    # 
    # @param ned_id 
    # @param group_id 
    # @param [Hash] opts the optional parameters
    # @return [Group]
    def get_group(ned_id, group_id, opts = {})
      if Configuration.debugging
        Configuration.logger.debug "Calling API: IndividualsApi#get_group ..."
      end
      
      # verify the required parameter 'ned_id' is set
      fail "Missing the required parameter 'ned_id' when calling get_group" if ned_id.nil?
      
      # verify the required parameter 'group_id' is set
      fail "Missing the required parameter 'group_id' when calling get_group" if group_id.nil?
      
      # resource path
      path = "/individuals/{nedId}/groups/{groupId}".sub('{format}','json').sub('{' + 'nedId' + '}', ned_id.to_s).sub('{' + 'groupId' + '}', group_id.to_s)

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
        :return_type => 'Group')
      if Configuration.debugging
        Configuration.logger.debug "API called: IndividualsApi#get_group. Result: #{result.inspect}"
      end
      return result
    end

    # Update group
    # 
    # @param ned_id 
    # @param group_id 
    # @param [Hash] opts the optional parameters
    # @option opts [String] :authorization 
    # @option opts [Group] :body 
    # @return [Group]
    def update_group(ned_id, group_id, opts = {})
      if Configuration.debugging
        Configuration.logger.debug "Calling API: IndividualsApi#update_group ..."
      end
      
      # verify the required parameter 'ned_id' is set
      fail "Missing the required parameter 'ned_id' when calling update_group" if ned_id.nil?
      
      # verify the required parameter 'group_id' is set
      fail "Missing the required parameter 'group_id' when calling update_group" if group_id.nil?
      
      # resource path
      path = "/individuals/{nedId}/groups/{groupId}".sub('{format}','json').sub('{' + 'nedId' + '}', ned_id.to_s).sub('{' + 'groupId' + '}', group_id.to_s)

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
      header_params[:'Authorization'] = opts[:'authorization'] if opts[:'authorization']

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
        :return_type => 'Group')
      if Configuration.debugging
        Configuration.logger.debug "API called: IndividualsApi#update_group. Result: #{result.inspect}"
      end
      return result
    end

    # Delete group
    # 
    # @param ned_id 
    # @param group_id 
    # @param [Hash] opts the optional parameters
    # @option opts [String] :authorization 
    # @return [nil]
    def delete_group(ned_id, group_id, opts = {})
      if Configuration.debugging
        Configuration.logger.debug "Calling API: IndividualsApi#delete_group ..."
      end
      
      # verify the required parameter 'ned_id' is set
      fail "Missing the required parameter 'ned_id' when calling delete_group" if ned_id.nil?
      
      # verify the required parameter 'group_id' is set
      fail "Missing the required parameter 'group_id' when calling delete_group" if group_id.nil?
      
      # resource path
      path = "/individuals/{nedId}/groups/{groupId}".sub('{format}','json').sub('{' + 'nedId' + '}', ned_id.to_s).sub('{' + 'groupId' + '}', group_id.to_s)

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
      header_params[:'Authorization'] = opts[:'authorization'] if opts[:'authorization']

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
        Configuration.logger.debug "API called: IndividualsApi#delete_group"
      end
      return nil
    end

    # List profiles
    # 
    # @param ned_id 
    # @param [Hash] opts the optional parameters
    # @return [Array<Individualprofile>]
    def get_profiles(ned_id, opts = {})
      if Configuration.debugging
        Configuration.logger.debug "Calling API: IndividualsApi#get_profiles ..."
      end
      
      # verify the required parameter 'ned_id' is set
      fail "Missing the required parameter 'ned_id' when calling get_profiles" if ned_id.nil?
      
      # resource path
      path = "/individuals/{nedId}/individualprofiles".sub('{format}','json').sub('{' + 'nedId' + '}', ned_id.to_s)

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
        :return_type => 'Array<Individualprofile>')
      if Configuration.debugging
        Configuration.logger.debug "API called: IndividualsApi#get_profiles. Result: #{result.inspect}"
      end
      return result
    end

    # Add profile
    # 
    # @param ned_id 
    # @param [Hash] opts the optional parameters
    # @option opts [Individualprofile] :body 
    # @option opts [String] :authorization 
    # @return [Individualprofile]
    def add_profile(ned_id, opts = {})
      if Configuration.debugging
        Configuration.logger.debug "Calling API: IndividualsApi#add_profile ..."
      end
      
      # verify the required parameter 'ned_id' is set
      fail "Missing the required parameter 'ned_id' when calling add_profile" if ned_id.nil?
      
      # resource path
      path = "/individuals/{nedId}/individualprofiles".sub('{format}','json').sub('{' + 'nedId' + '}', ned_id.to_s)

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
      header_params[:'Authorization'] = opts[:'authorization'] if opts[:'authorization']

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
        :return_type => 'Individualprofile')
      if Configuration.debugging
        Configuration.logger.debug "API called: IndividualsApi#add_profile. Result: #{result.inspect}"
      end
      return result
    end

    # Read profile
    # 
    # @param ned_id 
    # @param profile_id 
    # @param [Hash] opts the optional parameters
    # @return [Individualprofile]
    def get_profile(ned_id, profile_id, opts = {})
      if Configuration.debugging
        Configuration.logger.debug "Calling API: IndividualsApi#get_profile ..."
      end
      
      # verify the required parameter 'ned_id' is set
      fail "Missing the required parameter 'ned_id' when calling get_profile" if ned_id.nil?
      
      # verify the required parameter 'profile_id' is set
      fail "Missing the required parameter 'profile_id' when calling get_profile" if profile_id.nil?
      
      # resource path
      path = "/individuals/{nedId}/individualprofiles/{profileId}".sub('{format}','json').sub('{' + 'nedId' + '}', ned_id.to_s).sub('{' + 'profileId' + '}', profile_id.to_s)

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
        :return_type => 'Individualprofile')
      if Configuration.debugging
        Configuration.logger.debug "API called: IndividualsApi#get_profile. Result: #{result.inspect}"
      end
      return result
    end

    # Update a profile
    # 
    # @param ned_id 
    # @param profile_id 
    # @param [Hash] opts the optional parameters
    # @option opts [String] :authorization 
    # @option opts [Individualprofile] :body 
    # @return [Individualprofile]
    def update_profile(ned_id, profile_id, opts = {})
      if Configuration.debugging
        Configuration.logger.debug "Calling API: IndividualsApi#update_profile ..."
      end
      
      # verify the required parameter 'ned_id' is set
      fail "Missing the required parameter 'ned_id' when calling update_profile" if ned_id.nil?
      
      # verify the required parameter 'profile_id' is set
      fail "Missing the required parameter 'profile_id' when calling update_profile" if profile_id.nil?
      
      # resource path
      path = "/individuals/{nedId}/individualprofiles/{profileId}".sub('{format}','json').sub('{' + 'nedId' + '}', ned_id.to_s).sub('{' + 'profileId' + '}', profile_id.to_s)

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
      header_params[:'Authorization'] = opts[:'authorization'] if opts[:'authorization']

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
        :return_type => 'Individualprofile')
      if Configuration.debugging
        Configuration.logger.debug "API called: IndividualsApi#update_profile. Result: #{result.inspect}"
      end
      return result
    end

    # Delete a profile
    # 
    # @param ned_id 
    # @param profile_id 
    # @param [Hash] opts the optional parameters
    # @option opts [String] :authorization 
    # @return [nil]
    def delete_profile(ned_id, profile_id, opts = {})
      if Configuration.debugging
        Configuration.logger.debug "Calling API: IndividualsApi#delete_profile ..."
      end
      
      # verify the required parameter 'ned_id' is set
      fail "Missing the required parameter 'ned_id' when calling delete_profile" if ned_id.nil?
      
      # verify the required parameter 'profile_id' is set
      fail "Missing the required parameter 'profile_id' when calling delete_profile" if profile_id.nil?
      
      # resource path
      path = "/individuals/{nedId}/individualprofiles/{profileId}".sub('{format}','json').sub('{' + 'nedId' + '}', ned_id.to_s).sub('{' + 'profileId' + '}', profile_id.to_s)

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
      header_params[:'Authorization'] = opts[:'authorization'] if opts[:'authorization']

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
        Configuration.logger.debug "API called: IndividualsApi#delete_profile"
      end
      return nil
    end

    # List phone numbers
    # 
    # @param ned_id 
    # @param [Hash] opts the optional parameters
    # @return [Array<Phonenumber>]
    def get_phonenumbers(ned_id, opts = {})
      if Configuration.debugging
        Configuration.logger.debug "Calling API: IndividualsApi#get_phonenumbers ..."
      end
      
      # verify the required parameter 'ned_id' is set
      fail "Missing the required parameter 'ned_id' when calling get_phonenumbers" if ned_id.nil?
      
      # resource path
      path = "/individuals/{nedId}/phonenumbers".sub('{format}','json').sub('{' + 'nedId' + '}', ned_id.to_s)

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
        :return_type => 'Array<Phonenumber>')
      if Configuration.debugging
        Configuration.logger.debug "API called: IndividualsApi#get_phonenumbers. Result: #{result.inspect}"
      end
      return result
    end

    # List relationships
    # 
    # @param ned_id 
    # @param [Hash] opts the optional parameters
    # @return [Array<Relationship>]
    def get_relationships(ned_id, opts = {})
      if Configuration.debugging
        Configuration.logger.debug "Calling API: IndividualsApi#get_relationships ..."
      end
      
      # verify the required parameter 'ned_id' is set
      fail "Missing the required parameter 'ned_id' when calling get_relationships" if ned_id.nil?
      
      # resource path
      path = "/individuals/{nedId}/relationships".sub('{format}','json').sub('{' + 'nedId' + '}', ned_id.to_s)

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
        :return_type => 'Array<Relationship>')
      if Configuration.debugging
        Configuration.logger.debug "API called: IndividualsApi#get_relationships. Result: #{result.inspect}"
      end
      return result
    end

    # Create relationship
    # 
    # @param ned_id 
    # @param [Hash] opts the optional parameters
    # @option opts [Relationship] :body 
    # @option opts [String] :authorization 
    # @return [Relationship]
    def create_relationship(ned_id, opts = {})
      if Configuration.debugging
        Configuration.logger.debug "Calling API: IndividualsApi#create_relationship ..."
      end
      
      # verify the required parameter 'ned_id' is set
      fail "Missing the required parameter 'ned_id' when calling create_relationship" if ned_id.nil?
      
      # resource path
      path = "/individuals/{nedId}/relationships".sub('{format}','json').sub('{' + 'nedId' + '}', ned_id.to_s)

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
      header_params[:'Authorization'] = opts[:'authorization'] if opts[:'authorization']

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
        :return_type => 'Relationship')
      if Configuration.debugging
        Configuration.logger.debug "API called: IndividualsApi#create_relationship. Result: #{result.inspect}"
      end
      return result
    end

    # Read relationship
    # 
    # @param ned_id 
    # @param relationship_id 
    # @param [Hash] opts the optional parameters
    # @return [Relationship]
    def get_relationship(ned_id, relationship_id, opts = {})
      if Configuration.debugging
        Configuration.logger.debug "Calling API: IndividualsApi#get_relationship ..."
      end
      
      # verify the required parameter 'ned_id' is set
      fail "Missing the required parameter 'ned_id' when calling get_relationship" if ned_id.nil?
      
      # verify the required parameter 'relationship_id' is set
      fail "Missing the required parameter 'relationship_id' when calling get_relationship" if relationship_id.nil?
      
      # resource path
      path = "/individuals/{nedId}/relationships/{relationshipId}".sub('{format}','json').sub('{' + 'nedId' + '}', ned_id.to_s).sub('{' + 'relationshipId' + '}', relationship_id.to_s)

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
        :return_type => 'Relationship')
      if Configuration.debugging
        Configuration.logger.debug "API called: IndividualsApi#get_relationship. Result: #{result.inspect}"
      end
      return result
    end

    # Update relationship
    # 
    # @param ned_id 
    # @param relationship_id 
    # @param [Hash] opts the optional parameters
    # @option opts [String] :authorization 
    # @option opts [Relationship] :body 
    # @return [Relationship]
    def update_relationship(ned_id, relationship_id, opts = {})
      if Configuration.debugging
        Configuration.logger.debug "Calling API: IndividualsApi#update_relationship ..."
      end
      
      # verify the required parameter 'ned_id' is set
      fail "Missing the required parameter 'ned_id' when calling update_relationship" if ned_id.nil?
      
      # verify the required parameter 'relationship_id' is set
      fail "Missing the required parameter 'relationship_id' when calling update_relationship" if relationship_id.nil?
      
      # resource path
      path = "/individuals/{nedId}/relationships/{relationshipId}".sub('{format}','json').sub('{' + 'nedId' + '}', ned_id.to_s).sub('{' + 'relationshipId' + '}', relationship_id.to_s)

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
      header_params[:'Authorization'] = opts[:'authorization'] if opts[:'authorization']

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
        :return_type => 'Relationship')
      if Configuration.debugging
        Configuration.logger.debug "API called: IndividualsApi#update_relationship. Result: #{result.inspect}"
      end
      return result
    end

    # Delete relationship
    # 
    # @param ned_id 
    # @param relationship_id 
    # @param [Hash] opts the optional parameters
    # @option opts [String] :authorization 
    # @return [nil]
    def delete_relationship(ned_id, relationship_id, opts = {})
      if Configuration.debugging
        Configuration.logger.debug "Calling API: IndividualsApi#delete_relationship ..."
      end
      
      # verify the required parameter 'ned_id' is set
      fail "Missing the required parameter 'ned_id' when calling delete_relationship" if ned_id.nil?
      
      # verify the required parameter 'relationship_id' is set
      fail "Missing the required parameter 'relationship_id' when calling delete_relationship" if relationship_id.nil?
      
      # resource path
      path = "/individuals/{nedId}/relationships/{relationshipId}".sub('{format}','json').sub('{' + 'nedId' + '}', ned_id.to_s).sub('{' + 'relationshipId' + '}', relationship_id.to_s)

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
      header_params[:'Authorization'] = opts[:'authorization'] if opts[:'authorization']

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
        Configuration.logger.debug "API called: IndividualsApi#delete_relationship"
      end
      return nil
    end

    # List UIDs
    # 
    # @param ned_id 
    # @param [Hash] opts the optional parameters
    # @return [Array<Uniqueidentifier>]
    def get_uids(ned_id, opts = {})
      if Configuration.debugging
        Configuration.logger.debug "Calling API: IndividualsApi#get_uids ..."
      end
      
      # verify the required parameter 'ned_id' is set
      fail "Missing the required parameter 'ned_id' when calling get_uids" if ned_id.nil?
      
      # resource path
      path = "/individuals/{nedId}/uids".sub('{format}','json').sub('{' + 'nedId' + '}', ned_id.to_s)

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
        :return_type => 'Array<Uniqueidentifier>')
      if Configuration.debugging
        Configuration.logger.debug "API called: IndividualsApi#get_uids. Result: #{result.inspect}"
      end
      return result
    end

    # Create UID
    # 
    # @param ned_id 
    # @param [Hash] opts the optional parameters
    # @option opts [Uniqueidentifier] :body 
    # @option opts [String] :authorization 
    # @return [Uniqueidentifier]
    def create_uid(ned_id, opts = {})
      if Configuration.debugging
        Configuration.logger.debug "Calling API: IndividualsApi#create_uid ..."
      end
      
      # verify the required parameter 'ned_id' is set
      fail "Missing the required parameter 'ned_id' when calling create_uid" if ned_id.nil?
      
      # resource path
      path = "/individuals/{nedId}/uids".sub('{format}','json').sub('{' + 'nedId' + '}', ned_id.to_s)

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
      header_params[:'Authorization'] = opts[:'authorization'] if opts[:'authorization']

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
        :return_type => 'Uniqueidentifier')
      if Configuration.debugging
        Configuration.logger.debug "API called: IndividualsApi#create_uid. Result: #{result.inspect}"
      end
      return result
    end

    # Read uid
    # 
    # @param ned_id 
    # @param id 
    # @param [Hash] opts the optional parameters
    # @return [Uniqueidentifier]
    def get_uid(ned_id, id, opts = {})
      if Configuration.debugging
        Configuration.logger.debug "Calling API: IndividualsApi#get_uid ..."
      end
      
      # verify the required parameter 'ned_id' is set
      fail "Missing the required parameter 'ned_id' when calling get_uid" if ned_id.nil?
      
      # verify the required parameter 'id' is set
      fail "Missing the required parameter 'id' when calling get_uid" if id.nil?
      
      # resource path
      path = "/individuals/{nedId}/uids/{id}".sub('{format}','json').sub('{' + 'nedId' + '}', ned_id.to_s).sub('{' + 'id' + '}', id.to_s)

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
        :return_type => 'Uniqueidentifier')
      if Configuration.debugging
        Configuration.logger.debug "API called: IndividualsApi#get_uid. Result: #{result.inspect}"
      end
      return result
    end

    # Update UID
    # 
    # @param ned_id 
    # @param id 
    # @param [Hash] opts the optional parameters
    # @option opts [String] :authorization 
    # @option opts [Uniqueidentifier] :body 
    # @return [Uniqueidentifier]
    def update_uid(ned_id, id, opts = {})
      if Configuration.debugging
        Configuration.logger.debug "Calling API: IndividualsApi#update_uid ..."
      end
      
      # verify the required parameter 'ned_id' is set
      fail "Missing the required parameter 'ned_id' when calling update_uid" if ned_id.nil?
      
      # verify the required parameter 'id' is set
      fail "Missing the required parameter 'id' when calling update_uid" if id.nil?
      
      # resource path
      path = "/individuals/{nedId}/uids/{id}".sub('{format}','json').sub('{' + 'nedId' + '}', ned_id.to_s).sub('{' + 'id' + '}', id.to_s)

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
      header_params[:'Authorization'] = opts[:'authorization'] if opts[:'authorization']

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
        :return_type => 'Uniqueidentifier')
      if Configuration.debugging
        Configuration.logger.debug "API called: IndividualsApi#update_uid. Result: #{result.inspect}"
      end
      return result
    end

    # Delete UID
    # 
    # @param ned_id 
    # @param id 
    # @param [Hash] opts the optional parameters
    # @option opts [String] :authorization 
    # @return [nil]
    def delete_uid(ned_id, id, opts = {})
      if Configuration.debugging
        Configuration.logger.debug "Calling API: IndividualsApi#delete_uid ..."
      end
      
      # verify the required parameter 'ned_id' is set
      fail "Missing the required parameter 'ned_id' when calling delete_uid" if ned_id.nil?
      
      # verify the required parameter 'id' is set
      fail "Missing the required parameter 'id' when calling delete_uid" if id.nil?
      
      # resource path
      path = "/individuals/{nedId}/uids/{id}".sub('{format}','json').sub('{' + 'nedId' + '}', ned_id.to_s).sub('{' + 'id' + '}', id.to_s)

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
      header_params[:'Authorization'] = opts[:'authorization'] if opts[:'authorization']

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
        Configuration.logger.debug "API called: IndividualsApi#delete_uid"
      end
      return nil
    end

    # Read individual by UID
    # 
    # @param uid_type 
    # @param uid_value 
    # @param [Hash] opts the optional parameters
    # @return [IndividualComposite]
    def read_individual_by_uid(uid_type, uid_value, opts = {})
      if Configuration.debugging
        Configuration.logger.debug "Calling API: IndividualsApi#read_individual_by_uid ..."
      end
      
      # verify the required parameter 'uid_type' is set
      fail "Missing the required parameter 'uid_type' when calling read_individual_by_uid" if uid_type.nil?
      
      # verify the required parameter 'uid_value' is set
      fail "Missing the required parameter 'uid_value' when calling read_individual_by_uid" if uid_value.nil?
      
      # resource path
      path = "/individuals/{uidType}/{uidValue}".sub('{format}','json').sub('{' + 'uidType' + '}', uid_type.to_s).sub('{' + 'uidValue' + '}', uid_value.to_s)

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
        :return_type => 'IndividualComposite')
      if Configuration.debugging
        Configuration.logger.debug "API called: IndividualsApi#read_individual_by_uid. Result: #{result.inspect}"
      end
      return result
    end
  end
end




