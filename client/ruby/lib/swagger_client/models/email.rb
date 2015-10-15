module SwaggerClient
  # 
  class Email < BaseObject
    attr_accessor :type, :typeid, :emailaddress, :verified, :isactive, :source, :nedid, :sourcetypeid, :id
    # attribute mapping from ruby-style variable name to JSON key
    def self.attribute_map
      {
        
        # 
        :'type' => :'type',
        
        # 
        :'typeid' => :'typeid',
        
        # 
        :'emailaddress' => :'emailaddress',
        
        # 
        :'verified' => :'verified',
        
        # 
        :'isactive' => :'isactive',
        
        # 
        :'source' => :'source',
        
        # 
        :'nedid' => :'nedid',
        
        # 
        :'sourcetypeid' => :'sourcetypeid',
        
        # 
        :'id' => :'id'
        
      }
    end

    # attribute type
    def self.swagger_types
      {
        :'type' => :'String',
        :'typeid' => :'Integer',
        :'emailaddress' => :'String',
        :'verified' => :'BOOLEAN',
        :'isactive' => :'BOOLEAN',
        :'source' => :'String',
        :'nedid' => :'Integer',
        :'sourcetypeid' => :'Integer',
        :'id' => :'Integer'
        
      }
    end

    def initialize(attributes = {})
      return if !attributes.is_a?(Hash) || attributes.empty?

      # convert string to symbol for hash key
      attributes = attributes.inject({}){|memo,(k,v)| memo[k.to_sym] = v; memo}

      
      if attributes[:'type']
        self.type = attributes[:'type']
      end
      
      if attributes[:'typeid']
        self.typeid = attributes[:'typeid']
      end
      
      if attributes[:'emailaddress']
        self.emailaddress = attributes[:'emailaddress']
      end
      
      if attributes[:'verified']
        self.verified = attributes[:'verified']
      end
      
      if attributes[:'isactive']
        self.isactive = attributes[:'isactive']
      end
      
      if attributes[:'source']
        self.source = attributes[:'source']
      end
      
      if attributes[:'nedid']
        self.nedid = attributes[:'nedid']
      end
      
      if attributes[:'sourcetypeid']
        self.sourcetypeid = attributes[:'sourcetypeid']
      end
      
      if attributes[:'id']
        self.id = attributes[:'id']
      end
      
    end

  end
end
