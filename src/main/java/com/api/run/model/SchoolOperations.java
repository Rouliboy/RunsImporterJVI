package com.api.run.model;

import java.util.List;

public class SchoolOperations {
  private int id;
  
  private String timeChanged;
  
  private String userId;
  
  private int hazardType;
  
  private boolean webAllowed;
  
  private int maxAssignDist;
  
  private String schoolCalDesc;
  
  private Grade grade;
  
  private Program program;
  
  private List<SchoolOperationCovers> schoolOperationCovers;
  
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
  
  public void setHazardType(int hazardType) {
    this.hazardType = hazardType;
  }
  
  public int getHazardType() {
    return this.hazardType;
  }
  
  public void setWebAllowed(boolean webAllowed) {
    this.webAllowed = webAllowed;
  }
  
  public boolean getWebAllowed() {
    return this.webAllowed;
  }
  
  public void setMaxAssignDist(int maxAssignDist) {
    this.maxAssignDist = maxAssignDist;
  }
  
  public int getMaxAssignDist() {
    return this.maxAssignDist;
  }
  
  public void setSchoolCalDesc(String schoolCalDesc) {
    this.schoolCalDesc = schoolCalDesc;
  }
  
  public String getSchoolCalDesc() {
    return this.schoolCalDesc;
  }
  
  public void setGrade(Grade grade) {
    this.grade = grade;
  }
  
  public Grade getGrade() {
    return this.grade;
  }
  
  public void setProgram(Program program) {
    this.program = program;
  }
  
  public Program getProgram() {
    return this.program;
  }
  
  public void setSchoolOperationCovers(List<SchoolOperationCovers> schoolOperationCovers) {
    this.schoolOperationCovers = schoolOperationCovers;
  }
  
  public List<SchoolOperationCovers> getSchoolOperationCovers() {
    return this.schoolOperationCovers;
  }
  
  public void setFrequency(String frequency) {
    this.frequency = frequency;
  }
  
  public String getFrequency() {
    return this.frequency;
  }
}
