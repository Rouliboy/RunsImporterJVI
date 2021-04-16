package com.api.run.model;

public class Token {
  private String personId;
  
  private String accessToken;
  
  public String getPersonId() {
    return this.personId;
  }
  
  public void setPersonId(String personId) {
    this.personId = personId;
  }
  
  public String getAccessToken() {
    return this.accessToken;
  }
  
  public void setAccessToken(String accessToken) {
    this.accessToken = accessToken;
  }
}
