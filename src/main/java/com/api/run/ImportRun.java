package com.api.run;

import com.api.run.utils.SaveRuns;
import java.io.File;
import java.io.FileInputStream;
import java.util.Date;
import java.util.Properties;
import org.apache.log4j.Logger;

public class ImportRun {
  static final Logger logger = Logger.getLogger(ImportRun.class);
  
  public static void main(String[] args) {
    logger.info("Starting import ...");
    System.out.println("Starting import at..." + new Date());
    if (args.length > 0) {
      String csv_file_run = args[0];
      try {
        File file = new File("config/config.properties");
        FileInputStream inputStream = new FileInputStream(file);
        Properties prop = new Properties();
        prop.load(inputStream);
        String email = prop.getProperty("email");
        String password = prop.getProperty("password");
        String scope = prop.getProperty("scope");
        String url_api = prop.getProperty("url_api");
        Integer http_timeout = Integer.valueOf(Integer.parseInt(prop.getProperty("http_timeout")));
        SaveRuns saveRun = new SaveRuns();
        saveRun.email = email;
        saveRun.password = password;
        saveRun.scope = scope;
        saveRun.http_timeout = http_timeout;
        saveRun.url_api = url_api.trim();
        saveRun.csv_file_run = csv_file_run;
        //saveRun.save_All_Run();
        saveRun.save_All_Run_JVI();
      } catch (Throwable ex) {
        System.out.print("Import runs error: " + ex.getMessage());
        logger.error("Import runs error: " + ex.getMessage(), ex);
      } 
      logger.info("Import runs done !");
      System.out.println("Import runs done ! ..." + new Date());
    } else {
      logger.info("Invalid argument...!");
      System.out.println("Invalid argument...!");
    } 
  }
}
