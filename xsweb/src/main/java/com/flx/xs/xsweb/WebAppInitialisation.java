package com.flx.xs.xsweb;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
/**
 * Convenient way for classes not instantiated thru spring to get the spring application context..e.g. a servlet
 * @author andy
 *
 */
public class WebAppInitialisation implements ApplicationContextAware {
	private static WebAppInitialisation instance;
	private ApplicationContext appContext;
	

	WebAppInitialisation() {
		instance = this;
	}
	
	public static WebAppInitialisation getInstance() {
		return instance;
	}
	
	public ApplicationContext getAppContext() {
		return appContext;
	}


	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.appContext = applicationContext;
		
	}
}
