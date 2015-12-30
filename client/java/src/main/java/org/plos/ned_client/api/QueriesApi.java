package org.plos.ned_client.api;

import org.plos.ned_client.ApiException;
import org.plos.ned_client.ApiClient;
import org.plos.ned_client.Configuration;
import org.plos.ned_client.Pair;
import org.plos.ned_client.TypeRef;

import org.plos.ned_client.model.Alert;

import java.util.*;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2015-12-29T17:05:32.045-08:00")
public class QueriesApi {
  private ApiClient apiClient;

  public QueriesApi() {
    this(Configuration.getDefaultApiClient());
  }

  public QueriesApi(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  public ApiClient getApiClient() {
    return apiClient;
  }

  public void setApiClient(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  
  /**
   * Get a list of search alerts by type
   * 
   * @param frequency 
   * @return List<Alert>
   */
  public List<Alert> getAlerts (String frequency) throws ApiException {
    Object postBody = null;
    byte[] postBinaryBody = null;
    
     // verify the required parameter 'frequency' is set
     if (frequency == null) {
        throw new ApiException(400, "Missing the required parameter 'frequency' when calling getAlerts");
     }
     
    // create path and map variables
    String path = "/queries/alerts/{frequency}".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "frequency" + "\\}", apiClient.escapeString(frequency.toString()));

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

    String[] authNames = new String[] { "basic" };

    

    
    
    TypeRef returnType = new TypeRef<List<Alert>>() {};
    return apiClient.invokeAPI(path, "GET", queryParams, postBody, postBinaryBody, headerParams, formParams, accept, contentType, authNames, returnType);
    
    


  }
  
}
