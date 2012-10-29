package com.flx.xs.xsmm;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.flx.xs.common.App;
import com.flx.xs.common.logger.Logger;
/**
 * Created by andy.
 * Main class to start an application
 */
public class Main {
	
    @Logger private static Log log;

    public static void main(String[] args)
    {
    	String pathToApplicationContext = System.getProperty("pathToApplicationContext","classpath:/application-context.xml");
    	String[] contextPaths;
    	if(args.length > 0) {
    		contextPaths = new String[] {pathToApplicationContext,args[0]};
    	} else {
    		contextPaths = new String[] {pathToApplicationContext};
    	}
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(contextPaths);
        log = LogFactory.getLog(Main.class);
        log.info(App.CommonMethod());
        IRunner runner = ctx.getBean("runner", IRunner.class);
        runner.run();
    }
}
