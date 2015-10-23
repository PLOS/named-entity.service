module SwaggerClient
  # 
  class Relationship < BaseObject
    attr_accessor :nedidrelated, :typeid, :type, :title, :startdate, :enddate, :source, :nedid, :sourcetypeid, :id
    # attribute mapping from ruby-style variable name to JSON key
    def self.attribute_map
      {
        
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
        :'enddate' => :'enddate',
        
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
        :'nedidrelated' => :'Integer',
        :'typeid' => :'Integer',
        :'type' => :'String',
        :'title' => :'String',
        :'startdate' => :'DateTime',
        :'enddate' => :'DateTime',
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
