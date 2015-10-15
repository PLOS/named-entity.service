module SwaggerClient
  # 
  class ConfigInfo < BaseObject
    attr_accessor :version, :startime
    # attribute mapping from ruby-style variable name to JSON key
    def self.attribute_map
      {
        
        # 
        :'version' => :'version',
        
        # 
        :'startime' => :'startime'
        
      }
    end

    # attribute type
    def self.swagger_types
      {
        :'version' => :'String',
        :'startime' => :'DateTime'
        
      }
    end

    def initialize(attributes = {})
      return if !attributes.is_a?(Hash) || attributes.empty?

      # convert string to symbol for hash key
      attributes = attributes.inject({}){|memo,(k,v)| memo[k.to_sym] = v; memo}

      
      if attributes[:'version']
        self.version = attributes[:'version']
      end
      
      if attributes[:'startime']
        self.startime = attributes[:'startime']
      end
      
    end

  end
end
