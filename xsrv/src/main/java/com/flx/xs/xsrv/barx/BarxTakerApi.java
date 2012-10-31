package com.flx.xs.xsrv.barx;

import org.apache.commons.logging.Log;

import com.flx.xs.common.data.FullDataService;
import com.flx.xs.common.logger.Logger;
import com.flx.xs.xsrv.TakerApi;

public class BarxTakerApi implements TakerApi  {
	@Logger private Log log;
	@Override
	public void initialize() {
		log.info("In initialize");
		
	}

	@Override
	public void dispose() {
		log.info("In dispose");
		
	}
	
	FullDataService dataService;
	
	public void setDataService(FullDataService dataService) {
		this.dataService = dataService;
	}
}
