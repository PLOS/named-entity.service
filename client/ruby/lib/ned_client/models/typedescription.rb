module NedClient
  class Typedescription < BaseObject
    attr_accessor :id

    attr_accessor :description

    attr_accessor :howused

    attr_accessor :created

    attr_accessor :lastmodified

    # Attribute mapping from ruby-style variable name to JSON key.
    def self.attribute_map
      {
        
        :'id' => :'id',
        
        :'description' => :'description',
        
        :'howused' => :'howused',
        
        :'created' => :'created',
        
        :'lastmodified' => :'lastmodified'
        
      }
    end

    # Attribute type mapping.
    def self.swagger_types
      {
        :'id' => :'Integer',
        :'description' => :'String',
        :'howused' => :'String',
        :'created' => :'DateTime',
        :'lastmodified' => :'DateTime'
        
      }
    end

    def initialize(attributes = {})
      return unless attributes.is_a?(Hash)

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
      
      if attributes[:'created']
        self.created = attributes[:'created']
      end
      
      if attributes[:'lastmodified']
        self.lastmodified = attributes[:'lastmodified']
      end
      
    end

    # Check equality by comparing each attribute.
    def ==(o)
      return true if self.equal?(o)
      self.class == o.class &&
          id == o.id &&
          description == o.description &&
          howused == o.howused &&
          created == o.created &&
          lastmodified == o.lastmodified
    end

    # @see the `==` method
    def eql?(o)
      self == o
    end

    # Calculate hash code according to all attributes.
    def hash
      [id, description, howused, created, lastmodified].hash
    end
  end
end
