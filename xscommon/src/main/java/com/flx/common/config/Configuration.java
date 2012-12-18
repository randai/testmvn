package com.flx.common.config;


import java.awt.Color;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.prefs.BackingStoreException;

import org.apache.commons.configuration.BaseConfiguration;
import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.PropertiesConfiguration;

import com.flx.common.SystemException;
import com.flx.common.config.handler.IPConfigValueHandler;
import com.flx.common.util.InputLookup;
import com.flx.common.util.ValueEncrypter;

/**
 * Configuration provides configuration information from various media
 */
public class Configuration
extends		CompositeConfiguration
{
	private final static String		DELIMITER
									= ",";
	private final static String 		DELIM_START 
									= "${";
	private final static char 		DELIM_STOP 
									= '}';
	private final static int 			DELIM_START_LEN 
									= 2;
	private final static int 			DELIM_STOP_LEN 
									= 1;
	
	private org.apache.commons.configuration.Configuration		saveConfiguration;
	private HashMap					configValueHandlerLookup
									= new HashMap();
	
	/**
	 * create configuration
	 */
	public	Configuration()
	{
		registerHandler( new IPConfigValueHandler() );
	}
	
	/**
	 * register handler
	 * @param aHandler config value handler
	 */
	public void	registerHandler
				( ConfigValueHandler		aHandler )
	{
		configValueHandlerLookup.put( aHandler.getId(), aHandler );
	}
	
	/**
	 * convert to input lookup
	 * @return input lookup
	 */
	public InputLookup	convertToInputLookup()
	{
		return convertToInputLookup( null );
	}

	/**
	 * commit change
	 */
	public void	commit()
	throws	SystemException
	{
		if ( saveConfiguration != null )
		{
			if ( saveConfiguration instanceof PersistentConfiguration )
			{
				((PersistentConfiguration)saveConfiguration).commit();
			}
			else if ( saveConfiguration instanceof PropertiesConfiguration )
			{
				try
				{
					((PropertiesConfiguration)saveConfiguration).save();
				}
				catch( Throwable t )
				{
					throw new SystemException( "Unable to save configuration", t );
				}
			}
		}
	}
	
	/**
	 * convert to input lookup
	 * @return input lookup
	 */
	public InputLookup	convertToInputLookup
						( String		aPrefix )
	{
		InputLookup inputLookup = new InputLookup();
		String key = null;
		String[] values = null;
		for( Iterator iterator = getKeys(); iterator.hasNext(); )
		{
			key = (String)iterator.next();
			if ( aPrefix == null
				 ||
				 key.startsWith( aPrefix ) )
			{
				values = getStringArray( key );
				for( int count=0; count<values.length; count++ )
				{
					inputLookup.putInput( key, values[count] );
				}
			}
		}
		return inputLookup;
	}

	/**
	 * convert to properties
	 * @return properties
	 */
	public Properties	convertToProperties()
	{
		return convertToProperties( null );
	}
	
	/**
	 * convert to properties
	 * @return properties
	 */
	public Properties	convertToProperties
						( String		aPrefix )
	{
		InputLookup lookup = convertToInputLookup( aPrefix );
		String[] names = lookup.getParameterNames();
		Properties properties = new Properties();
		for( int count=0; count<names.length; count++ )
		{
			properties.put( names[count], lookup.getParameter( names[count] ) );
		}
		return properties;
	}
	
	/**
	 * add configuration
	 * 
	 * @param aConfiguration
	 * @param shouldUseToSave indicates if the configuration will be used to save configuration changes.
	 */
	public void	addConfigurationWithSaveOption
				( org.apache.commons.configuration.Configuration	aConfiguration,
				  boolean											shouldUseToSave )
	{
		addConfiguration( aConfiguration );
		if ( shouldUseToSave )
		{
			saveConfiguration = aConfiguration;
		}
	}
	
	/**
	 * add preference configuration
	 * @param aNodeName node name
	 * @param shouldUseToSave indicates if the configuration will be used to save configuration changes
	 * @exception BackingStoreException thrown if there is a problem adding the preference 
	 * configuration
	 */
	public void	addPreferenceConfig
				( String		aNodeName,
				  boolean		shouldUseToSave )
	throws	BackingStoreException
	{
		PreferenceConfiguration configuration = new PreferenceConfiguration( aNodeName );
		addConfiguration( configuration );
		if ( shouldUseToSave )
		{
			saveConfiguration = configuration;
		}
	}
	
	/**
	 * add command line configuration
	 * @param allArguments all command line arguments
	 */
	public void	addCommandLineConfig
				( String[]	allArguments )
	{
		BaseConfiguration configuration = new BaseConfiguration();
		String name = null;
		String value = null;
		int index = 0;
		for( int count=0; count<allArguments.length; count++ )
		{
			index = allArguments[count].indexOf( "=" );
			name = allArguments[count].substring( 0, index );
			value = allArguments[count].substring( index + 1, allArguments[count].length() );
			configuration.addProperty( name, value );
		}
		addConfiguration( configuration );
	}
	
	/**
	 * add file configuration
	 * @param aURI URI
	 * @param shouldUseToSave indicates if the configuration will be used to save configuration changes
	 */
	public void	addFileConfig
				( String		aURI,
				  boolean		shouldUseToSave )
	throws	SystemException
	{
		PropertiesConfiguration configuration = null;
		// try to interpret this as a URL first
		try
		{
			configuration = new PropertiesConfiguration( new java.net.URL( aURI ) );
			configuration.setAutoSave( true );
			// configure also the linked config files
			addConfiguration( configuration );
			if ( shouldUseToSave )
			{
				saveConfiguration = configuration;
			}
			return;
		}
		catch( Throwable t )
		{
		}
		// fail to interpret this as a URL
		// now interpret it as a file path
		try
		{
			configuration = new PropertiesConfiguration( aURI );
			addConfiguration( configuration );
			if ( shouldUseToSave )
			{
				saveConfiguration = configuration;
			}
		}
		catch( Throwable t )
		{
			throw new SystemException( "Unable to create file configuration", t );
		}
		String[] includeConfigFiles = configuration.getStringArray( "flx.includeConfigFiles" );
		for( String includeConfigFile: includeConfigFiles )
		{
			addFileConfig( includeConfigFile, false );
		}
	}
	
	/**
	 * get configuration value int. If the value cannot be parsed into int, then it will return the default value
	 * @param aConfigName configuration name
	 * @param aDefaultValue default value
	 * @return value as int or default value
	 */
	public int	getInt
				( String	aConfigName,
				  int		aDefaultValue )
	{
		try
		{
			return super.getInt( aConfigName );
		}
		catch( Throwable t )
		{
			return aDefaultValue;
		}
	}
	
	/**
	 * get configuration value big decimal. If the value cannot be parsed into big decimal, then
	 * it will return the default value
	 * @param aConfigName configuration name
	 * @param aDefaultValue default value
	 * @return value as big decimal or default value
	 */
	public BigDecimal	getBigDecimal
						( String		aConfigName,
						  BigDecimal	aDefaultValue )
	{
		try
		{
			return super.getBigDecimal( aConfigName, aDefaultValue );
		}
		catch( Throwable t )
		{
			return aDefaultValue;
		}
	}
	
	/**
	 * get configuration value as class
	 * @param aConfigName configuration name
	 * @return class
	 * @exception NoClassDefFoundError if the class is not found
	 */
	public Class	getValueAsClass
					( String		aConfigName )
	throws	ClassNotFoundException 
	{
		if ( aConfigName == null)
		{
			throw new ClassNotFoundException("Configuration.getValueAsClass - aConfigName for Configuration == null");
		}
		String className = getString( aConfigName );
		
		// it is not permitted to not define a value for a tag and have this return null.
		if (className == null)
		{
			throw new ClassNotFoundException("Configuration.getValueAsClass - couldn't find a value for aConfigName=" + aConfigName + " in the configuration");
		}
		Class c = null;
		try
		{
			c = Class.forName(className);
		}
		catch (ClassNotFoundException e)
		{
			throw new ClassNotFoundException("Configuration.getValueAsClass - couldn't get a class with className=" + className);
		}	
		return c;
	}
	
	/**
	 * get configuration value as string
	 * @param aName name of the configuration value
	 * @return configuration value as string
	 */
	public String	getString
					( String		aName )
	{
		Object property = getProperty( aName );
		String value = null;
		if ( property instanceof String )
		{
			value = (String)property;
		}
		else if ( property instanceof List )
		{
			List list = (List)property;
			StringBuffer buffer = new StringBuffer();
			boolean isInitial = true;
			for( Iterator iterator = list.iterator(); iterator.hasNext(); )
			{
				if ( isInitial )
				{
					isInitial = false;
				}
				else
				{
					buffer.append( ',' );
				}
				buffer.append( (String)iterator.next() );
			}
			value = buffer.toString();
		}
		if ( value != null )
		{
			if (ValueEncrypter.isEncryptedString( value )){
				// handle encrypted value
				try
				{
					value = ValueEncrypter.decrypt( value );
				}
				catch ( Exception e )
				{
					e.printStackTrace();
					throw new RuntimeException( e.getMessage() );
				}
			}
			return substitute( value );
		}
		return value;
	}
	
	/**
	 * get configuration value as string
	 * @param aName name of the configuration value
	 * @param aDefaultValue if configuration does not exist
	 * @return configuration value as string
	 */
	public String	getString
					( String		aName,
					  String		aDefaultValue )
	{
		String value = getString( aName );
		if ( value != null )
		{
			return value;
		}
		else
		{
			return aDefaultValue;
		}
	}

	/**
	 * get configuration value as string, throwing an exception if null
	 * or empty.
	 * 
	 * @param aName name of the configuration value
	 * @return configuration value as string
	 * @throws SystemException 
	 */
	public String	getRequiredString
					( String		aName ) throws SystemException
	{
		String result = getString( aName );
		if ( result == null )
		{
			throw new SystemException("Configuration getRequiredString - no value for aName=" + aName);		
		}
		if ( result.equals("") )
		{
			throw new SystemException("Configuration getRequiredString - no value for aName=" + aName);		
		}
		return result;
	}
	
	/**
	 * substitute the configuration value with the other configuration value
	 * @param aValue configuration value
	 * @return substituted configuration value
	 */
    private String 	substitute
    					( String		aValue )
	{
	    StringBuffer buffer = new StringBuffer();
	    int i = 0;
	    do
	    {
	        int j = aValue.indexOf(DELIM_START, i);
	        if(j == -1)
	        {
	            if(i == 0)
	            {
	                return aValue;
	            } else
	            {
	                buffer.append( aValue.substring(i, aValue.length()));
	                return buffer.toString();
	            }
	        }
	        buffer.append( aValue.substring(i, j));
	        int k = aValue.indexOf(DELIM_STOP, j);
	        if( k == -1 )
	        {
	            throw new IllegalArgumentException('"' + aValue + "\" has no closing brace. Opening brace at position " + j + '.');
	        }
	        j += DELIM_START_LEN;
	        String key = aValue.substring(j, k);
	        // get the substitution from configuration
	        String replacement = super.getString( key );
	        // if there is none, get it from system property
	        if( replacement == null )
	        {
	            replacement = System.getProperty( key );
	        }
	        // if there is none, get it from the config value handler
	        if ( replacement == null )
	        {
	        		// extract the value if there is one
	        		String keyValue = null;
	        		int keyValueIndex = key.indexOf( ":" );
	        		if ( keyValueIndex != -1 )
	        		{
	        			key = key.substring( 0, keyValueIndex );
	        			keyValue = key.substring( keyValueIndex + 1, key.length() );
	        		}
	        		// find config value handler
	        		ConfigValueHandler handler = (ConfigValueHandler)configValueHandlerLookup.get( key );
	        		if ( handler != null )
	        		{
	        			replacement = handler.getValue( keyValue );
	        		}
	        }
	        // substitute replacement
	        if( replacement != null )
	        {
	            String recursiveReplacement = substitute( replacement );
	            buffer.append( recursiveReplacement );
	        }
	        i = k + DELIM_STOP_LEN;
	    } while(true);
	}

    
	/**
	 * add property 
	 * @param aConfigName configuration name
	 * @param aValue value
	 */
	public void	addProperty
				( String		aConfigName,
				  Object		aValue )
	{
		saveConfiguration.addProperty( aConfigName, aValue );
	}
	
	/**
	 * set property 
	 * @param aConfigName configuration name
	 * @param aValue value
	 */
	public void	setProperty
				( String		aConfigName,
				  Object		aValue )
	{
		saveConfiguration.setProperty( aConfigName, aValue );
	}
	
	public void setProperty( String		aConfigName,
							Object		aValue,
							boolean 	shouldUseToSave)
	{
		if ( shouldUseToSave )
		{
			saveConfiguration.setProperty( aConfigName, aValue );
		}
		else
		{
			clearProperty( aConfigName );
			super.addProperty( aConfigName, aValue );
		}
	}
	
	/**
	 * get int array
	 * @param anID id
	 * @return int array
	 */
	public int[]	getIntArray
					( String		anID )
	{
		return getIntArray( anID, DELIMITER );
	}
	/**
	 * get int array
	 * @param anID id
	 * @param aDelimiter delimiter
	 * @return int array
	 */
	public int[]	getIntArray
					( String		anID,
					  String		aDelimiter )
	{
		String[] data = getStringArray( anID, aDelimiter );
		int[] result = new int[data.length];
		for( int count=0; count<result.length; count++ )
		{
			result[count] = Integer.parseInt( data[count] );
		}
		return result;
	}
	
	/**
	 * get string array
	 * @param anID id
	 * @return string array
	 */
	public String[]	getStringArray
					( String		anID )
	{
		return getStringArray( anID, DELIMITER );
	}
	/**
	 * get string array
	 * @param anID id
	 * @param aDelimiter delimiter
	 * @return string array
	 */
	public String[]	getStringArray
					( String		anID,
					  String		aDelimiter )
	{
		String value = getString( anID );
		if ( value == null )
		{
			return new String[0];
		}
		StringTokenizer tokenizer = new StringTokenizer( value, aDelimiter );
		String[] values = new String[tokenizer.countTokens()];
		int count=0;
		while( tokenizer.hasMoreTokens() )
		{
			values[count] = tokenizer.nextToken( aDelimiter );				
			count++;
		}
		return values;	
	}
	
	/**
	 * change property
	 * @param anID id
	 * @param aValue value
	 */
	public void	changeProperty
				( String		anID,
				  String		aValue )
	{
		if ( containsKey( anID ) )
		{
			setProperty( anID, aValue );
		}
		else
		{
			addProperty( anID, aValue );
		}
	}
	
	/**
	 * Creates an instance of a class that is defined by the Configuration value for the tagName.
	 * If no value is retrieved, throws exception. If the tagName is null, or the class can't
	 * be created for a className, then an exception is thrown.
	 * 
	 * and breakpointing.
	 * 
	 * @param tagName
	 * @return instance of a class
	 * @throws ClassNotFoundException 
	 * @throws SystemException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public  Object 	getClassInstance
					( String aTagName) 
	throws 	SystemException
	{
		Object o;
		Class c;
		try
		{
			c = getValueAsClass( aTagName );
			o = c.newInstance();
		}
		catch (Exception e)
		{
			throw new SystemException("getClassInstance failed - ", e);
		}
		if (o == null)
		{
			throw new SystemException("Configuration.getClassInstance - couldn't create an instance of class with className=" + c.getName());	
		}
		return o;
	}
	
	/**
	 * get value as color
	 * @param anID id
	 * @return color value or null if no such configuration
	 */
	public Color	getValueAsColor
					( String	anID )
	{
		String[] colorData = getStringArray( anID );
		if ( colorData.length != 3 )
		{
			return null;
		}
		else
		{
			try
			{
				return new Color( Integer.parseInt( colorData[0] ),
						Integer.parseInt( colorData[1] ),
						Integer.parseInt( colorData[2] ) );
			}
			catch( Throwable t )
			{
				return null;
			}
		}
	}
}