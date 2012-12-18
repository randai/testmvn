package com.flx.common.ui;

import java.awt.*;

import javax.swing.JButton;

import com.flx.common.internationalization.Translatable;

/**
 * ApplicationUIContainer defines application user interface
 * container. Container should be able to handle message display
 */
public interface ApplicationUIContainer
extends	Translatable
{
	/**
	 * show question dialog
	 * @param aTitle title 
	 * @param aQuestion question 
	 * @param allOptions options
	 */
	public int	showQuestionDialog
				( String	aTitle,
				  String	aQuestion,
				  String[]	allOptions );
	
	public int	showOverrideRatesDialog
				( 	String	aTitle,
					Object	anObject );

	/**
	 * show input information dialog
	 * @param aTitle title
	 * @param anInfoPanel info panel
	 * @return true if user confirm input or false if user cancel input
	 */
	public int	showConfigDialog
					( String	aTitle,
					  Object	anObject );

	/**
     * close the current config dialog if it is visible
     * @return true if the config dialog was visible and is closed
     */
	public boolean exitConfigDialog();
	
	/**
	 * show input information dialog
	 * @param aTitle title
	 * @param anInfoPanel info panel
	 * @return true if user confirm input or false if user cancel input
	 */
	public boolean	showInputInfoDialog
					( String	aTitle,
					  Object	anObject );
	
	/**
	 * show input information dialog
	 * @param aTitle title
	 * @param anInfoPanel info panel
	 * @param allOptions all options text displayed as option buttons
	 * @param aDefaultOptionIndex default option index
	 * @return index of options user has choosen
	 */
	public int		showInputInfoDialog
					( String	aTitle,
					  Object	anObject,
					  String[]	allOptions,
					  int		aDefaultOptionIndex );
					  
	/**
	 * show confirmation dialog
	 * @param aTitle title
	 * @param aMessage message
	 * @return true if user confirm or false if user cancel
	 */
	public boolean	showConfirmationDialog
					( String	aTitle,
					  String	aMessage );

	/**
	 * show acknowlegement 
	 * @param aTitle title
	 * @param aMessage message
	 */
	public void	showAcknowledgementDialog
				( String	aTitle,
				  String	aMessage );
				  
	/**
	 * show error dialog
	 * @param anErrorMessage error message
	 */
	public void	showErrorDialog
				( String	anErrorMessage );
							  
	/**
	 * show error dialog
	 * @param allErrorMessages all error messages
	 */
	public void	showErrorDialog
				( String[]	allErrorMessages );
	
	/**
	 * show error dialog
	 * @param aTitle title 
	 * @param anErrorMessage error message
	 */
	public void	showErrorDialog
				( String	aTitle,
				  String	anErrorMessage );
							  
	/**
	 * show error dialog
	 * @param aTitle title
	 * @param allErrorMessages all error messages
	 */
	public void	showErrorDialog
				( String	aTitle,
				  String[]	allErrorMessages );
	
	/**
	 * get preferred size
	 * @return preferred size
	 */
	public Dimension	getPreferredSize();
	
	/**
	 * set size
	 * @param aSize size
	 */
	public void	setSize
				( Dimension	aSize );
						
	/**
	 * pack
	 */
	public void	pack();
	
	/**
	 * set menu
	 * @param aMenu menu
	 */
	public void	setMenu
				( Object	aMenu );
	
	/**
	 * get title
	 * @param aTitle title
	 */
	public String getTitle();
	
	/**
	 * set title
	 * @param aTitle title
	 */
	public void	setTitle
				( String	aTitle );
	
	/**
	 * set icon image
	 * @param anIcon icon
	 */
	public void	setIconImage
				( Image		anIcon );
				
	/**
	 * close
	 */
	public void close();
	
	/**
	 * check if the current container is visible
	 * @return true if the current container is visible
	 */
	public boolean	isVisible();

	/**
	 * dispose the ui container
	 */
	public void dispose();
	
	/**
	 * lock the container which will prevent any UI event to occur
	 * @param aColor color to show application is locked
	 * @param anUnlockInstruction message to provide instruction to unlock
	 */
	public void	lock
				( Color		aColor,
				  String	anUnlockInstruction );
	
	/**
	 * lock the container which will prevent any UI event to occur but does not allow unlock
	 * @param aColor color to show application is locked
	 */
	public void	lock
				( Color		aColor );
	
	/**
	 * programmatically unlock the screen
	 */
	public void	unlock();
	
	/**
	 * bring the window to front
	 */
	public void bringToFront();
	
	/**
	 * set default button
	 * @param aDefaultButton default button
	 */
	public void	setDefaultButton
				( JButton	aDefaultButton );
	
	/**
	 * get default button
	 */
	public JButton getDefaultButton();
}