package com.api.run.model;

import java.util.List;

public class School {
  private String address;
  
  private String boardName;
  
  private String city;
  
  private String code;
  
  private String comments;
  
  private String country;
  
  private String level;
  
  private int gradeId;
  
  private int maxRideTime;
  
  private String name;
  
  private String postalCode;
  
  private List<BellTime> bellTimes;
  
  private SchoolDistrict schoolDistrict;
  
  private List<SchoolLocations> schoolLocations;
  
  private List<SchoolOperations> schoolOperations;
  
  public void setAddress(String address) {
    this.address = address;
  }
  
  public String getAddress() {
    return this.address;
  }
  
  public void setBoardName(String boardName) {
    this.boardName = boardName;
  }
  
  public String getBoardName() {
    return this.boardName;
  }
  
  public void setCity(String city) {
    this.city = city;
  }
  
  public String getCity() {
    return this.city;
  }
  
  public void setCode(String code) {
    this.code = code;
  }
  
  public String getCode() {
    return this.code;
  }
  
  public void setComments(String comments) {
    this.comments = comments;
  }
  
  public String getComments() {
    return this.comments;
  }
  
  public void setCountry(String country) {
    this.country = country;
  }
  
  public String getCountry() {
    return this.country;
  }
  
  public void setLevel(String level) {
    this.level = level;
  }
  
  public String getLevel() {
    return this.level;
  }
  
  public void setGradeId(int gradeId) {
    this.gradeId = gradeId;
  }
  
  public int getGradeId() {
    return this.gradeId;
  }
  
  public void setMaxRideTime(int maxRideTime) {
    this.maxRideTime = maxRideTime;
  }
  
  public int getMaxRideTime() {
    return this.maxRideTime;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  public String getName() {
    return this.name;
  }
  
  public void setPostalCode(String postalCode) {
    this.postalCode = postalCode;
  }
  
  public String getPostalCode() {
    return this.postalCode;
  }
  
  public void setBellTimes(List<BellTime> bellTimes) {
    this.bellTimes = bellTimes;
  }
  
  public List<BellTime> getBellTimes() {
    return this.bellTimes;
  }
  
  public void setSchoolDistrict(SchoolDistrict schoolDistrict) {
    this.schoolDistrict = schoolDistrict;
  }
  
  public SchoolDistrict getSchoolDistrict() {
    return this.schoolDistrict;
  }
  
  public void setSchoolLocations(List<SchoolLocations> schoolLocations) {
    this.schoolLocations = schoolLocations;
  }
  
  public List<SchoolLocations> getSchoolLocations() {
    return this.schoolLocations;
  }
  
  public void setSchoolOperations(List<SchoolOperations> schoolOperations) {
    this.schoolOperations = schoolOperations;
  }
  
  public List<SchoolOperations> getSchoolOperations() {
    return this.schoolOperations;
  }
}
