package org.plos.namedentity.service;


import org.ambraproject.admin.service.AdminRolesService;
import org.ambraproject.admin.views.UserRoleView;
import org.ambraproject.models.UserProfile;
import org.ambraproject.service.user.DuplicateUserException;
import org.ambraproject.service.user.UserRegistrationService;
import org.plos.namedentity.api.IndividualComposite;
import org.plos.namedentity.api.NedException;
import org.plos.namedentity.api.entity.Group;
import org.plos.namedentity.api.entity.Individualprofile;
import org.plos.namedentity.api.entity.Uniqueidentifier;
import org.plos.namedentity.api.enums.UidTypeEnum;
import org.plos.namedentity.persist.NamedEntityDBService;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import static org.plos.namedentity.api.NedException.ErrorType.DatabaseError;

public class AmbraService {

  @Inject
  private UserRegistrationService userRegistrationService;

  @Inject
  private AdminRolesService rolesService;

  @Inject
  private NamedEntityDBService namedEntityDBService;


  private static Map<String, String> ambraRoles = new HashMap<String, String>() {{
    put("NED Admin", "Admin");
    put("NED Manage Users", "Manage Users");

    put("Knowledge Base - PLOSONE", "AE-PLOSONE");
    put("Knowledge Base - Pathogens", "Editor-Pathog");
    put("Knowledge Base - Neglected Tropical Diseases", "Editor-NTDs");
    put("Knowledge Base - Medicine", "Editor-MED");
    put("Knowledge Base - Genetics", "Editor-Gen");
    put("Knowledge Base - Computational Biology", "Editor-CB");
    put("Knowledge Base - Biology", "Editor-BIO");
  }};

  public Long createUser(IndividualComposite composite) {

    UserProfile ambraProfile = toAmbraProfile(composite);

    ambraProfile.setAuthId(composite.getAuth().get(0).getAuthid());

    try {
      return userRegistrationService.registerUser(ambraProfile, ambraProfile.getPassword());
    } catch (DuplicateUserException e) {
      throw new NedException(DatabaseError, "Duplicte user in Ambra Database");
    }
  }

  public void addRole(Group group, int nedId) {

    // use the nedid to find the ambraid
    Uniqueidentifier uniqueidentifier = new Uniqueidentifier();

    Integer individualUidTypeId = namedEntityDBService.findTypeClass("UID Individual Types");
    Integer ambraTypeId = namedEntityDBService.findTypeValue(individualUidTypeId, UidTypeEnum.AMBRA.getName());

    uniqueidentifier.setTypeid(ambraTypeId);
    uniqueidentifier.setNedid(nedId);

    Long ambraId = Long.parseLong(namedEntityDBService.findByAttribute(uniqueidentifier).get(0).getUniqueidentifier());

//      rolesService.revokeAllRoles(ambraId);
    List<UserRoleView> possibleRoles = rolesService.getAllRoles(ambraId);

    String ambraRole = ambraRoles.get(group.getType());

    try {
      Long roleId = possibleRoles.stream()
          .filter(r -> r.getRoleName().equals(ambraRole))
          .findFirst().get().getID();

      rolesService.grantRole(ambraId, roleId);
    } catch (NoSuchElementException e) {
      // do nothing since the group does not exist in Ambra
      // TODO: or should we throw and exception?
    }
////
////        rolesService.grantRole(ambraProfile.getID(), (long)1);
  }


  private UserProfile toAmbraProfile(IndividualComposite composite) {

    Individualprofile profile = composite.getIndividualprofiles().get(0);

    UserProfile result = new UserProfile(
        composite.getEmails().get(0).getEmailaddress(),
        profile.getDisplayname(),
        composite.getAuth().get(0).getPlainTextPassword()
    );


    result.setGivenNames(profile.getFirstname());
    result.setSurname(profile.getLastname());

    String realName = profile.getFirstname();

    if (realName == null)
      realName = profile.getLastname();
    else if (profile.getLastname() != null)
      realName += "" + profile.getLastname();

    result.setRealName(realName);
    result.setBiography(profile.getBiography());
    result.setTitle(profile.getNameprefix());


    if (composite.getAddresses() != null && composite.getAddresses().size() > 0) {
      result.setCity(composite.getAddresses().get(0).getCity());
      result.setCountry(composite.getAddresses().get(0).getCountrycodetype());
      // TODO: postaladdress
    }

    if (composite.getUrls() != null && composite.getUrls().size() > 0) {
      result.setHomePage(composite.getUrls().get(0).getUrl());
    }



    // TODO: organization


    return result;
  }

}