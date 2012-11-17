package com.flx.xs.xsmm;

import com.flx.xs.common.logger.Logger;


import nf.fr.eraasoft.pool.ObjectPool;
import nf.fr.eraasoft.pool.PoolException;
import nf.fr.eraasoft.pool.PoolSettings;
import nf.fr.eraasoft.pool.PoolableObjectBase;

import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by IntelliJ IDEA. User: xaoc Date: 30.11.11 Time: 17:23
 */
public class Runner implements IRunner {

	private IBean1 bean1;

	@Logger
	private Log log;

	@Override
	public void run() {
		log.info(bean1.hello("world"));
		log.debug(bean1.hello("world"));
		log.error(bean1.hello("world"));

		log.info(bean1.getFlxConfigValue1());
		
		PoolSettings poolSettings = new PoolSettings(new MyPoolableObjectBase());
		
		// Add some settings
		poolSettings.min(0).max(10);

		// Get the objectPool instance using a Singleton Design Pattern is a
		// good idea
		ObjectPool<StringBuilder> objectPool = poolSettings.pool();

		// Use your pool
		StringBuilder buffer = null;
		try {
			buffer = objectPool.getObj();
			// Do something with your object
			buffer.append("yyyy");
		} catch (PoolException e) {
			e.printStackTrace();
		} finally {
			// Don't forget to return object in the pool
			objectPool.returnObj(buffer);
		}
		log.info("Finished creating pool");
		
	}
	class MyPoolableObjectBase extends PoolableObjectBase<StringBuilder>{

        @Override
        public StringBuilder make() {
                return new StringBuilder();
        }
        @Override
        public void activate(StringBuilder t) {
                t.setLength(0);
        }
		
	}	@Autowired
	@Override
	public void setBean1(IBean1 bean1) {
		this.bean1 = bean1;
	}
}
