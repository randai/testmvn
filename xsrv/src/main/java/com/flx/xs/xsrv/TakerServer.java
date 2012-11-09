package com.flx.xs.xsrv;

import org.apache.commons.logging.Log;

import com.flx.xs.common.logger.Logger;

public class TakerServer {
	@Logger private Log log;
	TakerApi takerAdaptor;
	
	boolean keepRunning = true;

	public void setTakerAdaptor(TakerApi takerAdaptor) {
		this.takerAdaptor = takerAdaptor;
	}
	
	public void initialize() {
		log.info("In initialize");
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				while(keepRunning) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				log.info("Thread terminated");
			}
			
		}).start();
	}

	public void dispose() {
		keepRunning = false;
		if(takerAdaptor == null)
			log.info("Already disposed");
		else {
			log.info("In dispose");
			takerAdaptor.dispose();
			takerAdaptor = null;
		}
		
	}

}
