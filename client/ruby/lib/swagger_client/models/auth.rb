module SwaggerClient
  # 
  class Auth < BaseObject
    attr_accessor :email, :emailid, :authid, :password, :passwordreset, :verificationtoken, :verified, :isactive, :source, :nedid, :sourcetypeid, :id
    # attribute mapping from ruby-style variable name to JSON key
    def self.attribute_map
      {
        
        # 
        :'email' => :'email',
        
        # 
        :'emailid' => :'emailid',
        
        # 
        :'authid' => :'authid',
        
        # 
        :'password' => :'password',
        
        # 
        :'passwordreset' => :'passwordreset',
        
        # 
        :'verificationtoken' => :'verificationtoken',
        
        # 
        :'verified' => :'verified',
        
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
        :'email' => :'String',
        :'emailid' => :'Integer',
        :'authid' => :'String',
        :'password' => :'String',
        :'passwordreset' => :'String',
        :'verificationtoken' => :'String',
        :'verified' => :'String',
        :'isactive' => :'String',
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

      
      if attributes[:'email']
        self.email = attributes[:'email']
      end
      
      if attributes[:'emailid']
        self.emailid = attributes[:'emailid']
      end
      
      if attributes[:'authid']
        self.authid = attributes[:'authid']
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
