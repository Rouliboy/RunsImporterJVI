package com.api.run.model;

public class Schools {
  private School school;
  
  private BellTime bellTime;
  
  public void setSchool(School school) {
    this.school = school;
  }
  
  public School getSchool() {
    return this.school;
  }
  
  public void setBellTime(BellTime bellTime) {
    this.bellTime = bellTime;
  }
  
  public BellTime getBellTime() {
    return this.bellTime;
  }
}
