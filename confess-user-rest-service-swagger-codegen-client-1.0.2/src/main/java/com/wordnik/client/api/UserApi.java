package com.wordnik.client.api;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.wordnik.client.common.ApiException;
import com.wordnik.client.common.ApiInvoker;
import com.wordnik.client.model.ResponseEntityUser;
import com.wordnik.client.model.User;

public class UserApi {
  String basePath = "/";
  ApiInvoker apiInvoker = ApiInvoker.getInstance();

  public ApiInvoker getInvoker() {
    return apiInvoker;
  }
  
  public void setBasePath(String basePath) {
    this.basePath = basePath;
  }
  
  public String getBasePath() {
    return basePath;
  }

  public User getUser (Long id) throws ApiException {
    // verify required params are set
    if(id == null ) {
       throw new ApiException(400, "missing required params");
    }
    // create path and map variables
    String path = "/user".replaceAll("\\{format\\}","json");

    // query params
    Map<String, String> queryParams = new HashMap<String, String>();
    Map<String, String> headerParams = new HashMap<String, String>();

    if(!"null".equals(String.valueOf(id)))
      queryParams.put("id", String.valueOf(id));
    String contentType = "application/json";

    try {
      String response = apiInvoker.invokeAPI(basePath, path, "GET", queryParams, null, headerParams, contentType);
      if(response != null){
    	  System.out.println("response: " + response);
    	  User entity = (User)ApiInvoker.deserialize(response, "", User.class);

          return (User) entity;
      }
      else {
        return null;
      }
    } catch (ApiException ex) {
      if(ex.getCode() == 404) {
      	return null;
      }
      else {
        throw ex;
      }
    }
  }
  public ResponseEntity<User> updateUser (Long id, String name) throws ApiException {
    // verify required params are set
    if(id == null || name == null ) {
       throw new ApiException(400, "missing required params");
    }
    // create path and map variables
    String path = "/user".replaceAll("\\{format\\}","json");

    // query params
    Map<String, String> queryParams = new HashMap<String, String>();
    Map<String, String> headerParams = new HashMap<String, String>();

    if(!"null".equals(String.valueOf(id)))
      queryParams.put("id", String.valueOf(id));
    if(!"null".equals(String.valueOf(name)))
      queryParams.put("name", String.valueOf(name));
    String contentType = "application/json";

    try {
      String response = apiInvoker.invokeAPI(basePath, path, "POST", queryParams, null, headerParams, contentType);
      if(response != null){
        return (ResponseEntity<User>) ApiInvoker.deserialize(response, "", ResponseEntity.class);
      }
      else {
        return null;
      }
    } catch (ApiException ex) {
      if(ex.getCode() == 404) {
      	return null;
      }
      else {
        throw ex;
      }
    }
  }
  }

