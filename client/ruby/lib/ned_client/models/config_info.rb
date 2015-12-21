module NedClient
  class ConfigInfo < BaseObject
    attr_accessor :version

    attr_accessor :startime

    # Attribute mapping from ruby-style variable name to JSON key.
    def self.attribute_map
      {
        
        :'version' => :'version',
        
        :'startime' => :'startime'
        
      }
    end

    # Attribute type mapping.
    def self.swagger_types
      {
        :'version' => :'String',
        :'startime' => :'DateTime'
        
      }
    end

    def initialize(attributes = {})
      return unless attributes.is_a?(Hash)

      # convert string to symbol for hash key
      attributes = attributes.inject({}){|memo,(k,v)| memo[k.to_sym] = v; memo}

      
      if attributes[:'version']
        self.version = attributes[:'version']
      end
      
      if attributes[:'startime']
        self.startime = attributes[:'startime']
      end
      
    end

    # Check equality by comparing each attribute.
    def ==(o)
      return true if self.equal?(o)
      self.class == o.class &&
          version == o.version &&
          startime == o.startime
    end

    # @see the `==` method
    def eql?(o)
      self == o
    end

    # Calculate hash code according to all attributes.
    def hash
      [version, startime].hash
    end
  end
end
