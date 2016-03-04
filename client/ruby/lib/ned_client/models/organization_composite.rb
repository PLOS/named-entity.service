require 'date'

module NedClient
  class OrganizationComposite
    attr_accessor :addresses

    attr_accessor :emails

    attr_accessor :phonenumbers

    attr_accessor :uniqueidentifiers

    attr_accessor :created

    attr_accessor :type

    attr_accessor :source

    attr_accessor :nedid

    attr_accessor :typeid

    attr_accessor :sourcetypeid

    attr_accessor :isactive

    attr_accessor :lastmodified

    attr_accessor :familiarname

    attr_accessor :legalname

    attr_accessor :maincontactid

    attr_accessor :type_name

    # Attribute mapping from ruby-style variable name to JSON key.
    def self.attribute_map
      {
        
        :'addresses' => :'addresses',
        
        :'emails' => :'emails',
        
        :'phonenumbers' => :'phonenumbers',
        
        :'uniqueidentifiers' => :'uniqueidentifiers',
        
        :'created' => :'created',
        
        :'type' => :'type',
        
        :'source' => :'source',
        
        :'nedid' => :'nedid',
        
        :'typeid' => :'typeid',
        
        :'sourcetypeid' => :'sourcetypeid',
        
        :'isactive' => :'isactive',
        
        :'lastmodified' => :'lastmodified',
        
        :'familiarname' => :'familiarname',
        
        :'legalname' => :'legalname',
        
        :'maincontactid' => :'maincontactid',
        
        :'type_name' => :'typeName'
        
      }
    end

    # Attribute type mapping.
    def self.swagger_types
      {
        :'addresses' => :'Array<Address>',
        :'emails' => :'Array<Email>',
        :'phonenumbers' => :'Array<Phonenumber>',
        :'uniqueidentifiers' => :'Array<Uniqueidentifier>',
        :'created' => :'DateTime',
        :'type' => :'String',
        :'source' => :'String',
        :'nedid' => :'Integer',
        :'typeid' => :'Integer',
        :'sourcetypeid' => :'Integer',
        :'isactive' => :'BOOLEAN',
        :'lastmodified' => :'DateTime',
        :'familiarname' => :'String',
        :'legalname' => :'String',
        :'maincontactid' => :'Integer',
        :'type_name' => :'String'
        
      }
    end

    def initialize(attributes = {})
      return unless attributes.is_a?(Hash)

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
      
      if attributes[:'created']
        self.created = attributes[:'created']
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
      
      if attributes[:'typeid']
        self.typeid = attributes[:'typeid']
      end
      
      if attributes[:'sourcetypeid']
        self.sourcetypeid = attributes[:'sourcetypeid']
      end
      
      if attributes[:'isactive']
        self.isactive = attributes[:'isactive']
      else
        self.isactive = false
      end
      
      if attributes[:'lastmodified']
        self.lastmodified = attributes[:'lastmodified']
      end
      
      if attributes[:'familiarname']
        self.familiarname = attributes[:'familiarname']
      end
      
      if attributes[:'legalname']
        self.legalname = attributes[:'legalname']
      end
      
      if attributes[:'maincontactid']
        self.maincontactid = attributes[:'maincontactid']
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
          addresses == o.addresses &&
          emails == o.emails &&
          phonenumbers == o.phonenumbers &&
          uniqueidentifiers == o.uniqueidentifiers &&
          created == o.created &&
          type == o.type &&
          source == o.source &&
          nedid == o.nedid &&
          typeid == o.typeid &&
          sourcetypeid == o.sourcetypeid &&
          isactive == o.isactive &&
          lastmodified == o.lastmodified &&
          familiarname == o.familiarname &&
          legalname == o.legalname &&
          maincontactid == o.maincontactid &&
          type_name == o.type_name
    end

    # @see the `==` method
    def eql?(o)
      self == o
    end

    # Calculate hash code according to all attributes.
    def hash
      [addresses, emails, phonenumbers, uniqueidentifiers, created, type, source, nedid, typeid, sourcetypeid, isactive, lastmodified, familiarname, legalname, maincontactid, type_name].hash
    end

    # build the object from hash
    def build_from_hash(attributes)
      return nil unless attributes.is_a?(Hash)
      self.class.swagger_types.each_pair do |key, type|
        if type =~ /^Array<(.*)>/i
          if attributes[self.class.attribute_map[key]].is_a?(Array)
            self.send("#{key}=", attributes[self.class.attribute_map[key]].map{ |v| _deserialize($1, v) } )
          else
            #TODO show warning in debug mode
          end
        elsif !attributes[self.class.attribute_map[key]].nil?
          self.send("#{key}=", _deserialize(type, attributes[self.class.attribute_map[key]]))
        else
          # data not found in attributes(hash), not an issue as the data can be optional
        end
      end

      self
    end

    def _deserialize(type, value)
      case type.to_sym
      when :DateTime
        DateTime.parse(value)
      when :Date
        Date.parse(value)
      when :String
        value.to_s
      when :Integer
        value.to_i
      when :Float
        value.to_f
      when :BOOLEAN
        if value =~ /^(true|t|yes|y|1)$/i
          true
        else
          false
        end
      when /\AArray<(?<inner_type>.+)>\z/
        inner_type = Regexp.last_match[:inner_type]
        value.map { |v| _deserialize(inner_type, v) }
      when /\AHash<(?<k_type>.+), (?<v_type>.+)>\z/
        k_type = Regexp.last_match[:k_type]
        v_type = Regexp.last_match[:v_type]
        {}.tap do |hash|
          value.each do |k, v|
            hash[_deserialize(k_type, k)] = _deserialize(v_type, v)
          end
        end
      else # model
        _model = NedClient.const_get(type).new
        _model.build_from_hash(value)
      end
    end

    def to_s
      to_hash.to_s
    end

    # to_body is an alias to to_body (backward compatibility))
    def to_body
      to_hash
    end

    # return the object in the form of hash
    def to_hash
      hash = {}
      self.class.attribute_map.each_pair do |attr, param|
        value = self.send(attr)
        next if value.nil?
        hash[param] = _to_hash(value)
      end
      hash
    end

    # Method to output non-array value in the form of hash
    # For object, use to_hash. Otherwise, just return the value
    def _to_hash(value)
      if value.is_a?(Array)
        value.compact.map{ |v| _to_hash(v) }
      elsif value.is_a?(Hash)
        {}.tap do |hash|
          value.each { |k, v| hash[k] = _to_hash(v) }
        end
      elsif value.respond_to? :to_hash
        value.to_hash
      else
        value
      end
    end

  end
end
