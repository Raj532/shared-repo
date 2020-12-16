
package com.spring.housekeep.DiskspaceHK;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:application.properties")
public class MainClass {
	
	private static Logger LOGGER = LoggerFactory.getLogger(MainClass.class);
	private static SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy") ; 
	static Date date = new Date();
		
/*	@Value("${path2}")
	private String path;
*/	
	public List<File> getFiles(String path) {
		
		File file = new File(path);
		if(file.isDirectory()) {
		List<File> listOfFiles = new ArrayList<>();
		for(File fileName: file.listFiles()) {
			listOfFiles.add(fileName);
			}
		return listOfFiles;
		}  else
			LOGGER.info("Directory is missing");
			return null;
	}

	public boolean getFileNames(List<File> rawFile, String path) {
		if(rawFile.size() > 0) {	
		LOGGER.info("List of files present in {} & last modified timestamp are ->", path); 
		
		for(File fileName: rawFile) {
			LOGGER.info("{} {}", fileName.getName(), sdf.format(fileName.lastModified()));
			}
		return true;
		}else {
			LOGGER.info("No files present in path");
			return false;
		}
	}
	 
	public void ListandDelOlderFiles(List<File> listFiles) throws ParseException {
		Date date = new Date();
		List<File> olderFiles = new ArrayList<>();
		int count = 0;
		String d = sdf.format(date.getTime());
		date = sdf.parse(d);
		Long dateInLong = date.getTime();
		LOGGER.info("today's date {}", dateInLong);
		for(File file : listFiles) {
			if(file.lastModified() < dateInLong) {
				olderFiles.add(file);
			}
		}	
		count = olderFiles.size();
		LOGGER.info("Delete count {}", count);
		if(count>0) {
		LOGGER.info("Files to be deleted :");
		for(File fileToDeletFile : olderFiles) {
			LOGGER.info("{}", fileToDeletFile);
		}
		for(File deletFile : olderFiles) {
			LOGGER.info("File deleted :{}", deletFile.delete());
			}
		}
		else
			LOGGER.info("No files to delete");
	}

	public Map<String, String> loadProperties() {
		try {
			File f = new File(".");
			String PATH = f.getCanonicalPath()+"/src/main/resources/application.properties";
			FileInputStream fis = new FileInputStream(new File(PATH));
			LOGGER.info("Property file path is : {}", PATH);
			Properties props = new Properties();
			Map<String, String> map = new HashMap<>();
			props.load(fis);
			for(String propertyName : props.stringPropertyNames()) {
				map.put(propertyName, props.getProperty(propertyName));
			}
			LOGGER.info("paths from property file are :{}", map.values());
			return map;
		} catch (FileNotFoundException e) {
			LOGGER.info("Error occured while loading property file");
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
	}
}
