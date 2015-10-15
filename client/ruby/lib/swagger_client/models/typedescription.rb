module SwaggerClient
  # 
  class Typedescription < BaseObject
    attr_accessor :id, :description, :howused
    # attribute mapping from ruby-style variable name to JSON key
    def self.attribute_map
      {
        
        # 
        :'id' => :'id',
        
        # 
        :'description' => :'description',
        
        # 
        :'howused' => :'howused'
        
      }
    end

    # attribute type
    def self.swagger_types
      {
        :'id' => :'Integer',
        :'description' => :'String',
        :'howused' => :'String'
        
      }
    end

    def initialize(attributes = {})
      return if !attributes.is_a?(Hash) || attributes.empty?

      # convert string to symbol for hash key
      attributes = attributes.inject({}){|memo,(k,v)| memo[k.to_sym] = v; memo}

      
      if attributes[:'id']
        self.id = attributes[:'id']
      end
      
      if attributes[:'description']
        self.description = attributes[:'description']
      end
      
      if attributes[:'howused']
        self.howused = attributes[:'howused']
      end
      
    end

  end
end
