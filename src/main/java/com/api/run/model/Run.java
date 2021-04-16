package com.api.run.model;

import java.util.List;

public class Run {
  private String taskID;
  
  private String code;
  
  private boolean dynamicFrequency;
  
  private String description;
  
  private int maxLoad;
  
  private int maxDuration;
  
  private String comments;
  
  private List<Schools> schools;
  
  public void setTaskID(String taskID) {
    this.taskID = taskID;
  }
  
  public String getTaskID() {
    return this.taskID;
  }
  
  public void setCode(String code) {
    this.code = code;
  }
  
  public String getCode() {
    return this.code;
  }
  
  public void setDynamicFrequency(boolean dynamicFrequency) {
    this.dynamicFrequency = dynamicFrequency;
  }
  
  public boolean getDynamicFrequency() {
    return this.dynamicFrequency;
  }
  
  public void setDescription(String description) {
    this.description = description;
  }
  
  public String getDescription() {
    return this.description;
  }
  
  public void setMaxLoad(int maxLoad) {
    this.maxLoad = maxLoad;
  }
  
  public int getMaxLoad() {
    return this.maxLoad;
  }
  
  public void setMaxDuration(int maxDuration) {
    this.maxDuration = maxDuration;
  }
  
  public int getMaxDuration() {
    return this.maxDuration;
  }
  
  public void setComments(String comments) {
    this.comments = comments;
  }
  
  public String getComments() {
    return this.comments;
  }
  
  public void setSchools(List<Schools> schools) {
    this.schools = schools;
  }
  
  public List<Schools> getSchools() {
    return this.schools;
  }
}
