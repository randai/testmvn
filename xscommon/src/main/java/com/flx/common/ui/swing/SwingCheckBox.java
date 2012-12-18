package com.flx.common.ui.swing;
import java.awt.Component;
import java.awt.event.*;
import java.util.Locale;

import javax.swing.*;

import com.flx.common.internationalization.Translator;
import com.flx.common.ui.DataUIChangeImpl;
import com.flx.common.ui.DataUIChangeListener;
import com.flx.common.ui.DataUIComponent;
import com.flx.common.util.*;

/**
 * SwingCheckBox is the swing version of check box
 */
public class SwingCheckBox
extends		JCheckBox
implements	DataUIComponent,
			ActionListener,
			ListCellRenderer
{
	private String					id;
	private String					value;
	private String					data;
	private String					text
									= "";
	private DataUIChangeImpl			changeImpl
									= new DataUIChangeImpl();
	private Translator				translator;
	private Locale					locale;
	
	/**
 	 * create check box
	 */
	public	SwingCheckBox()
	{
		addActionListener( this );
	}

	/**
	 * set date format style
	 * @param aDateFormatStyle
	 */
	public void	setDateFormatStyle
				( int		aDateFormatStyle )
	{
	}
				
	/**
 	 * action performed
	 */
	public void	actionPerformed
				( ActionEvent	anEvent )
	{
		data = String.valueOf( isSelected() );
		changeImpl.notifyDataChanged( id, data );
	}
			
	/**
	 * set data id
	 * @param anId id
	 */
	public void	setDataId
				( String	anId )
	{
		id = anId;
	}

	/**
	 * set data value. If this value is set, when fillInputLookup is called, this value will be returned
	 * instead of the default boolean value. If the checkbox is not selected, no value will be filled in the lookup
	 * @param aValue value
	 */
	public void	setDataValue
				( String	aValue )
	{
		value = aValue;
	}
		
	/**
	 * fill input lookup
	 * @param anInputLookup input lookup
	 */
	public void	fillInputLookup
				( InputLookup	anInputLookup )
	{
		if ( value == null )
		{	
			data = String.valueOf( isSelected() );
			anInputLookup.putInput( id, data );
		}
		else if ( isSelected() )
		{
			anInputLookup.putInput( id, value );
		}
	}

	/**
	 * set data value from input lookup
	 * @param anInputLookup input lookup
	 */
	public void	setInputLookup
				( InputLookup	anInputLookup )
	{
		if ( value == null )
		{
			data = anInputLookup.getParameter( id );
			setSelected( Boolean.valueOf( data ).booleanValue() );
		}
		else
		{
			boolean isSelected = false;
			String[] values = anInputLookup.getParameterValues( id );
			for( int count=0; count<values.length; count++ )
			{
				if ( values[count].equals( value ) )
				{
					isSelected = true;
					break;
				}
			}
			setSelected( isSelected );
		}
	}	
	
	/**
	 * set text
	 * @param aText text
	 */
	public void	setText
				( String	aText )
	{
		text = aText;
		super.setText( text );
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
		super.setText( aTranslator.translate( text, aLocale ) );
		translator = aTranslator;
		locale = aLocale;
	}

	/** 
	 * add data ui change listener
	 * @param aUIChangeListener ui change listener
	 */
	public final void	addDataUIChangeListener
						( DataUIChangeListener	aListener )
	{
		changeImpl.addDataUIChangeListener( aListener );
	}
	
	/** 
	 * remove data ui change listener
	 * @param aUIChangeListener ui change listener
	 */
	public final void	removeDataUIChangeListener
						( DataUIChangeListener	aListener )
	{
		changeImpl.removeDataUIChangeListener( aListener );
	}
	
	/**
	 * remove all listener
	 */
	public final void	removeAllDataUIChangeListeners()
	{
		changeImpl.removeAllListeners();
	}
		
	/**
	 * cleanup
	 */
	public void	cleanup()
	{
		removeAllDataUIChangeListeners();
	}
	
	public Component	getListCellRendererComponent
					( JList		aList,
			          Object 	aValue,
			          int 		anIndex,
			          boolean 	isSelected,
			          boolean 	cellHasFocus )
	{
		if ( aValue instanceof DescriptiveValue )
		{
			if ( translator != null )
			{
				setText( translator.translate( 
							((DescriptiveValue)aValue).getDescription(),
							locale ) );
			}
		}
		else
		{
			if ( translator != null )
			{
				setText( translator.translate( 
							aValue.toString(),
							locale ) );
			}			
		}
		setSelected( isSelected );
		return this;
	}

	public void setSelectedQuietly( boolean b )
	{
		ActionListener[] listeners = this.getActionListeners();
		
		for ( int i = 0; i < listeners.length; i++ )
		{
			this.removeActionListener( listeners[i] );
		}
		
		this.setSelected( b );
		
		for ( int i = 0; i < listeners.length; i++ )
		{
			this.addActionListener( listeners[i] );
		}
	}
}