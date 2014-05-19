package org.plos.namedentity.persist;

import java.util.EnumSet;

public enum TypeClassEnum {

    COMMUNICATION_METHODS  ("Communication Methods"),
    COUNTRY_CODES          ("Country Types"),
    COUNTRY_CODE_FOR_PHONE ("Country Codes for Phone Numbers"),
    EMAIL_TYPES            ("Email Address Types"),
    NAMED_ENTITY_TYPES     ("Named Party Types"),
    NAMED_ENTITY_PREFIX    ("Named Party Prefixes"),
    PHYSICAL_ADDRESS_TYPES ("Physical Address Types"),
    ROLES                  ("Roles"),
    SOURCE_APPLICATIONS    ("Source Applications"),
    STATES_AND_PROVINCES   ("State and Province Codes"),
    TELEPHONE_TYPES        ("Telephone Number Types"),
    UNIQUE_IDENTIFIERS     ("Unique Identifier Types"),
    INVALID_TYPE_CLASS     ("");

	String name;

	private TypeClassEnum(String name) {
		this.name   = name;
	}

	// assume uom names are unique; may not be true for symbols.
    public static TypeClassEnum getTypeClassEnum(String name) {
        for( TypeClassEnum typeclass : EnumSet.allOf(TypeClassEnum.class) )
			if( typeclass.name.equals(name) )
                return typeclass;
        return INVALID_TYPE_CLASS;
    }

	public String getName()   { return name;   } 
}
