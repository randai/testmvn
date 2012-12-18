package com.flx.common.config;

/**
 * ConfigValueHandler provides support to retrieve configuration
 * value in runtime
 */
public interface ConfigValueHandler 
{
	/**
	 * get id
	 * @return config id this handler handles
	 */
	public String	getId();
	
	/**
	 * get value
	 * @param aValue any value associated with the configuration
	 * @return value
	 */
	public String	getValue
					( String		aValue );
}
