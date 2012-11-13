package com.flx.xs.xsmm;

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

    @Logger private Log log;

    @Override
    public void run() {
        log.info(bean1.hello("world"));
        log.debug(bean1.hello("world"));
        log.error(bean1.hello("world"));
        
        log.info(bean1.getFlxConfigValue1());
    }

    @Autowired
    @Override
    public void setBean1(IBean1 bean1) {
        this.bean1 = bean1;
    }
}
