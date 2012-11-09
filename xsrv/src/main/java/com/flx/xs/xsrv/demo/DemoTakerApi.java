package com.flx.xs.xsrv.demo;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;

import com.flx.xs.common.data.FullDataService;
import com.flx.xs.common.logger.Logger;
import com.flx.xs.xsrv.TakerApi;
//@ManagedResource(objectName="xsrv:name=DemoTaker", description="My Demo Taker")
public class DemoTakerApi implements TakerApi  {
	@Logger private Log log;
	
	String fileName;
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	@Override
	public void initialize() {
		log.info("In initialize fileName="+fileName+" at "+new Date());
		try{
			FileInputStream fstream = new FileInputStream(fileName);
		  // Get the object of DataInputStream
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
		  //Read File Line By Line
			while ((strLine = br.readLine()) != null)   {
		  // Print the content on the console
				System.out.println (strLine);
			}
		  //Close the input stream
			in.close();
		}catch (Exception e){//Catch exception if any
			System.err.println("Error: " + e.getMessage());
		}
	}

	//@ManagedOperation(description="dispose")
	@Override
	public void dispose() {
		if(dataService == null) {
			log.info("Already disposed");
		} else {
			log.info("In dispose");
			dataService.dispose();
		}
	}

	FullDataService dataService;
	
	public void setDataService(FullDataService dataService) {
		this.dataService = dataService;
	}
}
