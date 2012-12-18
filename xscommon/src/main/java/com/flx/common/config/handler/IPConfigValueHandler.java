package com.flx.common.config.handler;

import java.net.InetAddress;
import java.net.UnknownHostException;
import com.flx.common.config.ConfigValueHandler;

/**
 * IPConfigValueHandler handles IP address configuration value
 */
public class IPConfigValueHandler 
implements	ConfigValueHandler
{
	/**
	 * get id
	 * @return config id this handler handles
	 */
	public String	getId()
	{
		return "network.ip";
	}
	
	/**
	 * get value
	 * @param aValue any value associated with the configuration
	 * @return value
	 */
	public String	getValue
					( String		aValue )
	{
		try
		{
			return InetAddress.getLocalHost().getHostAddress();
		}
		catch( UnknownHostException e )
		{
			throw new RuntimeException( "Unable to get local host IP address" );
		}
	}
}
