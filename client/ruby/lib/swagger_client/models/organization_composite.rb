module SwaggerClient
  # 
  class OrganizationComposite < BaseObject
    attr_accessor :addresses, :emails, :phonenumbers, :uniqueidentifiers, :type_name, :type, :source, :nedid, :maincontactid, :sourcetypeid, :isactive, :typeid, :familiarname, :legalname, :as_map
    # attribute mapping from ruby-style variable name to JSON key
    def self.attribute_map
      {
        
        # 
        :'addresses' => :'addresses',
        
        # 
        :'emails' => :'emails',
        
        # 
        :'phonenumbers' => :'phonenumbers',
        
        # 
        :'uniqueidentifiers' => :'uniqueidentifiers',
        
        # 
        :'type_name' => :'typeName',
        
        # 
        :'type' => :'type',
        
        # 
        :'source' => :'source',
        
        # 
        :'nedid' => :'nedid',
        
        # 
        :'maincontactid' => :'maincontactid',
        
        # 
        :'sourcetypeid' => :'sourcetypeid',
        
        # 
        :'isactive' => :'isactive',
        
        # 
        :'typeid' => :'typeid',
        
        # 
        :'familiarname' => :'familiarname',
        
        # 
        :'legalname' => :'legalname',
        
        # 
        :'as_map' => :'asMap'
        
      }
    end

    # attribute type
    def self.swagger_types
      {
        :'addresses' => :'Array<Address>',
        :'emails' => :'Array<Email>',
        :'phonenumbers' => :'Array<Phonenumber>',
        :'uniqueidentifiers' => :'Array<Uniqueidentifier>',
        :'type_name' => :'String',
        :'type' => :'String',
        :'source' => :'String',
        :'nedid' => :'Integer',
        :'maincontactid' => :'Integer',
        :'sourcetypeid' => :'Integer',
        :'isactive' => :'BOOLEAN',
        :'typeid' => :'Integer',
        :'familiarname' => :'String',
        :'legalname' => :'String',
        :'as_map' => :'Map[Class,List[? extends org.plos.namedentity.api.entity.Entity]]'
        
      }
    end

    def initialize(attributes = {})
      return if !attributes.is_a?(Hash) || attributes.empty?

      # convert string to symbol for hash key
      attributes = attributes.inject({}){|memo,(k,v)| memo[k.to_sym] = v; memo}

      
      if attributes[:'addresses']
        if (value = attributes[:'addresses']).is_a?(Array)
          self.addresses = value
        end
      end
      
      if attributes[:'emails']
        if (value = attributes[:'emails']).is_a?(Array)
          self.emails = value
        end
      end
      
      if attributes[:'phonenumbers']
        if (value = attributes[:'phonenumbers']).is_a?(Array)
          self.phonenumbers = value
        end
      end
      
      if attributes[:'uniqueidentifiers']
        if (value = attributes[:'uniqueidentifiers']).is_a?(Array)
          self.uniqueidentifiers = value
        end
      end
      
      if attributes[:'typeName']
        self.type_name = attributes[:'typeName']
      end
      
      if attributes[:'type']
        self.type = attributes[:'type']
      end
      
      if attributes[:'source']
        self.source = attributes[:'source']
      end
      
      if attributes[:'nedid']
        self.nedid = attributes[:'nedid']
      end
      
      if attributes[:'maincontactid']
        self.maincontactid = attributes[:'maincontactid']
      end
      
      if attributes[:'sourcetypeid']
        self.sourcetypeid = attributes[:'sourcetypeid']
      end
      
      if attributes[:'isactive']
        self.isactive = attributes[:'isactive']
      end
      
      if attributes[:'typeid']
        self.typeid = attributes[:'typeid']
      end
      
      if attributes[:'familiarname']
        self.familiarname = attributes[:'familiarname']
      end
      
      if attributes[:'legalname']
        self.legalname = attributes[:'legalname']
      end
      
      if attributes[:'asMap']
        self.as_map = attributes[:'asMap']
      end
      
    end

  end
end
