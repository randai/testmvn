package com.flx.common.ui;
import java.util.*;

/**
 * DataUIChangeImpl provides implementation on handling
 * data ui change listener registration and data change notification
 */
public class DataUIChangeImpl
{
	private ArrayList		listeners
								= new ArrayList();
	
	/** 
	 * add data ui change listener
	 * @param aUIChangeListener ui change listener
	 */
	public void	addDataUIChangeListener
				( DataUIChangeListener	aListener )
	{
		if ( !listeners.contains( aListener ) )
		{
			listeners.add( aListener );
		}
	}
	
	/** 
	 * remove data ui change listener
	 * @param aUIChangeListener ui change listener
	 */
	public void	removeDataUIChangeListener
				( DataUIChangeListener	aListener )
	{
		listeners.remove( aListener );
	}
	
	/**
	 * remove all listener
	 */
	public void	removeAllListeners()
	{
		listeners.clear();
	}
	
	/** 
	 * notify data changed
	 * @param anId id
	 * @param aValue value
	 */
	public void	notifyDataChanged
				( String	anId,
				  String	aValue )
	{
		for( Iterator e = listeners.iterator();
			 e.hasNext(); )
		{
			((DataUIChangeListener)e.next()).dataChanged( anId, aValue );
		}
	}
}