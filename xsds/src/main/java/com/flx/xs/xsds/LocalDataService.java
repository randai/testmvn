package com.flx.xs.xsds;

import com.flx.xs.common.data.FullDataService;
import com.flx.xs.common.logger.Logger;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;

public class LocalDataService implements FullDataService {
	@Logger private Log log;
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource)
	{
		this.dataSource = dataSource;
	}

	@Override
	public void initialize() {
		log.info("In Initialize...DataSource details are :"+dataSource.toString());
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getApplication(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getProduct(String id) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
