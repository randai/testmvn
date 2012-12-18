package com.flx.common.ui.swing;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Locale;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.JWindow;
import javax.swing.KeyStroke;
import javax.swing.MenuElement;
import javax.swing.text.DefaultEditorKit;
import javax.swing.text.JTextComponent;
import javax.swing.text.Keymap;

import com.flx.common.internationalization.Translatable;
import com.flx.common.internationalization.Translator;
import com.flx.common.ui.ApplicationUIContainer;
import com.flx.common.ui.UIHelper;

/**
 * SwingHelper contains helper methods for working with swing
 */
public class SwingHelper
{
	/**
	 * initialize keystroke bindings
	 */
	public final static void	InitializeKeyStrokeBindings()
	{
		String selectAllAction 	= DefaultEditorKit.selectAllAction;
        String cutAction 		= DefaultEditorKit.cutAction;
        String copyAction 		= DefaultEditorKit.copyAction;
        String pasteAction 		= DefaultEditorKit.pasteAction;

        int mask = Toolkit.getDefaultToolkit().getMenuShortcutKeyMask();

		JTextComponent.KeyBinding ctrlA = new JTextComponent.KeyBinding(
																KeyStroke.getKeyStroke( KeyEvent.VK_A, mask ), 
																selectAllAction);
        JTextComponent.KeyBinding ctrlX = new JTextComponent.KeyBinding(
                												KeyStroke.getKeyStroke(KeyEvent.VK_X, mask), 
                												cutAction);
        JTextComponent.KeyBinding ctrlC = new JTextComponent.KeyBinding(
                												KeyStroke.getKeyStroke(KeyEvent.VK_C, mask), 
                												copyAction);
        JTextComponent.KeyBinding ctrlV = new JTextComponent.KeyBinding(
                												KeyStroke.getKeyStroke(KeyEvent.VK_V, mask),
                												pasteAction );

        JTextComponent.KeyBinding[] extraBindings = new JTextComponent.KeyBinding[] {
                												ctrlA, ctrlX, ctrlC, ctrlV };

        Keymap defaultKeyMap = JTextComponent.getKeymap(JTextComponent.DEFAULT_KEYMAP);

        JTextComponent dummy = new JTextField();
        JTextComponent.loadKeymap( defaultKeyMap, extraBindings, dummy.getActions() );
	}
	
	/**
	 * create splash
	 * @param aSplashImage location of splash image related to the classpath
	 * @param aRetriever class of object request for the image
	 * @return splash window or null if unable to load the image
	 */
	public final static JWindow	CreateSplash
								( String		aSplashImage,
								  Class			aRetriever )
	{
		if ( aSplashImage != null )
		{
			ImageIcon icon = GetImageFromResource( aSplashImage, aRetriever );
			if ( icon != null )
			{
				JWindow window = new JWindow();
				window.getContentPane().add( new JLabel( icon ) );
				window.pack();
				UIHelper.PositionComponentAtCenter( window );
				window.setVisible( true );
				return window;
			}
		}
		return null;
	}
	
	/**
	 * dispose splash
	 * @param aSplashWindow splash window
	 */
	public final static void	DisposeSplash
							( JWindow	aSplashWindow )
	{
		if ( aSplashWindow != null )
		{
			aSplashWindow.setVisible( false );
			aSplashWindow.dispose();
		}
	}
	
	/**
	 * set icon
	 * @param anImageIcon image icon
	 * @param aContainer container
	 */
	public final static void	SetIcon	
							( ImageIcon				anImageIcon,
							  ApplicationUIContainer	aContainer )
	{
		if ( aContainer instanceof JInternalFrame )
		{
			((JInternalFrame)aContainer).setFrameIcon( anImageIcon );
		}
		else if ( aContainer instanceof JFrame )
		{
			((JFrame)aContainer).setIconImage( anImageIcon.getImage() );
		}
	}

	/**
	 * return image icon from resource location ( ie Classpath )
	 * File could be placed in the classpath or in a jar file
	 * @param aFilename file name of the image file
	 * @param aRetriever object request for the image
	 * @return image icon
	 */
	public final static ImageIcon	GetImageFromResource
									( String	aFilename,
									  Object	aRetriever )
	{
		return GetImageFromResource( aFilename, aRetriever.getClass() );
	}
	
	/**
	 * return image icon from resource location ( ie Classpath )
	 * File could be placed in the classpath or in a jar file
	 * @param aFilename file name of the image file
	 * @param aRetrieverClass class request for the image
	 * @return image icon
	 */
	public final static ImageIcon	GetImageFromResource
									( String	aFilename,
									  Class		aRetrieverClass )
	{
		URL url = null;
		url = aRetrieverClass.getResource( aFilename );
		if ( url == null )
			return null;
		else
			return new ImageIcon( url );
	}

	/**
	 * get the file contents from resource as string
	 * @param aFileName file name
	 * @param aRetrieverClass retriever class
	 * @return
	 * @throws IOException
	 */
	public static String GetFileContentFromResourceAsString
						( String aFileName ,
						 Class	aRetrieverClass)
	throws IOException
	{
		InputStream input = null;
		BufferedReader bReader = null;
		try
		{
			// read all the file content
			StringBuffer buffer = new StringBuffer();
			input = aRetrieverClass.getResourceAsStream( aFileName );
			InputStreamReader isr = new InputStreamReader( input );
			bReader = new BufferedReader( isr );
			String line;
			while ( ( line = bReader.readLine() ) != null )
			{
				buffer.append( line );
				buffer.append( System.getProperty( "line.separator" ) );
			}
			return buffer.toString();
		}
		finally
		{
			if ( bReader != null )
				bReader.close();
			if ( input != null )
				input.close();
		}
	}
	/**
	 * change language of a menu 
	 * @param allMenuElements menu elements
	 * @param aTranslator translator 
	 * @param aLocale locale
	 */
	public final static void	ChangeLanguage
							( MenuElement[]	allMenuElements,
							  Translator 	aTranslator,
							  Locale			aLocale )
	{
		for( int count=0; count<allMenuElements.length; count++ )
		{
			if ( allMenuElements[count] instanceof Translatable )
			{
				((Translatable)allMenuElements[count]).changeLanguage( aTranslator, aLocale );
				MenuElement[] subItems = allMenuElements[count].getSubElements();
				ChangeLanguage( subItems, aTranslator, aLocale );
			}
		}
	}
	
	/**
	 * change language of a menu 
	 * @param aMenu menu bar
	 * @param aTranslator translator 
	 * @param aLocale locale
	 */
	private final static void	ChangeLanguage
							( JMenuItem		aMenu,
							  Translator 	aTranslator,
							  Locale			aLocale )
	{
		if ( aTranslator == null )
		{
			return;
		}
		if ( aMenu instanceof Translatable )
		{
			((Translatable)aMenu).changeLanguage( aTranslator, aLocale );
		}
		if ( aMenu instanceof JMenu )
		{
			JMenu menu = (JMenu)aMenu;
			int noOfItems = menu.getItemCount();
			for( int count=0; count<noOfItems; count++ )
			{
				ChangeLanguage( menu.getItem( count ), aTranslator, aLocale );
			}
		}
	}
									
	/**
	 * change language of a menu bar
	 * @param aMenuBar menu bar
	 * @param aTranslator translator 
	 * @param aLocale locale
	 */
	public final static void	ChangeLanguage
							( JMenuBar		aMenuBar,
							  Translator 	aTranslator,
							  Locale			aLocale )
	{
		if ( aMenuBar == null 
			 ||
			 aTranslator == null )
		{
			return;
		}
		int noOfMenu = aMenuBar.getMenuCount();
		for( int count=0; count<noOfMenu; count++ )
		{
			ChangeLanguage( aMenuBar.getMenu( count ), aTranslator, aLocale );
		}
	}
	
	/**
	 * select index silently
	 * @param aComboBox combo box
	 * @param anIndex index
	 * @param aListener listener to be removed when selecting index
	 */
	public final static void	SelectIndexSilently
							( JComboBox		aComboBox,
							  int			anIndex,
							  ItemListener	aListener )
	{
		aComboBox.removeItemListener( aListener );
		aComboBox.setSelectedIndex( anIndex );
		aComboBox.addItemListener( aListener );
	}

	/**
	 * add item silently
	 * @param aComboBox combo box
	 * @param anObject object to be added
	 * @param aListener listener
	 */
	public final static void	AddItemSilently
							( JComboBox		aComboBox,
							  Object			anObject,
							  ItemListener	aListener )
	{
		aComboBox.removeItemListener( aListener );
		aComboBox.addItem( anObject );
		aComboBox.addItemListener( aListener );
	}

	/**
	 * remove all items silently
	 * @param aComboBox combo box
	 * @param aListener listener
	 */
	public final static void	RemoveAllItemsSilently
							( JComboBox		aComboBox,
							  ItemListener	aListener )
	{
		aComboBox.removeItemListener( aListener );
		aComboBox.removeAllItems();
		aComboBox.addItemListener( aListener );
	}
		
	/**
	 * select silently
	 * @param aButton button
	 * @param isSelected indicates if button should be selected
	 * @param aListener listener to be removed when selecting index
	 */
	public final static void	SelectSilently
							( AbstractButton	aButton,
							  boolean		isSelected,
							  ActionListener	aListener )
	{
		aButton.removeActionListener( aListener );
		aButton.setSelected( isSelected );
		aButton.addActionListener( aListener );
	}
	
	/**
	 * change background and foreground color for menu bar
	 * @param aMenuBar menu bar
	 * @param aBackgroundColor background color
	 * @param aForegroundColor foreground color
	 */
	public final static void	ChangeColor
								( MenuElement	aMenuItem,
								  Color			aBackgroundColor,
								  Color			aForegroundColor )
	{
		if ( aMenuItem instanceof JPopupMenu )
		{
			((JPopupMenu)aMenuItem).setBorderPainted( true );
		}
		if ( aBackgroundColor != null )
		{
			((JComponent)aMenuItem).setBackground( aBackgroundColor );
		}
		if ( aForegroundColor != null )
		{
			((JComponent)aMenuItem).setForeground( aForegroundColor );
		}
		MenuElement[] elements = aMenuItem.getSubElements();
		for( int count=0; count<elements.length; count++ )
		{
			ChangeColor( elements[count], aBackgroundColor, aForegroundColor );
		}
	}
	
	/**
	 * change background and foreground color for menu bar
	 * @param aMenuBar menu bar
	 * @param aBackgroundColor background color
	 * @param aForegroundColor foreground color
	 */
	public final static void	ChangeColor
								( JMenuBar	aMenuBar,
								  Color		aBackgroundColor,
								  Color		aForegroundColor )
	{
		if ( aBackgroundColor != null )
		{
			aMenuBar.setBackground( aBackgroundColor );
		}
		if ( aForegroundColor != null )
		{
			aMenuBar.setForeground( aForegroundColor );
		}
		JMenu menu = null;
		for( int count=0; count<aMenuBar.getMenuCount(); count++ )
		{
			menu = aMenuBar.getMenu( count );
			ChangeColor( menu, aBackgroundColor, aForegroundColor );
		}
	}	
}