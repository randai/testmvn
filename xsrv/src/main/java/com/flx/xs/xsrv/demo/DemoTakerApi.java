package com.flx.xs.xsrv.demo;

import org.apache.commons.logging.Log;

import com.flx.xs.common.logger.Logger;
import com.flx.xs.xsrv.TakerApi;

public class DemoTakerApi implements TakerApi  {
	@Logger private Log log;
	@Override
	public void initialize() {
		log.info("In initialize");
		
	}

	@Override
	public void dispose() {
		log.info("In dispose");
		
	}

}
