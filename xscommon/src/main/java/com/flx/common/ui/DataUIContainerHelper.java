package com.flx.common.ui;

import java.awt.*;
import java.util.Locale;

import com.flx.common.internationalization.Translatable;
import com.flx.common.internationalization.Translator;
import com.flx.common.util.InputLookup;

/**
 * DataUIContainerHelper contains methods to ease implementing
 * data ui container
 */
public class DataUIContainerHelper
{
	/**
	 * add data change listener
	 * @param aContainer container
	 * @param aListener listener
	 */
	public final static void	AddDataUIChangeListener	
							( Container				aContainer,
							  DataUIChangeListener	aListener )
	{
		Component[] components = aContainer.getComponents();
		for( int count=0; count<components.length; count++ )
		{
			if ( components[count] instanceof DataUIComponent )
			{
				DataUIComponent component = (DataUIComponent)components[count];
				component.addDataUIChangeListener( aListener );
			}
			else if ( components[count] instanceof Container )
			{
				AddDataUIChangeListener( (Container)components[count], aListener );
			}
		}
	}

	/**
	 * remove data change listener
	 * @param aContainer container
	 * @param aListener listener
	 */
	public final static void	RemoveDataUIChangeListener	
							( Container				aContainer,
							  DataUIChangeListener	aListener )
	{
		Component[] components = aContainer.getComponents();
		for( int count=0; count<components.length; count++ )
		{
			if ( components[count] instanceof DataUIComponent )
			{
				DataUIComponent component = (DataUIComponent)components[count];
				component.removeDataUIChangeListener( aListener );
			}
			else if ( components[count] instanceof Container )
			{
				RemoveDataUIChangeListener( (Container)components[count], aListener );
			}
		}
	}

	/**
	 * remove all data change listener
	 * @param aContainer container
	 * @param aListener listener
	 */
	public final static void	RemoveAllDataUIChangeListeners	
							( Container	aContainer )
	{
		Component[] components = aContainer.getComponents();
		for( int count=0; count<components.length; count++ )
		{
			if ( components[count] instanceof DataUIComponent )
			{
				DataUIComponent component = (DataUIComponent)components[count];
				component.removeAllDataUIChangeListeners();
			}
			else if ( components[count] instanceof Container )
			{
				RemoveAllDataUIChangeListeners( (Container)components[count] );
			}
		}
	}
		
	/**
	 * set input lookup
	 * @param aContainer container
	 * @param aLookup input lookup
	 */
	public final static void	SetInputLookup	
							( Container		aContainer,
							  InputLookup	aLookup )
	{
		Component[] components = aContainer.getComponents();
		for( int count=0; count<components.length; count++ )
		{
			if ( components[count] instanceof DataUIComponent )
			{
				DataUIComponent component = (DataUIComponent)components[count];
				component.setInputLookup( aLookup );
			}
			else if ( components[count] instanceof Container )
			{
				SetInputLookup( (Container)components[count], aLookup );
			}
		}
	}
								  
	/**
	 * fill input lookup with data information from data ui component
	 * @param aContainer container
	 * @param aLookup input lookup
	 */
	public final static void	FillInputLookup
							( Container		aContainer,
							  InputLookup 	aLookup )
	{
		Component[] components = aContainer.getComponents();
		for( int count=0; count<components.length; count++ )
		{
			if ( components[count] instanceof DataUIComponent )
			{
				DataUIComponent component = (DataUIComponent)components[count];
				component.fillInputLookup( aLookup );
			}
			else if ( components[count] instanceof Container )
			{
				FillInputLookup( (Container)components[count], aLookup );
			}
		}
	}
	
	/**
	 * change border language of a component
	 * @param aComponent component
	 */
	private static void	ChangeBorderLanguage
						( Component		aComponent,
						  Translator	aTranslator,
						  Locale		aLocale )
	{
		if ( aComponent instanceof javax.swing.JComponent )
		{
			javax.swing.border.Border border = 
				((javax.swing.JComponent)aComponent).getBorder();
			if ( border != null
				 &&
				 border instanceof Translatable )
			{
				((Translatable)border).changeLanguage( aTranslator, aLocale );
			}
		}
	}
			
	/** 
	 * change language
	 * @param aContainer container 
	 * @param aTranslator translator
	 * @param aLocale locale
	 */
	public final static void	ChangeLanguage
							( Container		aContainer,
							  Translator	aTranslator,
							  Locale		aLocale )
	{
		ChangeBorderLanguage( aContainer, aTranslator, aLocale );
		Component[] components = aContainer.getComponents();
		for( int count=0; count<components.length; count++ )
		{
			if ( components[count] instanceof Translatable )
			{
				Translatable component = (Translatable)components[count];
				component.changeLanguage( aTranslator, aLocale );
				ChangeBorderLanguage( (Component)component, aTranslator, aLocale );
			}
			else if ( components[count] instanceof Container )
			{
				ChangeLanguage( (Container)components[count], 
								aTranslator,
								aLocale );
			}
		}
	}		
	
	/**
	 * clean up
	 */
	public final static void	Cleanup
							( Container	aContainer )
	{
		Component[] components = aContainer.getComponents();
		for( int count=0; count<components.length; count++ )
		{
			if ( components[count] instanceof DataUIComponent )
			{
				DataUIComponent component = (DataUIComponent)components[count];
				component.cleanup();
				if ( component instanceof javax.swing.JComponent )
				{
					javax.swing.border.Border border = 
						((javax.swing.JComponent)component).getBorder();
					if ( border != null
						 &&
						 border instanceof DataUIComponent )
					{
						((DataUIComponent)border).cleanup();
					}
				}
			}
			else if ( components[count] instanceof Container )
			{
				Cleanup( (Container)components[count] );
			}
		}
	}
}