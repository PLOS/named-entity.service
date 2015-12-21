module NedClient
  class Address < BaseObject
    attr_accessor :id

    attr_accessor :nedid

    attr_accessor :source

    attr_accessor :sourcetypeid

    attr_accessor :created

    attr_accessor :lastmodified

    attr_accessor :createdby

    attr_accessor :createdbyname

    attr_accessor :lastmodifiedby

    attr_accessor :lastmodifiedbyname

    attr_accessor :typeid

    attr_accessor :type

    attr_accessor :addressline1

    attr_accessor :addressline2

    attr_accessor :addressline3

    attr_accessor :city

    attr_accessor :statecodetypeid

    attr_accessor :statecodetype

    attr_accessor :countrycodetypeid

    attr_accessor :countrycodetype

    attr_accessor :postalcode

    attr_accessor :maincontactnamedentityid

    attr_accessor :latitude

    attr_accessor :longitude

    attr_accessor :isactive

    # Attribute mapping from ruby-style variable name to JSON key.
    def self.attribute_map
      {
        
        :'id' => :'id',
        
        :'nedid' => :'nedid',
        
        :'source' => :'source',
        
        :'sourcetypeid' => :'sourcetypeid',
        
        :'created' => :'created',
        
        :'lastmodified' => :'lastmodified',
        
        :'createdby' => :'createdby',
        
        :'createdbyname' => :'createdbyname',
        
        :'lastmodifiedby' => :'lastmodifiedby',
        
        :'lastmodifiedbyname' => :'lastmodifiedbyname',
        
        :'typeid' => :'typeid',
        
        :'type' => :'type',
        
        :'addressline1' => :'addressline1',
        
        :'addressline2' => :'addressline2',
        
        :'addressline3' => :'addressline3',
        
        :'city' => :'city',
        
        :'statecodetypeid' => :'statecodetypeid',
        
        :'statecodetype' => :'statecodetype',
        
        :'countrycodetypeid' => :'countrycodetypeid',
        
        :'countrycodetype' => :'countrycodetype',
        
        :'postalcode' => :'postalcode',
        
        :'maincontactnamedentityid' => :'maincontactnamedentityid',
        
        :'latitude' => :'latitude',
        
        :'longitude' => :'longitude',
        
        :'isactive' => :'isactive'
        
      }
    end

    # Attribute type mapping.
    def self.swagger_types
      {
        :'id' => :'Integer',
        :'nedid' => :'Integer',
        :'source' => :'String',
        :'sourcetypeid' => :'Integer',
        :'created' => :'DateTime',
        :'lastmodified' => :'DateTime',
        :'createdby' => :'Integer',
        :'createdbyname' => :'String',
        :'lastmodifiedby' => :'Integer',
        :'lastmodifiedbyname' => :'String',
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
        :'isactive' => :'BOOLEAN'
        
      }
    end

    def initialize(attributes = {})
      return unless attributes.is_a?(Hash)

      # convert string to symbol for hash key
      attributes = attributes.inject({}){|memo,(k,v)| memo[k.to_sym] = v; memo}

      
      if attributes[:'id']
        self.id = attributes[:'id']
      end
      
      if attributes[:'nedid']
        self.nedid = attributes[:'nedid']
      end
      
      if attributes[:'source']
        self.source = attributes[:'source']
      end
      
      if attributes[:'sourcetypeid']
        self.sourcetypeid = attributes[:'sourcetypeid']
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
      
      if attributes[:'createdbyname']
        self.createdbyname = attributes[:'createdbyname']
      end
      
      if attributes[:'lastmodifiedby']
        self.lastmodifiedby = attributes[:'lastmodifiedby']
      end
      
      if attributes[:'lastmodifiedbyname']
        self.lastmodifiedbyname = attributes[:'lastmodifiedbyname']
      end
      
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
      else
        self.isactive = false
      end
      
    end

    # Check equality by comparing each attribute.
    def ==(o)
      return true if self.equal?(o)
      self.class == o.class &&
          id == o.id &&
          nedid == o.nedid &&
          source == o.source &&
          sourcetypeid == o.sourcetypeid &&
          created == o.created &&
          lastmodified == o.lastmodified &&
          createdby == o.createdby &&
          createdbyname == o.createdbyname &&
          lastmodifiedby == o.lastmodifiedby &&
          lastmodifiedbyname == o.lastmodifiedbyname &&
          typeid == o.typeid &&
          type == o.type &&
          addressline1 == o.addressline1 &&
          addressline2 == o.addressline2 &&
          addressline3 == o.addressline3 &&
          city == o.city &&
          statecodetypeid == o.statecodetypeid &&
          statecodetype == o.statecodetype &&
          countrycodetypeid == o.countrycodetypeid &&
          countrycodetype == o.countrycodetype &&
          postalcode == o.postalcode &&
          maincontactnamedentityid == o.maincontactnamedentityid &&
          latitude == o.latitude &&
          longitude == o.longitude &&
          isactive == o.isactive
    end

    # @see the `==` method
    def eql?(o)
      self == o
    end

    # Calculate hash code according to all attributes.
    def hash
      [id, nedid, source, sourcetypeid, created, lastmodified, createdby, createdbyname, lastmodifiedby, lastmodifiedbyname, typeid, type, addressline1, addressline2, addressline3, city, statecodetypeid, statecodetype, countrycodetypeid, countrycodetype, postalcode, maincontactnamedentityid, latitude, longitude, isactive].hash
    end
  end
end
