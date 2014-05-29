package org.plos.namedentity.spring.config;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.mockito.Mockito;
import org.plos.namedentity.api.IndividualComposite;
import org.plos.namedentity.api.NedValidationException;
import org.plos.namedentity.api.dto.AddressDTO;
import org.plos.namedentity.api.dto.EmailDTO;
import org.plos.namedentity.api.dto.IndividualDTO;
import org.plos.namedentity.api.dto.PhonenumberDTO;
import org.plos.namedentity.api.dto.RoleDTO;
import org.plos.namedentity.api.entity.GlobaltypeEntity;
import org.plos.namedentity.api.entity.IndividualEntity;
import org.plos.namedentity.api.entity.TypedescriptionEntity;
import org.plos.namedentity.rest.NamedEntityResource;
import org.plos.namedentity.service.NamedEntityService;
import org.plos.namedentity.service.NamedEntityServiceHighApi;
import org.plos.namedentity.utils.EntityPojoTransformer;
import org.plos.namedentity.utils.Transformer;
import org.springframework.context.annotation.Bean;

public class TestSpringConfig {

    @Bean @SuppressWarnings("unchecked")
    static public NamedEntityService namedEntityService() {
        NamedEntityService mockNamedEntityService =  Mockito.mock(NamedEntityService.class);

        when(mockNamedEntityService.create(any()))
            .thenReturn(Integer.valueOf(1))
                .thenThrow(NedValidationException.class)
                    .thenThrow(RuntimeException.class);

        when(mockNamedEntityService.update(any()))
            .thenReturn(true)
                .thenThrow(NedValidationException.class)
                    .thenThrow(RuntimeException.class);

        when(mockNamedEntityService.delete(any()))
            .thenReturn(true)
                .thenThrow(RuntimeException.class);

        // TYPE DESCRIPTIONS (TYPE CLASSES)

        when(mockNamedEntityService.findById(eq(1), eq(TypedescriptionEntity.class)))
            .thenReturn(new TypedescriptionEntity(1, "New Type Description", "New Type Usage"));

        List<TypedescriptionEntity> typeClassList = new ArrayList<>();
        typeClassList.add(new TypedescriptionEntity(1, "Type Description1", "Type Usage1"));
        typeClassList.add(new TypedescriptionEntity(2, "Type Description2", "Type Usage2"));
        typeClassList.add(new TypedescriptionEntity(3, "Type Description3", "Type Usage3"));

        when(mockNamedEntityService.findAll(eq(TypedescriptionEntity.class))).thenReturn(typeClassList);

        // TYPE VALUES (GLOBAL TYPES)

        GlobaltypeEntity typeVal = new GlobaltypeEntity();
        typeVal.setGlobaltypeid(1);
        typeVal.setTypeid(1);
        typeVal.setShortdescription("Type Value #1 Short Description");
        typeVal.setTypecode("TV1");

        when(mockNamedEntityService.findById(eq(1), eq(GlobaltypeEntity.class))).thenReturn(typeVal);

        List<GlobaltypeEntity> typeValuesForTypeClass = new ArrayList<>();
        for (int i = 1; i <=5; i++) {
            typeValuesForTypeClass.add(new GlobaltypeEntity(
                i, 1, "shortdesc"+i, "longdesc"+i, "typ"+i, null, null, null, null));
        }

        when(mockNamedEntityService.findByAttribute(isA(GlobaltypeEntity.class))).thenReturn(typeValuesForTypeClass);

        // INDIVIDUALS 

        when(mockNamedEntityService.findAll(eq(IndividualEntity.class))).thenReturn( newIndividualEntities() );

        return mockNamedEntityService;
    }

    @Bean @SuppressWarnings("unchecked")
    static public NamedEntityServiceHighApi namedEntityServiceHighApi() {
        NamedEntityServiceHighApi mockNamedEntityServiceHighApi =  Mockito.mock(NamedEntityServiceHighApi.class);

        IndividualDTO individualDto = newIndividualDto();

        when(mockNamedEntityServiceHighApi.createIndividual(isA(IndividualComposite.class)))
            .thenReturn(individualDto)
                .thenThrow(NedValidationException.class)
                    .thenThrow(RuntimeException.class);

        when(mockNamedEntityServiceHighApi.findIndividualByNedId(anyInt()))
            .thenReturn( individualDto );

        when(mockNamedEntityServiceHighApi.findAddressesByNedId(anyInt()))
            .thenReturn( newAddressesDto() );

        when(mockNamedEntityServiceHighApi.findEmailsByNedId(anyInt()))
            .thenReturn( newEmailsDto() );

        when(mockNamedEntityServiceHighApi.findPhoneNumbersByNedId(anyInt()))
            .thenReturn( newPhonenumbersDto() );

        when(mockNamedEntityServiceHighApi.findRolesByNedId(anyInt()))
            .thenReturn( newRolesDto() );

        return mockNamedEntityServiceHighApi;
    }

    @Bean 
    static public Transformer transformer() {
        return new EntityPojoTransformer();
    }

    @Bean
    static public NamedEntityResource namedEntityResource() {
        NamedEntityResource nedResource = new NamedEntityResource();
        nedResource.setNamedEntityService(namedEntityService());
        return nedResource;
    }

    static private IndividualDTO newIndividualDto() {
        IndividualDTO dto = new IndividualDTO();
        dto.setNamedentityid(1);
        dto.setFirstname("firstname");
        dto.setMiddlename("middlename");
        dto.setLastname("lastname");
        dto.setNameprefix("Mr.");
        dto.setNamesuffix("II");
        dto.setPreferredlanguage("Mandarin");
        dto.setPreferredcommunication("Phone");
        return dto;
    }

    static private List<AddressDTO> newAddressesDto() {
        List<AddressDTO> addresses = new ArrayList<>();

        AddressDTO address = new AddressDTO();
        address.setAddresstype("Office");
        address.setAddressline1("addressline1");
        address.setAddressline2("addressline2");
        address.setCity("city");
        address.setStatecodetype("CA");
        address.setCountrycodetype("United States");
        address.setPostalcode("1234567");
        address.setIsprimary(true);
        addresses.add( address );

        return addresses;
    }

    static private List<EmailDTO> newEmailsDto() {
        List<EmailDTO> emails = new ArrayList<>();

		EmailDTO workEmail = new EmailDTO();
		workEmail.setEmailtype("Work");
		workEmail.setEmailaddress("fu.manchu.work@foo.com");
		workEmail.setIsprimary(true);
		emails.add( workEmail );

		EmailDTO personalEmail = new EmailDTO();
		personalEmail.setEmailtype("Personal");
		personalEmail.setEmailaddress("fu.manchu.home@foo.com");
		personalEmail.setIsprimary(false);
		emails.add( personalEmail );

        return emails;
    }

    static private List<PhonenumberDTO> newPhonenumbersDto() {
        List<PhonenumberDTO> phonenumbers = new ArrayList<>();

        PhonenumberDTO officePhone = new PhonenumberDTO();
        officePhone.setPhonenumbertype("Office");
        officePhone.setCountrycodetype("01");
        officePhone.setPhonenumber("123-456-7890");
        officePhone.setIsprimary(true);
        phonenumbers.add( officePhone );

        PhonenumberDTO mobilePhone = new PhonenumberDTO();
        mobilePhone.setPhonenumbertype("Mobile");
        mobilePhone.setCountrycodetype("01");
        mobilePhone.setPhonenumber("123-444-0011");
        mobilePhone.setIsprimary(false);
        phonenumbers.add( mobilePhone );

        PhonenumberDTO homePhone = new PhonenumberDTO();
        homePhone.setPhonenumbertype("Home");
        homePhone.setCountrycodetype("01");
        homePhone.setPhonenumber("123-555-6666");
        homePhone.setIsprimary(false);
        phonenumbers.add( homePhone );

        return phonenumbers;
    }

    static private List<RoleDTO> newRolesDto() {
        List<RoleDTO> roles = new ArrayList<>();

        RoleDTO author = new RoleDTO();
        author.setRoletype("Author");
        author.setStartdate("2014-05-30");
        roles.add( author );

        return roles;
    }

    static private List<IndividualEntity> newIndividualEntities() {
        List<IndividualEntity> individualEntities = new ArrayList<>();

        for (int i = 1; i <=3; i++) {
            IndividualEntity individual = new IndividualEntity();
            individual.setNamedentityid(1);
            individual.setFirstname("firstname"+i);
            individual.setMiddlename("middlename"+i);
            individual.setLastname("lastname"+i);
            individual.setNameprefixtypeid(i);
            individual.setNamesuffixtypeid(i);
            individual.setPreferredlanguagetypeid(i);
            individual.setPreferredcommunicationmethodtypeid(i);
            individualEntities.add( individual );
        }
        return individualEntities;
    }
}
