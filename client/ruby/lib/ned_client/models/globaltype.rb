module NedClient
  # 
  class Globaltype < BaseObject
    attr_accessor :id, :typeid, :shortdescription, :longdescription, :typecode, :created, :lastmodified, :createdby, :lastmodifiedby
    # attribute mapping from ruby-style variable name to JSON key
    def self.attribute_map
      {
        
        # 
        :'id' => :'id',
        
        # 
        :'typeid' => :'typeid',
        
        # 
        :'shortdescription' => :'shortdescription',
        
        # 
        :'longdescription' => :'longdescription',
        
        # 
        :'typecode' => :'typecode',
        
        # 
        :'created' => :'created',
        
        # 
        :'lastmodified' => :'lastmodified',
        
        # 
        :'createdby' => :'createdby',
        
        # 
        :'lastmodifiedby' => :'lastmodifiedby'
        
      }
    end

    # attribute type
    def self.swagger_types
      {
        :'id' => :'Integer',
        :'typeid' => :'Integer',
        :'shortdescription' => :'String',
        :'longdescription' => :'String',
        :'typecode' => :'String',
        :'created' => :'DateTime',
        :'lastmodified' => :'DateTime',
        :'createdby' => :'Integer',
        :'lastmodifiedby' => :'Integer'
        
      }
    end

    def initialize(attributes = {})
      return if !attributes.is_a?(Hash) || attributes.empty?

      # convert string to symbol for hash key
      attributes = attributes.inject({}){|memo,(k,v)| memo[k.to_sym] = v; memo}

      
      if attributes[:'id']
        self.id = attributes[:'id']
      end
      
      if attributes[:'typeid']
        self.typeid = attributes[:'typeid']
      end
      
      if attributes[:'shortdescription']
        self.shortdescription = attributes[:'shortdescription']
      end
      
      if attributes[:'longdescription']
        self.longdescription = attributes[:'longdescription']
      end
      
      if attributes[:'typecode']
        self.typecode = attributes[:'typecode']
      end
      
      if attributes[:'created']
        self.created = attributes[:'created']
      end
      
      if attributes[:'lastmodified']
        self.lastmodified = attributes[:'lastmodified']
      end
      
      if attributes[:'createdby']
        self.createdby = attributes[:'createdby']
      end
      
      if attributes[:'lastmodifiedby']
        self.lastmodifiedby = attributes[:'lastmodifiedby']
      end
      
    end

  end
end
