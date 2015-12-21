module NedClient
  class Alert < BaseObject
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

    attr_accessor :frequencytypeid

    attr_accessor :frequency

    attr_accessor :journaltypeid

    attr_accessor :journal

    attr_accessor :name

    attr_accessor :query

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
        
        :'frequencytypeid' => :'frequencytypeid',
        
        :'frequency' => :'frequency',
        
        :'journaltypeid' => :'journaltypeid',
        
        :'journal' => :'journal',
        
        :'name' => :'name',
        
        :'query' => :'query'
        
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
        :'frequencytypeid' => :'Integer',
        :'frequency' => :'String',
        :'journaltypeid' => :'Integer',
        :'journal' => :'String',
        :'name' => :'String',
        :'query' => :'String'
        
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
      
      if attributes[:'frequencytypeid']
        self.frequencytypeid = attributes[:'frequencytypeid']
      end
      
      if attributes[:'frequency']
        self.frequency = attributes[:'frequency']
      end
      
      if attributes[:'journaltypeid']
        self.journaltypeid = attributes[:'journaltypeid']
      end
      
      if attributes[:'journal']
        self.journal = attributes[:'journal']
      end
      
      if attributes[:'name']
        self.name = attributes[:'name']
      end
      
      if attributes[:'query']
        self.query = attributes[:'query']
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
          frequencytypeid == o.frequencytypeid &&
          frequency == o.frequency &&
          journaltypeid == o.journaltypeid &&
          journal == o.journal &&
          name == o.name &&
          query == o.query
    end

    # @see the `==` method
    def eql?(o)
      self == o
    end

    # Calculate hash code according to all attributes.
    def hash
      [id, nedid, source, sourcetypeid, created, lastmodified, createdby, createdbyname, lastmodifiedby, lastmodifiedbyname, typeid, type, frequencytypeid, frequency, journaltypeid, journal, name, query].hash
    end
  end
end
