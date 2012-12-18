package com.flx.common.ui;

/** 
 * DataUIChangeListener allows interested party to receive
 * data change generated from data ui component
 */
public interface DataUIChangeListener
{
	/**
	 * data changed
	 * @param anId id
	 * @param aValue new value
	 */
	public void	dataChanged
				( String		anId,
				  String		aValue );
}
