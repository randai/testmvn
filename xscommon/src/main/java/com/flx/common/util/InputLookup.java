package com.flx.common.util;

import java.util.*;
import java.util.Map.Entry;
import java.io.Serializable;

/**
 * InputLookup contains all input information from
 * user interface
 */
public class InputLookup
implements	Serializable
{
	/**
	 * 
	 */
	private static final long	serialVersionUID	= -2967208930869875703L;

	private final static String	DELIMITER
								= ",";
	
	private HashMap<String, ArrayList<String >>		table;
	
	public InputLookup()
	{
		table = new HashMap <String, ArrayList<String >>();
	}
	
	public InputLookup( Map<String,String> allValues )
	{
		table = new HashMap <String, ArrayList<String >>();
		
		for( Entry < String, String > entry : allValues.entrySet() )
		{
			ArrayList<String> value = new ArrayList < String >();
			value.add( entry.getValue() );
			table.put( entry.getKey(), value );
		}
	}
	
	public InputLookup( InputLookup allParameters )
	{
		table = new HashMap <String, ArrayList<String >>();
		
		for( Entry < String, ArrayList<String >> entry : allParameters.table.entrySet() )
		{
			ArrayList<String> value = new ArrayList < String >( entry.getValue() );
			table.put( entry.getKey(), value );
		}
	}

	/**
	 * put input name and value into lookup
	 * @param aName name
	 * @param aValue value
	 */
	public void	putInput
				( String	aName,
				  String	aValue )
	{
		if ( aValue == null )
		{
			return;
		}
		if ( aValue.indexOf( DELIMITER ) == -1 )
		{
			if ( aName == null
				 ||
				 aValue == null )
			{
				return;
			}
			ArrayList<String> values = (ArrayList<String>)table.get( aName );
			if ( values == null )
			{
				values = new ArrayList<String>();
				table.put( aName, values );
			}
			values.add( aValue );
		}
		else
		{
			StringTokenizer tokenizer = new StringTokenizer( aValue, DELIMITER );
			while( tokenizer.hasMoreTokens() )
			{
				putInput( aName, tokenizer.nextToken() );
			}
		}
	}

	/**
	 * put input name and value into lookup
	 * @param aName name
	 * @param allValues values
	 */
	public void	putInput
				( String		aName,
				  String[]	allValues )
	{
		if ( aName == null
			 ||
			 allValues == null )
		{
			return;
		}
		for( int count=0; count<allValues.length; count++ )
		{
			putInput( aName, allValues[count] );
		}
	}

	/**
	 * get all parameter names
	 * @return all parameter names
	 */
	public String[]	getParameterNames()
	{
		String[] names = new String[table.size()];
		int count = 0;
		for( String key : table.keySet() )
		{
			names[count] = key;
			count++;
		}
		return names;
	}
	
	/**
	 * get all parameter values
	 * @param aKey key to lookup values
	 * @return value or null if not exists
	 */
	public String[]	getParameterValues
					( String	aKey )
	{
		if ( aKey == null )
		{
			return new String[0];
		}
		if ( table.containsKey( aKey ) )
		{
			ArrayList<String> values = (ArrayList<String>)table.get( aKey );
			String[] result = new String[values.size()];
			return values.toArray( result );
		}
		else
		{
			return new String[0];
		}
	}

	/**
	 * get all parameter values as vector
	 * @param aKey key to lookup values
	 * @return value or null if not exists
	 */
	public ArrayList<String>	getParameterValuesAsVector
					( String	aKey )
	{
		if ( aKey == null )
		{
			return new ArrayList<String>();
		}
		ArrayList<String> values = (ArrayList<String>)table.get( aKey );
		if ( values != null )
		{
			return (ArrayList<String>)values.clone();
		}
		else
		{
			return new ArrayList<String>();
		}
	}
	
	/**
	 * get all parameter values as map.
	 * Keys with multiple values have values separated by comma ','
	 * @return all parameters
	 */
	public Map<String, String >	getAllParametersAsMap( )
	{
		Map<String, String> retval = new HashMap<String, String>();
		
		for ( Entry < String, ArrayList<String >> entry : table.entrySet() )
		{
			String name = entry.getKey();
			StringBuffer valueBuffer = new StringBuffer();
			boolean isInitial = true;
			for ( String entryValue : entry.getValue() )
			{
				if ( !isInitial )
				{
					valueBuffer.append( ',' );
				}
				else
				{
					isInitial = false;
				}
				
				valueBuffer.append( entryValue );
			}
			
			retval.put( name, valueBuffer.toString() );
		}
		
		return retval;
	}
		
	/**
	 * get parameter
	 * @param aKey key to lookup value
	 * @return value or null if not exists
	 */
	public String	getParameter
					( String	aKey )
	{
		if ( aKey == null )
		{
			return "";
		}
		String[] values = getParameterValues( aKey );
		if ( values == null
		 	 ||
		 	 values.length == 0 )
		{
			return null;
		}
		else if ( values.length == 1 )
		{
			return values[0];
		}
		else
		{
			StringBuffer buffer = new StringBuffer();
			buffer.append( values[0] );
			for( int count = 1; count < values.length; count++ )
			{
				buffer.append( DELIMITER );
				buffer.append( values[count] );
			}
			return buffer.toString();
		}
	}
	
	/**
	 * clear
	 */
	public final void	clear()
	{
		table.clear();
	}
	
	/**
	 * to String
	 */
	public String	toString()
	{
		return table.toString();
	}

	public boolean containsParameter( String name )
	{
		return table.containsKey( name );
	}
}