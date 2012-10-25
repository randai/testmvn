package com.flx.xs.xsrv;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Andy.
 * The command line argument is the name of the Liquidity Provider e.g. BARX.
 * This causes a load of the appropriate spring context file for the Taker Class for the LP.
 * The ENV specific settings are propagated in the the master.properties and 
 */
public class Main {
    public static void main(String[] args)
    {
    	String[] contextPaths;
    	if(args.length > 0) {
    		contextPaths = new String[] {"classpath:/META-INF/application-context-root.xml",args[0]};
    	} else {
    		contextPaths = new String[] {"classpath:/META-INF/application-context-root.xml"};
    	}
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(contextPaths);

        IRunner runner = ctx.getBean("runner", IRunner.class);
        runner.run();
    }
}
