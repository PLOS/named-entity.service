package org.plos.ned_client.api;

import com.sun.jersey.api.client.GenericType;

import org.plos.ned_client.ApiException;
import org.plos.ned_client.ApiClient;
import org.plos.ned_client.Configuration;
import org.plos.ned_client.Pair;

import org.plos.ned_client.model.OrganizationComposite;
import org.plos.ned_client.model.Address;
import org.plos.ned_client.model.Email;
import org.plos.ned_client.model.Phonenumber;
import org.plos.ned_client.model.Uniqueidentifier;

import java.util.*;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2016-03-04T15:04:00.420-08:00")
public class OrganizationsApi {
  private ApiClient apiClient;

  public OrganizationsApi() {
    this(Configuration.getDefaultApiClient());
  }

  public OrganizationsApi(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  public ApiClient getApiClient() {
    return apiClient;
  }

  public void setApiClient(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  
  /**
   * Find organizations matching specified attribute.
   * 
   * @param attribute 
   * @param value 
   * @return List<OrganizationComposite>
   */
  public List<OrganizationComposite> findOrganizations(String attribute, String value) throws ApiException {
    Object postBody = null;
    
    // create path and map variables
    String path = "/organizations".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    Map<String, String> headerParams = new HashMap<String, String>();
    Map<String, Object> formParams = new HashMap<String, Object>();

    
    queryParams.addAll(apiClient.parameterToPairs("", "attribute", attribute));
    
    queryParams.addAll(apiClient.parameterToPairs("", "value", value));
    

    

    

    final String[] accepts = {
      "application/json"
    };
    final String accept = apiClient.selectHeaderAccept(accepts);

    final String[] contentTypes = {
      
    };
    final String contentType = apiClient.selectHeaderContentType(contentTypes);

    String[] authNames = new String[] {  };

    
    GenericType<List<OrganizationComposite>> returnType = new GenericType<List<OrganizationComposite>>() {};
    return apiClient.invokeAPI(path, "GET", queryParams, postBody, headerParams, formParams, accept, contentType, authNames, returnType);
    
  }
  
  /**
   * Create organization
   * 
   * @param body 
   * @param authorization 
   * @return OrganizationComposite
   */
  public OrganizationComposite createOrganization(OrganizationComposite body, String authorization) throws ApiException {
    Object postBody = body;
    
    // create path and map variables
    String path = "/organizations".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    Map<String, String> headerParams = new HashMap<String, String>();
    Map<String, Object> formParams = new HashMap<String, Object>();

    

    if (authorization != null)
      headerParams.put("Authorization", apiClient.parameterToString(authorization));
    

    

    final String[] accepts = {
      "application/json"
    };
    final String accept = apiClient.selectHeaderAccept(accepts);

    final String[] contentTypes = {
      
    };
    final String contentType = apiClient.selectHeaderContentType(contentTypes);

    String[] authNames = new String[] {  };

    
    GenericType<OrganizationComposite> returnType = new GenericType<OrganizationComposite>() {};
    return apiClient.invokeAPI(path, "POST", queryParams, postBody, headerParams, formParams, accept, contentType, authNames, returnType);
    
  }
  
  /**
   * Read organization by Ned ID
   * 
   * @param nedId 
   * @return OrganizationComposite
   */
  public OrganizationComposite readOrganization(Integer nedId) throws ApiException {
    Object postBody = null;
    
     // verify the required parameter 'nedId' is set
     if (nedId == null) {
        throw new ApiException(400, "Missing the required parameter 'nedId' when calling readOrganization");
     }
     
    // create path and map variables
    String path = "/organizations/{nedId}".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "nedId" + "\\}", apiClient.escapeString(nedId.toString()));

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    Map<String, String> headerParams = new HashMap<String, String>();
    Map<String, Object> formParams = new HashMap<String, Object>();

    

    

    

    final String[] accepts = {
      "application/json"
    };
    final String accept = apiClient.selectHeaderAccept(accepts);

    final String[] contentTypes = {
      
    };
    final String contentType = apiClient.selectHeaderContentType(contentTypes);

    String[] authNames = new String[] {  };

    
    GenericType<OrganizationComposite> returnType = new GenericType<OrganizationComposite>() {};
    return apiClient.invokeAPI(path, "GET", queryParams, postBody, headerParams, formParams, accept, contentType, authNames, returnType);
    
  }
  
  /**
   * List addresses
   * 
   * @param nedId 
   * @return List<Address>
   */
  public List<Address> getAddresses(Integer nedId) throws ApiException {
    Object postBody = null;
    
     // verify the required parameter 'nedId' is set
     if (nedId == null) {
        throw new ApiException(400, "Missing the required parameter 'nedId' when calling getAddresses");
     }
     
    // create path and map variables
    String path = "/organizations/{nedId}/addresses".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "nedId" + "\\}", apiClient.escapeString(nedId.toString()));

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    Map<String, String> headerParams = new HashMap<String, String>();
    Map<String, Object> formParams = new HashMap<String, Object>();

    

    

    

    final String[] accepts = {
      "application/json"
    };
    final String accept = apiClient.selectHeaderAccept(accepts);

    final String[] contentTypes = {
      
    };
    final String contentType = apiClient.selectHeaderContentType(contentTypes);

    String[] authNames = new String[] {  };

    
    GenericType<List<Address>> returnType = new GenericType<List<Address>>() {};
    return apiClient.invokeAPI(path, "GET", queryParams, postBody, headerParams, formParams, accept, contentType, authNames, returnType);
    
  }
  
  /**
   * Create address
   * 
   * @param nedId 
   * @param body 
   * @param authorization 
   * @return Address
   */
  public Address createAddress(Integer nedId, Address body, String authorization) throws ApiException {
    Object postBody = body;
    
     // verify the required parameter 'nedId' is set
     if (nedId == null) {
        throw new ApiException(400, "Missing the required parameter 'nedId' when calling createAddress");
     }
     
    // create path and map variables
    String path = "/organizations/{nedId}/addresses".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "nedId" + "\\}", apiClient.escapeString(nedId.toString()));

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    Map<String, String> headerParams = new HashMap<String, String>();
    Map<String, Object> formParams = new HashMap<String, Object>();

    

    if (authorization != null)
      headerParams.put("Authorization", apiClient.parameterToString(authorization));
    

    

    final String[] accepts = {
      "application/json"
    };
    final String accept = apiClient.selectHeaderAccept(accepts);

    final String[] contentTypes = {
      
    };
    final String contentType = apiClient.selectHeaderContentType(contentTypes);

    String[] authNames = new String[] {  };

    
    GenericType<Address> returnType = new GenericType<Address>() {};
    return apiClient.invokeAPI(path, "POST", queryParams, postBody, headerParams, formParams, accept, contentType, authNames, returnType);
    
  }
  
  /**
   * Read address
   * 
   * @param nedId 
   * @param addressId 
   * @return Address
   */
  public Address getAddress(Integer nedId, Integer addressId) throws ApiException {
    Object postBody = null;
    
     // verify the required parameter 'nedId' is set
     if (nedId == null) {
        throw new ApiException(400, "Missing the required parameter 'nedId' when calling getAddress");
     }
     
     // verify the required parameter 'addressId' is set
     if (addressId == null) {
        throw new ApiException(400, "Missing the required parameter 'addressId' when calling getAddress");
     }
     
    // create path and map variables
    String path = "/organizations/{nedId}/addresses/{addressId}".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "nedId" + "\\}", apiClient.escapeString(nedId.toString()))
      .replaceAll("\\{" + "addressId" + "\\}", apiClient.escapeString(addressId.toString()));

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    Map<String, String> headerParams = new HashMap<String, String>();
    Map<String, Object> formParams = new HashMap<String, Object>();

    

    

    

    final String[] accepts = {
      "application/json"
    };
    final String accept = apiClient.selectHeaderAccept(accepts);

    final String[] contentTypes = {
      
    };
    final String contentType = apiClient.selectHeaderContentType(contentTypes);

    String[] authNames = new String[] {  };

    
    GenericType<Address> returnType = new GenericType<Address>() {};
    return apiClient.invokeAPI(path, "GET", queryParams, postBody, headerParams, formParams, accept, contentType, authNames, returnType);
    
  }
  
  /**
   * Update address
   * 
   * @param nedId 
   * @param addressId 
   * @param body 
   * @param authorization 
   * @return Address
   */
  public Address updateAddress(Integer nedId, Integer addressId, Address body, String authorization) throws ApiException {
    Object postBody = body;
    
     // verify the required parameter 'nedId' is set
     if (nedId == null) {
        throw new ApiException(400, "Missing the required parameter 'nedId' when calling updateAddress");
     }
     
     // verify the required parameter 'addressId' is set
     if (addressId == null) {
        throw new ApiException(400, "Missing the required parameter 'addressId' when calling updateAddress");
     }
     
    // create path and map variables
    String path = "/organizations/{nedId}/addresses/{addressId}".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "nedId" + "\\}", apiClient.escapeString(nedId.toString()))
      .replaceAll("\\{" + "addressId" + "\\}", apiClient.escapeString(addressId.toString()));

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    Map<String, String> headerParams = new HashMap<String, String>();
    Map<String, Object> formParams = new HashMap<String, Object>();

    

    if (authorization != null)
      headerParams.put("Authorization", apiClient.parameterToString(authorization));
    

    

    final String[] accepts = {
      "application/json"
    };
    final String accept = apiClient.selectHeaderAccept(accepts);

    final String[] contentTypes = {
      
    };
    final String contentType = apiClient.selectHeaderContentType(contentTypes);

    String[] authNames = new String[] {  };

    
    GenericType<Address> returnType = new GenericType<Address>() {};
    return apiClient.invokeAPI(path, "PUT", queryParams, postBody, headerParams, formParams, accept, contentType, authNames, returnType);
    
  }
  
  /**
   * Delete address
   * 
   * @param nedId 
   * @param addressId 
   * @param authorization 
   * @return List<Address>
   */
  public List<Address> deleteAddress(Integer nedId, Integer addressId, String authorization) throws ApiException {
    Object postBody = null;
    
     // verify the required parameter 'nedId' is set
     if (nedId == null) {
        throw new ApiException(400, "Missing the required parameter 'nedId' when calling deleteAddress");
     }
     
     // verify the required parameter 'addressId' is set
     if (addressId == null) {
        throw new ApiException(400, "Missing the required parameter 'addressId' when calling deleteAddress");
     }
     
    // create path and map variables
    String path = "/organizations/{nedId}/addresses/{addressId}".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "nedId" + "\\}", apiClient.escapeString(nedId.toString()))
      .replaceAll("\\{" + "addressId" + "\\}", apiClient.escapeString(addressId.toString()));

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    Map<String, String> headerParams = new HashMap<String, String>();
    Map<String, Object> formParams = new HashMap<String, Object>();

    

    if (authorization != null)
      headerParams.put("Authorization", apiClient.parameterToString(authorization));
    

    

    final String[] accepts = {
      "application/json"
    };
    final String accept = apiClient.selectHeaderAccept(accepts);

    final String[] contentTypes = {
      
    };
    final String contentType = apiClient.selectHeaderContentType(contentTypes);

    String[] authNames = new String[] {  };

    
    GenericType<List<Address>> returnType = new GenericType<List<Address>>() {};
    return apiClient.invokeAPI(path, "DELETE", queryParams, postBody, headerParams, formParams, accept, contentType, authNames, returnType);
    
  }
  
  /**
   * List emails
   * 
   * @param nedId 
   * @return List<Email>
   */
  public List<Email> getEmails(Integer nedId) throws ApiException {
    Object postBody = null;
    
     // verify the required parameter 'nedId' is set
     if (nedId == null) {
        throw new ApiException(400, "Missing the required parameter 'nedId' when calling getEmails");
     }
     
    // create path and map variables
    String path = "/organizations/{nedId}/emails".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "nedId" + "\\}", apiClient.escapeString(nedId.toString()));

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    Map<String, String> headerParams = new HashMap<String, String>();
    Map<String, Object> formParams = new HashMap<String, Object>();

    

    

    

    final String[] accepts = {
      "application/json"
    };
    final String accept = apiClient.selectHeaderAccept(accepts);

    final String[] contentTypes = {
      
    };
    final String contentType = apiClient.selectHeaderContentType(contentTypes);

    String[] authNames = new String[] {  };

    
    GenericType<List<Email>> returnType = new GenericType<List<Email>>() {};
    return apiClient.invokeAPI(path, "GET", queryParams, postBody, headerParams, formParams, accept, contentType, authNames, returnType);
    
  }
  
  /**
   * Create email
   * 
   * @param nedId 
   * @param body 
   * @param authorization 
   * @return Email
   */
  public Email createEmail(Integer nedId, Email body, String authorization) throws ApiException {
    Object postBody = body;
    
     // verify the required parameter 'nedId' is set
     if (nedId == null) {
        throw new ApiException(400, "Missing the required parameter 'nedId' when calling createEmail");
     }
     
    // create path and map variables
    String path = "/organizations/{nedId}/emails".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "nedId" + "\\}", apiClient.escapeString(nedId.toString()));

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    Map<String, String> headerParams = new HashMap<String, String>();
    Map<String, Object> formParams = new HashMap<String, Object>();

    

    if (authorization != null)
      headerParams.put("Authorization", apiClient.parameterToString(authorization));
    

    

    final String[] accepts = {
      "application/json"
    };
    final String accept = apiClient.selectHeaderAccept(accepts);

    final String[] contentTypes = {
      
    };
    final String contentType = apiClient.selectHeaderContentType(contentTypes);

    String[] authNames = new String[] {  };

    
    GenericType<Email> returnType = new GenericType<Email>() {};
    return apiClient.invokeAPI(path, "POST", queryParams, postBody, headerParams, formParams, accept, contentType, authNames, returnType);
    
  }
  
  /**
   * Read email
   * 
   * @param nedId 
   * @param emailId 
   * @return Email
   */
  public Email getEmail(Integer nedId, Integer emailId) throws ApiException {
    Object postBody = null;
    
     // verify the required parameter 'nedId' is set
     if (nedId == null) {
        throw new ApiException(400, "Missing the required parameter 'nedId' when calling getEmail");
     }
     
     // verify the required parameter 'emailId' is set
     if (emailId == null) {
        throw new ApiException(400, "Missing the required parameter 'emailId' when calling getEmail");
     }
     
    // create path and map variables
    String path = "/organizations/{nedId}/emails/{emailId}".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "nedId" + "\\}", apiClient.escapeString(nedId.toString()))
      .replaceAll("\\{" + "emailId" + "\\}", apiClient.escapeString(emailId.toString()));

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    Map<String, String> headerParams = new HashMap<String, String>();
    Map<String, Object> formParams = new HashMap<String, Object>();

    

    

    

    final String[] accepts = {
      "application/json"
    };
    final String accept = apiClient.selectHeaderAccept(accepts);

    final String[] contentTypes = {
      
    };
    final String contentType = apiClient.selectHeaderContentType(contentTypes);

    String[] authNames = new String[] {  };

    
    GenericType<Email> returnType = new GenericType<Email>() {};
    return apiClient.invokeAPI(path, "GET", queryParams, postBody, headerParams, formParams, accept, contentType, authNames, returnType);
    
  }
  
  /**
   * Update email
   * 
   * @param nedId 
   * @param emailId 
   * @param body 
   * @param authorization 
   * @return Email
   */
  public Email updateEmail(Integer nedId, Integer emailId, Email body, String authorization) throws ApiException {
    Object postBody = body;
    
     // verify the required parameter 'nedId' is set
     if (nedId == null) {
        throw new ApiException(400, "Missing the required parameter 'nedId' when calling updateEmail");
     }
     
     // verify the required parameter 'emailId' is set
     if (emailId == null) {
        throw new ApiException(400, "Missing the required parameter 'emailId' when calling updateEmail");
     }
     
    // create path and map variables
    String path = "/organizations/{nedId}/emails/{emailId}".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "nedId" + "\\}", apiClient.escapeString(nedId.toString()))
      .replaceAll("\\{" + "emailId" + "\\}", apiClient.escapeString(emailId.toString()));

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    Map<String, String> headerParams = new HashMap<String, String>();
    Map<String, Object> formParams = new HashMap<String, Object>();

    

    if (authorization != null)
      headerParams.put("Authorization", apiClient.parameterToString(authorization));
    

    

    final String[] accepts = {
      "application/json"
    };
    final String accept = apiClient.selectHeaderAccept(accepts);

    final String[] contentTypes = {
      
    };
    final String contentType = apiClient.selectHeaderContentType(contentTypes);

    String[] authNames = new String[] {  };

    
    GenericType<Email> returnType = new GenericType<Email>() {};
    return apiClient.invokeAPI(path, "PUT", queryParams, postBody, headerParams, formParams, accept, contentType, authNames, returnType);
    
  }
  
  /**
   * Delete email
   * 
   * @param nedId 
   * @param emailId 
   * @param authorization 
   * @return void
   */
  public void deleteEmail(Integer nedId, Integer emailId, String authorization) throws ApiException {
    Object postBody = null;
    
     // verify the required parameter 'nedId' is set
     if (nedId == null) {
        throw new ApiException(400, "Missing the required parameter 'nedId' when calling deleteEmail");
     }
     
     // verify the required parameter 'emailId' is set
     if (emailId == null) {
        throw new ApiException(400, "Missing the required parameter 'emailId' when calling deleteEmail");
     }
     
    // create path and map variables
    String path = "/organizations/{nedId}/emails/{emailId}".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "nedId" + "\\}", apiClient.escapeString(nedId.toString()))
      .replaceAll("\\{" + "emailId" + "\\}", apiClient.escapeString(emailId.toString()));

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    Map<String, String> headerParams = new HashMap<String, String>();
    Map<String, Object> formParams = new HashMap<String, Object>();

    

    if (authorization != null)
      headerParams.put("Authorization", apiClient.parameterToString(authorization));
    

    

    final String[] accepts = {
      "application/json"
    };
    final String accept = apiClient.selectHeaderAccept(accepts);

    final String[] contentTypes = {
      
    };
    final String contentType = apiClient.selectHeaderContentType(contentTypes);

    String[] authNames = new String[] {  };

    
    apiClient.invokeAPI(path, "DELETE", queryParams, postBody, headerParams, formParams, accept, contentType, authNames, null);
    
  }
  
  /**
   * List phone numbers
   * 
   * @param nedId 
   * @return List<Phonenumber>
   */
  public List<Phonenumber> getPhonenumbers(Integer nedId) throws ApiException {
    Object postBody = null;
    
     // verify the required parameter 'nedId' is set
     if (nedId == null) {
        throw new ApiException(400, "Missing the required parameter 'nedId' when calling getPhonenumbers");
     }
     
    // create path and map variables
    String path = "/organizations/{nedId}/phonenumbers".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "nedId" + "\\}", apiClient.escapeString(nedId.toString()));

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    Map<String, String> headerParams = new HashMap<String, String>();
    Map<String, Object> formParams = new HashMap<String, Object>();

    

    

    

    final String[] accepts = {
      "application/json"
    };
    final String accept = apiClient.selectHeaderAccept(accepts);

    final String[] contentTypes = {
      
    };
    final String contentType = apiClient.selectHeaderContentType(contentTypes);

    String[] authNames = new String[] {  };

    
    GenericType<List<Phonenumber>> returnType = new GenericType<List<Phonenumber>>() {};
    return apiClient.invokeAPI(path, "GET", queryParams, postBody, headerParams, formParams, accept, contentType, authNames, returnType);
    
  }
  
  /**
   * List UIDs
   * 
   * @param nedId 
   * @return List<Uniqueidentifier>
   */
  public List<Uniqueidentifier> getUids(Integer nedId) throws ApiException {
    Object postBody = null;
    
     // verify the required parameter 'nedId' is set
     if (nedId == null) {
        throw new ApiException(400, "Missing the required parameter 'nedId' when calling getUids");
     }
     
    // create path and map variables
    String path = "/organizations/{nedId}/uids".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "nedId" + "\\}", apiClient.escapeString(nedId.toString()));

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    Map<String, String> headerParams = new HashMap<String, String>();
    Map<String, Object> formParams = new HashMap<String, Object>();

    

    

    

    final String[] accepts = {
      "application/json"
    };
    final String accept = apiClient.selectHeaderAccept(accepts);

    final String[] contentTypes = {
      
    };
    final String contentType = apiClient.selectHeaderContentType(contentTypes);

    String[] authNames = new String[] {  };

    
    GenericType<List<Uniqueidentifier>> returnType = new GenericType<List<Uniqueidentifier>>() {};
    return apiClient.invokeAPI(path, "GET", queryParams, postBody, headerParams, formParams, accept, contentType, authNames, returnType);
    
  }
  
  /**
   * Create UID
   * 
   * @param nedId 
   * @param body 
   * @param authorization 
   * @return Uniqueidentifier
   */
  public Uniqueidentifier createUid(Integer nedId, Uniqueidentifier body, String authorization) throws ApiException {
    Object postBody = body;
    
     // verify the required parameter 'nedId' is set
     if (nedId == null) {
        throw new ApiException(400, "Missing the required parameter 'nedId' when calling createUid");
     }
     
    // create path and map variables
    String path = "/organizations/{nedId}/uids".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "nedId" + "\\}", apiClient.escapeString(nedId.toString()));

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    Map<String, String> headerParams = new HashMap<String, String>();
    Map<String, Object> formParams = new HashMap<String, Object>();

    

    if (authorization != null)
      headerParams.put("Authorization", apiClient.parameterToString(authorization));
    

    

    final String[] accepts = {
      "application/json"
    };
    final String accept = apiClient.selectHeaderAccept(accepts);

    final String[] contentTypes = {
      
    };
    final String contentType = apiClient.selectHeaderContentType(contentTypes);

    String[] authNames = new String[] {  };

    
    GenericType<Uniqueidentifier> returnType = new GenericType<Uniqueidentifier>() {};
    return apiClient.invokeAPI(path, "POST", queryParams, postBody, headerParams, formParams, accept, contentType, authNames, returnType);
    
  }
  
  /**
   * Read uid
   * 
   * @param nedId 
   * @param id 
   * @return Uniqueidentifier
   */
  public Uniqueidentifier getUid(Integer nedId, Integer id) throws ApiException {
    Object postBody = null;
    
     // verify the required parameter 'nedId' is set
     if (nedId == null) {
        throw new ApiException(400, "Missing the required parameter 'nedId' when calling getUid");
     }
     
     // verify the required parameter 'id' is set
     if (id == null) {
        throw new ApiException(400, "Missing the required parameter 'id' when calling getUid");
     }
     
    // create path and map variables
    String path = "/organizations/{nedId}/uids/{id}".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "nedId" + "\\}", apiClient.escapeString(nedId.toString()))
      .replaceAll("\\{" + "id" + "\\}", apiClient.escapeString(id.toString()));

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    Map<String, String> headerParams = new HashMap<String, String>();
    Map<String, Object> formParams = new HashMap<String, Object>();

    

    

    

    final String[] accepts = {
      "application/json"
    };
    final String accept = apiClient.selectHeaderAccept(accepts);

    final String[] contentTypes = {
      
    };
    final String contentType = apiClient.selectHeaderContentType(contentTypes);

    String[] authNames = new String[] {  };

    
    GenericType<Uniqueidentifier> returnType = new GenericType<Uniqueidentifier>() {};
    return apiClient.invokeAPI(path, "GET", queryParams, postBody, headerParams, formParams, accept, contentType, authNames, returnType);
    
  }
  
  /**
   * Update UID
   * 
   * @param nedId 
   * @param id 
   * @param authorization 
   * @param body 
   * @return Uniqueidentifier
   */
  public Uniqueidentifier updateUid(Integer nedId, Integer id, String authorization, Uniqueidentifier body) throws ApiException {
    Object postBody = body;
    
     // verify the required parameter 'nedId' is set
     if (nedId == null) {
        throw new ApiException(400, "Missing the required parameter 'nedId' when calling updateUid");
     }
     
     // verify the required parameter 'id' is set
     if (id == null) {
        throw new ApiException(400, "Missing the required parameter 'id' when calling updateUid");
     }
     
    // create path and map variables
    String path = "/organizations/{nedId}/uids/{id}".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "nedId" + "\\}", apiClient.escapeString(nedId.toString()))
      .replaceAll("\\{" + "id" + "\\}", apiClient.escapeString(id.toString()));

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    Map<String, String> headerParams = new HashMap<String, String>();
    Map<String, Object> formParams = new HashMap<String, Object>();

    

    if (authorization != null)
      headerParams.put("Authorization", apiClient.parameterToString(authorization));
    

    

    final String[] accepts = {
      "application/json"
    };
    final String accept = apiClient.selectHeaderAccept(accepts);

    final String[] contentTypes = {
      
    };
    final String contentType = apiClient.selectHeaderContentType(contentTypes);

    String[] authNames = new String[] {  };

    
    GenericType<Uniqueidentifier> returnType = new GenericType<Uniqueidentifier>() {};
    return apiClient.invokeAPI(path, "PUT", queryParams, postBody, headerParams, formParams, accept, contentType, authNames, returnType);
    
  }
  
  /**
   * Read organization by UID
   * 
   * @param uidType 
   * @param uidValue 
   * @return OrganizationComposite
   */
  public OrganizationComposite readOrganizationByUid(String uidType, String uidValue) throws ApiException {
    Object postBody = null;
    
     // verify the required parameter 'uidType' is set
     if (uidType == null) {
        throw new ApiException(400, "Missing the required parameter 'uidType' when calling readOrganizationByUid");
     }
     
     // verify the required parameter 'uidValue' is set
     if (uidValue == null) {
        throw new ApiException(400, "Missing the required parameter 'uidValue' when calling readOrganizationByUid");
     }
     
    // create path and map variables
    String path = "/organizations/{uidType}/{uidValue}".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "uidType" + "\\}", apiClient.escapeString(uidType.toString()))
      .replaceAll("\\{" + "uidValue" + "\\}", apiClient.escapeString(uidValue.toString()));

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    Map<String, String> headerParams = new HashMap<String, String>();
    Map<String, Object> formParams = new HashMap<String, Object>();

    

    

    

    final String[] accepts = {
      "application/json"
    };
    final String accept = apiClient.selectHeaderAccept(accepts);

    final String[] contentTypes = {
      
    };
    final String contentType = apiClient.selectHeaderContentType(contentTypes);

    String[] authNames = new String[] {  };

    
    GenericType<OrganizationComposite> returnType = new GenericType<OrganizationComposite>() {};
    return apiClient.invokeAPI(path, "GET", queryParams, postBody, headerParams, formParams, accept, contentType, authNames, returnType);
    
  }
  
}
