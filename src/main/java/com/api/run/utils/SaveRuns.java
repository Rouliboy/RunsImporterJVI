package com.api.run.utils;

import com.api.run.model.BellTime;
import com.api.run.model.CsvRunBean;
import com.api.run.model.Run;
import com.api.run.model.RunsImportException;
import com.api.run.model.School;
import com.api.run.model.School_Data;
import com.api.run.model.Schools;
import com.api.run.model.Task;
import com.api.run.model.Token;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.opencsv.CSVReader;
import java.io.FileReader;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.util.CollectionUtils;

public class SaveRuns {
  public String url_gettoken;
  
  public String url_api = "";
  
  public String email = "";
  
  public String password = "";
  
  public String scope = "";
  
  public Integer http_timeout;
  
  public String csv_file_run;
  
  public String dataArea;
  
  static final Logger logger = Logger.getLogger(SaveRuns.class);
  
  Utils m_sevice = new Utils();

  public void save_All_Run_JVI() {
    try {
      Token token = retrieveToken();
      this.dataArea = getDataArea(token);
      if (StringUtils.isEmpty(this.dataArea)) {
        throw new RunsImportException("Failed to get data area");
      }
      Map<String, Integer> schoolsIdByCode = getAllSchool(token);
      if (CollectionUtils.isEmpty(schoolsIdByCode)) {
        throw new RunsImportException("Schools are empty");
      }

      Task task = createTask(token);
      if (task == null || task.getId() == null) {
        throw new RunsImportException("Failed to create task");
      }

      FileReader reader = new FileReader(this.csv_file_run);
      CsvToBean<CsvRunBean> csvToBean = new CsvToBeanBuilder(reader)
              .withType(CsvRunBean.class)
              .withIgnoreLeadingWhiteSpace(true)
              .build();

      List<CsvRunBean> runsImportData = csvToBean.parse();

      // Group by runsId
      Map<String, List<CsvRunBean>> runBeansByRunId = new LinkedHashMap<>();
      runsImportData.forEach(runBean -> {
        if (isValid(runBean)) {
          String runId = runBean.getRunID();
          List<CsvRunBean> list = runBeansByRunId.getOrDefault(runId, new ArrayList<>());
          list.add(runBean);
          runBeansByRunId.put(runId, list);
        } else {
          logger.error("Invalid run ignored: "+ runBean);
        }
      });
      //logger.info("runBeansByRunId="+ runBeansByRunId);
      runBeansByRunId.forEach( (runId, listOfBeans) -> {
        logger.info("Processing import of run " + runId);

        if (isValidRunBeanSet(listOfBeans, schoolsIdByCode)) {

          List<Schools> schools = new ArrayList<>();
          listOfBeans.forEach(runBean -> {
            int schoolId = schoolsIdByCode.get(runBean.getSchool_Code());
            School_Data schoolData = getOneSchool(token, schoolId);
            Schools school = buildSchoolFor(schoolData, runBean);
            schools.add(school);
          });

          String jsonRunBody = buildRunBody(schools,
                  runId,
                  listOfBeans.get(0).getDescription(),
                  listOfBeans.get(0).getMax_Load(),
                  listOfBeans.get(0).getMax_Duration(),
                  listOfBeans.get(0).getComments(),
                  task);
          String status = Save_One_Run(jsonRunBody, token);
          if (!status.equals("")) {
            logger.info(" Save run error: " + status);
            System.out.println(" Save run error: " + status);
          } else {
            logger.info(" Save run " + runId + " done !");
            System.out.println(" Save run " + runId + " done !");
          }

          status = finalizeTask(task, token);
          if (!status.equals("")) {
            logger.info("Finalize task error: " + status);
            System.out.println("Finalize task error: " + status);
          } else {
            logger.info("Finalize task done !");
            System.out.println("Finalize task done !");
          }
          status = deleteTask(task, token);
          if (!status.equals("")) {
            logger.error("Delete task error: " + status);
            System.out.println("Delete task error: " + status);
          } else {
            logger.info("Delete task done !");
            System.out.println("Delete task done !");
          }
        } else {
          logger.error("Invalid run set: "+ listOfBeans);
        }
      });

    } catch ( RunsImportException e) {
      logger.error("Import run error: " + e.getMessage());
    }
    catch (Exception ex) {
      logger.error("Unexpected error: " + ex.getMessage(), ex);
      System.out.println("Import run error: " + ex.toString());
    }
  }

  private boolean isValid(CsvRunBean runBean) {
    return runBean != null && StringUtils.isNotBlank(runBean.getRunID()) && !"null".equals(runBean.getRunID());
  }

  private boolean isValidRunBeanSet(List<CsvRunBean> runBeanSet, Map<String, Integer> schoolsIdByCode) {
    boolean valid =  runBeanSet != null && runBeanSet.size() > 0;
    if (valid) {
      for (CsvRunBean runBean: runBeanSet) {
        if (!schoolsIdByCode.containsKey(runBean.getSchool_Code())) {
          logger.error("Invalid school code: "+ runBean.getSchool_Code());
          valid = false;
        }
      }
      runBeanSet.forEach(runBean -> {

      });
    }
    return valid;
  }

  private Token retrieveToken() {
    this.url_gettoken = this.url_api + "/signin";
    Token token = this.m_sevice.getToken(this.url_gettoken, this.email, this.password, this.scope, this.http_timeout);
    if (token != null && token.getAccessToken() != "") {
      return token;
    }
    throw new RunsImportException("Failed to retrieve token");
  }
  
  public void save_All_Run() {
    String status = "";
    try {
      this.url_gettoken = this.url_api + "/signin";
      Token token = this.m_sevice.getToken(this.url_gettoken, this.email, this.password, this.scope, this.http_timeout);
      if (token != null && token.getAccessToken() != "") {
        logger.info("Starting save run into Athena...");
        System.out.println("Starting save run into Athena...");
        this.dataArea = getDataArea(token);
        if (!this.dataArea.equals("")) {
          HashMap<String, Integer> list_of_school = getAllSchool(token);
          if (list_of_school != null) {
            logger.info(" Create task ...!");
            System.out.println(" Create task..!");
            Task task = createTask(token);
            if (task != null && task.getId() != null) {
              FileReader reader = new FileReader(this.csv_file_run);
              CSVReader csvReader = new CSVReader(reader);
              List<String[]> runs = csvReader.readAll();
              for (int i = 1; i < runs.size(); i++) {
                String[] run = runs.get(i);
                logger.info("run length = "+ run.length);
                if (run.length == 8) {
                  CsvRunBean runBean = new CsvRunBean();
                  runBean.setRunID(run[0]);
                  runBean.setDescription(run[1]);
                  runBean.setMax_Load(run[2]);
                  runBean.setMax_Duration(run[3]);
                  runBean.setComments(run[4]);
                  runBean.setSchool_Code(run[5]);
                  runBean.setBell_Time(run[6]);
                  runBean.setBusTime(run[7]);
                  if (runBean.getRunID().trim().equals("") || runBean.getRunID().trim().equalsIgnoreCase("null")) {
                    logger.info("Run ID bad data : " + json_One_Run(runBean));
                    System.out.println("Run ID bad data : " + json_One_Run(runBean));
                  } else {
                    Integer school_id = list_of_school.get(runBean.getSchool_Code());
                    if (school_id != null && school_id.intValue() != 0) {
                      School_Data school_data = getOneSchool(token, school_id);
                      String json_run_body = build_Run_Body(school_data, runBean, task);
                      if (!json_run_body.equals("belltime bad data")) {
                        status = Save_One_Run(json_run_body, token);
                        if (!status.equals("")) {
                          logger.info(" Save run error: " + status);
                          System.out.println(" Save run error: " + status);
                        } else {
                          logger.info(" Save run " + runBean.getRunID() + " done !");
                          System.out.println(" Save run " + runBean.getRunID() + " done !");
                        } 
                      } else {
                        logger.info("School bellTime bad data: " + json_One_Run(runBean));
                        System.out.println("School bellTime bad data: " + json_One_Run(runBean));
                      } 
                    } else {
                      logger.info("School not matched in system: " + json_One_Run(runBean));
                      System.out.println("School not matched in system: " + json_One_Run(runBean));
                    } 
                  } 
                } else if (run.length > 1) {
                  logger.info("Run wrong format: " + Arrays.toString((Object[])run));
                  System.out.println("Run wrong format: " + Arrays.toString((Object[])run));
                } 
              } 
              reader.close();
              status = finalizeTask(task, token);
              if (!status.equals("")) {
                logger.info("Finalize task error: " + status);
                System.out.println("Finalize task error: " + status);
              } else {
                logger.info("Finalize task done !");
                System.out.println("Finalize task done !");
              } 
              status = deleteTask(task, token);
              if (!status.equals("")) {
                logger.error("Delete task error: " + status);
                System.out.println("Delete task error: " + status);
              } else {
                logger.info("Delete task done !");
                System.out.println("Delete task done !");
              } 
            } else {
              logger.info("Create task error...");
              System.out.println("Create task error...");
            } 
          } else {
            logger.info("Import run error: " + this.url_api + "/school return null");
            System.out.println("Import run error: " + this.url_api + "/school return null");
          } 
        } else {
          logger.info("Get data area error...");
          System.out.println("Get data area error...");
        } 
      } else {
        logger.info("Get token error...");
        System.out.println("Get token error...");
      } 
    } catch (Exception ex) {
      ex.printStackTrace();
      logger.error("Import run error: " + ex.getMessage(), ex);
      System.out.println("Import run error: " + ex.toString());
    } 
  }
  
  private String Save_One_Run(String json_run_body, Token token) {
    String status = "";
    try {
      String url_tasks = this.url_api + "/runs/createRun";
      String response = this.m_sevice.postToURL(url_tasks, json_run_body, this.http_timeout, Boolean.valueOf(true), token.getAccessToken(), this.dataArea);
      if (response != null && response.indexOf("HTTP error code:401") > 0) {
        token = this.m_sevice.getToken(this.url_gettoken, this.email, this.password, this.scope, this.http_timeout);
        if (token != null && token.getAccessToken() != "")
          response = this.m_sevice.postToURL(url_tasks, json_run_body, this.http_timeout, Boolean.valueOf(true), token.getAccessToken(), this.dataArea); 
      } 
      if (response.indexOf("HTTP error") < 0) {
        JSONObject jsonObject = new JSONObject(response);
        JSONArray dataObj = (JSONArray)jsonObject.get("errors");
        if (dataObj.length() > 0) {
          String errors = dataObj.get(1).toString();
          status = errors + ":" + json_run_body;
        } 
      } else {
        status = response;
      } 
    } catch (Exception ex) {
      ex.printStackTrace();
      logger.error("Import run error:" + ex.getMessage(), ex);
      System.out.println("Import run error:" + ex.toString());
    } 
    return status;
  }

  private Schools buildSchoolFor(School_Data school_data, CsvRunBean runBean) {
    School school = new School();
    school.setAddress(school_data.getAddress());
    school.setBoardName(school_data.getBoardName());
    school.setCity(school_data.getCity());
    school.setCode(school_data.getCode());
    school.setComments(school_data.getComments());
    school.setCountry(school_data.getCountry());
    school.setLevel(school_data.getLevel());
    school.setGradeId(0);
    school.setMaxRideTime(school_data.getMaxRideTime());
    school.setName(school_data.getName());
    school.setPostalCode(school_data.getPostalCode());
    school.setBellTimes(school_data.getBellTimes());
    school.setSchoolDistrict(school_data.getSchoolDistrict());
    school.setSchoolLocations(school_data.getSchoolLocations());
    school.setSchoolOperations(school_data.getSchoolOperations());
    BellTime bellTime = new BellTime();
    String[] m_bellTime = runBean.getBell_Time().split("-");
//    if (m_bellTime.length <= 1)
//      return "belltime bad data";

    // Bus time
    String busTimeCsv = runBean.getBusTime();
    String bellTimeCsv = runBean.getBell_Time();
    if (StringUtils.isBlank(bellTimeCsv) && StringUtils.isBlank(busTimeCsv)) {
      String message = "belltime or bustime must be specified";
      logger.error(message);
      return null;
    }

    // Find bell time from bell time
    // FIXME: mettre tout ça dans le validator
    if (StringUtils.isNotBlank(bellTimeCsv)) {
      if (m_bellTime.length <= 1)
        return null;
      bellTime = findBellTimeFromBellTimeCsv(school_data, m_bellTime);
    } else if (StringUtils.isNotBlank(busTimeCsv)) {
      bellTime = findBellTimeFromBusTimeCsv(school_data, busTimeCsv);
    }

    if (null == bellTime || (bellTime.getId() == 0 && bellTime.getBell() == null))
      //return "belltime bad data";
      return null;

    Schools school_bellTime = new Schools();
    school_bellTime.setSchool(school);
    school_bellTime.setBellTime(bellTime);
    return school_bellTime;
  }

  private String buildRunBody(List<Schools> schools, String runId, String runDescription, int maxLoad, int maxDuration, String comments, Task task) {
    Run run = new Run();
    run.setTaskID(task.getId());
    run.setCode(runId);
    run.setDynamicFrequency(false);
    run.setDescription(runDescription);
    run.setMaxLoad(maxLoad);
    run.setMaxDuration(maxDuration);
    run.setComments(comments);
    run.setSchools(schools);
    GsonBuilder gsonBuilder = new GsonBuilder();
    gsonBuilder.serializeNulls();
    Gson gson = gsonBuilder.create();
    String json_run = gson.toJson(run);
    return json_run;
  }
  
  private String build_Run_Body(School_Data school_data, CsvRunBean runBean, Task task) {
    School school = new School();
    school.setAddress(school_data.getAddress());
    school.setBoardName(school_data.getBoardName());
    school.setCity(school_data.getCity());
    school.setCode(school_data.getCode());
    school.setComments(school_data.getComments());
    school.setCountry(school_data.getCountry());
    school.setLevel(school_data.getLevel());
    school.setGradeId(0);
    school.setMaxRideTime(school_data.getMaxRideTime());
    school.setName(school_data.getName());
    school.setPostalCode(school_data.getPostalCode());
    school.setBellTimes(school_data.getBellTimes());
    school.setSchoolDistrict(school_data.getSchoolDistrict());
    school.setSchoolLocations(school_data.getSchoolLocations());
    school.setSchoolOperations(school_data.getSchoolOperations());
    BellTime bellTime = new BellTime();
    String[] m_bellTime = runBean.getBell_Time().split("-");
//    if (m_bellTime.length <= 1)
//      return "belltime bad data";

    // Bus time
    String busTimeCsv = runBean.getBusTime();
    String bellTimeCsv = runBean.getBell_Time();
    if (StringUtils.isBlank(bellTimeCsv) && StringUtils.isBlank(busTimeCsv)) {
      String message = "belltime or bustime must be specified";
      logger.error(message);
      return message;
    }

    // Find bell time from bell time
    if (StringUtils.isNotBlank(bellTimeCsv)) {
      if (m_bellTime.length <= 1)
        return "belltime bad data";
      bellTime = findBellTimeFromBellTimeCsv(school_data, m_bellTime);
    } else if (StringUtils.isNotBlank(busTimeCsv)) {
      bellTime = findBellTimeFromBusTimeCsv(school_data, busTimeCsv);
    }

    if (null == bellTime || (bellTime.getId() == 0 && bellTime.getBell() == null))
      return "belltime bad data";

    Schools school_bellTime = new Schools();
    school_bellTime.setSchool(school);
    school_bellTime.setBellTime(bellTime);
    ArrayList<Schools> schools = new ArrayList<>(1);
    schools.add(school_bellTime);
    Run run = new Run();
    run.setTaskID(task.getId());
    run.setCode(runBean.getRunID());
    run.setDynamicFrequency(false);
    run.setDescription(runBean.getDescription());
    run.setMaxLoad(runBean.getMax_Load().intValue());
    run.setMaxDuration(runBean.getMax_Duration().intValue());
    run.setComments(runBean.getComments());
    run.setSchools(schools);
    GsonBuilder gsonBuilder = new GsonBuilder();
    gsonBuilder.serializeNulls();
    Gson gson = gsonBuilder.create();
    String json_run = gson.toJson(run);
    return json_run;
  }

  private BellTime findBellTimeFromBusTimeCsv(School_Data school_data, String busTimeCsv) {
    LocalTime busTime = LocalTime.parse(busTimeCsv);
    logger.info("busTime" + busTime);

    List<BellTime> bellTimesCandidates = new ArrayList<>(1);

    for (int i = 0; i < school_data.getBellTimes().size(); i++) {
      BellTime bellTime = school_data.getBellTimes().get(i);
      LocalTime early = LocalTime.parse(bellTime.getEarly());
      LocalTime late = LocalTime.parse(bellTime.getLate());
      logger.info("early" + early);
      logger.info("late" + late);
      if (early.compareTo(busTime) <= 0 && busTime.compareTo(late) <= 0) {
        logger.info("Found a belltime from bus time: "+ busTimeCsv);
        bellTimesCandidates.add(bellTime);
      }
    }

    BellTime result = new BellTime();
    if (bellTimesCandidates.size() == 1) {
      result = bellTimesCandidates.get(0);
    } else if (bellTimesCandidates.size() > 1) {
      logger.error("Found several bell times with bus time of " + busTimeCsv);
    } else if (bellTimesCandidates.size() == 0) {
    logger.error("No bell time found for bus time " + busTimeCsv);
  }
    return result;
  }

  private BellTime findBellTimeFromBellTimeCsv(School_Data school_data, String[] bellTimeCsv) {
    BellTime result = new BellTime();

    for (int i = 0; i < school_data.getBellTimes().size(); i++) {
      BellTime bellTime = school_data.getBellTimes().get(i);
      if (this.m_sevice.convert24to12(bellTime.getBell()).equals(bellTimeCsv[0].toString()) && bellTime.getType().equals(bellTimeCsv[1].toString())) {
        logger.info("Found a belltime from belltime: "+ Arrays.asList(bellTimeCsv));
        result = bellTime;
        break;
      }
    }

    return result;
  }

  private School_Data getOneSchool(Token token, Integer school_id) {
    School_Data school_data = new School_Data();
    try {
      String url_school = this.url_api + "/schools/" + school_id;
      String response = this.m_sevice.getToURL(url_school, this.http_timeout, Boolean.valueOf(true), token.getAccessToken(), this.dataArea);
      if (response != null && response.indexOf("HTTP error code:401") > 0) {
        token = this.m_sevice.getToken(this.url_gettoken, this.email, this.password, this.scope, this.http_timeout);
        if (token != null && token.getAccessToken() != "")
          response = this.m_sevice.getToURL(url_school, this.http_timeout, Boolean.valueOf(true), token.getAccessToken(), this.dataArea); 
      } 
      if (response != null && response.indexOf("HTTP error") < 0) {
        JSONObject jsonObject = new JSONObject(response);
        JSONObject dataObj = (JSONObject)jsonObject.get("data");
        Gson gson = (new GsonBuilder()).create();
        school_data = (School_Data)gson.fromJson(dataObj.toString(), School_Data.class);
      } else {
        logger.info("Get one school error: " + url_school);
        System.out.println("Get one school error: " + url_school);
      } 
    } catch (Exception ex) {
      ex.printStackTrace();
      logger.error("Get one school error: " + ex.getMessage(), ex);
      System.out.println("Get one school error: " + ex.toString());
    } 
    return school_data;
  }
  
  private Task createTask(Token token) {
    Task task = new Task();
    try {
      String url_tasks = this.url_api + "/tasks";
      String jsbody = String.format("{\"taskDescription\":\"%s\",\"userID\":\"%s\",\"taskType\":\"%s\"}", new Object[] { "Save_Run", token.getPersonId(), "BELLTIME" });
      String response = this.m_sevice.postToURL(url_tasks, jsbody, this.http_timeout, Boolean.valueOf(true), token.getAccessToken(), this.dataArea);
      if (response != null && response.indexOf("HTTP error code:401") > 0) {
        token = this.m_sevice.getToken(this.url_gettoken, this.email, this.password, this.scope, this.http_timeout);
        if (token != null && token.getAccessToken() != "")
          response = this.m_sevice.postToURL(url_tasks, jsbody, this.http_timeout, Boolean.valueOf(true), token.getAccessToken(), this.dataArea); 
      } 
      if (response != null && response.indexOf("HTTP error") < 0) {
        JSONObject jsonObject = new JSONObject(response);
        JSONObject datObject = (JSONObject)jsonObject.get("data");
        Gson gson = (new GsonBuilder()).create();
        task = (Task)gson.fromJson(datObject.toString(), Task.class);
      } 
    } catch (Exception ex) {
      logger.error("Create task error: " + ex.getMessage(), ex);
      System.out.println("Create task error: " + ex.getMessage());
    } 
    return task;
  }
  
  private String finalizeTask(Task task, Token token) {
    String status = "";
    try {
      String url_tasks = this.url_api + "/contexts/finalize";
      GsonBuilder gsonBuilder = new GsonBuilder();
      gsonBuilder.serializeNulls();
      Gson gson = gsonBuilder.create();
      String json_task = gson.toJson(task);
      String response = this.m_sevice.postToURL(url_tasks, json_task, this.http_timeout, Boolean.valueOf(true), token.getAccessToken(), this.dataArea);
      if (response != null && response.indexOf("HTTP error code:401") > 0) {
        token = this.m_sevice.getToken(this.url_gettoken, this.email, this.password, this.scope, this.http_timeout);
        if (token != null && token.getAccessToken() != "")
          response = this.m_sevice.postToURL(url_tasks, json_task, this.http_timeout, Boolean.valueOf(true), token.getAccessToken(), this.dataArea); 
      } 
      if (response != null && response.indexOf("HTTP error") < 0) {
        JSONObject jsonObject = new JSONObject(response);
        String hasErrors = jsonObject.get("hasErrors").toString();
        if (hasErrors.equals("true")) {
          JSONArray dataObj = (JSONArray)jsonObject.get("data");
          for (int i = 0; i < dataObj.length(); i++) {
            JSONObject jsonEntry = (JSONObject)dataObj.get(i);
            String runid = (String)jsonEntry.get("id");
            String codes = ((JSONArray)jsonEntry.get("codes")).get(0).toString();
            String Error = runid + " - " + codes;
            logger.info("Finalize Task error: " + Error);
            System.out.println("Finalize Task error: " + Error);
          } 
          status = "Finalize Task error";
        } 
      } else {
        status = response;
      } 
    } catch (Exception ex) {
      logger.error("Finalize task error: " + ex.getMessage(), ex);
      System.out.println("Finalize task error: " + ex.getMessage());
    } 
    return status;
  }
  
  private String deleteTask(Task task, Token token) {
    String status = "";
    try {
      String url_tasks = this.url_api + "/tasks/" + task.getId();
      String response = this.m_sevice.deleteToURL(url_tasks, this.http_timeout, Boolean.valueOf(true), token.getAccessToken(), this.dataArea);
      if (response != null && response.indexOf("HTTP error code:401") > 0) {
        token = this.m_sevice.getToken(this.url_gettoken, this.email, this.password, this.scope, this.http_timeout);
        if (token != null && token.getAccessToken() != "")
          response = this.m_sevice.deleteToURL(url_tasks, this.http_timeout, Boolean.valueOf(true), token.getAccessToken(), this.dataArea); 
      } 
      if (response != null && response.indexOf("HTTP error") >= 0)
        status = response; 
    } catch (Exception ex) {
      logger.error("Delete task error: " + ex.getMessage(), ex);
      System.out.println("Delete task error: " + ex.getMessage());
    } 
    return status;
  }
  
  private String getDataArea(Token token) {
    String da = "";
    try {
      String url_da = this.url_api + "/dataAreas";
      String response = this.m_sevice.getToURL(url_da, this.http_timeout, Boolean.valueOf(true), token.getAccessToken(), "");
      if (response != null && response.indexOf("HTTP error code:401") > 0) {
        token = this.m_sevice.getToken(this.url_gettoken, this.email, this.password, this.scope, this.http_timeout);
        if (token != null && token.getAccessToken() != "")
          response = this.m_sevice.getToURL(url_da, this.http_timeout, Boolean.valueOf(true), token.getAccessToken(), ""); 
      } 
      if (response != null && response.indexOf("HTTP error") < 0) {
        JSONObject jsonObject = new JSONObject(response);
        JSONObject datObject = (JSONObject)jsonObject.get("data");
        JSONArray dataAreasArray = (JSONArray)datObject.get("rollingDataAreas");
        if (dataAreasArray != null)
          for (int i = 0; i < dataAreasArray.length(); i++) {
            JSONObject jsonEntry = (JSONObject)dataAreasArray.get(i);
            String userID = (String)jsonEntry.get("userId");
            String surrogateKey = (String)jsonEntry.get("surrogateKey");
            if (userID != null && userID.equalsIgnoreCase("system"))
              da = surrogateKey; 
          }  
      } 
    } catch (Exception ex) {
      logger.error("Get data area error: " + ex.getMessage(), ex);
      System.out.println("Get data area error: " + ex.getMessage());
    } 
    return da;
  }
  
  private HashMap<String, Integer> getAllSchool(Token token) {
    HashMap<String, Integer> list_of_school = new HashMap<>();
    try {
      String url_school = this.url_api + "/schools";
      String response = this.m_sevice.getToURL(url_school, this.http_timeout, Boolean.valueOf(true), token.getAccessToken(), this.dataArea);
      if (response != null && response.indexOf("HTTP error code:401") > 0) {
        token = this.m_sevice.getToken(this.url_gettoken, this.email, this.password, this.scope, this.http_timeout);
        if (token != null && token.getAccessToken() != "")
          response = this.m_sevice.getToURL(url_school, this.http_timeout, Boolean.valueOf(true), token.getAccessToken(), this.dataArea); 
      } 
      if (response != null && response.indexOf("HTTP error") < 0) {
        JSONObject jsonObject = new JSONObject(response);
        JSONArray dataArray = (JSONArray)jsonObject.get("data");
        if (dataArray != null) {
          Gson gson = (new GsonBuilder()).create();
          for (int i = 0; i < dataArray.length(); i++) {
            School_Data school_data = (School_Data)gson.fromJson(dataArray.get(i).toString(), School_Data.class);
            list_of_school.put(school_data.getCode(), Integer.valueOf(school_data.getId()));
          } 
        } 
      } 
    } catch (Exception ex) {
      logger.error("Get list of school error:" + ex.getMessage(), ex);
      System.out.println("Get list of school error:" + ex.toString());
    } 
    return list_of_school;
  }
  
  private String json_One_Run(CsvRunBean RunObject) {
    GsonBuilder gsonBuilder = new GsonBuilder();
    gsonBuilder.serializeNulls();
    Gson gson = gsonBuilder.create();
    String json_run = gson.toJson(RunObject);
    return json_run;
  }
}
