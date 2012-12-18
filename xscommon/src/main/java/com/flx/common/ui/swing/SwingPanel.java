package com.flx.common.ui.swing;

import java.awt.Component;
import java.util.Locale;

import javax.swing.*;

import com.flx.common.internationalization.*;
import com.flx.common.ui.DataUIChangeListener;
import com.flx.common.ui.DataUIContainer;
import com.flx.common.ui.DataUIContainerHelper;
import com.flx.common.util.InputLookup;

/**
 * SwingPanel is the swing version of data ui container
 */
public class SwingPanel
extends		JPanel
implements	DataUIContainer,
			Translatable
{
	/**
	 * get input information
	 * @return input information
	 */
	public InputLookup	getInputInformation()
	{
		InputLookup lookup = new InputLookup();
		DataUIContainerHelper.FillInputLookup( this, lookup );
		return lookup;
	}

	/**
	 * set input information
	 * @param anInputLookup input lookup
	 */
	public void	setInputInformation
				( InputLookup	anInputLookup )
	{
		DataUIContainerHelper.SetInputLookup( this, anInputLookup );
	}

	
	/**
	 * change language
	 * @param aTranslator translator 
	 * @param aLocale locale
	 */
	public void	changeLanguage
				( Translator	aTranslator,
				  Locale		aLocale )
	{
		DataUIContainerHelper.ChangeLanguage( this, aTranslator, aLocale );
		invalidate();
		validate();
		repaint();
	}

	/** 
	 * add data ui change listener
	 * @param aUIChangeListener ui change listener
	 */
	public void	addDataUIChangeListener
				( DataUIChangeListener	aListener )
	{
		DataUIContainerHelper.AddDataUIChangeListener( this, aListener );
	}
	
	/** 
	 * remove data ui change listener
	 * @param aUIChangeListener ui change listener
	 */
	public void	removeDataUIChangeListener
				( DataUIChangeListener	aListener )
	{
		DataUIContainerHelper.RemoveDataUIChangeListener( this, aListener );
	}
	
	/**
	 * remove all listener
	 */
	public void	removeAllDataUIChangeListeners()
	{
		DataUIContainerHelper.RemoveAllDataUIChangeListeners( this );
	}
	
	/**
 	 * remove all
	 */
	public void	removeAll()
	{
		removeAllDataUIChangeListeners();
		super.removeAll();
	}
		
	/**
	 * release any help resource such as translator
	 */
	public void	cleanup()
	{
		removeAllDataUIChangeListeners();
		DataUIContainerHelper.Cleanup( this );
	}
}