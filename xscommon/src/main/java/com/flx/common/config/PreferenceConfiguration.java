package com.flx.common.config;

import java.util.*;
import java.util.prefs.*;

import org.apache.commons.configuration.*;

import com.flx.common.SystemException;

/**
 * PreferenceConfiguration is a configuration implementation using
 * Java Preference package
 */
public class PreferenceConfiguration
extends		AbstractConfiguration
implements	PersistentConfiguration
{
	private String		nodeName;
	private Preferences	preferences;
	private String[]		names;
	
	/**
	 * create a preference config service
	 * @param aNodeName preference node name
	 */
	public	PreferenceConfiguration
			( String		aNodeName )
	throws	BackingStoreException
	{
		nodeName = aNodeName;
		refresh();
	}
	
	/**
	 * commit change
	 */
	public void	commit()
	throws	SystemException
	{
		try
		{
			preferences.flush();
		}
		catch( Throwable t )
		{
			throw new SystemException( "Unable to commit changes", t );
		}
	}
	
	/**
	 * check if configuration has been changed
	 * @return true if the configuration has been changed
	 */
	public boolean	hasChanged()
	{
		return false;
	}
	
	/**
	 * refresh the configuration information
	 * @exception ConfigException thrown if problem getting 
	 * configuration information
	 */
	public void	refresh()
	throws	BackingStoreException
	{
		preferences = Preferences.userRoot();
		preferences = preferences.node( nodeName );
		names = preferences.keys();
	}
	
	/**
	 * get all names
	 * @return all names
	 */
	public String[]	getAllNames()
	{
		return names;
	}
	
	/**
	 * get value
	 * @param anID id of the value 
	 * @return value of the provided id or null of not found
	 */
	public Object	getPropertyDirect
					( String	anID )
	{
		return preferences.get( anID, null );
	}

	/**
	 * get value
	 * @param anID id of the value 
	 * @return value of the provided id or null of not found
	 */
	public Object	getProperty
					( String	anID )
	{
		return getPropertyDirect( anID );
	}
	
	/**
	 * check if configuration value with id exists
	 * @param anID id of the value
	 * @return true if configuration value exists or false else
	 */
	public boolean	containsKey
					( String	anID )
	{
		return preferences.get( anID, null ) != null;
	}

	/**
	 * save configuration. Please note that not all configuration service supports saving.
	 * @param allUpdatedConfiguration updated configuration
	 * @param allRemovedConfiguration configuration to be removed
	 * @exception ConfigException thrown if there is a problem saving the configuration
	 */
	public void	saveConfiguration
				( Hashtable		allUpdatedConfiguration,
				  String[]		allRemovedConfiguration )
	{
		String key = null;
		String value = null;
		for( Enumeration e=allUpdatedConfiguration.keys();
			 e.hasMoreElements(); )
		{
			key = (String)e.nextElement();
			value = (String)allUpdatedConfiguration.get( key );
			preferences.put( key, value );
		}
		for( int count=0; count<allRemovedConfiguration.length; count++ )
		{
			preferences.remove( allRemovedConfiguration[count] );
		}
	}

	protected void 	addPropertyDirect
					( String		anID, 
					  Object 	aValue ) 
	{
		String value = preferences.get( anID, null );
		// if the configuration does not exists
		if ( value == null )
		{
			preferences.put( anID, (String)aValue );
			// update names
			ArrayList list = new ArrayList();
			for( int count=0; count<names.length; count++ )
			{
				list.add( count, names[count] );
			}
			list.add( anID );
			names = new String[list.size()];
			list.toArray( names );
		}
		// if the configuration exists
		else
		{
			StringBuffer buffer = new StringBuffer();
			buffer.append( value );
			buffer.append( ',' );
			buffer.append( (String)aValue );
			preferences.put( anID, buffer.toString() );
		}
	}

	public boolean isEmpty() 
	{
		return names == null
			   &&
			   names.length == 0;
	}

	public void 	clearProperty
					( String		aKey ) 
	{
		preferences.remove( aKey );
	}

	/* (non-Javadoc)
	 * @see org.apache.commons.configuration.AbstractConfiguration#getKeys()
	 */
	public Iterator getKeys() 
	{
		ArrayList list = new ArrayList();
		for( int count=0; count<names.length; count++ )
		{
			list.add( names[count] );
		}
		return list.iterator();
	}
	
	/**
	 * remove the preference node
	 */
	public void	remove()
	throws	BackingStoreException
	{
		preferences.removeNode();
	}
}
