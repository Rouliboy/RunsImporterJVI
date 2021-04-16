package com.api.run.model;

public class SchoolOperationCovers {
  private int id;
  
  private String timeChanged;
  
  private String userId;
  
  private String frequency;
  
  private ArrivalBellTime arrivalBellTime;
  
  private DepartBellTime departBellTime;
  
  private int schoolOperationId;
  
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
  
  public void setFrequency(String frequency) {
    this.frequency = frequency;
  }
  
  public String getFrequency() {
    return this.frequency;
  }
  
  public void setArrivalBellTime(ArrivalBellTime arrivalBellTime) {
    this.arrivalBellTime = arrivalBellTime;
  }
  
  public ArrivalBellTime getArrivalBellTime() {
    return this.arrivalBellTime;
  }
  
  public void setDepartBellTime(DepartBellTime departBellTime) {
    this.departBellTime = departBellTime;
  }
  
  public DepartBellTime getDepartBellTime() {
    return this.departBellTime;
  }
  
  public void setSchoolOperationId(int schoolOperationId) {
    this.schoolOperationId = schoolOperationId;
  }
  
  public int getSchoolOperationId() {
    return this.schoolOperationId;
  }
}
