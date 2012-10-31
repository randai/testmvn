package com.flx.xs.xsrv;

import org.apache.commons.logging.Log;

import com.flx.xs.common.logger.Logger;

public class TakerServer {
	@Logger private Log log;
	TakerApi takerAdaptor;
	

	public void setTakerAdaptor(TakerApi takerAdaptor) {
		this.takerAdaptor = takerAdaptor;
	}
	
	public void initialize() {
		log.info("In initialize");
		
	}

	public void dispose() {
		log.info("In dispose");
		
	}

}
