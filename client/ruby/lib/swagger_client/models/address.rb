module SwaggerClient
  # 
  class Address < BaseObject
    attr_accessor :typeid, :type, :addressline1, :addressline2, :addressline3, :city, :statecodetypeid, :statecodetype, :countrycodetypeid, :countrycodetype, :postalcode, :maincontactnamedentityid, :latitude, :longitude, :isactive, :id, :source, :nedid, :sourcetypeid
    # attribute mapping from ruby-style variable name to JSON key
    def self.attribute_map
      {
        
        # 
        :'typeid' => :'typeid',
        
        # 
        :'type' => :'type',
        
        # 
        :'addressline1' => :'addressline1',
        
        # 
        :'addressline2' => :'addressline2',
        
        # 
        :'addressline3' => :'addressline3',
        
        # 
        :'city' => :'city',
        
        # 
        :'statecodetypeid' => :'statecodetypeid',
        
        # 
        :'statecodetype' => :'statecodetype',
        
        # 
        :'countrycodetypeid' => :'countrycodetypeid',
        
        # 
        :'countrycodetype' => :'countrycodetype',
        
        # 
        :'postalcode' => :'postalcode',
        
        # 
        :'maincontactnamedentityid' => :'maincontactnamedentityid',
        
        # 
        :'latitude' => :'latitude',
        
        # 
        :'longitude' => :'longitude',
        
        # 
        :'isactive' => :'isactive',
        
        # 
        :'id' => :'id',
        
        # 
        :'source' => :'source',
        
        # 
        :'nedid' => :'nedid',
        
        # 
        :'sourcetypeid' => :'sourcetypeid'
        
      }
    end

    # attribute type
    def self.swagger_types
      {
        :'typeid' => :'Integer',
        :'type' => :'String',
        :'addressline1' => :'String',
        :'addressline2' => :'String',
        :'addressline3' => :'String',
        :'city' => :'String',
        :'statecodetypeid' => :'Integer',
        :'statecodetype' => :'String',
        :'countrycodetypeid' => :'Integer',
        :'countrycodetype' => :'String',
        :'postalcode' => :'String',
        :'maincontactnamedentityid' => :'Integer',
        :'latitude' => :'Integer',
        :'longitude' => :'Integer',
        :'isactive' => :'BOOLEAN',
        :'id' => :'Integer',
        :'source' => :'String',
        :'nedid' => :'Integer',
        :'sourcetypeid' => :'Integer'
        
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
      
      if attributes[:'addressline1']
        self.addressline1 = attributes[:'addressline1']
      end
      
      if attributes[:'addressline2']
        self.addressline2 = attributes[:'addressline2']
      end
      
      if attributes[:'addressline3']
        self.addressline3 = attributes[:'addressline3']
      end
      
      if attributes[:'city']
        self.city = attributes[:'city']
      end
      
      if attributes[:'statecodetypeid']
        self.statecodetypeid = attributes[:'statecodetypeid']
      end
      
      if attributes[:'statecodetype']
        self.statecodetype = attributes[:'statecodetype']
      end
      
      if attributes[:'countrycodetypeid']
        self.countrycodetypeid = attributes[:'countrycodetypeid']
      end
      
      if attributes[:'countrycodetype']
        self.countrycodetype = attributes[:'countrycodetype']
      end
      
      if attributes[:'postalcode']
        self.postalcode = attributes[:'postalcode']
      end
      
      if attributes[:'maincontactnamedentityid']
        self.maincontactnamedentityid = attributes[:'maincontactnamedentityid']
      end
      
      if attributes[:'latitude']
        self.latitude = attributes[:'latitude']
      end
      
      if attributes[:'longitude']
        self.longitude = attributes[:'longitude']
      end
      
      if attributes[:'isactive']
        self.isactive = attributes[:'isactive']
      end
      
      if attributes[:'id']
        self.id = attributes[:'id']
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
      
    end

  end
end
