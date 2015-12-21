module NedClient
  class Auth < BaseObject
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

    attr_accessor :email

    attr_accessor :emailid

    attr_accessor :authid

    attr_accessor :plain_text_password

    attr_accessor :password

    attr_accessor :passwordreset

    attr_accessor :verificationtoken

    attr_accessor :verified

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
        
        :'email' => :'email',
        
        :'emailid' => :'emailid',
        
        :'authid' => :'authid',
        
        :'plain_text_password' => :'plainTextPassword',
        
        :'password' => :'password',
        
        :'passwordreset' => :'passwordreset',
        
        :'verificationtoken' => :'verificationtoken',
        
        :'verified' => :'verified',
        
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
        :'email' => :'String',
        :'emailid' => :'Integer',
        :'authid' => :'String',
        :'plain_text_password' => :'String',
        :'password' => :'String',
        :'passwordreset' => :'BOOLEAN',
        :'verificationtoken' => :'String',
        :'verified' => :'BOOLEAN',
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
      
      if attributes[:'email']
        self.email = attributes[:'email']
      end
      
      if attributes[:'emailid']
        self.emailid = attributes[:'emailid']
      end
      
      if attributes[:'authid']
        self.authid = attributes[:'authid']
      end
      
      if attributes[:'plainTextPassword']
        self.plain_text_password = attributes[:'plainTextPassword']
      end
      
      if attributes[:'password']
        self.password = attributes[:'password']
      end
      
      if attributes[:'passwordreset']
        self.passwordreset = attributes[:'passwordreset']
      else
        self.passwordreset = false
      end
      
      if attributes[:'verificationtoken']
        self.verificationtoken = attributes[:'verificationtoken']
      end
      
      if attributes[:'verified']
        self.verified = attributes[:'verified']
      else
        self.verified = false
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
          email == o.email &&
          emailid == o.emailid &&
          authid == o.authid &&
          plain_text_password == o.plain_text_password &&
          password == o.password &&
          passwordreset == o.passwordreset &&
          verificationtoken == o.verificationtoken &&
          verified == o.verified &&
          isactive == o.isactive
    end

    # @see the `==` method
    def eql?(o)
      self == o
    end

    # Calculate hash code according to all attributes.
    def hash
      [id, nedid, source, sourcetypeid, created, lastmodified, createdby, createdbyname, lastmodifiedby, lastmodifiedbyname, email, emailid, authid, plain_text_password, password, passwordreset, verificationtoken, verified, isactive].hash
    end
  end
end
