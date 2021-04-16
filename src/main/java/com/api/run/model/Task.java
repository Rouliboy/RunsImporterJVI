package com.api.run.model;

public class Task {
  private String id;
  
  private String userID;
  
  private String taskDescription;
  
  private String taskType;
  
  private String taskState;
  
  private String dateTimeCreated;
  
  private String lastAccessAsLiveTask;
  
  private String nmbrOfNotifiedDataConflicts;
  
  private String dataArea;
  
  private String[] searchParams;
  
  private String[] bellTimeIDs;
  
  public String getId() {
    return this.id;
  }
  
  public void setId(String id) {
    this.id = id;
  }
  
  public String getUserID() {
    return this.userID;
  }
  
  public void setUserID(String userID) {
    this.userID = userID;
  }
  
  public String getTaskDescription() {
    return this.taskDescription;
  }
  
  public void setTaskDescription(String taskDescription) {
    this.taskDescription = taskDescription;
  }
  
  public String getTaskType() {
    return this.taskType;
  }
  
  public void setTaskType(String taskType) {
    this.taskType = taskType;
  }
  
  public String getTaskState() {
    return this.taskState;
  }
  
  public void setTaskState(String taskState) {
    this.taskState = taskState;
  }
  
  public String getDateTimeCreated() {
    return this.dateTimeCreated;
  }
  
  public void setDateTimeCreated(String dateTimeCreated) {
    this.dateTimeCreated = dateTimeCreated;
  }
  
  public String getLastAccessAsLiveTask() {
    return this.lastAccessAsLiveTask;
  }
  
  public void setLastAccessAsLiveTask(String lastAccessAsLiveTask) {
    this.lastAccessAsLiveTask = lastAccessAsLiveTask;
  }
  
  public String getNmbrOfNotifiedDataConflicts() {
    return this.nmbrOfNotifiedDataConflicts;
  }
  
  public void setNmbrOfNotifiedDataConflicts(String nmbrOfNotifiedDataConflicts) {
    this.nmbrOfNotifiedDataConflicts = nmbrOfNotifiedDataConflicts;
  }
  
  public String getDataArea() {
    return this.dataArea;
  }
  
  public void setDataArea(String dataArea) {
    this.dataArea = dataArea;
  }
  
  public String[] getSearchParams() {
    return this.searchParams;
  }
  
  public void setSearchParams(String[] searchParams) {
    this.searchParams = searchParams;
  }
  
  public String[] getBellTimeIDs() {
    return this.bellTimeIDs;
  }
  
  public void setBellTimeIDs(String[] bellTimeIDs) {
    this.bellTimeIDs = bellTimeIDs;
  }
}
