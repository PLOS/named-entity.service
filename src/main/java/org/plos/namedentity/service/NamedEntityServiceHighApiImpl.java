package org.plos.namedentity.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.plos.namedentity.api.IndividualComposite;
import org.plos.namedentity.api.NedValidationException;
import org.plos.namedentity.api.dto.AddressDTO;
import org.plos.namedentity.api.dto.EmailDTO;
import org.plos.namedentity.api.dto.IndividualDTO;
import org.plos.namedentity.api.dto.PhonenumberDTO;
import org.plos.namedentity.api.dto.RoleDTO;
import org.plos.namedentity.api.entity.AddressEntity;
import org.plos.namedentity.api.entity.EmailEntity;
import org.plos.namedentity.api.entity.GlobaltypeEntity;
import org.plos.namedentity.api.entity.IndividualEntity;
import org.plos.namedentity.api.entity.PhonenumberEntity;
import org.plos.namedentity.api.entity.RoleEntity;
import org.plos.namedentity.api.entity.TypedescriptionEntity;
import org.plos.namedentity.persist.NamedEntityDBService;
import org.plos.namedentity.persist.NamedEntityQueries;
import org.springframework.transaction.annotation.Transactional;

public class NamedEntityServiceHighApiImpl implements NamedEntityServiceHighApi {

    @Inject private NamedEntityDBService nedDBSvc; 

    @Override @Transactional
    public IndividualDTO createIndividual(IndividualComposite composite) {
        //TODO - better validation. handle null fields!

        Integer nedId = nedDBSvc.newNamedEntityId("Individual");

        /* ------------------------------------------------------------------ */
        /*  INDIVIDUAL                                                        */
        /* ------------------------------------------------------------------ */

		Integer prefixTypeClassId      = findTypeClassStartWith("Named Party Prefixes");
		Integer suffixTypeClassId      = findTypeClassStartWith("Named Party Suffixes");
		Integer langTypeClassId        = findTypeClassStartWith("Languages");
		Integer commMethodsTypeClassId = findTypeClassStartWith("Communication Methods");

        Integer prefixTypeId     = findTypeValueByName(prefixTypeClassId, composite.getNameprefix());
        Integer suffixTypeId     = findTypeValueByName(suffixTypeClassId, composite.getNamesuffix());
        Integer langTypeId       = findTypeValueByName(langTypeClassId, composite.getPreferredlanguage());
        Integer commMethodTypeId = findTypeValueByName(commMethodsTypeClassId, composite.getPreferredcommunication());

        IndividualEntity individual = new IndividualEntity();
        individual.setNamedentityid(nedId);
        individual.setFirstname(composite.getFirstname());
        individual.setMiddlename(composite.getMiddlename());
        individual.setLastname(composite.getLastname());

        individual.setNameprefixtypeid(prefixTypeId);
        individual.setNamesuffixtypeid(suffixTypeId);
        individual.setPreferredlanguagetypeid(langTypeId);
        individual.setPreferredcommunicationmethodtypeid(commMethodTypeId);

        nedDBSvc.create( individual );

        /* ------------------------------------------------------------------ */
        /*  ADDRESSES                                                         */
        /* ------------------------------------------------------------------ */

		Integer addressTypeClassId   = findTypeClassStartWith("Physical Address Types");
		Integer countryTypeClassId   = findTypeClassStartWith("Country Types");
		Integer stateCodeTypeClassId = findTypeClassStartWith("State and Province Codes");

        //TODO - move to transformer
        if (composite.getAddresses() != null) {
            for (AddressDTO address : composite.getAddresses()) {
                Integer addressTypeId   = findTypeValueByName(addressTypeClassId, address.getAddresstype());
                Integer countryTypeId   = findTypeValueByName(countryTypeClassId, address.getCountrycodetype());
                Integer stateCodeTypeId = findTypeValueByName(stateCodeTypeClassId, address.getStatecodetype());

                AddressEntity addressEntity = new AddressEntity();
                addressEntity.setNamedentityid(nedId);
                addressEntity.setAddresstypeid(addressTypeId);
                addressEntity.setAddressline1(address.getAddressline1());
                addressEntity.setAddressline2(address.getAddressline2());
                addressEntity.setAddressline3(address.getAddressline3());
                addressEntity.setCity(address.getCity());
                addressEntity.setStatecodetypeid(stateCodeTypeId);
                addressEntity.setCountrycodetypeid(countryTypeId);
                addressEntity.setPostalcode(address.getPostalcode());
                addressEntity.setIsprimary(address.getIsprimary()? (byte)1 : (byte)0);

                nedDBSvc.create( addressEntity );
            }
        }

        /* ------------------------------------------------------------------ */
        /*  PHONE NUMBERS                                                     */
        /* ------------------------------------------------------------------ */

		Integer phoneTypeClassId       = findTypeClassStartWith("Telephone Number Types");
		Integer countryCodeTypeClassId = findTypeClassStartWith("Country Codes for Phone Numbers");

        if (composite.getPhonenumbers() != null) {
            for (PhonenumberDTO phonenumber : composite.getPhonenumbers()) {
                Integer phoneTypeId       = findTypeValueByName(phoneTypeClassId, phonenumber.getPhonenumbertype());
                Integer countryCodeTypeId = findTypeValueByName(countryCodeTypeClassId, phonenumber.getCountrycodetype());

                PhonenumberEntity phoneEntity = new PhonenumberEntity();
                phoneEntity.setNamedentityid(nedId);
                phoneEntity.setPhonenumbertypeid(phoneTypeId);
                phoneEntity.setCountrycodetypeid(countryCodeTypeId);
                phoneEntity.setPhonenumber(phonenumber.getPhonenumber());
                phoneEntity.setExtension(phonenumber.getExtension());
                phoneEntity.setIsprimary(phonenumber.getIsprimary()? (byte)1 : (byte)0);

                nedDBSvc.create( phoneEntity );
            }
        }

        /* ------------------------------------------------------------------ */
        /*  EMAILS                                                            */
        /* ------------------------------------------------------------------ */

		Integer emailTypeClassId = findTypeClassStartWith("Email Address Types");

        if (composite.getEmails() != null) {
            for (EmailDTO email : composite.getEmails()) {
                Integer emailTypeId = findTypeValueByName(emailTypeClassId, email.getEmailtype());

                EmailEntity emailEntity = new EmailEntity();
                emailEntity.setNamedentityid(nedId);
                emailEntity.setEmailtypeid(emailTypeId);
                emailEntity.setEmailaddress(email.getEmailaddress());
                emailEntity.setIsprimary(email.getIsprimary()? (byte)1 : (byte)0);

                nedDBSvc.create( emailEntity );
            }
        }

        /* ------------------------------------------------------------------ */
        /*  ROLE                                                              */
        /* ------------------------------------------------------------------ */

		Integer srcAppTypeClassId = findTypeClassStartWith("Source Applications");
		Integer roleTypeClassId   = findTypeClassStartWith("Roles");

        RoleDTO role = composite.getRole();
        if (role != null) {
            Integer srcAppTypeId = findTypeValueByName(srcAppTypeClassId, "Editorial Manager");
            Integer roleTypeId   = findTypeValueByName(roleTypeClassId, role.getRoletype());

            RoleEntity roleEntity = new RoleEntity();
            roleEntity.setNamedentityid(nedId);
            roleEntity.setSourceapplicationtypeid(srcAppTypeId);
            roleEntity.setRoletypeid(roleTypeId);
            roleEntity.setStartdate(new Timestamp(new Date().getTime()));

            nedDBSvc.create( roleEntity );
        }

        return ((NamedEntityQueries)nedDBSvc).findIndividualByNedId(nedId);
        //return nedDBSvc.findById(nedId, IndividualEntity.class);
    }

    @Override
    public IndividualDTO findIndividualByNedId(Integer nedId) {
        return ((NamedEntityQueries)nedDBSvc).findIndividualByNedId(nedId);
    }

    @Override
    public List<AddressDTO> findAddressesByNedId(Integer nedId) {
        return ((NamedEntityQueries)nedDBSvc).findAddressesByNedId(nedId);
    }

    @Override
    public List<EmailDTO> findEmailsByNedId(Integer nedId) {
        return ((NamedEntityQueries)nedDBSvc).findEmailsByNedId(nedId);
    }

    @Override
    public List<PhonenumberDTO> findPhoneNumbersByNedId(Integer nedId) {
        return ((NamedEntityQueries)nedDBSvc).findPhoneNumbersByNedId(nedId);
    }

    @Override
    public List<RoleDTO> findRolesByNedId(Integer nedId) {
        return ((NamedEntityQueries)nedDBSvc).findRolesByNedId(nedId);
    }
    
    public NamedEntityDBService getNamedEntityDBService() {
        return nedDBSvc;
    }
    
    public void setNamedEntityDBService(NamedEntityDBService nedDBSvc) {
        this.nedDBSvc = nedDBSvc;
    }

    //TODO - cache type classes and values.
   
	private Integer findTypeClassStartWith(String prefix) {
        for(TypedescriptionEntity typeClass : nedDBSvc.findAll(TypedescriptionEntity.class)) {
			if (typeClass.getDescription().startsWith(prefix)) {
				return typeClass.getTypeid();
			}
		}
		throw new NedValidationException("No type class found which begins with " + prefix);
	}

	private Integer findTypeValueByName(Integer typeClassId, String name) {
        for(GlobaltypeEntity typeValue : nedDBSvc.findAll(GlobaltypeEntity.class)) {
			if (typeClassId.equals(typeValue.getTypeid()) &&
				typeValue.getShortdescription().equalsIgnoreCase(name))
			{
				return typeValue.getGlobaltypeid();
			}
		}
		throw new NedValidationException("No type value found with short description =  " + name);
	}
}
