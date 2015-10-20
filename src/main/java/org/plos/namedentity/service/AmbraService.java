package org.plos.namedentity.service;


import org.ambraproject.admin.service.AdminRolesService;
import org.ambraproject.models.UserProfile;
import org.ambraproject.service.user.DuplicateUserException;
import org.ambraproject.service.user.UserRegistrationService;
import org.ambraproject.service.user.UserService;
import org.plos.namedentity.api.IndividualComposite;
import org.plos.namedentity.api.NedException;
import org.plos.namedentity.api.entity.Individualprofile;
import org.plos.namedentity.persist.NamedEntityDBService;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

import static org.plos.namedentity.api.NedException.ErrorType.DatabaseError;

public class AmbraService {

  @Inject
  private UserRegistrationService userRegistrationService;

  @Inject
  private AdminRolesService rolesService;

  @Inject
  private NamedEntityDBService namedEntityDBService;

  @Inject
  private UserService userService;


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

  public void updateProfile(Individualprofile profile, Long ambraId) {

    UserProfile ambraProfile = userService.getUser(ambraId);





  }

//  // this method is probably not needed
//  public void addRole(Group group, int nedId) {
//
//    // use the nedid to find the ambraid
//    Uniqueidentifier uniqueidentifier = new Uniqueidentifier();
//
//    Integer individualUidTypeId = namedEntityDBService.findTypeClass("UID Individual Types");
//    Integer ambraTypeId = namedEntityDBService.findTypeValue(individualUidTypeId, UidTypeEnum.AMBRA.getName());
//
//    uniqueidentifier.setTypeid(ambraTypeId);
//    uniqueidentifier.setNedid(nedId);
//
//    Long ambraId = Long.parseLong(namedEntityDBService.findByAttribute(uniqueidentifier).get(0).getUniqueidentifier());
//
//    List<UserRoleView> possibleRoles = rolesService.getAllRoles(ambraId);
//
//    String ambraRole = ambraRoles.get(group.getType());
//
//    try {
//      Long roleId = possibleRoles.stream()
//          .filter(r -> r.getRoleName().equals(ambraRole))
//          .findFirst().get().getID();
//
//      rolesService.grantRole(ambraId, roleId);
//    } catch (NoSuchElementException e) {
//
//      throw new NedException(DatabaseError, "Specified role does not exist in Ambra");
//    }
//  }


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
