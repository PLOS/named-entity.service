package io.swagger.client.api;

import io.swagger.client.ApiException;
import io.swagger.client.ApiClient;
import io.swagger.client.Configuration;
import io.swagger.client.Pair;
import io.swagger.client.TypeRef;

import io.swagger.client.model.IndividualComposite;
import io.swagger.client.model.Address;
import io.swagger.client.model.Auth;
import io.swagger.client.model.Degree;
import io.swagger.client.model.Email;
import io.swagger.client.model.Group;
import io.swagger.client.model.Individualprofile;
import io.swagger.client.model.Phonenumber;
import io.swagger.client.model.Relationship;
import io.swagger.client.model.Uniqueidentifier;

import java.util.*;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2015-10-20T11:32:47.715-07:00")
public class IndividualsApi {
  private ApiClient apiClient;

  public IndividualsApi() {
    this(Configuration.getDefaultApiClient());
  }

  public IndividualsApi(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  public ApiClient getApiClient() {
    return apiClient;
  }

  public void setApiClient(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  
  /**
   * Find individual matching specified attribute.
   * 
   * @param entity 
   * @param attribute 
   * @param value 
   * @return List<IndividualComposite>
   */
  public List<IndividualComposite> findIndividuals (String entity, String attribute, String value) throws ApiException {
    Object postBody = null;
    byte[] postBinaryBody = null;
    
    // create path and map variables
    String path = "/individuals".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    Map<String, String> headerParams = new HashMap<String, String>();
    Map<String, Object> formParams = new HashMap<String, Object>();

    
    queryParams.addAll(apiClient.parameterToPairs("", "entity", entity));
    
    queryParams.addAll(apiClient.parameterToPairs("", "attribute", attribute));
    
    queryParams.addAll(apiClient.parameterToPairs("", "value", value));
    

    

    

    final String[] accepts = {
      "application/json", "application/xml"
    };
    final String accept = apiClient.selectHeaderAccept(accepts);

    final String[] contentTypes = {
      
    };
    final String contentType = apiClient.selectHeaderContentType(contentTypes);

    String[] authNames = new String[] {  };

    

    
    
    TypeRef returnType = new TypeRef<List<IndividualComposite>>() {};
    return apiClient.invokeAPI(path, "GET", queryParams, postBody, postBinaryBody, headerParams, formParams, accept, contentType, authNames, returnType);
    
    


  }
  
  /**
   * Create individual
   * 
   * @param body 
   * @param authorization 
   * @return IndividualComposite
   */
  public IndividualComposite createIndividual (IndividualComposite body, String authorization) throws ApiException {
    Object postBody = body;
    byte[] postBinaryBody = null;
    
    // create path and map variables
    String path = "/individuals".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    Map<String, String> headerParams = new HashMap<String, String>();
    Map<String, Object> formParams = new HashMap<String, Object>();

    

    if (authorization != null)
    headerParams.put("Authorization", apiClient.parameterToString(authorization));
    

    

    final String[] accepts = {
      
    };
    final String accept = apiClient.selectHeaderAccept(accepts);

    final String[] contentTypes = {
      
    };
    final String contentType = apiClient.selectHeaderContentType(contentTypes);

    String[] authNames = new String[] {  };

    

    
    
    TypeRef returnType = new TypeRef<IndividualComposite>() {};
    return apiClient.invokeAPI(path, "POST", queryParams, postBody, postBinaryBody, headerParams, formParams, accept, contentType, authNames, returnType);
    
    


  }
  
  /**
   * Read individual by CAS ID
   * 
   * @param casId 
   * @return IndividualComposite
   */
  public IndividualComposite readIndividualByCasId (String casId) throws ApiException {
    Object postBody = null;
    byte[] postBinaryBody = null;
    
     // verify the required parameter 'casId' is set
     if (casId == null) {
        throw new ApiException(400, "Missing the required parameter 'casId' when calling readIndividualByCasId");
     }
     
    // create path and map variables
    String path = "/individuals/CAS/{casId}".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "casId" + "\\}", apiClient.escapeString(casId.toString()));

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    Map<String, String> headerParams = new HashMap<String, String>();
    Map<String, Object> formParams = new HashMap<String, Object>();

    

    

    

    final String[] accepts = {
      
    };
    final String accept = apiClient.selectHeaderAccept(accepts);

    final String[] contentTypes = {
      
    };
    final String contentType = apiClient.selectHeaderContentType(contentTypes);

    String[] authNames = new String[] {  };

    

    
    
    TypeRef returnType = new TypeRef<IndividualComposite>() {};
    return apiClient.invokeAPI(path, "GET", queryParams, postBody, postBinaryBody, headerParams, formParams, accept, contentType, authNames, returnType);
    
    


  }
  
  /**
   * Read individual by Ned ID
   * 
   * @param nedId 
   * @return IndividualComposite
   */
  public IndividualComposite readIndividual (Integer nedId) throws ApiException {
    Object postBody = null;
    byte[] postBinaryBody = null;
    
     // verify the required parameter 'nedId' is set
     if (nedId == null) {
        throw new ApiException(400, "Missing the required parameter 'nedId' when calling readIndividual");
     }
     
    // create path and map variables
    String path = "/individuals/{nedId}".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "nedId" + "\\}", apiClient.escapeString(nedId.toString()));

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    Map<String, String> headerParams = new HashMap<String, String>();
    Map<String, Object> formParams = new HashMap<String, Object>();

    

    

    

    final String[] accepts = {
      "application/json", "application/xml"
    };
    final String accept = apiClient.selectHeaderAccept(accepts);

    final String[] contentTypes = {
      
    };
    final String contentType = apiClient.selectHeaderContentType(contentTypes);

    String[] authNames = new String[] {  };

    

    
    
    TypeRef returnType = new TypeRef<IndividualComposite>() {};
    return apiClient.invokeAPI(path, "GET", queryParams, postBody, postBinaryBody, headerParams, formParams, accept, contentType, authNames, returnType);
    
    


  }
  
  /**
   * List addresses
   * 
   * @param nedId 
   * @return List<Address>
   */
  public List<Address> getAddresses (Integer nedId) throws ApiException {
    Object postBody = null;
    byte[] postBinaryBody = null;
    
     // verify the required parameter 'nedId' is set
     if (nedId == null) {
        throw new ApiException(400, "Missing the required parameter 'nedId' when calling getAddresses");
     }
     
    // create path and map variables
    String path = "/individuals/{nedId}/addresses".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "nedId" + "\\}", apiClient.escapeString(nedId.toString()));

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    Map<String, String> headerParams = new HashMap<String, String>();
    Map<String, Object> formParams = new HashMap<String, Object>();

    

    

    

    final String[] accepts = {
      
    };
    final String accept = apiClient.selectHeaderAccept(accepts);

    final String[] contentTypes = {
      
    };
    final String contentType = apiClient.selectHeaderContentType(contentTypes);

    String[] authNames = new String[] {  };

    

    
    
    TypeRef returnType = new TypeRef<List<Address>>() {};
    return apiClient.invokeAPI(path, "GET", queryParams, postBody, postBinaryBody, headerParams, formParams, accept, contentType, authNames, returnType);
    
    


  }
  
  /**
   * Create address
   * 
   * @param nedId 
   * @param body 
   * @param authorization 
   * @return Address
   */
  public Address createAddress (Integer nedId, Address body, String authorization) throws ApiException {
    Object postBody = body;
    byte[] postBinaryBody = null;
    
     // verify the required parameter 'nedId' is set
     if (nedId == null) {
        throw new ApiException(400, "Missing the required parameter 'nedId' when calling createAddress");
     }
     
    // create path and map variables
    String path = "/individuals/{nedId}/addresses".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "nedId" + "\\}", apiClient.escapeString(nedId.toString()));

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    Map<String, String> headerParams = new HashMap<String, String>();
    Map<String, Object> formParams = new HashMap<String, Object>();

    

    if (authorization != null)
    headerParams.put("Authorization", apiClient.parameterToString(authorization));
    

    

    final String[] accepts = {
      
    };
    final String accept = apiClient.selectHeaderAccept(accepts);

    final String[] contentTypes = {
      
    };
    final String contentType = apiClient.selectHeaderContentType(contentTypes);

    String[] authNames = new String[] {  };

    

    
    
    TypeRef returnType = new TypeRef<Address>() {};
    return apiClient.invokeAPI(path, "POST", queryParams, postBody, postBinaryBody, headerParams, formParams, accept, contentType, authNames, returnType);
    
    


  }
  
  /**
   * Read address
   * 
   * @param nedId 
   * @param addressId 
   * @return Address
   */
  public Address getAddress (Integer nedId, Integer addressId) throws ApiException {
    Object postBody = null;
    byte[] postBinaryBody = null;
    
     // verify the required parameter 'nedId' is set
     if (nedId == null) {
        throw new ApiException(400, "Missing the required parameter 'nedId' when calling getAddress");
     }
     
     // verify the required parameter 'addressId' is set
     if (addressId == null) {
        throw new ApiException(400, "Missing the required parameter 'addressId' when calling getAddress");
     }
     
    // create path and map variables
    String path = "/individuals/{nedId}/addresses/{addressId}".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "nedId" + "\\}", apiClient.escapeString(nedId.toString()))
      .replaceAll("\\{" + "addressId" + "\\}", apiClient.escapeString(addressId.toString()));

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    Map<String, String> headerParams = new HashMap<String, String>();
    Map<String, Object> formParams = new HashMap<String, Object>();

    

    

    

    final String[] accepts = {
      
    };
    final String accept = apiClient.selectHeaderAccept(accepts);

    final String[] contentTypes = {
      
    };
    final String contentType = apiClient.selectHeaderContentType(contentTypes);

    String[] authNames = new String[] {  };

    

    
    
    TypeRef returnType = new TypeRef<Address>() {};
    return apiClient.invokeAPI(path, "GET", queryParams, postBody, postBinaryBody, headerParams, formParams, accept, contentType, authNames, returnType);
    
    


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
  public Address updateAddress (Integer nedId, Integer addressId, Address body, String authorization) throws ApiException {
    Object postBody = body;
    byte[] postBinaryBody = null;
    
     // verify the required parameter 'nedId' is set
     if (nedId == null) {
        throw new ApiException(400, "Missing the required parameter 'nedId' when calling updateAddress");
     }
     
     // verify the required parameter 'addressId' is set
     if (addressId == null) {
        throw new ApiException(400, "Missing the required parameter 'addressId' when calling updateAddress");
     }
     
    // create path and map variables
    String path = "/individuals/{nedId}/addresses/{addressId}".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "nedId" + "\\}", apiClient.escapeString(nedId.toString()))
      .replaceAll("\\{" + "addressId" + "\\}", apiClient.escapeString(addressId.toString()));

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    Map<String, String> headerParams = new HashMap<String, String>();
    Map<String, Object> formParams = new HashMap<String, Object>();

    

    if (authorization != null)
    headerParams.put("Authorization", apiClient.parameterToString(authorization));
    

    

    final String[] accepts = {
      
    };
    final String accept = apiClient.selectHeaderAccept(accepts);

    final String[] contentTypes = {
      
    };
    final String contentType = apiClient.selectHeaderContentType(contentTypes);

    String[] authNames = new String[] {  };

    

    
    
    TypeRef returnType = new TypeRef<Address>() {};
    return apiClient.invokeAPI(path, "PUT", queryParams, postBody, postBinaryBody, headerParams, formParams, accept, contentType, authNames, returnType);
    
    


  }
  
  /**
   * Delete address
   * 
   * @param nedId 
   * @param addressId 
   * @param authorization 
   * @return void
   */
  public void deleteAddress (Integer nedId, Integer addressId, String authorization) throws ApiException {
    Object postBody = null;
    byte[] postBinaryBody = null;
    
     // verify the required parameter 'nedId' is set
     if (nedId == null) {
        throw new ApiException(400, "Missing the required parameter 'nedId' when calling deleteAddress");
     }
     
     // verify the required parameter 'addressId' is set
     if (addressId == null) {
        throw new ApiException(400, "Missing the required parameter 'addressId' when calling deleteAddress");
     }
     
    // create path and map variables
    String path = "/individuals/{nedId}/addresses/{addressId}".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "nedId" + "\\}", apiClient.escapeString(nedId.toString()))
      .replaceAll("\\{" + "addressId" + "\\}", apiClient.escapeString(addressId.toString()));

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    Map<String, String> headerParams = new HashMap<String, String>();
    Map<String, Object> formParams = new HashMap<String, Object>();

    

    if (authorization != null)
    headerParams.put("Authorization", apiClient.parameterToString(authorization));
    

    

    final String[] accepts = {
      
    };
    final String accept = apiClient.selectHeaderAccept(accepts);

    final String[] contentTypes = {
      
    };
    final String contentType = apiClient.selectHeaderContentType(contentTypes);

    String[] authNames = new String[] {  };

    

    
    
    apiClient.invokeAPI(path, "DELETE", queryParams, postBody, postBinaryBody, headerParams, formParams, accept, contentType, authNames, null);
    
    


  }
  
  /**
   * List auth record(s)
   * 
   * @param nedId 
   * @return void
   */
  public void getAuthRecord (Integer nedId) throws ApiException {
    Object postBody = null;
    byte[] postBinaryBody = null;
    
     // verify the required parameter 'nedId' is set
     if (nedId == null) {
        throw new ApiException(400, "Missing the required parameter 'nedId' when calling getAuthRecord");
     }
     
    // create path and map variables
    String path = "/individuals/{nedId}/auth".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "nedId" + "\\}", apiClient.escapeString(nedId.toString()));

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    Map<String, String> headerParams = new HashMap<String, String>();
    Map<String, Object> formParams = new HashMap<String, Object>();

    

    

    

    final String[] accepts = {
      
    };
    final String accept = apiClient.selectHeaderAccept(accepts);

    final String[] contentTypes = {
      
    };
    final String contentType = apiClient.selectHeaderContentType(contentTypes);

    String[] authNames = new String[] {  };

    

    
    
    apiClient.invokeAPI(path, "GET", queryParams, postBody, postBinaryBody, headerParams, formParams, accept, contentType, authNames, null);
    
    


  }
  
  /**
   * Validate password
   * 
   * @param nedId 
   * @param body 
   * @return void
   */
  public void checkPassword (Integer nedId, Auth body) throws ApiException {
    Object postBody = body;
    byte[] postBinaryBody = null;
    
     // verify the required parameter 'nedId' is set
     if (nedId == null) {
        throw new ApiException(400, "Missing the required parameter 'nedId' when calling checkPassword");
     }
     
    // create path and map variables
    String path = "/individuals/{nedId}/auth/checkpassword".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "nedId" + "\\}", apiClient.escapeString(nedId.toString()));

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    Map<String, String> headerParams = new HashMap<String, String>();
    Map<String, Object> formParams = new HashMap<String, Object>();

    

    

    

    final String[] accepts = {
      
    };
    final String accept = apiClient.selectHeaderAccept(accepts);

    final String[] contentTypes = {
      
    };
    final String contentType = apiClient.selectHeaderContentType(contentTypes);

    String[] authNames = new String[] {  };

    

    
    
    apiClient.invokeAPI(path, "POST", queryParams, postBody, postBinaryBody, headerParams, formParams, accept, contentType, authNames, null);
    
    


  }
  
  /**
   * Update auth record
   * 
   * @param nedId 
   * @param authId 
   * @param authorization 
   * @param body 
   * @return Auth
   */
  public Auth updateAuthRecord (Integer nedId, Integer authId, String authorization, Auth body) throws ApiException {
    Object postBody = body;
    byte[] postBinaryBody = null;
    
     // verify the required parameter 'nedId' is set
     if (nedId == null) {
        throw new ApiException(400, "Missing the required parameter 'nedId' when calling updateAuthRecord");
     }
     
     // verify the required parameter 'authId' is set
     if (authId == null) {
        throw new ApiException(400, "Missing the required parameter 'authId' when calling updateAuthRecord");
     }
     
    // create path and map variables
    String path = "/individuals/{nedId}/auth/{authId}".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "nedId" + "\\}", apiClient.escapeString(nedId.toString()))
      .replaceAll("\\{" + "authId" + "\\}", apiClient.escapeString(authId.toString()));

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    Map<String, String> headerParams = new HashMap<String, String>();
    Map<String, Object> formParams = new HashMap<String, Object>();

    

    if (authorization != null)
    headerParams.put("Authorization", apiClient.parameterToString(authorization));
    

    

    final String[] accepts = {
      
    };
    final String accept = apiClient.selectHeaderAccept(accepts);

    final String[] contentTypes = {
      
    };
    final String contentType = apiClient.selectHeaderContentType(contentTypes);

    String[] authNames = new String[] {  };

    

    
    
    TypeRef returnType = new TypeRef<Auth>() {};
    return apiClient.invokeAPI(path, "PUT", queryParams, postBody, postBinaryBody, headerParams, formParams, accept, contentType, authNames, returnType);
    
    


  }
  
  /**
   * List degrees
   * 
   * @param nedId 
   * @return List<Degree>
   */
  public List<Degree> getDegrees (Integer nedId) throws ApiException {
    Object postBody = null;
    byte[] postBinaryBody = null;
    
     // verify the required parameter 'nedId' is set
     if (nedId == null) {
        throw new ApiException(400, "Missing the required parameter 'nedId' when calling getDegrees");
     }
     
    // create path and map variables
    String path = "/individuals/{nedId}/degrees".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "nedId" + "\\}", apiClient.escapeString(nedId.toString()));

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    Map<String, String> headerParams = new HashMap<String, String>();
    Map<String, Object> formParams = new HashMap<String, Object>();

    

    

    

    final String[] accepts = {
      
    };
    final String accept = apiClient.selectHeaderAccept(accepts);

    final String[] contentTypes = {
      
    };
    final String contentType = apiClient.selectHeaderContentType(contentTypes);

    String[] authNames = new String[] {  };

    

    
    
    TypeRef returnType = new TypeRef<List<Degree>>() {};
    return apiClient.invokeAPI(path, "GET", queryParams, postBody, postBinaryBody, headerParams, formParams, accept, contentType, authNames, returnType);
    
    


  }
  
  /**
   * List emails
   * 
   * @param nedId 
   * @return List<Email>
   */
  public List<Email> getEmails (Integer nedId) throws ApiException {
    Object postBody = null;
    byte[] postBinaryBody = null;
    
     // verify the required parameter 'nedId' is set
     if (nedId == null) {
        throw new ApiException(400, "Missing the required parameter 'nedId' when calling getEmails");
     }
     
    // create path and map variables
    String path = "/individuals/{nedId}/emails".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "nedId" + "\\}", apiClient.escapeString(nedId.toString()));

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    Map<String, String> headerParams = new HashMap<String, String>();
    Map<String, Object> formParams = new HashMap<String, Object>();

    

    

    

    final String[] accepts = {
      
    };
    final String accept = apiClient.selectHeaderAccept(accepts);

    final String[] contentTypes = {
      
    };
    final String contentType = apiClient.selectHeaderContentType(contentTypes);

    String[] authNames = new String[] {  };

    

    
    
    TypeRef returnType = new TypeRef<List<Email>>() {};
    return apiClient.invokeAPI(path, "GET", queryParams, postBody, postBinaryBody, headerParams, formParams, accept, contentType, authNames, returnType);
    
    


  }
  
  /**
   * Create email
   * 
   * @param nedId 
   * @param body 
   * @param authorization 
   * @return Email
   */
  public Email createEmail (Integer nedId, Email body, String authorization) throws ApiException {
    Object postBody = body;
    byte[] postBinaryBody = null;
    
     // verify the required parameter 'nedId' is set
     if (nedId == null) {
        throw new ApiException(400, "Missing the required parameter 'nedId' when calling createEmail");
     }
     
    // create path and map variables
    String path = "/individuals/{nedId}/emails".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "nedId" + "\\}", apiClient.escapeString(nedId.toString()));

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    Map<String, String> headerParams = new HashMap<String, String>();
    Map<String, Object> formParams = new HashMap<String, Object>();

    

    if (authorization != null)
    headerParams.put("Authorization", apiClient.parameterToString(authorization));
    

    

    final String[] accepts = {
      
    };
    final String accept = apiClient.selectHeaderAccept(accepts);

    final String[] contentTypes = {
      
    };
    final String contentType = apiClient.selectHeaderContentType(contentTypes);

    String[] authNames = new String[] {  };

    

    
    
    TypeRef returnType = new TypeRef<Email>() {};
    return apiClient.invokeAPI(path, "POST", queryParams, postBody, postBinaryBody, headerParams, formParams, accept, contentType, authNames, returnType);
    
    


  }
  
  /**
   * Read email
   * 
   * @param nedId 
   * @param emailId 
   * @return Email
   */
  public Email getEmail (Integer nedId, Integer emailId) throws ApiException {
    Object postBody = null;
    byte[] postBinaryBody = null;
    
     // verify the required parameter 'nedId' is set
     if (nedId == null) {
        throw new ApiException(400, "Missing the required parameter 'nedId' when calling getEmail");
     }
     
     // verify the required parameter 'emailId' is set
     if (emailId == null) {
        throw new ApiException(400, "Missing the required parameter 'emailId' when calling getEmail");
     }
     
    // create path and map variables
    String path = "/individuals/{nedId}/emails/{emailId}".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "nedId" + "\\}", apiClient.escapeString(nedId.toString()))
      .replaceAll("\\{" + "emailId" + "\\}", apiClient.escapeString(emailId.toString()));

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    Map<String, String> headerParams = new HashMap<String, String>();
    Map<String, Object> formParams = new HashMap<String, Object>();

    

    

    

    final String[] accepts = {
      
    };
    final String accept = apiClient.selectHeaderAccept(accepts);

    final String[] contentTypes = {
      
    };
    final String contentType = apiClient.selectHeaderContentType(contentTypes);

    String[] authNames = new String[] {  };

    

    
    
    TypeRef returnType = new TypeRef<Email>() {};
    return apiClient.invokeAPI(path, "GET", queryParams, postBody, postBinaryBody, headerParams, formParams, accept, contentType, authNames, returnType);
    
    


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
  public Email updateEmail (Integer nedId, Integer emailId, Email body, String authorization) throws ApiException {
    Object postBody = body;
    byte[] postBinaryBody = null;
    
     // verify the required parameter 'nedId' is set
     if (nedId == null) {
        throw new ApiException(400, "Missing the required parameter 'nedId' when calling updateEmail");
     }
     
     // verify the required parameter 'emailId' is set
     if (emailId == null) {
        throw new ApiException(400, "Missing the required parameter 'emailId' when calling updateEmail");
     }
     
    // create path and map variables
    String path = "/individuals/{nedId}/emails/{emailId}".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "nedId" + "\\}", apiClient.escapeString(nedId.toString()))
      .replaceAll("\\{" + "emailId" + "\\}", apiClient.escapeString(emailId.toString()));

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    Map<String, String> headerParams = new HashMap<String, String>();
    Map<String, Object> formParams = new HashMap<String, Object>();

    

    if (authorization != null)
    headerParams.put("Authorization", apiClient.parameterToString(authorization));
    

    

    final String[] accepts = {
      
    };
    final String accept = apiClient.selectHeaderAccept(accepts);

    final String[] contentTypes = {
      
    };
    final String contentType = apiClient.selectHeaderContentType(contentTypes);

    String[] authNames = new String[] {  };

    

    
    
    TypeRef returnType = new TypeRef<Email>() {};
    return apiClient.invokeAPI(path, "PUT", queryParams, postBody, postBinaryBody, headerParams, formParams, accept, contentType, authNames, returnType);
    
    


  }
  
  /**
   * Delete email
   * 
   * @param nedId 
   * @param emailId 
   * @param authorization 
   * @return void
   */
  public void deleteEmail (Integer nedId, Integer emailId, String authorization) throws ApiException {
    Object postBody = null;
    byte[] postBinaryBody = null;
    
     // verify the required parameter 'nedId' is set
     if (nedId == null) {
        throw new ApiException(400, "Missing the required parameter 'nedId' when calling deleteEmail");
     }
     
     // verify the required parameter 'emailId' is set
     if (emailId == null) {
        throw new ApiException(400, "Missing the required parameter 'emailId' when calling deleteEmail");
     }
     
    // create path and map variables
    String path = "/individuals/{nedId}/emails/{emailId}".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "nedId" + "\\}", apiClient.escapeString(nedId.toString()))
      .replaceAll("\\{" + "emailId" + "\\}", apiClient.escapeString(emailId.toString()));

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    Map<String, String> headerParams = new HashMap<String, String>();
    Map<String, Object> formParams = new HashMap<String, Object>();

    

    if (authorization != null)
    headerParams.put("Authorization", apiClient.parameterToString(authorization));
    

    

    final String[] accepts = {
      
    };
    final String accept = apiClient.selectHeaderAccept(accepts);

    final String[] contentTypes = {
      
    };
    final String contentType = apiClient.selectHeaderContentType(contentTypes);

    String[] authNames = new String[] {  };

    

    
    
    apiClient.invokeAPI(path, "DELETE", queryParams, postBody, postBinaryBody, headerParams, formParams, accept, contentType, authNames, null);
    
    


  }
  
  /**
   * List groups
   * 
   * @param nedId 
   * @return List<Group>
   */
  public List<Group> getGroups (Integer nedId) throws ApiException {
    Object postBody = null;
    byte[] postBinaryBody = null;
    
     // verify the required parameter 'nedId' is set
     if (nedId == null) {
        throw new ApiException(400, "Missing the required parameter 'nedId' when calling getGroups");
     }
     
    // create path and map variables
    String path = "/individuals/{nedId}/groups".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "nedId" + "\\}", apiClient.escapeString(nedId.toString()));

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    Map<String, String> headerParams = new HashMap<String, String>();
    Map<String, Object> formParams = new HashMap<String, Object>();

    

    

    

    final String[] accepts = {
      
    };
    final String accept = apiClient.selectHeaderAccept(accepts);

    final String[] contentTypes = {
      
    };
    final String contentType = apiClient.selectHeaderContentType(contentTypes);

    String[] authNames = new String[] {  };

    

    
    
    TypeRef returnType = new TypeRef<List<Group>>() {};
    return apiClient.invokeAPI(path, "GET", queryParams, postBody, postBinaryBody, headerParams, formParams, accept, contentType, authNames, returnType);
    
    


  }
  
  /**
   * Create group
   * 
   * @param nedId 
   * @param body 
   * @param authorization 
   * @return Group
   */
  public Group createGroup (Integer nedId, Group body, String authorization) throws ApiException {
    Object postBody = body;
    byte[] postBinaryBody = null;
    
     // verify the required parameter 'nedId' is set
     if (nedId == null) {
        throw new ApiException(400, "Missing the required parameter 'nedId' when calling createGroup");
     }
     
    // create path and map variables
    String path = "/individuals/{nedId}/groups".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "nedId" + "\\}", apiClient.escapeString(nedId.toString()));

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    Map<String, String> headerParams = new HashMap<String, String>();
    Map<String, Object> formParams = new HashMap<String, Object>();

    

    if (authorization != null)
    headerParams.put("Authorization", apiClient.parameterToString(authorization));
    

    

    final String[] accepts = {
      
    };
    final String accept = apiClient.selectHeaderAccept(accepts);

    final String[] contentTypes = {
      
    };
    final String contentType = apiClient.selectHeaderContentType(contentTypes);

    String[] authNames = new String[] {  };

    

    
    
    TypeRef returnType = new TypeRef<Group>() {};
    return apiClient.invokeAPI(path, "POST", queryParams, postBody, postBinaryBody, headerParams, formParams, accept, contentType, authNames, returnType);
    
    


  }
  
  /**
   * Read group
   * 
   * @param nedId 
   * @param groupId 
   * @return Group
   */
  public Group getGroup (Integer nedId, Integer groupId) throws ApiException {
    Object postBody = null;
    byte[] postBinaryBody = null;
    
     // verify the required parameter 'nedId' is set
     if (nedId == null) {
        throw new ApiException(400, "Missing the required parameter 'nedId' when calling getGroup");
     }
     
     // verify the required parameter 'groupId' is set
     if (groupId == null) {
        throw new ApiException(400, "Missing the required parameter 'groupId' when calling getGroup");
     }
     
    // create path and map variables
    String path = "/individuals/{nedId}/groups/{groupId}".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "nedId" + "\\}", apiClient.escapeString(nedId.toString()))
      .replaceAll("\\{" + "groupId" + "\\}", apiClient.escapeString(groupId.toString()));

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    Map<String, String> headerParams = new HashMap<String, String>();
    Map<String, Object> formParams = new HashMap<String, Object>();

    

    

    

    final String[] accepts = {
      
    };
    final String accept = apiClient.selectHeaderAccept(accepts);

    final String[] contentTypes = {
      
    };
    final String contentType = apiClient.selectHeaderContentType(contentTypes);

    String[] authNames = new String[] {  };

    

    
    
    TypeRef returnType = new TypeRef<Group>() {};
    return apiClient.invokeAPI(path, "GET", queryParams, postBody, postBinaryBody, headerParams, formParams, accept, contentType, authNames, returnType);
    
    


  }
  
  /**
   * Update group
   * 
   * @param nedId 
   * @param groupId 
   * @param authorization 
   * @param body 
   * @return Group
   */
  public Group updateGroup (Integer nedId, Integer groupId, String authorization, Group body) throws ApiException {
    Object postBody = body;
    byte[] postBinaryBody = null;
    
     // verify the required parameter 'nedId' is set
     if (nedId == null) {
        throw new ApiException(400, "Missing the required parameter 'nedId' when calling updateGroup");
     }
     
     // verify the required parameter 'groupId' is set
     if (groupId == null) {
        throw new ApiException(400, "Missing the required parameter 'groupId' when calling updateGroup");
     }
     
    // create path and map variables
    String path = "/individuals/{nedId}/groups/{groupId}".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "nedId" + "\\}", apiClient.escapeString(nedId.toString()))
      .replaceAll("\\{" + "groupId" + "\\}", apiClient.escapeString(groupId.toString()));

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    Map<String, String> headerParams = new HashMap<String, String>();
    Map<String, Object> formParams = new HashMap<String, Object>();

    

    if (authorization != null)
    headerParams.put("Authorization", apiClient.parameterToString(authorization));
    

    

    final String[] accepts = {
      
    };
    final String accept = apiClient.selectHeaderAccept(accepts);

    final String[] contentTypes = {
      
    };
    final String contentType = apiClient.selectHeaderContentType(contentTypes);

    String[] authNames = new String[] {  };

    

    
    
    TypeRef returnType = new TypeRef<Group>() {};
    return apiClient.invokeAPI(path, "PUT", queryParams, postBody, postBinaryBody, headerParams, formParams, accept, contentType, authNames, returnType);
    
    


  }
  
  /**
   * Delete group
   * 
   * @param nedId 
   * @param groupId 
   * @param authorization 
   * @return void
   */
  public void deleteGroup (Integer nedId, Integer groupId, String authorization) throws ApiException {
    Object postBody = null;
    byte[] postBinaryBody = null;
    
     // verify the required parameter 'nedId' is set
     if (nedId == null) {
        throw new ApiException(400, "Missing the required parameter 'nedId' when calling deleteGroup");
     }
     
     // verify the required parameter 'groupId' is set
     if (groupId == null) {
        throw new ApiException(400, "Missing the required parameter 'groupId' when calling deleteGroup");
     }
     
    // create path and map variables
    String path = "/individuals/{nedId}/groups/{groupId}".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "nedId" + "\\}", apiClient.escapeString(nedId.toString()))
      .replaceAll("\\{" + "groupId" + "\\}", apiClient.escapeString(groupId.toString()));

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    Map<String, String> headerParams = new HashMap<String, String>();
    Map<String, Object> formParams = new HashMap<String, Object>();

    

    if (authorization != null)
    headerParams.put("Authorization", apiClient.parameterToString(authorization));
    

    

    final String[] accepts = {
      
    };
    final String accept = apiClient.selectHeaderAccept(accepts);

    final String[] contentTypes = {
      
    };
    final String contentType = apiClient.selectHeaderContentType(contentTypes);

    String[] authNames = new String[] {  };

    

    
    
    apiClient.invokeAPI(path, "DELETE", queryParams, postBody, postBinaryBody, headerParams, formParams, accept, contentType, authNames, null);
    
    


  }
  
  /**
   * List profiles
   * 
   * @param nedId 
   * @return List<Individualprofile>
   */
  public List<Individualprofile> getProfiles (Integer nedId) throws ApiException {
    Object postBody = null;
    byte[] postBinaryBody = null;
    
     // verify the required parameter 'nedId' is set
     if (nedId == null) {
        throw new ApiException(400, "Missing the required parameter 'nedId' when calling getProfiles");
     }
     
    // create path and map variables
    String path = "/individuals/{nedId}/individualprofiles".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "nedId" + "\\}", apiClient.escapeString(nedId.toString()));

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    Map<String, String> headerParams = new HashMap<String, String>();
    Map<String, Object> formParams = new HashMap<String, Object>();

    

    

    

    final String[] accepts = {
      
    };
    final String accept = apiClient.selectHeaderAccept(accepts);

    final String[] contentTypes = {
      
    };
    final String contentType = apiClient.selectHeaderContentType(contentTypes);

    String[] authNames = new String[] {  };

    

    
    
    TypeRef returnType = new TypeRef<List<Individualprofile>>() {};
    return apiClient.invokeAPI(path, "GET", queryParams, postBody, postBinaryBody, headerParams, formParams, accept, contentType, authNames, returnType);
    
    


  }
  
  /**
   * Add profile
   * 
   * @param nedId 
   * @param body 
   * @param authorization 
   * @return Individualprofile
   */
  public Individualprofile addProfile (Integer nedId, Individualprofile body, String authorization) throws ApiException {
    Object postBody = body;
    byte[] postBinaryBody = null;
    
     // verify the required parameter 'nedId' is set
     if (nedId == null) {
        throw new ApiException(400, "Missing the required parameter 'nedId' when calling addProfile");
     }
     
    // create path and map variables
    String path = "/individuals/{nedId}/individualprofiles".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "nedId" + "\\}", apiClient.escapeString(nedId.toString()));

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    Map<String, String> headerParams = new HashMap<String, String>();
    Map<String, Object> formParams = new HashMap<String, Object>();

    

    if (authorization != null)
    headerParams.put("Authorization", apiClient.parameterToString(authorization));
    

    

    final String[] accepts = {
      
    };
    final String accept = apiClient.selectHeaderAccept(accepts);

    final String[] contentTypes = {
      
    };
    final String contentType = apiClient.selectHeaderContentType(contentTypes);

    String[] authNames = new String[] {  };

    

    
    
    TypeRef returnType = new TypeRef<Individualprofile>() {};
    return apiClient.invokeAPI(path, "POST", queryParams, postBody, postBinaryBody, headerParams, formParams, accept, contentType, authNames, returnType);
    
    


  }
  
  /**
   * Read profile
   * 
   * @param nedId 
   * @param profileId 
   * @return Individualprofile
   */
  public Individualprofile getProfile (Integer nedId, Integer profileId) throws ApiException {
    Object postBody = null;
    byte[] postBinaryBody = null;
    
     // verify the required parameter 'nedId' is set
     if (nedId == null) {
        throw new ApiException(400, "Missing the required parameter 'nedId' when calling getProfile");
     }
     
     // verify the required parameter 'profileId' is set
     if (profileId == null) {
        throw new ApiException(400, "Missing the required parameter 'profileId' when calling getProfile");
     }
     
    // create path and map variables
    String path = "/individuals/{nedId}/individualprofiles/{profileId}".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "nedId" + "\\}", apiClient.escapeString(nedId.toString()))
      .replaceAll("\\{" + "profileId" + "\\}", apiClient.escapeString(profileId.toString()));

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    Map<String, String> headerParams = new HashMap<String, String>();
    Map<String, Object> formParams = new HashMap<String, Object>();

    

    

    

    final String[] accepts = {
      
    };
    final String accept = apiClient.selectHeaderAccept(accepts);

    final String[] contentTypes = {
      
    };
    final String contentType = apiClient.selectHeaderContentType(contentTypes);

    String[] authNames = new String[] {  };

    

    
    
    TypeRef returnType = new TypeRef<Individualprofile>() {};
    return apiClient.invokeAPI(path, "GET", queryParams, postBody, postBinaryBody, headerParams, formParams, accept, contentType, authNames, returnType);
    
    


  }
  
  /**
   * Update a profile
   * 
   * @param nedId 
   * @param profileId 
   * @param authorization 
   * @param body 
   * @return Individualprofile
   */
  public Individualprofile updateProfile (Integer nedId, Integer profileId, String authorization, Individualprofile body) throws ApiException {
    Object postBody = body;
    byte[] postBinaryBody = null;
    
     // verify the required parameter 'nedId' is set
     if (nedId == null) {
        throw new ApiException(400, "Missing the required parameter 'nedId' when calling updateProfile");
     }
     
     // verify the required parameter 'profileId' is set
     if (profileId == null) {
        throw new ApiException(400, "Missing the required parameter 'profileId' when calling updateProfile");
     }
     
    // create path and map variables
    String path = "/individuals/{nedId}/individualprofiles/{profileId}".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "nedId" + "\\}", apiClient.escapeString(nedId.toString()))
      .replaceAll("\\{" + "profileId" + "\\}", apiClient.escapeString(profileId.toString()));

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    Map<String, String> headerParams = new HashMap<String, String>();
    Map<String, Object> formParams = new HashMap<String, Object>();

    

    if (authorization != null)
    headerParams.put("Authorization", apiClient.parameterToString(authorization));
    

    

    final String[] accepts = {
      
    };
    final String accept = apiClient.selectHeaderAccept(accepts);

    final String[] contentTypes = {
      
    };
    final String contentType = apiClient.selectHeaderContentType(contentTypes);

    String[] authNames = new String[] {  };

    

    
    
    TypeRef returnType = new TypeRef<Individualprofile>() {};
    return apiClient.invokeAPI(path, "PUT", queryParams, postBody, postBinaryBody, headerParams, formParams, accept, contentType, authNames, returnType);
    
    


  }
  
  /**
   * Delete a profile
   * 
   * @param nedId 
   * @param profileId 
   * @param authorization 
   * @return void
   */
  public void deleteProfile (Integer nedId, Integer profileId, String authorization) throws ApiException {
    Object postBody = null;
    byte[] postBinaryBody = null;
    
     // verify the required parameter 'nedId' is set
     if (nedId == null) {
        throw new ApiException(400, "Missing the required parameter 'nedId' when calling deleteProfile");
     }
     
     // verify the required parameter 'profileId' is set
     if (profileId == null) {
        throw new ApiException(400, "Missing the required parameter 'profileId' when calling deleteProfile");
     }
     
    // create path and map variables
    String path = "/individuals/{nedId}/individualprofiles/{profileId}".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "nedId" + "\\}", apiClient.escapeString(nedId.toString()))
      .replaceAll("\\{" + "profileId" + "\\}", apiClient.escapeString(profileId.toString()));

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    Map<String, String> headerParams = new HashMap<String, String>();
    Map<String, Object> formParams = new HashMap<String, Object>();

    

    if (authorization != null)
    headerParams.put("Authorization", apiClient.parameterToString(authorization));
    

    

    final String[] accepts = {
      
    };
    final String accept = apiClient.selectHeaderAccept(accepts);

    final String[] contentTypes = {
      
    };
    final String contentType = apiClient.selectHeaderContentType(contentTypes);

    String[] authNames = new String[] {  };

    

    
    
    apiClient.invokeAPI(path, "DELETE", queryParams, postBody, postBinaryBody, headerParams, formParams, accept, contentType, authNames, null);
    
    


  }
  
  /**
   * List phone numbers
   * 
   * @param nedId 
   * @return List<Phonenumber>
   */
  public List<Phonenumber> getPhonenumbers (Integer nedId) throws ApiException {
    Object postBody = null;
    byte[] postBinaryBody = null;
    
     // verify the required parameter 'nedId' is set
     if (nedId == null) {
        throw new ApiException(400, "Missing the required parameter 'nedId' when calling getPhonenumbers");
     }
     
    // create path and map variables
    String path = "/individuals/{nedId}/phonenumbers".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "nedId" + "\\}", apiClient.escapeString(nedId.toString()));

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    Map<String, String> headerParams = new HashMap<String, String>();
    Map<String, Object> formParams = new HashMap<String, Object>();

    

    

    

    final String[] accepts = {
      
    };
    final String accept = apiClient.selectHeaderAccept(accepts);

    final String[] contentTypes = {
      
    };
    final String contentType = apiClient.selectHeaderContentType(contentTypes);

    String[] authNames = new String[] {  };

    

    
    
    TypeRef returnType = new TypeRef<List<Phonenumber>>() {};
    return apiClient.invokeAPI(path, "GET", queryParams, postBody, postBinaryBody, headerParams, formParams, accept, contentType, authNames, returnType);
    
    


  }
  
  /**
   * List relationships
   * 
   * @param nedId 
   * @return List<Relationship>
   */
  public List<Relationship> getRelationships (Integer nedId) throws ApiException {
    Object postBody = null;
    byte[] postBinaryBody = null;
    
     // verify the required parameter 'nedId' is set
     if (nedId == null) {
        throw new ApiException(400, "Missing the required parameter 'nedId' when calling getRelationships");
     }
     
    // create path and map variables
    String path = "/individuals/{nedId}/relationships".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "nedId" + "\\}", apiClient.escapeString(nedId.toString()));

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    Map<String, String> headerParams = new HashMap<String, String>();
    Map<String, Object> formParams = new HashMap<String, Object>();

    

    

    

    final String[] accepts = {
      
    };
    final String accept = apiClient.selectHeaderAccept(accepts);

    final String[] contentTypes = {
      
    };
    final String contentType = apiClient.selectHeaderContentType(contentTypes);

    String[] authNames = new String[] {  };

    

    
    
    TypeRef returnType = new TypeRef<List<Relationship>>() {};
    return apiClient.invokeAPI(path, "GET", queryParams, postBody, postBinaryBody, headerParams, formParams, accept, contentType, authNames, returnType);
    
    


  }
  
  /**
   * Create relationship
   * 
   * @param nedId 
   * @param body 
   * @param authorization 
   * @return Relationship
   */
  public Relationship createRelationship (Integer nedId, Relationship body, String authorization) throws ApiException {
    Object postBody = body;
    byte[] postBinaryBody = null;
    
     // verify the required parameter 'nedId' is set
     if (nedId == null) {
        throw new ApiException(400, "Missing the required parameter 'nedId' when calling createRelationship");
     }
     
    // create path and map variables
    String path = "/individuals/{nedId}/relationships".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "nedId" + "\\}", apiClient.escapeString(nedId.toString()));

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    Map<String, String> headerParams = new HashMap<String, String>();
    Map<String, Object> formParams = new HashMap<String, Object>();

    

    if (authorization != null)
    headerParams.put("Authorization", apiClient.parameterToString(authorization));
    

    

    final String[] accepts = {
      
    };
    final String accept = apiClient.selectHeaderAccept(accepts);

    final String[] contentTypes = {
      
    };
    final String contentType = apiClient.selectHeaderContentType(contentTypes);

    String[] authNames = new String[] {  };

    

    
    
    TypeRef returnType = new TypeRef<Relationship>() {};
    return apiClient.invokeAPI(path, "POST", queryParams, postBody, postBinaryBody, headerParams, formParams, accept, contentType, authNames, returnType);
    
    


  }
  
  /**
   * Read relationship
   * 
   * @param nedId 
   * @param relationshipId 
   * @return Relationship
   */
  public Relationship getRelationship (Integer nedId, Integer relationshipId) throws ApiException {
    Object postBody = null;
    byte[] postBinaryBody = null;
    
     // verify the required parameter 'nedId' is set
     if (nedId == null) {
        throw new ApiException(400, "Missing the required parameter 'nedId' when calling getRelationship");
     }
     
     // verify the required parameter 'relationshipId' is set
     if (relationshipId == null) {
        throw new ApiException(400, "Missing the required parameter 'relationshipId' when calling getRelationship");
     }
     
    // create path and map variables
    String path = "/individuals/{nedId}/relationships/{relationshipId}".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "nedId" + "\\}", apiClient.escapeString(nedId.toString()))
      .replaceAll("\\{" + "relationshipId" + "\\}", apiClient.escapeString(relationshipId.toString()));

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    Map<String, String> headerParams = new HashMap<String, String>();
    Map<String, Object> formParams = new HashMap<String, Object>();

    

    

    

    final String[] accepts = {
      
    };
    final String accept = apiClient.selectHeaderAccept(accepts);

    final String[] contentTypes = {
      
    };
    final String contentType = apiClient.selectHeaderContentType(contentTypes);

    String[] authNames = new String[] {  };

    

    
    
    TypeRef returnType = new TypeRef<Relationship>() {};
    return apiClient.invokeAPI(path, "GET", queryParams, postBody, postBinaryBody, headerParams, formParams, accept, contentType, authNames, returnType);
    
    


  }
  
  /**
   * Update relationship
   * 
   * @param nedId 
   * @param relationshipId 
   * @param authorization 
   * @param body 
   * @return Relationship
   */
  public Relationship updateRelationship (Integer nedId, Integer relationshipId, String authorization, Relationship body) throws ApiException {
    Object postBody = body;
    byte[] postBinaryBody = null;
    
     // verify the required parameter 'nedId' is set
     if (nedId == null) {
        throw new ApiException(400, "Missing the required parameter 'nedId' when calling updateRelationship");
     }
     
     // verify the required parameter 'relationshipId' is set
     if (relationshipId == null) {
        throw new ApiException(400, "Missing the required parameter 'relationshipId' when calling updateRelationship");
     }
     
    // create path and map variables
    String path = "/individuals/{nedId}/relationships/{relationshipId}".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "nedId" + "\\}", apiClient.escapeString(nedId.toString()))
      .replaceAll("\\{" + "relationshipId" + "\\}", apiClient.escapeString(relationshipId.toString()));

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    Map<String, String> headerParams = new HashMap<String, String>();
    Map<String, Object> formParams = new HashMap<String, Object>();

    

    if (authorization != null)
    headerParams.put("Authorization", apiClient.parameterToString(authorization));
    

    

    final String[] accepts = {
      
    };
    final String accept = apiClient.selectHeaderAccept(accepts);

    final String[] contentTypes = {
      
    };
    final String contentType = apiClient.selectHeaderContentType(contentTypes);

    String[] authNames = new String[] {  };

    

    
    
    TypeRef returnType = new TypeRef<Relationship>() {};
    return apiClient.invokeAPI(path, "PUT", queryParams, postBody, postBinaryBody, headerParams, formParams, accept, contentType, authNames, returnType);
    
    


  }
  
  /**
   * Delete relationship
   * 
   * @param nedId 
   * @param relationshipId 
   * @param authorization 
   * @return void
   */
  public void deleteRelationship (Integer nedId, Integer relationshipId, String authorization) throws ApiException {
    Object postBody = null;
    byte[] postBinaryBody = null;
    
     // verify the required parameter 'nedId' is set
     if (nedId == null) {
        throw new ApiException(400, "Missing the required parameter 'nedId' when calling deleteRelationship");
     }
     
     // verify the required parameter 'relationshipId' is set
     if (relationshipId == null) {
        throw new ApiException(400, "Missing the required parameter 'relationshipId' when calling deleteRelationship");
     }
     
    // create path and map variables
    String path = "/individuals/{nedId}/relationships/{relationshipId}".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "nedId" + "\\}", apiClient.escapeString(nedId.toString()))
      .replaceAll("\\{" + "relationshipId" + "\\}", apiClient.escapeString(relationshipId.toString()));

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    Map<String, String> headerParams = new HashMap<String, String>();
    Map<String, Object> formParams = new HashMap<String, Object>();

    

    if (authorization != null)
    headerParams.put("Authorization", apiClient.parameterToString(authorization));
    

    

    final String[] accepts = {
      
    };
    final String accept = apiClient.selectHeaderAccept(accepts);

    final String[] contentTypes = {
      
    };
    final String contentType = apiClient.selectHeaderContentType(contentTypes);

    String[] authNames = new String[] {  };

    

    
    
    apiClient.invokeAPI(path, "DELETE", queryParams, postBody, postBinaryBody, headerParams, formParams, accept, contentType, authNames, null);
    
    


  }
  
  /**
   * List UIDs
   * 
   * @param nedId 
   * @return List<Uniqueidentifier>
   */
  public List<Uniqueidentifier> getUids (Integer nedId) throws ApiException {
    Object postBody = null;
    byte[] postBinaryBody = null;
    
     // verify the required parameter 'nedId' is set
     if (nedId == null) {
        throw new ApiException(400, "Missing the required parameter 'nedId' when calling getUids");
     }
     
    // create path and map variables
    String path = "/individuals/{nedId}/uids".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "nedId" + "\\}", apiClient.escapeString(nedId.toString()));

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    Map<String, String> headerParams = new HashMap<String, String>();
    Map<String, Object> formParams = new HashMap<String, Object>();

    

    

    

    final String[] accepts = {
      
    };
    final String accept = apiClient.selectHeaderAccept(accepts);

    final String[] contentTypes = {
      
    };
    final String contentType = apiClient.selectHeaderContentType(contentTypes);

    String[] authNames = new String[] {  };

    

    
    
    TypeRef returnType = new TypeRef<List<Uniqueidentifier>>() {};
    return apiClient.invokeAPI(path, "GET", queryParams, postBody, postBinaryBody, headerParams, formParams, accept, contentType, authNames, returnType);
    
    


  }
  
  /**
   * Create UID
   * 
   * @param nedId 
   * @param body 
   * @param authorization 
   * @return Uniqueidentifier
   */
  public Uniqueidentifier createUid (Integer nedId, Uniqueidentifier body, String authorization) throws ApiException {
    Object postBody = body;
    byte[] postBinaryBody = null;
    
     // verify the required parameter 'nedId' is set
     if (nedId == null) {
        throw new ApiException(400, "Missing the required parameter 'nedId' when calling createUid");
     }
     
    // create path and map variables
    String path = "/individuals/{nedId}/uids".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "nedId" + "\\}", apiClient.escapeString(nedId.toString()));

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    Map<String, String> headerParams = new HashMap<String, String>();
    Map<String, Object> formParams = new HashMap<String, Object>();

    

    if (authorization != null)
    headerParams.put("Authorization", apiClient.parameterToString(authorization));
    

    

    final String[] accepts = {
      
    };
    final String accept = apiClient.selectHeaderAccept(accepts);

    final String[] contentTypes = {
      
    };
    final String contentType = apiClient.selectHeaderContentType(contentTypes);

    String[] authNames = new String[] {  };

    

    
    
    TypeRef returnType = new TypeRef<Uniqueidentifier>() {};
    return apiClient.invokeAPI(path, "POST", queryParams, postBody, postBinaryBody, headerParams, formParams, accept, contentType, authNames, returnType);
    
    


  }
  
  /**
   * Read uid
   * 
   * @param nedId 
   * @param id 
   * @return Uniqueidentifier
   */
  public Uniqueidentifier getUid (Integer nedId, Integer id) throws ApiException {
    Object postBody = null;
    byte[] postBinaryBody = null;
    
     // verify the required parameter 'nedId' is set
     if (nedId == null) {
        throw new ApiException(400, "Missing the required parameter 'nedId' when calling getUid");
     }
     
     // verify the required parameter 'id' is set
     if (id == null) {
        throw new ApiException(400, "Missing the required parameter 'id' when calling getUid");
     }
     
    // create path and map variables
    String path = "/individuals/{nedId}/uids/{id}".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "nedId" + "\\}", apiClient.escapeString(nedId.toString()))
      .replaceAll("\\{" + "id" + "\\}", apiClient.escapeString(id.toString()));

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    Map<String, String> headerParams = new HashMap<String, String>();
    Map<String, Object> formParams = new HashMap<String, Object>();

    

    

    

    final String[] accepts = {
      
    };
    final String accept = apiClient.selectHeaderAccept(accepts);

    final String[] contentTypes = {
      
    };
    final String contentType = apiClient.selectHeaderContentType(contentTypes);

    String[] authNames = new String[] {  };

    

    
    
    TypeRef returnType = new TypeRef<Uniqueidentifier>() {};
    return apiClient.invokeAPI(path, "GET", queryParams, postBody, postBinaryBody, headerParams, formParams, accept, contentType, authNames, returnType);
    
    


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
  public Uniqueidentifier updateUid (Integer nedId, Integer id, String authorization, Uniqueidentifier body) throws ApiException {
    Object postBody = body;
    byte[] postBinaryBody = null;
    
     // verify the required parameter 'nedId' is set
     if (nedId == null) {
        throw new ApiException(400, "Missing the required parameter 'nedId' when calling updateUid");
     }
     
     // verify the required parameter 'id' is set
     if (id == null) {
        throw new ApiException(400, "Missing the required parameter 'id' when calling updateUid");
     }
     
    // create path and map variables
    String path = "/individuals/{nedId}/uids/{id}".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "nedId" + "\\}", apiClient.escapeString(nedId.toString()))
      .replaceAll("\\{" + "id" + "\\}", apiClient.escapeString(id.toString()));

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    Map<String, String> headerParams = new HashMap<String, String>();
    Map<String, Object> formParams = new HashMap<String, Object>();

    

    if (authorization != null)
    headerParams.put("Authorization", apiClient.parameterToString(authorization));
    

    

    final String[] accepts = {
      
    };
    final String accept = apiClient.selectHeaderAccept(accepts);

    final String[] contentTypes = {
      
    };
    final String contentType = apiClient.selectHeaderContentType(contentTypes);

    String[] authNames = new String[] {  };

    

    
    
    TypeRef returnType = new TypeRef<Uniqueidentifier>() {};
    return apiClient.invokeAPI(path, "PUT", queryParams, postBody, postBinaryBody, headerParams, formParams, accept, contentType, authNames, returnType);
    
    


  }
  
  /**
   * Delete UID
   * 
   * @param nedId 
   * @param id 
   * @param authorization 
   * @return void
   */
  public void deleteUid (Integer nedId, Integer id, String authorization) throws ApiException {
    Object postBody = null;
    byte[] postBinaryBody = null;
    
     // verify the required parameter 'nedId' is set
     if (nedId == null) {
        throw new ApiException(400, "Missing the required parameter 'nedId' when calling deleteUid");
     }
     
     // verify the required parameter 'id' is set
     if (id == null) {
        throw new ApiException(400, "Missing the required parameter 'id' when calling deleteUid");
     }
     
    // create path and map variables
    String path = "/individuals/{nedId}/uids/{id}".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "nedId" + "\\}", apiClient.escapeString(nedId.toString()))
      .replaceAll("\\{" + "id" + "\\}", apiClient.escapeString(id.toString()));

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    Map<String, String> headerParams = new HashMap<String, String>();
    Map<String, Object> formParams = new HashMap<String, Object>();

    

    if (authorization != null)
    headerParams.put("Authorization", apiClient.parameterToString(authorization));
    

    

    final String[] accepts = {
      
    };
    final String accept = apiClient.selectHeaderAccept(accepts);

    final String[] contentTypes = {
      
    };
    final String contentType = apiClient.selectHeaderContentType(contentTypes);

    String[] authNames = new String[] {  };

    

    
    
    apiClient.invokeAPI(path, "DELETE", queryParams, postBody, postBinaryBody, headerParams, formParams, accept, contentType, authNames, null);
    
    


  }
  
  /**
   * Read individual by UID
   * 
   * @param uidType 
   * @param uidValue 
   * @return IndividualComposite
   */
  public IndividualComposite readIndividualByUid (String uidType, String uidValue) throws ApiException {
    Object postBody = null;
    byte[] postBinaryBody = null;
    
     // verify the required parameter 'uidType' is set
     if (uidType == null) {
        throw new ApiException(400, "Missing the required parameter 'uidType' when calling readIndividualByUid");
     }
     
     // verify the required parameter 'uidValue' is set
     if (uidValue == null) {
        throw new ApiException(400, "Missing the required parameter 'uidValue' when calling readIndividualByUid");
     }
     
    // create path and map variables
    String path = "/individuals/{uidType}/{uidValue}".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "uidType" + "\\}", apiClient.escapeString(uidType.toString()))
      .replaceAll("\\{" + "uidValue" + "\\}", apiClient.escapeString(uidValue.toString()));

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    Map<String, String> headerParams = new HashMap<String, String>();
    Map<String, Object> formParams = new HashMap<String, Object>();

    

    

    

    final String[] accepts = {
      
    };
    final String accept = apiClient.selectHeaderAccept(accepts);

    final String[] contentTypes = {
      
    };
    final String contentType = apiClient.selectHeaderContentType(contentTypes);

    String[] authNames = new String[] {  };

    

    
    
    TypeRef returnType = new TypeRef<IndividualComposite>() {};
    return apiClient.invokeAPI(path, "GET", queryParams, postBody, postBinaryBody, headerParams, formParams, accept, contentType, authNames, returnType);
    
    


  }
  
}
