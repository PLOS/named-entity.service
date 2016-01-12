module NedClient
  # 
  class Relationship < BaseObject
    attr_accessor :id, :nedid, :source, :sourcetypeid, :created, :lastmodified, :createdby, :createdbyname, :lastmodifiedby, :lastmodifiedbyname, :nedidrelated, :typeid, :type, :title, :startdate, :enddate
    # attribute mapping from ruby-style variable name to JSON key
    def self.attribute_map
      {
        
        # 
        :'id' => :'id',
        
        # 
        :'nedid' => :'nedid',
        
        # 
        :'source' => :'source',
        
        # 
        :'sourcetypeid' => :'sourcetypeid',
        
        # 
        :'created' => :'created',
        
        # 
        :'lastmodified' => :'lastmodified',
        
        # 
        :'createdby' => :'createdby',
        
        # 
        :'createdbyname' => :'createdbyname',
        
        # 
        :'lastmodifiedby' => :'lastmodifiedby',
        
        # 
        :'lastmodifiedbyname' => :'lastmodifiedbyname',
        
        # 
        :'nedidrelated' => :'nedidrelated',
        
        # 
        :'typeid' => :'typeid',
        
        # 
        :'type' => :'type',
        
        # 
        :'title' => :'title',
        
        # 
        :'startdate' => :'startdate',
        
        # 
        :'enddate' => :'enddate'
        
      }
    end

    # attribute type
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
        :'nedidrelated' => :'Integer',
        :'typeid' => :'Integer',
        :'type' => :'String',
        :'title' => :'String',
        :'startdate' => :'DateTime',
        :'enddate' => :'DateTime'
        
      }
    end

    def initialize(attributes = {})
      return if !attributes.is_a?(Hash) || attributes.empty?

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
      
      if attributes[:'nedidrelated']
        self.nedidrelated = attributes[:'nedidrelated']
      end
      
      if attributes[:'typeid']
        self.typeid = attributes[:'typeid']
      end
      
      if attributes[:'type']
        self.type = attributes[:'type']
      end
      
      if attributes[:'title']
        self.title = attributes[:'title']
      end
      
      if attributes[:'startdate']
        self.startdate = attributes[:'startdate']
      end
      
      if attributes[:'enddate']
        self.enddate = attributes[:'enddate']
      end
      
    end

  end
end
