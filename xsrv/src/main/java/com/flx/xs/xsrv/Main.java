package com.flx.xs.xsrv;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.flx.xs.common.logger.Logger;
import com.flx.xs.xsrv.Main;

/**
 * Created by Andy.
 * The command line argument is the name of the Liquidity Provider e.g. BARX.
 * This causes a load of the appropriate spring context file for the Taker Class for the LP.
 * The ENV specific settings are propagated in the the master.properties and 
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
        
        
    }
}
