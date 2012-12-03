package com.flx.xs.xsweb;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Properties;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class ApplicationPropertiesServlet extends GenericServlet {

	@Override
	public void service(ServletRequest req, ServletResponse res)
			throws ServletException, IOException {
		res.setContentType("text/plain");

		PrintWriter out = res.getWriter();
		Properties props  = WebAppInitialisation.getInstance().getAppContext().getBean("applicationProps", Properties.class);
		Enumeration enprop = props.propertyNames();
		String key;
		while (enprop.hasMoreElements()) {
			key = (String) enprop.nextElement();
			if (key.startsWith("xs.prop."))
				out.println(key + "=" + props.getProperty(key));
		}

		out.close();
		return;
	}

}
