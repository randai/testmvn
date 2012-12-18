package com.flx.common.ui;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.TimeZone;

import org.joda.time.DateTimeZone;

/**
 * UIHelper contains helper method to manage user interface
 */
public class UIHelper
{
	public static final Insets		ZERO_INSETS
									= new Insets( 0,0,0,0 );
	public static final Cursor		HAND_CURSOR
									= new Cursor( Cursor.HAND_CURSOR );
	public static final Cursor		EAST_RESIZE_CURSOR
									= Cursor.getPredefinedCursor( Cursor.E_RESIZE_CURSOR );
	private static TimeZone 		timeZone
									= TimeZone.getDefault();
	
    /**
     * position component at the center of the screen
     * @param aComponent component to be positioned
     */
    public final static void    PositionComponentAtCenter
                                ( Component aComponent )
    {
        Toolkit toolkit = aComponent.getToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        Dimension componentSize = aComponent.getSize();
        aComponent.setLocation(
            ( screenSize.width - componentSize.width ) / 2,
            ( screenSize.height - componentSize.height ) / 2 );
    }
    
    /**
     * position component at the center of the component
     * @param aReference the given component will be positioned against
     * @param aComponent component to be positioned
     */
    public final static void    PositionComponentAtCenter
                                ( Component		aReference,
                                  Component 	aComponent )
    {
    	if ( aReference == null )
    	{
    		PositionComponentAtCenter( aComponent );
    		return;
    	}
        Point location = null;
        location = aReference.getLocation();
        Dimension referenceSize = aReference.getSize();
        Dimension componentSize = aComponent.getSize();
        location.x = location.x + ( referenceSize.width - componentSize.width ) / 2;
        location.y = location.y + ( referenceSize.height - componentSize.height ) / 2;
        aComponent.setLocation( location );
    }
    
    /**
     * position component at the center of the component
     * @param aReference the given component will be positioned against
     * @param aComponent component to be positioned
     */
    public final static void    PositionComponentRelativeToInvoker
                                ( Component		aReference,
                                  Component 	aComponent )
    {
        Toolkit toolkit = aComponent.getToolkit();
        Point location = aReference.getLocation();
        Dimension screenSize = toolkit.getScreenSize();
        Dimension componentSize = aComponent.getSize();
        location.x = location.x + ( screenSize.width - componentSize.width ) / 2;
        location.y = location.y + ( screenSize.height - componentSize.height ) / 2;
        aComponent.setLocation( location );
        
    }
    
    
    /**
     * get root parent
     * @param aComponent root parent of this component will be returned
     * @return root parent
     */
    public final static Container	GetRootParent
    								( Component	aComponent )
    {
    	Component component = aComponent;
    	while( true )
    	{
    		Container parent = component.getParent();
       		if ( parent == null 
    			 ||
    			 parent == aComponent )
    		{
    			return (Container)component;
    		}
    		if ( parent instanceof Dialog )
    		{
    			return (Container)parent;
    		}
    		component = parent;
    	}
    }
    
	/**
	 * apply font
	 * @param aContainer container
	 * @param aFont font
	 */
	public final static void	ApplyFont
								( Container		aContainer,
								  Font		 	aFont )
	{
		Component[] components = aContainer.getComponents();
		for( int count=0; count<components.length; count++ )
		{
			components[count].setFont( aFont );
			if ( components[count] instanceof Container )
			{
				ApplyFont( (Container)components[count], aFont );
			}
		}
	}
	
	/**
	 * Set the default timezone for displaying dates in a localized timezone.
	 * @param aTimeZone
	 */
	public static final void SetTimeZone ( TimeZone aTimeZone )
	{
		if ( aTimeZone != null )
		{
			timeZone = aTimeZone;
		}
	}
	
	/**
	 * get the default timezone for display purposes.  (used to convert from GMT)
	 * @return
	 */
	public static final TimeZone GetTimeZone()
	{
		return timeZone;
	}
	
	/**
	 * Obtain the display string for the specified timezone
	 * formatted in GMT
	 * @param aTimeZone
	 * @return formatted display string
	 */
	public static final String GetTimeZoneDisplayString( TimeZone aTimeZone )
	{
		int rawOffset = aTimeZone.getRawOffset() / 60000;
        int hours = rawOffset / 60;
        int minutes = Math.abs(rawOffset) % 60;
        String hrStr = "";

        if (Math.abs(hours) < 10) {
            if (hours < 0) {
                hrStr = "-0" + Math.abs(hours);
            } else {
                hrStr = "0" + Math.abs(hours);
            }
        } else {
            hrStr = Integer.toString(hours);
        }

        String minStr = (minutes < 10) ? ("0" + Integer.toString(minutes)) : Integer.toString(minutes);
        String str = "(GMT " + ((aTimeZone.getRawOffset() >= 0) ? "+" : "") + hrStr + ":" + minStr + ") " + aTimeZone.getID();
		return str;
	}
	
	/**
	 * check if we can check if the mouse event is a popup trigger
	 * @param anEvent event
	 * @return true if popup trigger 
	 */
	public static final boolean	CanCheckPopupTrigger
								( MouseEvent	anEvent )
	{
		String system = System.getProperty( "os.name" );
		if ( system.startsWith( "Windows" ) )
		{
			return anEvent.getID() == MouseEvent.MOUSE_RELEASED;
		}
		else if ( system.startsWith( "Mac OS" ) )
		{
			return anEvent.getID() == MouseEvent.MOUSE_PRESSED;
		}
		return anEvent.isPopupTrigger();
	}

	public static String GetDateTimeZoneDisplayString(DateTimeZone aTimeZone)
	{
		int rawOffset = aTimeZone.getStandardOffset(0) / 60000;
        int hours = rawOffset / 60;
        int minutes = Math.abs(rawOffset) % 60;
        String hrStr = "";

        if (Math.abs(hours) < 10) {
            if (hours < 0) {
                hrStr = "-0" + Math.abs(hours);
            } else {
                hrStr = "0" + Math.abs(hours);
            }
        } else {
            hrStr = Integer.toString(hours);
        }

        String minStr = (minutes < 10) ? ("0" + Integer.toString(minutes)) : Integer.toString(minutes);
        String str = "(GMT " + ((aTimeZone.getStandardOffset(0) >= 0) ? "+" : "") + hrStr + ":" + minStr + ") " + aTimeZone.getID();
		return str;
	}
	
    /**
     * get first parent. This method will return the first parent found traversing up the tree
     * @param aComponent first parent of this component will be returned
     * @return first parent found traversing up the tree
     */
    public final static Container	GetFirstParent
    								( Component	aComponent )
    {
    	Component component = aComponent;
    	while( true )
    	{
    		Container parent = component.getParent();
       		if ( parent == null 
    			 ||
    			 parent == aComponent )
    		{
    			return (Container)component;
    		}
    		if ( parent instanceof Window )
    		{
    			return (Container)parent;
    		}
    		component = parent;
    	}
    }

    /**
     * get location on screen. This method will take care of multi-screen
     * @param aComponent GUI component
     * @return location on screen
     */
    public final static Point	GetLocationOnScreen
    							( Component	aComponent )
    {
    	Point point = aComponent.getLocationOnScreen();
    	Rectangle bounds = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration().getBounds();
    	point.x = point.x + bounds.x;
    	point.y = point.y + bounds.y;
    	return point;
    }
    
    /**
     * check if the incoming mouse event is a single click or 
     * double click based on click count. This is meant to work around the fact that rapid
     * clicking on a button will keep the click count to continuously increase. And a slow
     * hold and release will yield a 0 count
     * @param anEvent mouse event
     * @return 1 if it is a single click or 2 if it is a double click
     */
    public final static int	GetClickCount
    						( MouseEvent	anEvent )
    {
    	int clickCount = anEvent.getClickCount();
    	if ( clickCount == 0 // if hold and release then click count is zero
    	     || 
    	     clickCount == 1 )
    	{
    		return 1;
    	}
    	else
    	{
    		clickCount = clickCount % 2;
    		if ( clickCount == 1 )
    		{
    			return 1;
    		}
    		else 
    		{
    			return 2;
    		}
    	}
    }
}