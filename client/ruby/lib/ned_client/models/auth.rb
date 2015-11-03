module NedClient
  # 
  class Auth < BaseObject
    attr_accessor :id, :nedid, :source, :sourcetypeid, :created, :lastmodified, :createdby, :createdbyname, :lastmodifiedby, :lastmodifiedbyname, :email, :emailid, :authid, :plain_text_password, :password, :passwordreset, :verificationtoken, :verified, :isactive
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
        :'email' => :'email',
        
        # 
        :'emailid' => :'emailid',
        
        # 
        :'authid' => :'authid',
        
        # 
        :'plain_text_password' => :'plainTextPassword',
        
        # 
        :'password' => :'password',
        
        # 
        :'passwordreset' => :'passwordreset',
        
        # 
        :'verificationtoken' => :'verificationtoken',
        
        # 
        :'verified' => :'verified',
        
        # 
        :'isactive' => :'isactive'
        
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
      end
      
      if attributes[:'verificationtoken']
        self.verificationtoken = attributes[:'verificationtoken']
      end
      
      if attributes[:'verified']
        self.verified = attributes[:'verified']
      end
      
      if attributes[:'isactive']
        self.isactive = attributes[:'isactive']
      end
      
    end

  end
end
