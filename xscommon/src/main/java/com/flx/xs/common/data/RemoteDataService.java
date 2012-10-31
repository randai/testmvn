package com.flx.xs.common.data;

import org.apache.commons.logging.Log;

import com.flx.xs.common.logger.Logger;

public class RemoteDataService implements FullDataService {
	
	@Logger private Log log;
	public void initialize() {
		log.info("In initialize");
		
	}

	public void dispose() {
		log.info("In dispose");
		
	}
	public String getApplication(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getProduct(String id) {
		// TODO Auto-generated method stub
		return null;
	}


}
