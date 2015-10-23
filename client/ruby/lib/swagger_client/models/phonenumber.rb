module SwaggerClient
  # 
  class Phonenumber < BaseObject
    attr_accessor :typeid, :type, :countrycodetypeid, :countrycodetype, :phonenumber, :extension, :isactive, :source, :nedid, :sourcetypeid, :id
    # attribute mapping from ruby-style variable name to JSON key
    def self.attribute_map
      {
        
        # 
        :'typeid' => :'typeid',
        
        # 
        :'type' => :'type',
        
        # 
        :'countrycodetypeid' => :'countrycodetypeid',
        
        # 
        :'countrycodetype' => :'countrycodetype',
        
        # 
        :'phonenumber' => :'phonenumber',
        
        # 
        :'extension' => :'extension',
        
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
        :'typeid' => :'Integer',
        :'type' => :'String',
        :'countrycodetypeid' => :'Integer',
        :'countrycodetype' => :'String',
        :'phonenumber' => :'String',
        :'extension' => :'String',
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

      
      if attributes[:'typeid']
        self.typeid = attributes[:'typeid']
      end
      
      if attributes[:'type']
        self.type = attributes[:'type']
      end
      
      if attributes[:'countrycodetypeid']
        self.countrycodetypeid = attributes[:'countrycodetypeid']
      end
      
      if attributes[:'countrycodetype']
        self.countrycodetype = attributes[:'countrycodetype']
      end
      
      if attributes[:'phonenumber']
        self.phonenumber = attributes[:'phonenumber']
      end
      
      if attributes[:'extension']
        self.extension = attributes[:'extension']
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
