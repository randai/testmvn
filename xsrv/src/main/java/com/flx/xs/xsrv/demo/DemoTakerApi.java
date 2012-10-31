package com.flx.xs.xsrv.demo;

import org.apache.commons.logging.Log;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;

import com.flx.xs.common.data.FullDataService;
import com.flx.xs.common.logger.Logger;
import com.flx.xs.xsrv.TakerApi;
//@ManagedResource(objectName="xsrv:name=DemoTaker", description="My Demo Taker")
public class DemoTakerApi implements TakerApi  {
	@Logger private Log log;
	@Override
	public void initialize() {
		log.info("In initialize");
		
	}
	//@ManagedOperation(description="dispose")
	@Override
	public void dispose() {
		log.info("In dispose");
		
	}

	FullDataService dataService;
	
	public void setDataService(FullDataService dataService) {
		this.dataService = dataService;
	}
}
