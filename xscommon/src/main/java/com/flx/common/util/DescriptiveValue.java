package com.flx.common.util;


/**
 * DescriptiveValue contains value with its corresponding description
 */
public class DescriptiveValue
implements	Cloneable,
			Comparable
{
	private String		description;
	private Object		value;
	
	/**
	 * create a name value
	 */
	public	DescriptiveValue()
	{
	}

	/**
	 * create a name value with value provided
	 * @param aValue value
	 */
	public	DescriptiveValue
			( Object	aValue )
	{
		this( "", aValue );
	}
	
	/**
	 * create a descriptive value with description and value provided
	 * @param aDescription description
	 * @param aValue value
	 */
	public	DescriptiveValue
			( String	aDescription,
			  Object	aValue )
	{
		description = aDescription;
		value = aValue;
	}

	/**
	 * get description
	 * @return description
	 */
	public String	getDescription()
	{
		return description;
	}
	
	/**
	 * get value
	 * @return value
	 */
	public Object	getValue()
	{
		return value;
	}

	/**
	 * hash code
	 */
	public int	hashCode()
	{
		if ( value == null )
		{
			return 0;
		}
		return value.hashCode();
	}
	
	/**
	 * check if two objects are equal
	 * @param anObject object
	 */
	public boolean	equals
					( Object	anObject )
	{
		if ( anObject == null )
		{
			if ( getValue() == null )
			{
				return true;
			}
			return false;
		}
		if ( anObject instanceof DescriptiveValue )
		{
			DescriptiveValue nameValue = (DescriptiveValue)anObject;
			if ( nameValue.getValue() == null )
			{
				if ( getValue() == null )
				{
					return true;
				}
				return false;
			}
			if ( getValue() == null )
			{
				return false;
			}
			return nameValue.getValue().equals( getValue() );
		}
		else
		{
			return anObject.equals( getValue() );
		}
	}
	
	/**
	 * to string
	 */
	public String	toString()
	{
		return getDescription();
	}
	
	/**
	 * clone
	 */
	public Object	clone()
	{
		DescriptiveValue nameValue = new DescriptiveValue( getDescription(), getValue() );
		return nameValue;
	}
	
	/**
	 * compare to
	 */
	public int	compareTo
				( Object	aValue )
	{
		return description.compareTo( aValue.toString() );
	}
	
	/**
	 * change description
	 * @param aDescription description
	 */
	public void	changeDescription
				( String	aDescription )
	{
		description = aDescription;
	}

}
