package com.flx.common.ui;
import com.flx.common.internationalization.Translatable;
import com.flx.common.util.InputLookup;

/**
 * DataUIComponent is responsible for presenting data information
 */
public interface DataUIComponent
extends		Translatable
{
	/**
	 * set data id
	 * @param aDataId data id
	 */
	public void	setDataId
				( String	aDataId );
	
	/**
	 * fill input lookup
	 * @param anInputLookup input lookup
	 */
	public void	fillInputLookup
				( InputLookup	anInputLookup );

	/**
	 * set data value from input lookup
	 * @param anInputLookup input lookup
	 */
	public void	setInputLookup
				( InputLookup	anInputLookup );
	
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