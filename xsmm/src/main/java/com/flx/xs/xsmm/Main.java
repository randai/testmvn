package com.flx.xs.xsmm;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.flx.xs.common.App;
/**
 * Created by IntelliJ IDEA.
 * User: xaoc
 * Date: 30.11.11
 * Time: 17:20
 */
public class Main {
    public static void main(String[] args)
    {
    	String pathToApplicationContext = System.getProperty("pathToApplicationContext","classpath:/META-INF/application-context-root.xml");
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(pathToApplicationContext);
        IRunner runner = ctx.getBean("runner", IRunner.class);
        System.out.println( App.CommonMethod());
        runner.run();
    }
}
