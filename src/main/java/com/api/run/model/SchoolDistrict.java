package com.api.run.model;

public class SchoolDistrict {
  private int id;
  
  private String timeChanged;
  
  private String userId;
  
  private String code;
  
  private String description;
  
  private String county;
  
  private String url;
  
  private int calId;
  
  public void setId(int id) {
    this.id = id;
  }
  
  public int getId() {
    return this.id;
  }
  
  public void setTimeChanged(String timeChanged) {
    this.timeChanged = timeChanged;
  }
  
  public String getTimeChanged() {
    return this.timeChanged;
  }
  
  public void setUserId(String userId) {
    this.userId = userId;
  }
  
  public String getUserId() {
    return this.userId;
  }
  
  public void setCode(String code) {
    this.code = code;
  }
  
  public String getCode() {
    return this.code;
  }
  
  public void setDescription(String description) {
    this.description = description;
  }
  
  public String getDescription() {
    return this.description;
  }
  
  public void setCounty(String county) {
    this.county = county;
  }
  
  public String getCounty() {
    return this.county;
  }
  
  public void setUrl(String url) {
    this.url = url;
  }
  
  public String getUrl() {
    return this.url;
  }
  
  public void setCalId(int calId) {
    this.calId = calId;
  }
  
  public int getCalId() {
    return this.calId;
  }
}
