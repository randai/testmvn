package com.flx.xs.xsrv;

import java.util.TimeZone;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.flx.xs.common.logger.Logger;
import com.flx.xs.xsrv.Main;

/**
 * Created by Andy.
 * The command line argument is the name of the Liquidity Provider e.g. barx or citi or gs etc
 * This causes a load of the appropriate spring context file for the Taker Class for the LP.
 * The environment specific settings are propagated in via the xs.properties located in the $XS_HOME folder.
 * In normal mode this process is run from a folder that is specific to the Liquidity Provider e.g. barx or citi or gs etc 
 * and in this folder are all the config files...including the main spring context. This file is named after
 * the liquidity provider with xml extension e.g. barx.xml.
 * In development mode its typical to override the location of where to get the config files from using XS_APP_DIR system property
 * and point to a location in the xsbuild project.
 * 
 */
public class Main {
    @Logger private static Log log;

    public static void main(String[] args)
    {
    	TimeZone.setDefault(TimeZone.getTimeZone("GMT"));
    	System.out.println("NOTE : This processes internal DEFAULT timezone has been set to GMT via Main() method");
    	String pathToApplicationContext = System.getProperty("pathToApplicationContext","classpath:/application-context.xml");
    	String[] contextPaths;
    	if(args.length > 0) {
    		String pathPrefix = System.getProperty("XS_APP_DIR") != null ? System.getProperty("XS_APP_DIR") +"/" : "";
    		String pathToTakerContext = "file:"+pathPrefix + args[0]+"-context.xml";
    		contextPaths = new String[] {pathToApplicationContext,pathToTakerContext};
    	} else {
    		contextPaths = new String[] {pathToApplicationContext};
    	}
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(contextPaths);
        log = LogFactory.getLog(Main.class);
        ctx.registerShutdownHook();
        log.info("main thread finished...process started.");
    }
}
