package com.api.run.model;

import java.util.List;

public class School_Data {
  private int id;
  
  private String timeChanged;
  
  private String userId;
  
  private String key;
  
  private String code;
  
  private String name;
  
  private String level;
  
  private String address;
  
  private String city;
  
  private String state;
  
  private String postalCode;
  
  private String country;
  
  private int maxRideTime;
  
  private String transportSchool;
  
  private String boardName;
  
  private String schoolTypeDesc;
  
  private String url;
  
  private String comments;
  
  private SchoolDistrict schoolDistrict;
  
  private List<BellTime> bellTimes;
  
  private List<SchoolLocations> schoolLocations;
  
  private List<SchoolOperations> schoolOperations;
  
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
  
  public void setKey(String key) {
    this.key = key;
  }
  
  public String getKey() {
    return this.key;
  }
  
  public void setCode(String code) {
    this.code = code;
  }
  
  public String getCode() {
    return this.code;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  public String getName() {
    return this.name;
  }
  
  public void setLevel(String level) {
    this.level = level;
  }
  
  public String getLevel() {
    return this.level;
  }
  
  public void setAddress(String address) {
    this.address = address;
  }
  
  public String getAddress() {
    return this.address;
  }
  
  public void setCity(String city) {
    this.city = city;
  }
  
  public String getCity() {
    return this.city;
  }
  
  public void setState(String state) {
    this.state = state;
  }
  
  public String getState() {
    return this.state;
  }
  
  public void setPostalCode(String postalCode) {
    this.postalCode = postalCode;
  }
  
  public String getPostalCode() {
    return this.postalCode;
  }
  
  public void setCountry(String country) {
    this.country = country;
  }
  
  public String getCountry() {
    return this.country;
  }
  
  public void setMaxRideTime(int maxRideTime) {
    this.maxRideTime = maxRideTime;
  }
  
  public int getMaxRideTime() {
    return this.maxRideTime;
  }
  
  public void setTransportSchool(String transportSchool) {
    this.transportSchool = transportSchool;
  }
  
  public String getTransportSchool() {
    return this.transportSchool;
  }
  
  public void setBoardName(String boardName) {
    this.boardName = boardName;
  }
  
  public String getBoardName() {
    return this.boardName;
  }
  
  public void setSchoolTypeDesc(String schoolTypeDesc) {
    this.schoolTypeDesc = schoolTypeDesc;
  }
  
  public String getSchoolTypeDesc() {
    return this.schoolTypeDesc;
  }
  
  public void setUrl(String url) {
    this.url = url;
  }
  
  public String getUrl() {
    return this.url;
  }
  
  public void setComments(String comments) {
    this.comments = comments;
  }
  
  public String getComments() {
    return this.comments;
  }
  
  public void setSchoolDistrict(SchoolDistrict schoolDistrict) {
    this.schoolDistrict = schoolDistrict;
  }
  
  public SchoolDistrict getSchoolDistrict() {
    return this.schoolDistrict;
  }
  
  public void setBellTimes(List<BellTime> bellTimes) {
    this.bellTimes = bellTimes;
  }
  
  public List<BellTime> getBellTimes() {
    return this.bellTimes;
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
  
  public void setCalId(int calId) {
    this.calId = calId;
  }
  
  public int getCalId() {
    return this.calId;
  }
}
