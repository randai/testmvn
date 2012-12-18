package com.flx.common.internationalization;

import java.util.Locale;

/**
 * Translatable marks the class that supports internationalization
 */
public interface Translatable
{
	/**
	 * change language
	 * @param aTranslator translator 
	 * @param aLocale locale
	 */
	public void	changeLanguage
				( Translator	aTranslator,
				  Locale		aLocale );
}