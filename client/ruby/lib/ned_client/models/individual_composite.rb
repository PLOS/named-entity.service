module NedClient
  class IndividualComposite < BaseObject
    attr_accessor :auth

    attr_accessor :individualprofiles

    attr_accessor :groups

    attr_accessor :addresses

    attr_accessor :emails

    attr_accessor :phonenumbers

    attr_accessor :uniqueidentifiers

    attr_accessor :degrees

    attr_accessor :urls

    attr_accessor :relationships

    attr_accessor :type_name

    # Attribute mapping from ruby-style variable name to JSON key.
    def self.attribute_map
      {
        
        :'auth' => :'auth',
        
        :'individualprofiles' => :'individualprofiles',
        
        :'groups' => :'groups',
        
        :'addresses' => :'addresses',
        
        :'emails' => :'emails',
        
        :'phonenumbers' => :'phonenumbers',
        
        :'uniqueidentifiers' => :'uniqueidentifiers',
        
        :'degrees' => :'degrees',
        
        :'urls' => :'urls',
        
        :'relationships' => :'relationships',
        
        :'type_name' => :'typeName'
        
      }
    end

    # Attribute type mapping.
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
      return unless attributes.is_a?(Hash)

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

    # Custom attribute writer method checking allowed values (enum).
    def type_name=(type_name)
      allowed_values = ["INDIVIDUAL", "ORGANIZATION", "INVALID_NAMEDPARTY_TYPE"]
      if type_name && !allowed_values.include?(type_name)
        fail "invalid value for 'type_name', must be one of #{allowed_values}"
      end
      @type_name = type_name
    end

    # Check equality by comparing each attribute.
    def ==(o)
      return true if self.equal?(o)
      self.class == o.class &&
          auth == o.auth &&
          individualprofiles == o.individualprofiles &&
          groups == o.groups &&
          addresses == o.addresses &&
          emails == o.emails &&
          phonenumbers == o.phonenumbers &&
          uniqueidentifiers == o.uniqueidentifiers &&
          degrees == o.degrees &&
          urls == o.urls &&
          relationships == o.relationships &&
          type_name == o.type_name
    end

    # @see the `==` method
    def eql?(o)
      self == o
    end

    # Calculate hash code according to all attributes.
    def hash
      [auth, individualprofiles, groups, addresses, emails, phonenumbers, uniqueidentifiers, degrees, urls, relationships, type_name].hash
    end
  end
end
