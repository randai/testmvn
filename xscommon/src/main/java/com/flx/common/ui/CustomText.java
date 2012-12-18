package com.flx.common.ui;

import java.awt.Color;
import java.awt.Font;

/**
 * custom text contains information on the text, the corresponding font, and the color
 */
public class CustomText
{
	private String			text;
	private Font			font;
	private Color			color;
	
	/**
	 * custom text
	 * @param aText text
	 * @param aFont font
	 * @param aColor color
	 */
	public	CustomText
			( String	aText,
			  Font		aFont,
			  Color		aColor )
	{
		text = aText;
		font = aFont;
		color = aColor;
	}
	
	/*
	 * get text
	 * @return text
	 */
	public String	getText()
	{
		return text;
	}
	
	/**
	 * get font
	 * @return font
	 */
	public Font	getFont()
	{
		return font;
	}
	
	/**
	 * get color
	 * @return color
	 */
	public Color	getColor()
	{
		return color;
	}
	
}
