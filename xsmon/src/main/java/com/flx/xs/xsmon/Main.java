package com.flx.xs.xsmon;
import java.util.TimeZone;

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