module NedClient
  # 
  class IndividualComposite < BaseObject
    attr_accessor :auth, :individualprofiles, :groups, :addresses, :emails, :phonenumbers, :uniqueidentifiers, :degrees, :urls, :relationships, :type_name
    # attribute mapping from ruby-style variable name to JSON key
    def self.attribute_map
      {
        
        # 
        :'auth' => :'auth',
        
        # 
        :'individualprofiles' => :'individualprofiles',
        
        # 
        :'groups' => :'groups',
        
        # 
        :'addresses' => :'addresses',
        
        # 
        :'emails' => :'emails',
        
        # 
        :'phonenumbers' => :'phonenumbers',
        
        # 
        :'uniqueidentifiers' => :'uniqueidentifiers',
        
        # 
        :'degrees' => :'degrees',
        
        # 
        :'urls' => :'urls',
        
        # 
        :'relationships' => :'relationships',
        
        # 
        :'type_name' => :'typeName'
        
      }
    end

    # attribute type
    def self.swagger_types
      {
        :'auth' => :'Array<Auth>',
        :'individualprofiles' => :'Array<Individualprofile>',
        :'groups' => :'Array<Group>',
        :'addresses' => :'Array<Address>',
        :'emails' => :'Array<Email>',
        :'phonenumbers' => :'Array<Phonenumber>',
        :'uniqueidentifiers' => :'Array<Uniqueidentifier>',
        :'degrees' => :'Array<Degree>',
        :'urls' => :'Array<Url>',
        :'relationships' => :'Array<Relationship>',
        :'type_name' => :'String'
        
      }
    end

    def initialize(attributes = {})
      return if !attributes.is_a?(Hash) || attributes.empty?

      # convert string to symbol for hash key
      attributes = attributes.inject({}){|memo,(k,v)| memo[k.to_sym] = v; memo}

      
      if attributes[:'auth']
        if (value = attributes[:'auth']).is_a?(Array)
          self.auth = value
        end
      end
      
      if attributes[:'individualprofiles']
        if (value = attributes[:'individualprofiles']).is_a?(Array)
          self.individualprofiles = value
        end
      end
      
      if attributes[:'groups']
        if (value = attributes[:'groups']).is_a?(Array)
          self.groups = value
        end
      end
      
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
      
      if attributes[:'degrees']
        if (value = attributes[:'degrees']).is_a?(Array)
          self.degrees = value
        end
      end
      
      if attributes[:'urls']
        if (value = attributes[:'urls']).is_a?(Array)
          self.urls = value
        end
      end
      
      if attributes[:'relationships']
        if (value = attributes[:'relationships']).is_a?(Array)
          self.relationships = value
        end
      end
      
      if attributes[:'typeName']
        self.type_name = attributes[:'typeName']
      end
      
    end

  end
end
