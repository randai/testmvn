package com.flx.common.ui.swing;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.basic.BasicArrowButton;

import com.flx.common.SystemException;
import com.flx.common.config.Configuration;

/**
 * SwingUILookAndFeel provide customization on swing look and feel. 
 */
public abstract class SwingUILookAndFeel
implements	ChangeListener,
			ComponentListener
{
	protected static SwingUILookAndFeel		Instance;
	protected static String					prefix = "ui.lookAndFeel.";
		
	/**
	 * get instance
	 * @return instance
	 */
	public static SwingUILookAndFeel	GetInstance()
	{
		return Instance;
	}
	
	public int getBannerImageAlignment() {
		return bannerImageAlignment;
	}

	public void setBannerImageAlignment(int bannerImageAlignment) {
		this.bannerImageAlignment = bannerImageAlignment;
	}

	public String getBannerLeftImageLocation() {
		return bannerLeftImageLocation;
	}

	public void setBannerLeftImageLocation(String bannerLeftImageLocation) {
		this.bannerLeftImageLocation = bannerLeftImageLocation;
	}

	public int getBannerLeftImageAlignment() {
		return bannerLeftImageAlignment;
	}

	public void setBannerLeftImageAlignment(int bannerLeftImageAlignment) {
		this.bannerLeftImageAlignment = bannerLeftImageAlignment;
	}

	public String getBannerCentreImageLocation() {
		return bannerCentreImageLocation;
	}

	public void setBannerCentreImageLocation(String bannerCentreImageLocation) {
		this.bannerCentreImageLocation = bannerCentreImageLocation;
	}

	public int getBannerCentreImageAlignment() {
		return bannerCentreImageAlignment;
	}

	public void setBannerCentreImageAlignment(int bannerCentreImageAlignment) {
		this.bannerCentreImageAlignment = bannerCentreImageAlignment;
	}

	public String getBannerRightImageLocation() {
		return bannerRightImageLocation;
	}

	public void setBannerRightImageLocation(String bannerRightImageLocation) {
		this.bannerRightImageLocation = bannerRightImageLocation;
	}

	public int getBannerRightImageAlignment() {
		return bannerRightImageAlignment;
	}

	public void setBannerRightImageAlignment(int bannerRightImageAlignment) {
		this.bannerRightImageAlignment = bannerRightImageAlignment;
	}

	public String getIconLocation() {
		return iconLocation;
	}

	public void setIconLocation(String iconLocation) {
		this.iconLocation = iconLocation;
	}

	public Color getBannerBackgroundColor() {
		return bannerBackgroundColor;
	}

	public void setBannerBackgroundColor(Color bannerBackgroundColor) {
		this.bannerBackgroundColor = bannerBackgroundColor;
	}

	public int getBannerHeight() {
		return bannerHeight;
	}

	public void setBannerHeight(int bannerHeight) {
		this.bannerHeight = bannerHeight;
	}

	public Color getInactiveTabForegroundColor() {
		return inactiveTabForegroundColor;
	}

	public void setInactiveTabForegroundColor(Color inactiveTabForegroundColor) {
		this.inactiveTabForegroundColor = inactiveTabForegroundColor;
	}

	public Color getInactiveTabBackgroundColor() {
		return inactiveTabBackgroundColor;
	}

	public void setInactiveTabBackgroundColor(Color inactiveTabBackgroundColor) {
		this.inactiveTabBackgroundColor = inactiveTabBackgroundColor;
	}

	public Color getMenuBackgroundColor() {
		return menuBackgroundColor;
	}

	public void setMenuBackgroundColor(Color menuBackgroundColor) {
		this.menuBackgroundColor = menuBackgroundColor;
	}

	public Color getMenuForegroundColor() {
		return menuForegroundColor;
	}

	public void setMenuForegroundColor(Color menuForegroundColor) {
		this.menuForegroundColor = menuForegroundColor;
	}

	public Color getRateIconBackgroundColor() {
		return rateIconBackgroundColor;
	}

	public void setRateIconBackgroundColor(Color rateIconBackgroundColor) {
		this.rateIconBackgroundColor = rateIconBackgroundColor;
	}

	public Color getRateIconForegroundColor() {
		return rateIconForegroundColor;
	}

	public void setRateIconForegroundColor(Color rateIconForegroundColor) {
		this.rateIconForegroundColor = rateIconForegroundColor;
	}

	public Color getThemeBackgroundColor() {
		return themeBackgroundColor;
	}

	public void setThemeBackgroundColor(Color themeBackgroundColor) {
		this.themeBackgroundColor = themeBackgroundColor;
	}

	public Color getThemeForegroundColor() {
		return themeForegroundColor;
	}

	public void setThemeForegroundColor(Color themeForegroundColor) {
		this.themeForegroundColor = themeForegroundColor;
	}

	public String getFrameIconLocation() {
		return frameIconLocation;
	}

	public void setFrameIconLocation(String frameIconLocation) {
		this.frameIconLocation = frameIconLocation;
	}

	public Color getStandBackgroundColor() {
		return standBackgroundColor;
	}

	public void setStandBackgroundColor(Color standBackgroundColor) {
		this.standBackgroundColor = standBackgroundColor;
	}

	public boolean isMenuSeparator() {
		return menuSeparator;
	}

	public void setMenuSeparator(boolean menuSeparator) {
		this.menuSeparator = menuSeparator;
	}

	public Configuration getConfiguration() {
		return configuration;
	}

	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}

	public void setActiveTabForegroundColor(Color activeTabForegroundColor) {
		this.activeTabForegroundColor = activeTabForegroundColor;
	}

	public void setActiveTabBackgroundColor(Color activeTabBackgroundColor) {
		this.activeTabBackgroundColor = activeTabBackgroundColor;
	}

	public void setRateButtonColor(Color rateButtonColor) {
		this.rateButtonColor = rateButtonColor;
	}

	public void setTableHeaderBackgroundColor(Color tableHeaderBackgroundColor) {
		this.tableHeaderBackgroundColor = tableHeaderBackgroundColor;
	}

	public void setTableHeaderForegroundColor(Color tableHeaderForegroundColor) {
		this.tableHeaderForegroundColor = tableHeaderForegroundColor;
	}

	public void setRateRolloverColor(Color rateRolloverColor) {
		this.rateRolloverColor = rateRolloverColor;
	}

	public void setBidRateColor(Color bidRateColor) {
		this.bidRateColor = bidRateColor;
	}

	public void setAskRateColor(Color askRateColor) {
		this.askRateColor = askRateColor;
	}

	public void setRatesBackgroundColor(Color ratesBackgroundColor) {
		this.ratesBackgroundColor = ratesBackgroundColor;
	}

	public void setAcceptedColor(Color acceptedColor) {
		this.acceptedColor = acceptedColor;
	}

	public void setRejectedColor(Color rejectedColor) {
		this.rejectedColor = rejectedColor;
	}

	public void setPendingColor(Color pendingColor) {
		this.pendingColor = pendingColor;
	}

	public void setDisabledRateColor(Color disabledRateColor) {
		this.disabledRateColor = disabledRateColor;
	}

	protected String	bannerImageLocation;
	public String getBannerImageLocation() {
		return bannerImageLocation;
	}

	public void setBannerImageLocation(String bannerImageLocation) {
		this.bannerImageLocation = bannerImageLocation;
	}

	protected int		bannerImageAlignment;
	protected String	bannerLeftImageLocation;
	protected int		bannerLeftImageAlignment;
	protected String	bannerCentreImageLocation;
	protected int		bannerCentreImageAlignment;
	protected String	bannerRightImageLocation;
	protected int		bannerRightImageAlignment;
	protected String	iconLocation;
	protected Color		bannerBackgroundColor;
	protected int		bannerHeight;
	protected Color		headerForegroundColor;
	protected Color		headerBackgroundColor;
	protected Color		inactiveTabForegroundColor;
	protected Color		inactiveTabBackgroundColor;
	protected Color		activeTabForegroundColor;
	protected Color		activeTabBackgroundColor;
	protected Color		menuBackgroundColor;
	protected Color		menuForegroundColor;
	protected Color		rateButtonColor;
	protected Color		rateIconBackgroundColor;
	protected Color		rateIconForegroundColor;
	protected Color		tableHeaderBackgroundColor;
	protected Color		tableHeaderForegroundColor;
	protected Color		themeBackgroundColor;
	protected Color		themeForegroundColor;
	protected Color		rateRolloverColor;
	protected Color		bidRateColor;
	protected Color		askRateColor;
	protected Color		ratesBackgroundColor;
	protected Color		acceptedColor;
	protected Color		rejectedColor;
	protected Color		pendingColor;
	protected Color		disabledRateColor;
	protected String    frameIconLocation;
	protected Color     standBackgroundColor;
	protected boolean	menuSeparator = false;
	
	private Configuration	configuration;
	
	/**
	 * create look and feel
	 */
	public	SwingUILookAndFeel()
	{
		Instance = this;
	}
	
	/**
	 * get color
	 * @param aConfiguration configuration
	 * @param aConfigName configuration name
	 * @return color or null of there is no configuration
	 * @throws SystemException 
	 */
	protected final Color	getColor
							( Configuration		aConfiguration,
							  String			aConfigName ) 
	{
		String[] colorData = aConfiguration.getStringArray( aConfigName );
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

	/**
	 * get bid rate color
	 * @return bid rate color
	 */
	public Color	getBidRateColor()
	{
		return bidRateColor;
	}
	
	/**
	 * get ask rate color
	 * @return ask rate color
	 */
	public Color	getAskRateColor()
	{
		return askRateColor;
	}
	
	/**
	 * get disabled rate color
	 * @return disabled rate color
	 */
	public Color getDisabledRateColor()
	{
		return disabledRateColor;
	}
	
	/**
	 * get rates background
	 * @return rates background
	 */
	public Color	getRatesBackgroundColor()
	{
		return ratesBackgroundColor;
	}
	
	/**
	 * get accepted color
	 * @return rates background
	 */
	public Color	getAcceptedColor()
	{
		return acceptedColor;
	}
	
	/**
	 * get rejected color
	 * @return rejected color
	 */
	public Color	getRejectedColor()
	{
		return rejectedColor;
	}
	
	/**
	 * get pending color
	 * @return pending color
	 */
	public Color	getPendingColor()
	{
		return pendingColor;
	}
	
	/**
	 * get color from configuration
	 * @param aColorId color id
	 * @return color
	 * @throws SystemException 
	 */
	public Color	getColor
					( String	aColorId ) 
	{
		return getColor( configuration, prefix + aColorId );
	}
	
	/**
	 * get look and feel configuration value in integer
	 * @param anId configuration id
	 * @return look and feel configuration in integer
	 */
	public int		getLookAndFeelIntConfig
					( String	aConfigId,
					  int		aDefaultValue )
	{
		return configuration.getInt( aConfigId, aDefaultValue );
	}
	
	/**
	 * configure trader ui information
	 * @param aConfiguration configuration
	 * @exception SystemException thrown if there is a problem configuring trader ui information
	 */
	public void	configure
				( Configuration	aConfiguration )
	throws	SystemException
	{
		configuration = aConfiguration;
		iconLocation = aConfiguration.getString( prefix + "iconLocation" );
		frameIconLocation = aConfiguration.getString( prefix + "frameIconLocation");
		bannerImageLocation = aConfiguration.getString( prefix
				+ "banner.imageLocation" );
		bannerImageAlignment = aConfiguration.getInt( prefix 
				+ "banner.imageAlignment", SwingLabel.LEFT );
		bannerLeftImageLocation = aConfiguration.getString( prefix
				+ "banner.left.imageLocation" );
		bannerLeftImageAlignment = aConfiguration.getInt( prefix
				+ "banner.left.imageAlignment", SwingLabel.CENTER );
		bannerCentreImageLocation = aConfiguration.getString( prefix
				+ "banner.centre.imageLocation" );
		bannerCentreImageAlignment = aConfiguration.getInt( prefix
				+ "banner.centre.imageAlignment", SwingLabel.CENTER );
		bannerRightImageLocation = aConfiguration.getString( prefix
				+ "banner.right.imageLocation" );
		bannerRightImageAlignment = aConfiguration.getInt( prefix
				+ "banner.right.imageAlignment", SwingLabel.RIGHT );
		bannerBackgroundColor = getColor( aConfiguration, prefix
				+ "banner.backgroundColor" );
		bannerHeight = aConfiguration.getInt( prefix + "banner.height", 0 );
		headerForegroundColor = getColor( aConfiguration, prefix + "header.foreground" );
		headerBackgroundColor = getColor( aConfiguration, prefix + "header.background" );
		inactiveTabForegroundColor = getColor( aConfiguration, prefix
				+ "tab.foreground.inactive" );
		inactiveTabBackgroundColor = getColor( aConfiguration, prefix
				+ "tab.background.inactive" );
		activeTabForegroundColor = getColor( aConfiguration, prefix
				+ "tab.foreground.active" );
		activeTabBackgroundColor = getColor( aConfiguration, prefix
				+ "tab.background.active" );
		menuBackgroundColor = getColor( aConfiguration, prefix
				+ "menu.background" );
		menuForegroundColor = getColor( aConfiguration, prefix
				+ "menu.foreground" );		
		menuSeparator = aConfiguration.getBoolean(  prefix + "menu.separator", false );		
		
		rateIconBackgroundColor = getColor( aConfiguration, prefix
				+ "rate.icon.background" );
		rateIconForegroundColor = getColor( aConfiguration, prefix
				+ "rate.icon.foreground" );
		tableHeaderBackgroundColor = getColor( aConfiguration, prefix
				+ "table.header.background" );
		tableHeaderForegroundColor = getColor( aConfiguration, prefix
				+ "table.header.foreground" );
		themeBackgroundColor = getColor( aConfiguration, prefix
				+ "theme.background" );
		themeForegroundColor = getColor( aConfiguration, prefix
				+ "theme.foreground" );
		bidRateColor = getColor( aConfiguration, prefix + "rate.bid.color" );
		askRateColor = getColor( aConfiguration, prefix + "rate.ask.color" );
		disabledRateColor = getColor( aConfiguration, prefix
				+ "rate.disabled.color" );
		if ( bidRateColor == null )
		{
			bidRateColor = Color.RED;
		}
		if ( askRateColor == null )
		{
			askRateColor = Color.GREEN;
		}
		ratesBackgroundColor = getColor( aConfiguration, prefix
				+ "rates.background" );
		acceptedColor = getColor( aConfiguration, prefix + "accepted.color" );
		rejectedColor = getColor( aConfiguration, prefix + "rejected.color" );
		pendingColor = getColor( aConfiguration, prefix + "pending.color" );
		if ( acceptedColor == null )
		{
			acceptedColor = Color.BLUE;
		}
		if ( rejectedColor == null )
		{
			rejectedColor = Color.RED;
		}
		if ( pendingColor == null )
		{
			pendingColor = Color.GREEN;
		}
		
		standBackgroundColor = getColor( aConfiguration, prefix +  "alloy.standard");
		
		UIManager.put( "DockableFrame.activeTitleBackground", headerBackgroundColor );
		UIManager.put( "DockableFrame.activeTitleForeground", headerForegroundColor );
		UIManager.put( "DockableFrame.inactiveTitleBackground", headerBackgroundColor ); 
		UIManager.put( "DockableFrame.inactiveTitleForeground", headerForegroundColor );

		if ( aConfiguration.containsKey( prefix + "button.defaultIndicatorColor" ) )
		{
			UIManager.put( "Button.defaultIndicatorColor", getColor( aConfiguration, prefix + "button.defaultIndicatorColor" ) );
		}
		
		rateButtonColor = getColor( aConfiguration, prefix
				+ "rate.button.background" );
		rateRolloverColor = getColor( aConfiguration, prefix
				+ "rate.button.rollover" );
		
		postConfigure( aConfiguration );
	}
	
	/**
	 * post configure
	 * @param aConfiguration configuration
	 */
	protected abstract void	postConfigure
							( Configuration		aConfiguration )
	throws	SystemException;
	
	
	/**
	 * create icon
	 * @return icon
	 */
	public final ImageIcon	createFrameIcon()
	{
		if ( frameIconLocation == null )
		{
			return null;
		}
		return SwingHelper.GetImageFromResource( frameIconLocation, this );
	}
	
	/**
	 * create icon
	 * @return icon
	 */
	public final ImageIcon	createIcon()
	{
		return SwingHelper.GetImageFromResource( iconLocation, this );
	}
	
	public SwingLabel createLabelBanner()
	{
		return createLabelBanner( true );
	}
	
	/**
	 * create horizontal banner
	 * @return banner or null if there is no banner
	 */
	public SwingPanel createBanner()
//	public final SwingLabel	createBanner()
	{
		if ( bannerLeftImageLocation == null
				&& bannerCentreImageLocation == null
				&& bannerRightImageLocation == null )
		{
			return createBanner( true );
		}
		else
		{
			SwingPanel bannerPanel = new SwingPanel();
			bannerPanel.setLayout( new BorderLayout() );
			
			if ( menuSeparator )
			{
				JSeparator separator = new JSeparator();
				bannerPanel.add( separator, BorderLayout.NORTH  );
			}
			
			if ( bannerBackgroundColor != null )
			{
				bannerPanel.setOpaque( true );
				bannerPanel.setBackground( bannerBackgroundColor );
			}
			if ( bannerHeight != 0 )
			{
				bannerPanel.setMinimumSize( new Dimension( 100, bannerHeight ) );
			}
			SwingLabel leftBanner = createInnerBanner( bannerLeftImageLocation );
			if ( leftBanner != null )
			{
				leftBanner.setHorizontalAlignment( bannerLeftImageAlignment );
				bannerPanel.add( leftBanner, BorderLayout.WEST);
			}
			SwingLabel centreBanner = createInnerBanner( bannerCentreImageLocation );
			if ( centreBanner != null ) 
			{
				centreBanner.setHorizontalAlignment( bannerCentreImageAlignment );
				bannerPanel.add( centreBanner, BorderLayout.CENTER );
			}
			SwingLabel rightBanner = createInnerBanner( bannerRightImageLocation );
			if ( rightBanner != null ) 
			{
				rightBanner.setHorizontalAlignment( bannerRightImageAlignment );
				bannerPanel.add( rightBanner, BorderLayout.EAST );
			}
				
			return bannerPanel;
		}
	}
	
	public final SwingLabel createLabelBanner
							( boolean   isHorizontal )
	{
		SwingLabel label = new SwingLabel();
		if ( !isHorizontal )
		{
			label.setVerticalAlignment( SwingLabel.BOTTOM );
		}
		label.setIcon( SwingHelper.GetImageFromResource( bannerImageLocation, this ) );
		if ( bannerBackgroundColor != null )
		{
			label.setOpaque( true );
			label.setBackground( bannerBackgroundColor );
		}
		if ( bannerHeight != 0 )
		{
			if ( isHorizontal )
			{
				label.setMinimumSize( new Dimension( 100, bannerHeight ) );
			}
			else
			{
				label.setMinimumSize( new Dimension( bannerHeight, 100 ) );
			}
		}
		return label;
	}
	
	/**
	 * create banner
	 * @param isHorizontal indicates if the banner is horizontal
	 * @return banner or null if there is no banner
	 */
	public final SwingPanel	createBanner
							( boolean	isHorizontal )
	{
		SwingPanel bannerPanel = new SwingPanel();
		if ( bannerImageLocation != null )
		{
			SwingLabel label = createLabelBanner( isHorizontal );
			label.setHorizontalAlignment( bannerImageAlignment );
			bannerPanel.add(label);
			return bannerPanel;
		}		
		return null;
	}
	
	/**
	 * create tabbed pane
	 * @return tabbed pane
	 */
	public final SwingTabbedPane	createTabbedPane()
	{
		SwingTabbedPane tabbedPane = new SwingTabbedPane();
		tabbedPane.addChangeListener( this );
		tabbedPane.setFont( tabbedPane.getFont().deriveFont( Font.BOLD ) );
		return tabbedPane;
	}
	
	/**
	 * process tabbed pane
	 * @param aTabbedPane tabbed pane
	 */
	public final void	processTabbedPane
						( JTabbedPane	aTabbedPane )
	{
		processTabbedPane( aTabbedPane, true );
	}
	
	/**
	 * process tabbed pane
	 * @param aTabbedPane tabbed pane
	 */
	public final void	processTabbedPane
						( JTabbedPane	aTabbedPane,
						  boolean		isInitial )
	{
		JTabbedPane tabbedPane = aTabbedPane;
		for( int count=0; count<tabbedPane.getComponentCount(); count++ )
		{
			try 
			{
				tabbedPane.setForegroundAt( count, inactiveTabForegroundColor );
				tabbedPane.setBackgroundAt( count, inactiveTabBackgroundColor );
			}
			catch (Throwable t)
			{
				// ignore any error
			}
		}
		int selectedIndex = tabbedPane.getSelectedIndex();
		if ( selectedIndex != -1 )
		{
			if ( activeTabBackgroundColor != null )
			{
				try
				{
					tabbedPane.setBackgroundAt( selectedIndex, activeTabBackgroundColor );
				}
				catch ( Throwable t )
				{
				}
			}
			if ( activeTabForegroundColor != null )
			{	
				try
				{
					tabbedPane.setForegroundAt( selectedIndex, activeTabForegroundColor );
				}
				catch (Throwable t)
				{
				}
			}	
		}
		if ( isInitial )
		{
			boolean hasRegisteredAsListener = false;
			for( ComponentListener listener: tabbedPane.getComponentListeners() )
			{
				if ( listener == this )
				{
					hasRegisteredAsListener = true;
					return;
				}
			}
			if ( !hasRegisteredAsListener )
			{
				tabbedPane.addComponentListener( this );
			}
		}
	}
	
	
	public final void	stateChanged
						( ChangeEvent	anEvent )
	{
		JTabbedPane tabbedPane = (JTabbedPane)anEvent.getSource();
		processTabbedPane( tabbedPane, false );
	}
	
	/**
	 * process menu, set the background color and foreground color for the menu
	 * @param aMenuBar menu bar
	 */
	public final void	processMenu
						( JMenuBar	aMenuBar )
	{
		SwingHelper.ChangeColor( aMenuBar, 
								 menuBackgroundColor, 
								 menuForegroundColor );
	}
	
	/**
	 * process menu
	 * @param aMenu menu
	 */
	public final void	processMenu
						( JMenuItem		aMenuItem )
	{
		SwingHelper.ChangeColor( aMenuItem, 
								 menuBackgroundColor, 
								 menuForegroundColor );
	}
	
	/**
	 * process rate icon panel
	 * @param aPanel panel
	 * @param aLabel label
	 */
	public void	processRateIconPanel
				( JPanel	aPanel,
				  JLabel	aLabel )
	{
		if ( rateIconBackgroundColor != null )
		{
			processRateIconPanel( aPanel, aLabel, rateIconBackgroundColor );
		}
		else
		{
			processRateIconPanel( aPanel, aLabel, new Color( 211,211,211 ) );
		}
	}
	
	/**
	 * process rate icon panel
	 * @param aPanel panel
	 * @param aLabel label
	 */
	public void	processRateIconPanel
				( JPanel	aPanel,
				  JLabel	aLabel,
				  Color		aBackground )
	{
		aPanel.setBackground( aBackground );
		if ( rateIconForegroundColor != null )
		{
			aLabel.setForeground( rateIconForegroundColor );
		}
	}
	
	/**
	 * process combo box
	 * @param aComboBox combo box
	 */
	public void	processComboBox
				( JComboBox	aComboBox )
	{
		if ( rateIconBackgroundColor != null )
		{
			aComboBox.setBackground( rateIconBackgroundColor );
		}
		else
		{
			aComboBox.setBackground( new Color( 211,211,211 ) );
		}
		if ( rateIconForegroundColor != null )
		{
			aComboBox.setForeground( rateIconForegroundColor );
		}
		aComboBox.setFont( aComboBox.getFont().deriveFont( Font.BOLD ) );
	}
	
	/**
	 * @param aCheckBox
	 */
	public void processCheckBox
				( SwingCheckBox aCheckBox )
	{
		aCheckBox.setBorderPainted( false );
		aCheckBox.setFocusPainted( false );
		
		if ( rateIconBackgroundColor != null )
		{
			aCheckBox.setBackground( rateIconBackgroundColor );
		}
		else
		{
			aCheckBox.setBackground( new Color( 211,211,211 ) );
		}
		if ( rateIconForegroundColor != null )
		{
			aCheckBox.setForeground( rateIconForegroundColor );
		}
		aCheckBox.setFont( aCheckBox.getFont().deriveFont( Font.BOLD ) );
		
	}
	/**
	 * process label
	 * @param aLabel label
	 */
	public final void	processLabel
						( SwingLabel	aLabel )
	{
		aLabel.setOpaque( true );
		if ( themeBackgroundColor != null )
		{
			aLabel.setBackground( themeBackgroundColor );
		}
		if ( themeForegroundColor != null )
		{
			aLabel.setForeground( themeForegroundColor );
		}
	}
	
	/**
	 * process table, set table header's background color and foreground color.
	 * @param aTable table
	 */
	public final void	processTable
						( JTable	aTable )
	{
		if ( tableHeaderBackgroundColor != null )
		{
			aTable.getTableHeader().setBackground( tableHeaderBackgroundColor );
		}
		if ( tableHeaderForegroundColor != null )
		{
			aTable.getTableHeader().setForeground( tableHeaderForegroundColor );
		}

	}
	
	/**
	 * get rate button color
	 * @return rate button color
	 */
	public Color	getRateButtonColor()
	{
		return rateButtonColor;
	}
	
	/**
	 * set background color
	 * @param aButton button
	 * @param aBackground background color
	 */
	public abstract void	setBackgroundColor
							( JButton	aButton,
							  Color		aBackground );
	
	public Color getActiveTabBackgroundColor()
	{
		return activeTabBackgroundColor;
	}
	
	public Color getRateRolloverColor()
	{
		return rateRolloverColor;
	}


	/**
	 * process rate button
	 * @param aButton button
	 */
	public abstract void	processRateButton
							( JButton	aButton );
	
	/**
	 * set button color to be the theme background 
	 * @param aButton button
	 */
	public final void	setThemeBackground
						( JButton	aButton )
	{
		setBackground( aButton, themeBackgroundColor );
	}
	
	/**
	 * set button color to be the theme foreground 
	 * @param aButton button
	 */
	public final void	setThemeForeground
						( JButton	aButton )
	{
		setBackground( aButton, themeForegroundColor );
	}
	
	/**
	 * set button background
	 * @param aButton button
	 * @param aColor background color
	 */
	public abstract void	setBackground
							( JButton	aButton,
							  Color		aBackground );

	/**
	 * create inner banner from the image location specified
	 * @param anImageLocation location of the image
	 */
	private SwingLabel createInnerBanner
						( String anImageLocation )
	{
		if ( anImageLocation != null )
		{
			SwingLabel innerBanner = new SwingLabel();
			
			innerBanner.setIcon( SwingHelper.GetImageFromResource( anImageLocation, this ) );
			
			if ( bannerBackgroundColor != null )
			{
				innerBanner.setOpaque( true );
				innerBanner.setBackground( bannerBackgroundColor );
			}
			
			if ( bannerHeight != 0 )
			{
				innerBanner.setMinimumSize( new Dimension( 100, bannerHeight ) );
			}

			return innerBanner;
		}
		
		return null;
	}

	public Color getActiveTabForegroundColor()
	{
		return activeTabForegroundColor;
	}

	public Color getStandardBackgroundColor() {
		return standBackgroundColor;
	}

	public void componentResized
				( ComponentEvent	anEvent )
	{
		handleProcessTabbedPaneEvent( anEvent );
	}
	
	public void componentMoved
				( ComponentEvent	anEvent )
	{
		handleProcessTabbedPaneEvent( anEvent );
	}
	  
	public void componentShown
				( ComponentEvent	anEvent )
	{
		handleProcessTabbedPaneEvent( anEvent );
	}

	/**
	 * handle process tabbed pane event
	 * @param anEvent event
	 */
	private void	handleProcessTabbedPaneEvent
					( ComponentEvent	anEvent )
	{
		if ( anEvent.getSource() instanceof JTabbedPane )
		{
			processTabbedPane( (JTabbedPane)anEvent.getSource(), false );
		}	
	}
	
	public void componentHidden
				( ComponentEvent	anEvent )
	{
	}
	
	/**
	 * getter for tableHeaderBackgroundColor
	 * @return tableHeaderBackgroundColor
	 */	
	public Color getTableHeaderBackgroundColor() 
	{
		return tableHeaderBackgroundColor;
	}

	/**
	 * getter for tableHeaderForegroundColor
	 * @return tableHeaderForegroundColor
	 */
	public Color getTableHeaderForegroundColor() 
	{
		return tableHeaderForegroundColor;
	}
	
	/**
	 * set the bold fond for the ProgressBar
	 * @param aProgressBar
	 */
	public void processProgressBar
				( JProgressBar aProgressBar )
	{
		aProgressBar.setFont( aProgressBar.getFont().deriveFont( Font.BOLD ) );
		if ( themeForegroundColor != null )
		{
			aProgressBar.setForeground( themeForegroundColor );
		}
	}
	
	/**
	 * create basic arrow button. Subclass can override to provide proper implementation of basic arrow button
	 * @param aDirection direction of the arrow
	 * @return basic arrow button
	 */
	public BasicArrowButton	createBasicArrowButton
							( int	aDirection )
	{
		return new BasicArrowButton( aDirection );
	}

	public Color getHeaderForegroundColor() {
		return headerForegroundColor;
	}

	public Color getHeaderBackgroundColor() {
		return headerBackgroundColor;
	}
	
	
}