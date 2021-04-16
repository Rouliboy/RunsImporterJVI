package com.api.run.model;

public class SchoolLocations {
  private int id;
  
  private String timeChanged;
  
  private String userId;
  
  private Location location;
  
  private String type;
  
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
  
  public void setLocation(Location location) {
    this.location = location;
  }
  
  public Location getLocation() {
    return this.location;
  }
  
  public void setType(String type) {
    this.type = type;
  }
  
  public String getType() {
    return this.type;
  }
}
