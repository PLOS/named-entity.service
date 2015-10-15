module SwaggerClient
  # 
  class NedErrorResponse < BaseObject
    attr_accessor :problem, :error_code, :error_msg, :detailed_msg, :acceptable_values, :timestamp
    # attribute mapping from ruby-style variable name to JSON key
    def self.attribute_map
      {
        
        # 
        :'problem' => :'problem',
        
        # 
        :'error_code' => :'errorCode',
        
        # 
        :'error_msg' => :'errorMsg',
        
        # 
        :'detailed_msg' => :'detailedMsg',
        
        # 
        :'acceptable_values' => :'acceptableValues',
        
        # 
        :'timestamp' => :'timestamp'
        
      }
    end

    # attribute type
    def self.swagger_types
      {
        :'problem' => :'String',
        :'error_code' => :'Integer',
        :'error_msg' => :'String',
        :'detailed_msg' => :'String',
        :'acceptable_values' => :'Array<String>',
        :'timestamp' => :'DateTime'
        
      }
    end

    def initialize(attributes = {})
      return if !attributes.is_a?(Hash) || attributes.empty?

      # convert string to symbol for hash key
      attributes = attributes.inject({}){|memo,(k,v)| memo[k.to_sym] = v; memo}

      
      if attributes[:'problem']
        self.problem = attributes[:'problem']
      end
      
      if attributes[:'errorCode']
        self.error_code = attributes[:'errorCode']
      end
      
      if attributes[:'errorMsg']
        self.error_msg = attributes[:'errorMsg']
      end
      
      if attributes[:'detailedMsg']
        self.detailed_msg = attributes[:'detailedMsg']
      end
      
      if attributes[:'acceptableValues']
        if (value = attributes[:'acceptableValues']).is_a?(Array)
          self.acceptable_values = value
        end
      end
      
      if attributes[:'timestamp']
        self.timestamp = attributes[:'timestamp']
      end
      
    end

  end
end
