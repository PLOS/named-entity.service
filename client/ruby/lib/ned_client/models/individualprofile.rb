module NedClient
  class Individualprofile < BaseObject
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

    attr_accessor :firstname

    attr_accessor :middlename

    attr_accessor :lastname

    attr_accessor :nickname

    attr_accessor :nameprefix

    attr_accessor :nameprefixtypeid

    attr_accessor :namesuffix

    attr_accessor :namesuffixtypeid

    attr_accessor :displayname

    attr_accessor :biography

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
        
        :'firstname' => :'firstname',
        
        :'middlename' => :'middlename',
        
        :'lastname' => :'lastname',
        
        :'nickname' => :'nickname',
        
        :'nameprefix' => :'nameprefix',
        
        :'nameprefixtypeid' => :'nameprefixtypeid',
        
        :'namesuffix' => :'namesuffix',
        
        :'namesuffixtypeid' => :'namesuffixtypeid',
        
        :'displayname' => :'displayname',
        
        :'biography' => :'biography',
        
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
        :'firstname' => :'String',
        :'middlename' => :'String',
        :'lastname' => :'String',
        :'nickname' => :'String',
        :'nameprefix' => :'String',
        :'nameprefixtypeid' => :'Integer',
        :'namesuffix' => :'String',
        :'namesuffixtypeid' => :'Integer',
        :'displayname' => :'String',
        :'biography' => :'String',
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
      
      if attributes[:'firstname']
        self.firstname = attributes[:'firstname']
      end
      
      if attributes[:'middlename']
        self.middlename = attributes[:'middlename']
      end
      
      if attributes[:'lastname']
        self.lastname = attributes[:'lastname']
      end
      
      if attributes[:'nickname']
        self.nickname = attributes[:'nickname']
      end
      
      if attributes[:'nameprefix']
        self.nameprefix = attributes[:'nameprefix']
      end
      
      if attributes[:'nameprefixtypeid']
        self.nameprefixtypeid = attributes[:'nameprefixtypeid']
      end
      
      if attributes[:'namesuffix']
        self.namesuffix = attributes[:'namesuffix']
      end
      
      if attributes[:'namesuffixtypeid']
        self.namesuffixtypeid = attributes[:'namesuffixtypeid']
      end
      
      if attributes[:'displayname']
        self.displayname = attributes[:'displayname']
      end
      
      if attributes[:'biography']
        self.biography = attributes[:'biography']
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
          firstname == o.firstname &&
          middlename == o.middlename &&
          lastname == o.lastname &&
          nickname == o.nickname &&
          nameprefix == o.nameprefix &&
          nameprefixtypeid == o.nameprefixtypeid &&
          namesuffix == o.namesuffix &&
          namesuffixtypeid == o.namesuffixtypeid &&
          displayname == o.displayname &&
          biography == o.biography &&
          isactive == o.isactive
    end

    # @see the `==` method
    def eql?(o)
      self == o
    end

    # Calculate hash code according to all attributes.
    def hash
      [id, nedid, source, sourcetypeid, created, lastmodified, createdby, createdbyname, lastmodifiedby, lastmodifiedbyname, firstname, middlename, lastname, nickname, nameprefix, nameprefixtypeid, namesuffix, namesuffixtypeid, displayname, biography, isactive].hash
    end
  end
end
