module SwaggerClient
  # 
  class Individualprofile < BaseObject
    attr_accessor :firstname, :middlename, :lastname, :nickname, :nameprefix, :nameprefixtypeid, :namesuffix, :namesuffixtypeid, :displayname, :biography, :isactive, :source, :nedid, :sourcetypeid, :id
    # attribute mapping from ruby-style variable name to JSON key
    def self.attribute_map
      {
        
        # 
        :'firstname' => :'firstname',
        
        # 
        :'middlename' => :'middlename',
        
        # 
        :'lastname' => :'lastname',
        
        # 
        :'nickname' => :'nickname',
        
        # 
        :'nameprefix' => :'nameprefix',
        
        # 
        :'nameprefixtypeid' => :'nameprefixtypeid',
        
        # 
        :'namesuffix' => :'namesuffix',
        
        # 
        :'namesuffixtypeid' => :'namesuffixtypeid',
        
        # 
        :'displayname' => :'displayname',
        
        # 
        :'biography' => :'biography',
        
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
