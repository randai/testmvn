package com.flx.common.ui.swing;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;
import java.util.Locale;

import javax.swing.*;

import com.flx.common.internationalization.Translator;
import com.flx.common.ui.CustomText;
import com.flx.common.ui.DataUIChangeListener;
import com.flx.common.ui.DataUIComponent;
import com.flx.common.util.InputLookup;

/**
 * SwingLabel is dreamwire's enhanced JLabel from Swing package
 */
public class SwingLabel 
extends		JLabel
implements	DataUIComponent
{
	private String			text
							= "";
	private String			tooltipText
							= "";
	private CustomText[]	customTexts;
	
	/**
	 * create label
	 */
	public	SwingLabel()
	{
		
	}
	
	/**
	 * create label
	 * @param aText text
	 */
	public	SwingLabel
			( String	aText )
	{
		setText( aText );
	}
	
	/**
	 * create label
	 * @param aFontStyle font style
	 */
	public	SwingLabel
			( int		aFontStyle )
	{
		setFont( getFont().deriveFont( aFontStyle ) );
	}
	
	/**
	 * create label
	 * @param aFontStyle font style
	 * @param aTextPosition text position
	 */
	public	SwingLabel
			( int		aFontStyle,
			  int		aTextPosition )
	{
		setFont( getFont().deriveFont( aFontStyle ) );
		setHorizontalAlignment( aTextPosition );
	}
	
	/**
	 * create label
	 * @param aFontStyle font style
	 * @param aTextPosition text position
	 * @param aFontSize font size
	 */
	public	SwingLabel
			( int		aFontStyle,
			  int		aTextPosition,
			  float		aFontSize )
	{
		setFont( getFont().deriveFont( aFontStyle ).deriveFont( aFontSize ) );
		setHorizontalAlignment( aTextPosition );
	}
	
	/**
	 * create label
	 * @param aText text
	 * @param aFontStyle font style
	 */
	public	SwingLabel
			( String	aText,
			  int		aFontStyle )
	{
		this( aText );
		setFont( getFont().deriveFont( aFontStyle ) );
	}
	
	/**
	 * create label
	 * @param aText text
	 * @param aFontStyle font style
	 * @param aTextPosition text position
	 */
	public	SwingLabel
			( String	aText,
			  int		aFontStyle,
			  int		aTextPosition )
	{
		this( aText, aFontStyle );
		setHorizontalAlignment( aTextPosition );
	}
	
	/**
	 * set data id
	 * @param aDataId data id
	 */
	public void	setDataId
				( String	aDataId )
	{
		
	}
	
	/**
	 * fill input lookup
	 * @param anInputLookup input lookup
	 */
	public void	fillInputLookup
				( InputLookup	anInputLookup )
	{
	}

	/**
	 * set input lookup
	 * @param anInputLookup input lookup
	 */
	public void	setInputLookup
				( InputLookup	anInputLookup )
	{
	}	

	/**
	 * set custom texts
	 * @param allTexts all texts
	 * @param allFonts all fonts
	 * @param allColors all colors
	 */
	public void	setCustomTexts
				( String[]		allTexts,
				  Font[]		allFonts,
				  Color[]		allColors )
	{
		customTexts = new CustomText[allTexts.length];
		for( int count=0; count<allTexts.length; count++ )
		{
			customTexts[count] = new CustomText( allTexts[count], allFonts[count], allColors[count] );
		}
		text = null;
		super.setText( null );
	}
	
	/**
	 * set text
	 * @param aText text
	 */
	public void	setText
				( String	aText )
	{
		text = aText;
		customTexts = null;
		super.setText( aText );
	}
		
	/**
	 * set tooltip text
	 * @param aText tooltip text
	 */
	public void	setToolTipText
				( String		aText )
	{
		tooltipText = aText;
		super.setToolTipText( aText );
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
		super.setToolTipText( aTranslator.translate( tooltipText, aLocale ) );
	}

	/** 
	 * add data ui change listener
	 * @param aUIChangeListener ui change listener
	 */
	public final void	addDataUIChangeListener
						( DataUIChangeListener	aListener )
	{
	}
	
	/** 
	 * remove data ui change listener
	 * @param aUIChangeListener ui change listener
	 */
	public final void	removeDataUIChangeListener
						( DataUIChangeListener	aListener )
	{
	}
	
	/**
	 * remove all listener
	 */
	public final void	removeAllDataUIChangeListeners()
	{
	}
	
	/**
	 * cleanup
	 */
	public void	cleanup()
	{
	}
	
	/**
	 * paint
	 */
	public void	paint
				( Graphics	aGraphics )
	{
		super.paint( aGraphics );
		// paint custom texts
		if ( customTexts != null )
		{
			Graphics2D graphics = (Graphics2D)aGraphics;
			FontRenderContext fontRenderContext = (FontRenderContext)graphics.getFontRenderContext();
			Rectangle2D[] bounds = new Rectangle2D[customTexts.length];
			TextLayout layout = null;
			// calculate the max font height and total width
			double height = 0;
			double width = 0;		
			for( int count=0; count<customTexts.length; count++ )
			{
				if ( customTexts[count].getText().length() != 0 )
				{
					layout = new TextLayout( customTexts[count].getText(), customTexts[count].getFont(), fontRenderContext );
					bounds[count] = layout.getBounds();
					if ( bounds[count].getHeight() > height )
					{
						height = bounds[count].getHeight();
					}
					width += bounds[count].getWidth();
				}
			}
			// calculate starting point
			double x = ( getSize().getWidth() - width ) / 2d;
			double y = ( getSize().getHeight() - height ) / 2d + height;
			// start painting the text
			for( int count=0; count<customTexts.length; count++ )
			{
				graphics.setColor( customTexts[count].getColor() );
				graphics.setFont( customTexts[count].getFont() );
				graphics.drawString( customTexts[count].getText(), (float)x, (float)y );
				if ( bounds[count] != null )
				{
					x += bounds[count].getWidth() + 5;
				}
			}			
		}
	}	
}
