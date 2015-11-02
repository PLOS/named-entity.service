package org.plos.namedentity.service;

import org.ambraproject.models.UserProfile;
import org.ambraproject.service.user.DuplicateUserException;
import org.ambraproject.service.user.NoSuchUserException;
import org.ambraproject.service.user.UserRegistrationService;
import org.ambraproject.service.user.UserService;
import org.plos.namedentity.api.IndividualComposite;
import org.plos.namedentity.api.NedException;
import org.plos.namedentity.api.entity.Address;
import org.plos.namedentity.api.entity.Auth;
import org.plos.namedentity.api.entity.Email;
import org.plos.namedentity.api.entity.Entity;
import org.plos.namedentity.api.entity.Individualprofile;
import org.plos.namedentity.api.entity.Uniqueidentifier;
import org.plos.namedentity.api.enums.UidTypeEnum;
import org.plos.namedentity.persist.NamedEntityDBService;

import javax.inject.Inject;
import java.util.NoSuchElementException;

import static org.plos.namedentity.api.NedException.ErrorType.DatabaseError;

public class AmbraServiceImpl implements AmbraService {

  @Inject
  private UserRegistrationService userRegistrationService;

  @Inject
  private NamedEntityDBService namedEntityDBService;

  @Inject
  private UserService userService;

//  @Inject
//  private AdminRolesService rolesService;


//  private static Map<String, String> ambraRoles = new HashMap<String, String>() {{
//    put("NED Admin", "Admin");
//    put("NED Manage Users", "Manage Users");
//
//    put("Knowledge Base - PLOSONE", "AE-PLOSONE");
//    put("Knowledge Base - Pathogens", "Editor-Pathog");
//    put("Knowledge Base - Neglected Tropical Diseases", "Editor-NTDs");
//    put("Knowledge Base - Medicine", "Editor-MED");
//    put("Knowledge Base - Genetics", "Editor-Gen");
//    put("Knowledge Base - Computational Biology", "Editor-CB");
//    put("Knowledge Base - Biology", "Editor-BIO");
//  }};

  @Override
  public Long createUser(IndividualComposite composite) {

    UserProfile ambraProfile = toAmbraProfile(composite);

    ambraProfile.setAuthId(composite.getAuth().get(0).getAuthid());

    try {
      return userRegistrationService.registerUser(ambraProfile, ambraProfile.getPassword());
    } catch (DuplicateUserException e) {
      throw new NedException(DatabaseError, "Duplicate user in Ambra Database", e);
    }
  }

  @Override
  public <S extends Entity> void update(S entity) {

    String cname = entity.getClass().getCanonicalName();

    if (cname.equals(Individualprofile.class.getCanonicalName()))
      update((Individualprofile) entity, entity.getNedid());
    else if (cname.equals(Address.class.getCanonicalName()))
      update((Address) entity, entity.getNedid());
    else if (cname.equals(Email.class.getCanonicalName()))
      update((Email) entity, entity.getNedid());
    // else, its not something in Ambra, so nothing to update

  }

  private void update(Address address, int nedId) {
    UserProfile ambraProfile = getAmbraProfile(nedId);
    copyToAmbraPojo(address, ambraProfile);
    updateInAmbra(ambraProfile);
  }

  private void update(Email email, int nedId) {
    UserProfile ambraProfile = getAmbraProfile(nedId);
    copyToAmbraPojo(email, ambraProfile);
    updateInAmbra(ambraProfile);
  }

  private void update(Individualprofile profile, int nedId) {
    UserProfile ambraProfile = getAmbraProfile(nedId);
    copyToAmbraPojo(profile, ambraProfile);
    updateInAmbra(ambraProfile);
  }

  @Override
  public void updatePassword(String plaintext, int nedId) {
    userRegistrationService.resetPassword(getEmailAddress(nedId), plaintext);
  }

  private UserProfile getAmbraProfile(int nedId) {
    UserProfile ambraProfile = userService.getUser(getAmbraId(nedId));
    ambraProfile.setAuthId(getAuthId(nedId));
    return ambraProfile;
  }

  private void updateInAmbra(UserProfile profile) {

    try {
      userService.updateProfile(profile);
    } catch (NoSuchUserException e) {
      throw new NedException(DatabaseError, "Ambra user does not exist");
    }
  }

  private String getEmailAddress(int nedId) {
    try {
      return namedEntityDBService.findResolvedEntities(nedId, Email.class)
          .stream()
          .findFirst()
          .get().getEmailaddress();
    } catch (NoSuchElementException e) {
      throw new NedException(DatabaseError, "Email address not found in NED");
    }
  }

  private Long getAmbraId(int nedId) {

    try {
      return Long.parseLong(namedEntityDBService.findResolvedEntities(nedId, Uniqueidentifier.class)
          .stream()
          .filter(u -> u.getType().equals(UidTypeEnum.AMBRA.getName()))
          .findFirst()
          .get().getUniqueidentifier());
    } catch (NoSuchElementException e) {
      throw new NedException(DatabaseError, "Ambra ID not found in NED");
    }
  }

  private String getAuthId(int nedId) {
    try {
      return namedEntityDBService.findResolvedEntities(nedId, Auth.class)
          .stream()
          .findFirst()
          .get()
          .getAuthid();

    } catch (NoSuchElementException e) {
      throw new NedException(DatabaseError, "Unable to find authId");
    }
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

  private void copyToAmbraPojo(Individualprofile nedUser, UserProfile ambraUser) {

    String realName = nedUser.getFirstname();

    if (realName == null)
      realName = nedUser.getLastname();
    else if (nedUser.getLastname() != null)
      realName += " " + nedUser.getLastname();

    ambraUser.setDisplayName(nedUser.getDisplayname());
    ambraUser.setGivenNames(nedUser.getFirstname());
    ambraUser.setSurname(nedUser.getLastname());
    ambraUser.setRealName(realName);
    ambraUser.setBiography(nedUser.getBiography());
    ambraUser.setTitle(nedUser.getNameprefix());
  }

  private void copyToAmbraPojo(Address address, UserProfile ambraUser) {
    ambraUser.setCity(address.getCity());
    ambraUser.setCountry(address.getCountrycodetype());
    ambraUser.setPostalAddress(address.getAddressline1() + "\n" +
                               address.getAddressline2() + "\n" +
                               address.getAddressline3());
  }

  private void copyToAmbraPojo(Email email, UserProfile ambraUser) {
    ambraUser.setEmail(email.getEmailaddress());
  }

  private UserProfile toAmbraProfile(IndividualComposite composite) {

    UserProfile result = new UserProfile();

    copyToAmbraPojo(composite.getIndividualprofiles().get(0), result);
    copyToAmbraPojo(composite.getEmails().get(0), result);

    if (composite.getAddresses() != null && composite.getAddresses().size() > 0)
      copyToAmbraPojo(composite.getAddresses().get(0), result);

    if (composite.getUrls() != null && composite.getUrls().size() > 0)
      result.setHomePage(composite.getUrls().get(0).getUrl());

    result.setPassword(composite.getAuth().get(0).getPlainTextPassword());

    // TODO: organization

    return result;
  }

  public UserRegistrationService getUserRegistrationService() {
    return userRegistrationService;
  }

  public void setUserRegistrationService(UserRegistrationService userRegistrationService) {
    this.userRegistrationService = userRegistrationService;
  }

  public NamedEntityDBService getNamedEntityDBService() {
    return namedEntityDBService;
  }

  public void setNamedEntityDBService(NamedEntityDBService namedEntityDBService) {
    this.namedEntityDBService = namedEntityDBService;
  }

  public UserService getUserService() {
    return userService;
  }

  public void setUserService(UserService userService) {
    this.userService = userService;
  }
}
