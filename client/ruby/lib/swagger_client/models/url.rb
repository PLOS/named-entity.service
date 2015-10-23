module SwaggerClient
  # 
  class Url < BaseObject
    attr_accessor :url, :source, :nedid, :sourcetypeid, :id
    # attribute mapping from ruby-style variable name to JSON key
    def self.attribute_map
      {
        
        # 
        :'url' => :'url',
        
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
        :'url' => :'String',
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

      
      if attributes[:'url']
        self.url = attributes[:'url']
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
