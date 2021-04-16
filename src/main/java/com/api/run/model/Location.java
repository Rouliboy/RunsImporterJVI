package com.api.run.model;

public class Location {
  private int id;
  
  private String timeChanged;
  
  private String userId;
  
  private String address;
  
  private Coordinate coordinate;
  
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
  
  public void setAddress(String address) {
    this.address = address;
  }
  
  public String getAddress() {
    return this.address;
  }
  
  public void setCoordinate(Coordinate coordinate) {
    this.coordinate = coordinate;
  }
  
  public Coordinate getCoordinate() {
    return this.coordinate;
  }
}
