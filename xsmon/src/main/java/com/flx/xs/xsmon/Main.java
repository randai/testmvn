package com.flx.xs.xsmon;
import java.util.TimeZone;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.flx.xs.common.App;
import com.flx.xs.common.logger.Logger;

/**
 * Created by andy.
 * Main class to start a GUI application
 */
public class Main {
	
    @Logger private static Log log;

    public static void main(String[] args)
    {
    	TimeZone.setDefault(TimeZone.getTimeZone("GMT"));
    	System.out.println("NOTE : This processes internal DEFAULT timezone has been set to GMT via Main() method");
    	String pathToApplicationContext = null;
    	if(args.length > 0) {
    		if("webstart".equals(args[0])){
    			pathToApplicationContext = "classpath:/application-context-webstart.xml";
    		}
    	}
    	if(pathToApplicationContext == null)
    		pathToApplicationContext = System.getProperty("pathToApplicationContext","classpath:/application-context-standalone.xml");
    	String pathToMoniorContext = System.getProperty("pathToMonitorContext","classpath:/xsmon-context.xml");
    	String[] contextPaths = new String[] {pathToApplicationContext,pathToMoniorContext};
         ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(contextPaths);
        log = LogFactory.getLog(Main.class);
        ctx.registerShutdownHook();
        log.info("main thread finished...process started.");
   }
}