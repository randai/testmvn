package com.flx.common.internationalization;

import java.util.*;

import org.apache.struts.util.*;

/**
 * Translator handles translation of text to provide internationalization
 * to user interface applications
 */
public class Translator 
{
	private ArrayList		translators
							= new ArrayList();
	
	/**
	 * add property based message translation provider
	 * @param aPropertyName property name
	 */
	public void	addPropertyMessageProvider
				( String		aPropertyName )
	{
		PropertyMessageResourcesFactory factory = new PropertyMessageResourcesFactory();
		translators.add( factory.createResources( aPropertyName ) );
	}
	
	/**
	 * translate message
	 * @param aMessageKey message key
	 * @param aLocale locale
	 * @return translated key
	 */
	public String	translate
					( String		aMessageKey,
					  Locale		aLocale )
	{
		MessageResources messageResources = null;
		for( Iterator iterator = translators.iterator();
			 iterator.hasNext(); )
		{
			messageResources = (MessageResources)iterator.next();
			if ( messageResources.isPresent( aLocale, aMessageKey ) )
			{
				return messageResources.getMessage( aLocale, aMessageKey );
			}
		}
		return aMessageKey;
	}
	
	/**
	 * translate message
	 * @param aMessageKey message key
	 * @param allArguments all message arguments
	 * @param aLocale locale
	 * @return translated key
	 */
	public String	translate
					( String		aMessageKey,
					  Object[]	allArguments,
					  Locale		aLocale )
	{
		MessageResources messageResources = null;
		for( Iterator iterator = translators.iterator();
			 iterator.hasNext(); )
		{
			messageResources = (MessageResources)iterator.next();
			if ( messageResources.isPresent( aLocale, aMessageKey ) )
			{
				return messageResources.getMessage( aLocale, aMessageKey, allArguments );
			}
		}
		return aMessageKey;
	}
	
	/**
	 * transfer all translators to the given translator
	 * @param aTranslator translator to receive the translators
	 */
	public void	transferTo
				( Translator	aTranslator )
	{
		Object translator = null;
		for( Iterator iterator = translators.iterator();
		 	 iterator.hasNext(); )
		{
			translator = iterator.next();
			if ( !aTranslator.translators.contains( translator ) )
			{
				aTranslator.translators.add( translator );
			}
		}		
	}
}
