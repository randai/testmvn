package com.flx.common.util;

/**
 * HexTools is a collection of methods for manipulating binary data to and 
 * from a printable hexidecimal format.
 **/
public class HexTools {
    /**
     * Returns a two character string with the hexidecimal representation of a 
     * byte.
     */
    public static String hexFromByte (byte buf) {
        int hi, lo;
        char hic, loc;

	// logical ands to isolate bits then divide/modulus to get high and low nibble
	hi = ((((int) buf) & 0x000000ff)) / 16;
	lo = ((((int) buf) & 0x000000ff)) % 16;	
	// determine if numeric or alphabetic and adjust text representation of nibble
	hic = (char) ((hi > 9) ? (hi - 10 + (int) 'A') : (hi + (int) '0'));
	loc = (char) ((lo > 9) ? (lo - 10 + (int) 'A') : (lo + (int) '0'));

	String result = hic + "" + loc;

	return (result);
    }


    /**
     * Returns a byte with the binary equivalent of the two chars passed to it
     */
    public static byte  byteFromHex (char hic, char loc) {
	//Trust no-one to give us uppercase alphas
    	byte hib = (byte) ((hic >= 'a' && hic <= 'f') ? (hic - 'a' + 'A') : (hic));
    	byte lob = (byte) ((loc >= 'a' && loc <= 'f') ? (loc - 'a' + 'A') : (loc));

	// check if alpha or numeric and adjust accordingly, left high order nibble
    	byte hin = (byte) (((hib >= 'A' && hib <= 'F') ? (hib - 'A' + 10) : (hib - '0')) << 4);
    	byte lon = (byte) ((lob >= 'A' && lob <= 'F') ? (lob - 'A' + 10) : (lob - '0'));
	
    	return ((byte) (hin | lon));
    }



    /**
     * Returns a byte array containing the binary equivalent of the string of
     * hexidecimal characters passed to it.
     */
    public static byte[] deHexify (String buf) {
    	byte result[] = new byte [(buf.length () / 2)];
	
		for ( int i = 0; i < ( buf.length() / 2 ); i++ )
			result[i] = byteFromHex( buf.charAt( i * 2 ), buf
					.charAt( ( i * 2 ) + 1 ) );
		return result;
    }


    /**
     * Returns a string containing the hexidecimal representation of an
     * array of bytes.
     */
    public static String hexify (byte[] buf) {
        String s = new String();

        for (int i = 0; i < buf.length; i++)
            s += hexFromByte(buf[i]);

        return s;
    }


    /**
     * Takes a string and replaces all instances of the specified character
     * with the characters hexidecimal value in the form %XX and returns the
     * result.
     */
    public static String hexifyChars (String buffer, char hexor) {
    	StringBuffer result = new StringBuffer (buffer.length () * 2);
	String hexChar = hexFromByte ((byte) hexor);

	for (int i=0; i < buffer.length (); i++) {
	    if (buffer.charAt(i) != hexor)
	    	result.append (buffer.charAt(i));
	    else
	    	result.append ("%" + hexChar);
	}

	return result.toString ();
    }


    /**
     * Takes a string and a character and replaces all instances of the characters
     * hexidecimal value (in the form %XX) with the character and returns the 
     * result.
     */
    public static String deHexifyChars (String buffer, char hexor) {
    	StringBuffer result = new StringBuffer (buffer.length () * 2);
	String hexorString = "%" + hexFromByte ((byte) hexor);

	int currpos  = 0;
	int hexStart = 0;
	boolean done = false;

	while (done != true) {
	    hexStart = buffer.indexOf (hexorString, currpos);
	    if (hexStart >= 0) {
		result.append (buffer.substring (currpos, hexStart) + hexor);
		currpos = hexStart + 3;
	    } else {
		result.append (buffer.substring (currpos, buffer.length ()));
		done = true;
	    }
	}

	return result.toString ();
    }

    /**
     * Tests HexTools
     */
    public static void main (String[] args) {

	if (args.length == 2) {
	    String hexor = args[0];
	    String term  = args[1];

	    System.out.println ("The hexor is \'" + hexor + "\' and the term is:\n \"" + term + "\"\n\n");
	    String hexedTerm = hexifyChars (term, hexor.charAt(0));
	    System.out.println ("hexifyChars   - \"" + hexedTerm + "\"");
            
	    String deHexedTerm = deHexifyChars (term, hexor.charAt(0));
	    System.out.println ("deHexifyChars - \"" + deHexedTerm + "\"");
            
	    byte testArray[] = new byte[16];
	    for (int i=0; i <= 15; i++)
		testArray[i] = (byte) ((i*3) + 1);
            
	    for (int i=0; i <= 15; i++)
		System.out.println ("testArray[" + i + "] = " + testArray[i] + " = %" + hexFromByte(testArray[i]));

	    String hexified = hexify (testArray);
	    System.out.println ("hexified - \"" + hexified + "\"");

	    byte deHexified[] = deHexify (hexified);

	    for (int i=0; i <= 15; i++)
		System.out.println ("deHexified[" + i + "] = " + deHexified[i] + " = %" + hexFromByte (deHexified[i]));


	} else 
	     System.out.println ("HexTools \"String to convert\" \' \' character to convert");

    }


//Begin code purgatory
/*    public static byte[] deHexify (String buf) {
	//Trust no-one to give us uppercase alphas
	byte workBuf[]  = buf.toUpperCase ().getBytes (); 
    	byte result[] = new byte [(buf.length () / 2)];
	byte hin, lon, hic, loc;
	int  index = 0;
	
	for (int i=0; i < workBuf.length; i += 2) {
	    hic = workBuf[i];
	    hin = (byte) (((hic >= 'A' && hic <= 'F') ? (hic - 'A' + 10) : (hic - '0')) << 4);

	    loc = workBuf[i+1];
	    lon = (byte) ((loc >= 'A' && loc <= 'F') ? (loc - 'A' + 10) : (loc - '0'));

	    result[index] = (byte) (hin | lon);
	    index++;
	}

	return result;
    }
*/
// End code purgatory

}
