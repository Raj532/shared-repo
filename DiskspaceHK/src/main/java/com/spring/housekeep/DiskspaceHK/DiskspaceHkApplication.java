package com.spring.housekeep.DiskspaceHK;

import java.io.File;
import java.text.ParseException;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DiskspaceHkApplication {

	private static Logger LOGGER = LoggerFactory.getLogger(DiskspaceHkApplication.class);
	
	@Autowired
	static MainClass mainClass;
	
	public static void main(String[] args) throws ParseException {
		
		LOGGER.info("Tool started");
		SpringApplication.run(DiskspaceHkApplication.class, args);
//		MainClass mainClass = context.getBean(MainClass.class);
		Map<String, String> prop = mainClass.loadProperties();
		for(Map.Entry<String, String> propValue : prop.entrySet()) {
			List<File> files = mainClass.getFiles(propValue.getValue());
			mainClass.getFileNames(files, propValue.getValue());
			if(true)
				mainClass.ListandDelOlderFiles(files);
		}
	}
}

