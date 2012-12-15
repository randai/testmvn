package com.flx.xs.common.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.Properties;

import javax.jnlp.BasicService;
import javax.jnlp.ServiceManager;

/**
 * ConfigurablePropertyPlaceholder takes instructions which SystemProperty
 * contains the path to the propertyfile to load.
 * 
 */
public class ConfigurablePropertyViaServlet extends PropertyPlaceholderConfigurer implements ApplicationContextAware {


    private String propertyLocationSystemProperty;
    private String defaultPropertyFileName;


    public String getPropertyLocationSystemProperty() {
        return propertyLocationSystemProperty;
    }

    public void setPropertyLocationSystemProperty(String propertyLocationSystemProperty) {
        this.propertyLocationSystemProperty = propertyLocationSystemProperty;
    }

    public String getDefaultPropertyFileName() {
        return defaultPropertyFileName;
    }

    public void setDefaultPropertyFileName(String defaultPropertyFileName) {
        this.defaultPropertyFileName = defaultPropertyFileName;
    }

    /**
     * Overridden to fill the location with the path from the {@link #propertyLocationSystemProperty}
     *
     * @param props propeties instance to fill
     * @throws IOException
     */

    @Override
    protected void loadProperties(Properties props) throws IOException {
        Resource location = null;
        String webAppContextUrl=null;
        try
        {
          BasicService basicService =
              (BasicService) ServiceManager.lookup( "javax.jnlp.BasicService" );
          String codeBase = basicService.getCodeBase().toExternalForm();
          System.out.println("codeBase=["+codeBase+"]");
          if ( !codeBase.endsWith( "/" ) )
          {
            codeBase += "/";
          }
          int webAppContextUrlLength =
              codeBase.lastIndexOf("applications");
          webAppContextUrl = codeBase.substring( 0, webAppContextUrlLength)+"config/";
          System.out.println("webAppContextUrl=["+webAppContextUrl+"]");
        } catch ( Exception e ) {
          // TODO logging
          e.printStackTrace();
        }
        location = appContext.getResource("url:"+webAppContextUrl);
        
        setLocation(location);
        super.loadProperties(props);
    }
    private ApplicationContext appContext;
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		appContext = applicationContext;
		
	}
}

