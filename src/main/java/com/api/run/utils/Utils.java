package com.api.run.utils;

import com.api.run.model.Token;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.log4j.Logger;
import org.json.JSONObject;

public class Utils {
  static final Logger logger = Logger.getLogger(Utils.class);
  
  public Token getToken(String url_gettoken, String email, String password, String scope, Integer http_timeout) {
    logger.info("Get token key with URL:" + url_gettoken);
    System.out.println("Waiting for get token key...");
    Token token = new Token();
    String accessToken = "";
    String personId = "";
    if (email != "" && password != "") {
      String pattern = String.format("{\"email\":\"%s\",\"password\":\"%s\",\"scope\":\"%s\"}", new Object[] { email, password, scope });
      String response = postToURL(url_gettoken, pattern, http_timeout, Boolean.valueOf(false), "", "");
      if (response != null && response.indexOf("HTTP error") < 0) {
        JSONObject jsonObject = new JSONObject(response);
        accessToken = (String)jsonObject.get("accessToken");
        personId = (String)jsonObject.get("personId");
        token.setAccessToken(accessToken);
        token.setPersonId(personId);
        logger.info("Get token key success: " + accessToken);
      } else {
        accessToken = "";
        personId = "";
        token.setAccessToken(accessToken);
        token.setPersonId(personId);
        logger.info("Get token key fail: " + response);
        System.out.println("Get token key fail: " + response);
      } 
    } 
    return token;
  }
  
  public String postToURL(String url, String message, Integer http_timeout, Boolean isToken, String token, String dataArea) {
    StringBuffer totalOutput = new StringBuffer();
    try {
      int TIMEOUT_MILLIS = http_timeout.intValue() * 1000;
      RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(TIMEOUT_MILLIS).setConnectTimeout(TIMEOUT_MILLIS).setConnectionRequestTimeout(TIMEOUT_MILLIS).build();
      HttpPost postRequest = new HttpPost(url);
      postRequest.setConfig(requestConfig);
      StringEntity input = new StringEntity(message);
      input.setContentType("application/json");
      postRequest.setEntity((HttpEntity)input);
      if (isToken.booleanValue()) {
        postRequest.setHeader("Authorization", "Bearer " + token);
        postRequest.setHeader("da", dataArea);
      } 
      CloseableHttpClient closeableHttpClient = HttpClientBuilder.create().build();
      HttpResponse response = closeableHttpClient.execute((HttpUriRequest)postRequest);
      if (response.getStatusLine().getStatusCode() != 200 && response.getStatusLine().getStatusCode() != 201) {
        logger.error("Failed : HTTP error code:" + String.valueOf(response.getStatusLine().getStatusCode()));
        return "Failed : HTTP error code:" + String.valueOf(response.getStatusLine().getStatusCode());
      } 
      BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
      String output = "";
      while ((output = br.readLine()) != null)
        totalOutput.append(output); 
    } catch (Exception ex) {
      logger.error("Failed : HTTP error:" + ex.getMessage());
      return "Failed : HTTP error:" + ex.getMessage();
    } 
    return totalOutput.toString();
  }
  
  public String getToURL(String url, Integer http_timeout, Boolean isToken, String token, String dataArea) {
    StringBuffer totalOutput = new StringBuffer();
    try {
      int TIMEOUT_MILLIS = http_timeout.intValue() * 1000;
      RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(TIMEOUT_MILLIS).setConnectTimeout(TIMEOUT_MILLIS).setConnectionRequestTimeout(TIMEOUT_MILLIS).build();
      HttpGet getRequest = new HttpGet(url);
      getRequest.setConfig(requestConfig);
      if (isToken.booleanValue())
        getRequest.setHeader("Authorization", "Bearer " + token); 
      if (!dataArea.equals(""))
        getRequest.setHeader("da", dataArea); 
      CloseableHttpClient closeableHttpClient = HttpClientBuilder.create().build();
      HttpResponse response = closeableHttpClient.execute((HttpUriRequest)getRequest);
      if (response.getStatusLine().getStatusCode() != 200) {
        logger.error("Failed : HTTP error code:" + String.valueOf(response.getStatusLine().getStatusCode()));
        return "Failed : HTTP error code:" + String.valueOf(response.getStatusLine().getStatusCode());
      } 
      BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
      String output = "";
      while ((output = br.readLine()) != null)
        totalOutput.append(output); 
    } catch (Exception ex) {
      logger.error("Failed : HTTP error:" + ex.getMessage());
      return "Failed : HTTP error:" + ex.getMessage();
    } 
    return totalOutput.toString();
  }
  
  public String putToURL(String url, String message, Integer http_timeout, Boolean isToken, String token, String dataArea) {
    StringBuffer totalOutput = new StringBuffer();
    try {
      int TIMEOUT_MILLIS = http_timeout.intValue() * 1000;
      RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(TIMEOUT_MILLIS).setConnectTimeout(TIMEOUT_MILLIS).setConnectionRequestTimeout(TIMEOUT_MILLIS).build();
      HttpPut putRequest = new HttpPut(url);
      putRequest.setConfig(requestConfig);
      StringEntity input = new StringEntity(message);
      input.setContentType("application/json");
      putRequest.setEntity((HttpEntity)input);
      if (isToken.booleanValue()) {
        putRequest.setHeader("Authorization", "Bearer " + token);
        putRequest.setHeader("da", dataArea);
      } 
      CloseableHttpClient closeableHttpClient = HttpClientBuilder.create().build();
      HttpResponse response = closeableHttpClient.execute((HttpUriRequest)putRequest);
      if (response.getStatusLine().getStatusCode() != 200 && response.getStatusLine().getStatusCode() != 201) {
        logger.error("Failed : HTTP error code:" + String.valueOf(response.getStatusLine().getStatusCode()));
        return "Failed : HTTP error code:" + String.valueOf(response.getStatusLine().getStatusCode());
      } 
      BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
      String output = "";
      while ((output = br.readLine()) != null)
        totalOutput.append(output); 
    } catch (Exception ex) {
      logger.error("Failed : HTTP error:" + ex.getMessage());
      return "Failed : HTTP error:" + ex.getMessage();
    } 
    return totalOutput.toString();
  }
  
  public String deleteToURL(String url, Integer http_timeout, Boolean isToken, String token, String dataArea) {
    try {
      int TIMEOUT_MILLIS = http_timeout.intValue() * 1000;
      RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(TIMEOUT_MILLIS).setConnectTimeout(TIMEOUT_MILLIS).setConnectionRequestTimeout(TIMEOUT_MILLIS).build();
      HttpDelete deleteRequest = new HttpDelete(url);
      deleteRequest.setConfig(requestConfig);
      if (isToken.booleanValue()) {
        deleteRequest.setHeader("Authorization", "Bearer " + token);
        deleteRequest.setHeader("da", dataArea);
      } 
      CloseableHttpClient closeableHttpClient = HttpClientBuilder.create().build();
      HttpResponse response = closeableHttpClient.execute((HttpUriRequest)deleteRequest);
      if (response.getStatusLine().getStatusCode() != 200) {
        logger.error("Failed : HTTP error code:" + String.valueOf(response.getStatusLine().getStatusCode()));
        return "Failed : HTTP error code:" + String.valueOf(response.getStatusLine().getStatusCode());
      } 
    } catch (Exception ex) {
      logger.error("Failed : HTTP error:" + ex.getMessage());
      return "Failed : HTTP error:" + ex.getMessage();
    } 
    return "Done";
  }
  
  public String convert24to12(String time) {
    String time_amp = "";
    try {
      SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
      Date dateObj = sdf.parse(time);
      time_amp = (new SimpleDateFormat("hh:mm:ss a")).format(dateObj);
    } catch (ParseException|java.text.ParseException e) {
      e.printStackTrace();
    } 
    return time_amp;
  }
}
