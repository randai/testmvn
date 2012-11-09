package com.flx.xs.xsds;

import com.flx.xs.common.logger.Logger;
import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by IntelliJ IDEA.
 * User: xaoc
 * Date: 30.11.11
 * Time: 17:23
 */
public class Runner implements IRunner {


    private IBean1 bean1;
    
    private boolean keepRunning = true;

    @Logger private Log log;

    @Override
    public void run() {
        log.info(bean1.hello("worldxx"));
        //Kick of a thread to simulate a deamon
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

    @Autowired
    @Override
    public void setBean1(IBean1 bean1) {
        this.bean1 = bean1;
    }
    
    public void dispose() {
    	keepRunning = false;
    }
}
