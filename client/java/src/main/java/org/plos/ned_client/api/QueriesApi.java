package org.plos.ned_client.api;

import com.sun.jersey.api.client.GenericType;

import org.plos.ned_client.ApiException;
import org.plos.ned_client.ApiClient;
import org.plos.ned_client.Configuration;
import org.plos.ned_client.Pair;

import org.plos.ned_client.model.Alert;

import java.util.*;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2016-03-03T13:54:47.854-08:00")
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
  public List<Alert> getAlerts(String frequency) throws ApiException {
    Object postBody = null;
    
    // create path and map variables
    String path = "/queries/alerts".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    Map<String, String> headerParams = new HashMap<String, String>();
    Map<String, Object> formParams = new HashMap<String, Object>();

    
    queryParams.addAll(apiClient.parameterToPairs("", "frequency", frequency));
    

    

    

    final String[] accepts = {
      "application/json"
    };
    final String accept = apiClient.selectHeaderAccept(accepts);

    final String[] contentTypes = {
      
    };
    final String contentType = apiClient.selectHeaderContentType(contentTypes);

    String[] authNames = new String[] { "basic" };

    
    GenericType<List<Alert>> returnType = new GenericType<List<Alert>>() {};
    return apiClient.invokeAPI(path, "GET", queryParams, postBody, headerParams, formParams, accept, contentType, authNames, returnType);
    
  }
  
}
