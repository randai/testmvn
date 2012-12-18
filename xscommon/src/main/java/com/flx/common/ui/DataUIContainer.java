package com.flx.common.ui;

import java.util.Locale;

import com.flx.common.internationalization.Translator;
import com.flx.common.util.InputLookup;

/**
 * DataUIContainer allows UI to collect data information and
 * also change language on the UI components
 */
public interface DataUIContainer
{
	/**
	 * get input information
	 * @return input information
	 */
	public InputLookup	getInputInformation();
	
	/**
	 * set input information
	 * @param anInputInfo input lookup
	 */
	public void	setInputInformation
				( InputLookup	anInputInfo );
				
	/**
	 * change language
	 * @param aTranslator translator 
	 * @param aLocale locale
	 */
	public void	changeLanguage
				( Translator	aTranslator,
				  Locale		aLocale );

	/** 
	 * add data ui change listener
	 * @param aUIChangeListener ui change listener
	 */
	public void	addDataUIChangeListener
				( DataUIChangeListener	aListener );
	
	/** 
	 * remove data ui change listener
	 * @param aUIChangeListener ui change listener
	 */
	public void	removeDataUIChangeListener
				( DataUIChangeListener	aListener );
	
	/**
	 * remove all listener
	 */
	public void	removeAllDataUIChangeListeners();
								  
	/**
	 * release any help resource such as translator
	 */
	public void	cleanup();
}