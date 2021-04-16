package com.api.run.model;

import com.opencsv.bean.CsvBindByName;

public class CsvRunBean {
  @CsvBindByName(column = "RunID")
  private String RunID;
  
  @CsvBindByName(column = "Description")
  private String Description;
  
  @CsvBindByName(column = "Max_Load")
  private Integer Max_Load;
  
  @CsvBindByName(column = "Max_Duration")
  private Integer Max_Duration;
  
  @CsvBindByName(column = "Comments")
  private String Comments;
  
  @CsvBindByName(column = "School_Code")
  private String School_Code;
  
  @CsvBindByName(column = "Bell_Time")
  private String Bell_Time;

  @CsvBindByName(column = "Bus_Time")
  private String busTime;
  
  public String getRunID() {
    return this.RunID;
  }
  
  public void setRunID(String runID) {
    this.RunID = runID;
  }
  
  public String getDescription() {
    return this.Description;
  }
  
  public void setDescription(String description) {
    this.Description = description;
  }
  
  public Integer getMax_Load() {
    if (this.Max_Load == null)
      this.Max_Load = Integer.valueOf(0); 
    return this.Max_Load;
  }
  
  public void setMax_Load(String max_Load) {
    if (max_Load.trim().equals("") || max_Load.trim().equals("null")) {
      this.Max_Load = Integer.valueOf(0);
    } else {
      this.Max_Load = Integer.valueOf(Integer.parseInt(max_Load));
    } 
  }
  
  public Integer getMax_Duration() {
    if (this.Max_Duration == null)
      this.Max_Duration = Integer.valueOf(0); 
    return this.Max_Duration;
  }
  
  public void setMax_Duration(String max_Duration) {
    if (max_Duration.trim().equals("") || max_Duration.trim().equals("null")) {
      this.Max_Duration = Integer.valueOf(0);
    } else {
      this.Max_Duration = Integer.valueOf(Integer.parseInt(max_Duration));
    } 
  }
  
  public String getComments() {
    return this.Comments;
  }
  
  public void setComments(String comments) {
    this.Comments = comments;
  }
  
  public String getSchool_Code() {
    return this.School_Code;
  }
  
  public void setSchool_Code(String school_Code) {
    this.School_Code = school_Code;
  }
  
  public String getBell_Time() {
    return this.Bell_Time;
  }
  
  public void setBell_Time(String bell_Time) {
    this.Bell_Time = bell_Time;
  }

  public String getBusTime() {
    return busTime;
  }

  public void setBusTime(String busTime) {
    this.busTime = busTime;
  }

  @Override
  public String toString() {
    return "CsvRunBean{" +
            "RunID='" + RunID + '\'' +
            ", Description='" + Description + '\'' +
            ", Max_Load=" + Max_Load +
            ", Max_Duration=" + Max_Duration +
            ", Comments='" + Comments + '\'' +
            ", School_Code='" + School_Code + '\'' +
            ", Bell_Time='" + Bell_Time + '\'' +
            ", busTime='" + busTime + '\'' +
            '}';
  }
}
