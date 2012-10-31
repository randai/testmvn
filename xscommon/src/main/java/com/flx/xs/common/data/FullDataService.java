package com.flx.xs.common.data;

public interface FullDataService {
	
	public void initialize();
	
	public void dispose();

	public String getApplication(String id);
	
	public String getProduct(String id);
}
