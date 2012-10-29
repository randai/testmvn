package com.flx.xs.xsmm;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.flx.xs.common.App;
/**
 * Created by andy.
 * Main class to start an application
 */
public class Main {
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

        IRunner runner = ctx.getBean("runner", IRunner.class);
        runner.run();
    }
}
