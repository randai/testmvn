package com.flx.common.ui.swing.laf;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.plaf.basic.BasicArrowButton;

import com.flx.common.SystemException;
import com.flx.common.config.Configuration;
import com.flx.common.ui.swing.SwingUILookAndFeel;
import com.incors.plaf.alloy.AlloyCommonUtilities;
import com.incors.plaf.alloy.AlloyLookAndFeel;
import com.incors.plaf.alloy.dr;

/**
 * AlloyTraderUILookAndFeel is an implementation of trader ui look and feel
 * using alloy look and feel
 */
public class AlloyUILookAndFeel
extends	SwingUILookAndFeel
{
	/**
	 * configure trader ui information
	 * @param aConfiguration configuration
	 * @exception SystemException thrown if there is a problem configuring trader ui information
	 */
	public void	postConfigure
				( Configuration	aConfiguration )
	throws	SystemException
	{
		String prefix = "ui.lookAndFeel.alloy.";
		Color contrast = getColor( aConfiguration, prefix + "contrast" );
		Color desktop = getColor( aConfiguration, prefix + "desktop" );
		Color standard = getColor( aConfiguration, prefix + "standard" );
		Color selection = getColor( aConfiguration, prefix + "selection" );
		Color rollover = getColor( aConfiguration, prefix + "rollover" );
		Color highlight = getColor( aConfiguration, prefix + "highlight" );
		com.incors.plaf.alloy.AlloyTheme myTheme = null;
		// create custom theme
		if ( contrast != null
			 &&
			 desktop != null
			 &&
			 standard != null
			 &&
			 selection != null
			 &&
			 rollover != null
			 &&
			 highlight != null )
		{
			myTheme =
			    com.incors.plaf.alloy.themes.custom.CustomThemeFactory.createTheme(contrast,
			    standard, desktop, selection, rollover, highlight);	
		}
		if ( aConfiguration.getBoolean( prefix + "turnOffMenuLayoutAdjustment", false ) )
		{
			AlloyLookAndFeel.setProperty( "alloy.menuLayoutStyle", "noAdjustment" );	
		}
		AlloyLookAndFeel.setProperty( "alloy.isLookAndFeelFrameDecoration", "true" );
		AlloyLookAndFeel.setProperty( "alloy.frameButtonStyle", "modern" );
		JFrame.setDefaultLookAndFeelDecorated( true );
		// create look and feel with the custom theme 
		try 
		{
			javax.swing.LookAndFeel alloyLnF = null;
			if ( myTheme != null )
			{
				alloyLnF = new com.incors.plaf.alloy.AlloyLookAndFeel(myTheme);
			}
			else
			{
				alloyLnF = new com.incors.plaf.alloy.AlloyLookAndFeel();
			}
			javax.swing.UIManager.setLookAndFeel(alloyLnF);
		} 
		catch (javax.swing.UnsupportedLookAndFeelException ex) 
		{
		  throw new SystemException( "Unable to configure AlloyTraderUILookAndFeel", ex );
		}	
	}
	
	/**
	 * set button background
	 * @param aButton button
	 * @param aColor background color
	 */
	public void	setBackground
				( JButton	aButton,
				  Color		aBackground )
	{
		aButton.setOpaque( false );
		AlloyCommonUtilities.set3DBackground( aButton, aBackground );
	}

	/**
	 * process rate button
	 * @param aButton button
	 */
	public void	processRateButton
				( JButton	aButton )
	{
		if ( rateButtonColor != null )
		{
			setBackgroundColor( aButton, rateButtonColor );
		}
	}

	/**
	 * set background color
	 * @param aButton button
	 * @param aBackground background color
	 */
	public void	setBackgroundColor
				( JButton	aButton,
				  Color		aBackground )
	{
		aButton.setOpaque( false );
		AlloyCommonUtilities.set3DBackground( aButton, aBackground );
	}
	
	/**
	 * create basic arrow button. Subclass can override to provide proper implementation of basic arrow button
	 * @param aDirection direction of the arrow
	 * @return basic arrow button
	 */
	public BasicArrowButton	createBasicArrowButton
							( int	aDirection )
	{
		return new dr( aDirection );
	}
}
