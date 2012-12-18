package com.flx.common;

/**
 * SystemException indicates there is a unexpected system problem
 */
public class SystemException 
extends		Exception
{
	/**
	 * create system exception
	 * @param aMessage message
	 */
	public	SystemException
			( String		aMessage )
	{
		super( aMessage );
	}
	
	/**
	 * create system exception
	 * @param aMessage message
	 * @param anException exception
	 */
	public	SystemException
			( String		aMessage,
			  Throwable	anException )
	{
		super( aMessage, anException );
	}
}
