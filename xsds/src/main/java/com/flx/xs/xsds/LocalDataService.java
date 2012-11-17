package com.flx.xs.xsds;

import java.sql.SQLException;

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
		try {
			log.info("In Initialize...testing connection :"+dataSource.getConnection());
		} catch (SQLException e) {
			log.error("Connection Error",e);
		}
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getApplication(String id) {
		try {
			dataSource.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public String getProduct(String id) {
		return null;
	}
	
}
