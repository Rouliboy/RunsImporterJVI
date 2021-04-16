package com.api.run.model;

public class ArrivalBellTime {
  private int id;
  
  private String timeChanged;
  
  private String userId;
  
  private String key;
  
  private String bell;
  
  private String early;
  
  private String late;
  
  private String depart;
  
  private String type;
  
  private int section;
  
  private int schoolId;
  
  private String frequency;
  
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
  
  public void setKey(String key) {
    this.key = key;
  }
  
  public String getKey() {
    return this.key;
  }
  
  public void setBell(String bell) {
    this.bell = bell;
  }
  
  public String getBell() {
    return this.bell;
  }
  
  public void setEarly(String early) {
    this.early = early;
  }
  
  public String getEarly() {
    return this.early;
  }
  
  public void setLate(String late) {
    this.late = late;
  }
  
  public String getLate() {
    return this.late;
  }
  
  public void setDepart(String depart) {
    this.depart = depart;
  }
  
  public String getDepart() {
    return this.depart;
  }
  
  public void setType(String type) {
    this.type = type;
  }
  
  public String getType() {
    return this.type;
  }
  
  public void setSection(int section) {
    this.section = section;
  }
  
  public int getSection() {
    return this.section;
  }
  
  public void setSchoolId(int schoolId) {
    this.schoolId = schoolId;
  }
  
  public int getSchoolId() {
    return this.schoolId;
  }
  
  public void setFrequency(String frequency) {
    this.frequency = frequency;
  }
  
  public String getFrequency() {
    return this.frequency;
  }
}
