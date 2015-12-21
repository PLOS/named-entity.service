module NedClient
  class NedErrorResponse < BaseObject
    attr_accessor :failure_msg

    attr_accessor :error_code

    attr_accessor :error_msg

    attr_accessor :detailed_msg

    attr_accessor :acceptable_values

    attr_accessor :timestamp

    # Attribute mapping from ruby-style variable name to JSON key.
    def self.attribute_map
      {
        
        :'failure_msg' => :'failureMsg',
        
        :'error_code' => :'errorCode',
        
        :'error_msg' => :'errorMsg',
        
        :'detailed_msg' => :'detailedMsg',
        
        :'acceptable_values' => :'acceptableValues',
        
        :'timestamp' => :'timestamp'
        
      }
    end

    # Attribute type mapping.
    def self.swagger_types
      {
        :'failure_msg' => :'String',
        :'error_code' => :'Integer',
        :'error_msg' => :'String',
        :'detailed_msg' => :'String',
        :'acceptable_values' => :'Array<String>',
        :'timestamp' => :'DateTime'
        
      }
    end

    def initialize(attributes = {})
      return unless attributes.is_a?(Hash)

      # convert string to symbol for hash key
      attributes = attributes.inject({}){|memo,(k,v)| memo[k.to_sym] = v; memo}

      
      if attributes[:'failureMsg']
        self.failure_msg = attributes[:'failureMsg']
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

    # Check equality by comparing each attribute.
    def ==(o)
      return true if self.equal?(o)
      self.class == o.class &&
          failure_msg == o.failure_msg &&
          error_code == o.error_code &&
          error_msg == o.error_msg &&
          detailed_msg == o.detailed_msg &&
          acceptable_values == o.acceptable_values &&
          timestamp == o.timestamp
    end

    # @see the `==` method
    def eql?(o)
      self == o
    end

    # Calculate hash code according to all attributes.
    def hash
      [failure_msg, error_code, error_msg, detailed_msg, acceptable_values, timestamp].hash
    end
  end
end
