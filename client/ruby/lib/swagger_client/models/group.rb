module SwaggerClient
  # 
  class Group < BaseObject
    attr_accessor :typeid, :type, :startdate, :enddate, :createdby, :lastmodifiedby, :applicationtype, :applicationtypeid, :source, :nedid, :sourcetypeid, :id
    # attribute mapping from ruby-style variable name to JSON key
    def self.attribute_map
      {
        
        # 
        :'typeid' => :'typeid',
        
        # 
        :'type' => :'type',
        
        # 
        :'startdate' => :'startdate',
        
        # 
        :'enddate' => :'enddate',
        
        # 
        :'createdby' => :'createdby',
        
        # 
        :'lastmodifiedby' => :'lastmodifiedby',
        
        # 
        :'applicationtype' => :'applicationtype',
        
        # 
        :'applicationtypeid' => :'applicationtypeid',
        
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
        :'startdate' => :'DateTime',
        :'enddate' => :'DateTime',
        :'createdby' => :'Integer',
        :'lastmodifiedby' => :'Integer',
        :'applicationtype' => :'String',
        :'applicationtypeid' => :'Integer',
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
      
      if attributes[:'startdate']
        self.startdate = attributes[:'startdate']
      end
      
      if attributes[:'enddate']
        self.enddate = attributes[:'enddate']
      end
      
      if attributes[:'createdby']
        self.createdby = attributes[:'createdby']
      end
      
      if attributes[:'lastmodifiedby']
        self.lastmodifiedby = attributes[:'lastmodifiedby']
      end
      
      if attributes[:'applicationtype']
        self.applicationtype = attributes[:'applicationtype']
      end
      
      if attributes[:'applicationtypeid']
        self.applicationtypeid = attributes[:'applicationtypeid']
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
