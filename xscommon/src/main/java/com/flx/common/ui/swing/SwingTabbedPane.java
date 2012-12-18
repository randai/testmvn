package com.flx.common.ui.swing;

import java.awt.*;
import java.util.*;

import javax.swing.*;

import com.flx.common.internationalization.Translatable;
import com.flx.common.internationalization.Translator;
import com.flx.common.ui.DataUIContainerHelper;
import com.flx.common.util.InputLookup;

/**
 * SwingTabbedPane is the enhanced version of JTabbedPane which
 * handles internationalization
 */
public class SwingTabbedPane
extends		JTabbedPane
implements	Translatable
{
	private ArrayList			titles
								= new ArrayList();

	/**
	 * set date format style
	 * @param aDateFormatStyle date format style
	 */
	public void	setDateFormatStyle
				( int		aDateFormatStyle )
	{
	}

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
	 * add tab
	 * @param aTitle title
	 * @param aComponent component
	 * @param isVertical indicates if the tab title is vertical
	 */
	public void	add
				( String		aTitle,
				  Component	aComponent,
				  boolean	isVertical )
	{
		if ( isVertical )
		{
			VerticalTextIcon icon = new VerticalTextIcon( this, aTitle );
			super.addTab( "", icon, aComponent );
		}
		else
		{
			super.addTab( aTitle, aComponent );
		}
		titles.add( aTitle );
	}
	
	/**
	 * add tab
	 * @param aTitle title
	 * @param aComponent component
	 */
	public void	addTab
				( String		aTitle,
				  Component		aComponent )
	{
		super.addTab( aTitle, aComponent );
		titles.add( aTitle );
	}

	/**
	 * add tab
	 * @param aTitle title
	 * @param anIcon icon
	 * @param aComponent component
	 */
	public void	addTab
				( String	aTitle,
				  Icon		anIcon,
				  Component	aComponent )
	{
		super.addTab( aTitle, anIcon, aComponent );
		titles.add( aTitle );
	}
	
	/**
	 * add tab
	 * @param aTitle title
	 * @param anIcon icon
	 * @param aComponent component
	 * @param aTip tip
	 */
	public void	addTab
				( String	aTitle,
				  Icon		anIcon,
				  Component	aComponent,
				  String	aTip )
	{
		super.addTab( aTitle, anIcon, aComponent, aTip );
		titles.add( aTitle );
	}

	/**
	 * remove
	 * @param anIndex index
	 */
	public void	remove
				( int	anIndex )
	{
		super.remove( anIndex );
		// This has been removed due to the fact that super.remove( anIndex )
		// will call removeTabAt( anIndex ) which will remove the titles already
		// titles.remove( anIndex );
	}
	
	/**
	 * remove 
	 * @param aComponent component
	 */
	public void	remove
				( Component	aComponent )
	{
		int noOfTabs = getTabRunCount();
		for( int count=0; count<noOfTabs; count++ )
		{
			if ( getComponentAt( count ) == aComponent )
			{
				titles.remove( count );
				break;
			}
		}
		super.remove( aComponent );
	}
	
	/**
	 * remove all
	 */
	public void	removeAll()
	{
		super.removeAll();
		titles.clear();
	}
	
	/**
	 * remove tab at
	 * @param anIndex index
	 */
	public void	removeTabAt
				( int	anIndex )
	{
		super.removeTabAt( anIndex );
		// somehow 1.6.0.25 seems to be calling removeTabAt at index 0
		// even though there is no tab
		// put this in to avoid issue
		if ( titles.size() > anIndex )
		{
			titles.remove( anIndex );
		}
	}

	/**
	 * set title at
	 * @param anIndex index
	 * @param aTitle title
	 */
	public void	setTitleAt
				( int 		anIndex, 
				  String 	aTitle )
	{
		super.setTitleAt( anIndex, aTitle );
		titles.remove( anIndex );
		titles.add( anIndex, aTitle );
	}

	//void setToolTipTextAt(int index, String toolTipText) 
 	
	/**
	 * change language
	 * @param aTranslator translator 
	 * @param aLocale locale
	 */
	public void	changeLanguage
				( Translator	aTranslator,
				  Locale		aLocale )
	{
		int count=0;
		for( Iterator i = titles.iterator();
			 i.hasNext(); )
		{
			super.setTitleAt( count, aTranslator.translate( (String)i.next(), aLocale ) );
			count++;
		}
		Component component = null;
		for( count=0; count<getTabCount(); count++ )
		{
			component = getComponentAt( count );
			if ( component instanceof Translatable )
			{
				((Translatable)component).changeLanguage( aTranslator, aLocale );
			}
			else if ( component instanceof Container )
			{
				DataUIContainerHelper.ChangeLanguage( (Container)component, aTranslator, aLocale );
			}
		}
	}

	/**
 	 * reset height. This is useful to set the height to minimum value
	 * when no component is added to the tabbed pane
	 */	
	public void	resetNoComponentHeight()
	{
		Dimension size = getPreferredSize();
		if ( UIManager.getLookAndFeel().getClass().getName().equals(
				"apple.laf.AquaLookAndFeel" ) )
		{
			size.height = 30;
		}
		else
		{
			size.height = 25;
		}
		setPreferredSize( size );
	}
}
