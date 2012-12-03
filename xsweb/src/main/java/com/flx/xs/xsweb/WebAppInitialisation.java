package com.flx.xs.xsweb;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class WebAppInitialisation implements ApplicationContextAware {
	private static WebAppInitialisation instance;
	private String location;
	private ApplicationContext appContext;
	

	WebAppInitialisation() {
		instance = this;
	}
	
	public static WebAppInitialisation getInstance() {
		return instance;
	}
	
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
		System.out.println("location="+location);
	}
	
	public ApplicationContext getAppContext() {
		return appContext;
	}


	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.appContext = applicationContext;
		
	}
}
