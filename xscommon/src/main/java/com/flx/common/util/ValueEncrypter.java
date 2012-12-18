package com.flx.common.util;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

public class ValueEncrypter {

    private static String algorithm = "DESede";
    private static Key key = null;
    private static Cipher cipher = null;
    private static final String KEY_HEX = "07E543EFDFFD80F25B19DCAB1CE91FE346C7A4076D85102A";
	private static final String ENCRYPTED_VALUE_PREFIX = "FLX_EV:";

    private static void setup() throws Exception {
        key = getKey();
        cipher = Cipher.getInstance(algorithm, "SunJCE");
    }
    
    private static SecretKey getKey() throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchProviderException{
//    	 Convert the raw bytes to a secret key like this
        DESedeKeySpec keyspec = new DESedeKeySpec(KEY_HEX.getBytes());
        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("DESede", "SunJCE");
        SecretKey key = keyfactory.generateSecret(keyspec);
        return key;
    }
    
    /**
     * 
     * @param aString value to test for encryption
     * @return whether value is encrypted or not
     */
    public static boolean isEncryptedString( String aString ){
    	return aString.indexOf( ValueEncrypter.ENCRYPTED_VALUE_PREFIX ) == 0;
    }
    
    public static void main(String[] args){
    	if (args.length != 1){
    		System.out.println( "Usage: <value-to-encrypt>" );
    	}
    	else{
    		String clearValue = args[0];
    		try
			{
				String encryptedValue = encrypt( clearValue );
				String displayEncryptedValue = ENCRYPTED_VALUE_PREFIX + encryptedValue;
				System.out.println( "Encrypted value : " + displayEncryptedValue );
				String decryptedValue = decrypt(displayEncryptedValue);
				System.out.println( "Decrypted value : " +  decryptedValue );
			}
			catch ( Exception e )
			{
				e.printStackTrace();
			}
    	}
    }
    
    /**
     * 
     * @param input value to encrypt
     * @return hex encoded string representation of encrypted value
     * @throws Exception
     */
    public static String encrypt(String input)
        throws Exception {
    	if (key == null) setup();
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] inputBytes = input.getBytes();
        return HexTools.hexify( cipher.doFinal( inputBytes ) );
    }

    /**
     * 
     * @param aValue a hex encoded string representation of an encoded value
     * @return the decrypted value
     * @throws Exception
     */
    public static String decrypt( String aValue ) throws Exception{
    	if (aValue == null || aValue.length() == 0) return aValue;
    	String trimmedValue = aValue.substring( ENCRYPTED_VALUE_PREFIX.length() );
    	if (trimmedValue.length() == 0) return trimmedValue;
    	byte[] rawValue = HexTools.deHexify( trimmedValue );
		return decrypt( rawValue );
    }
    
    /**
     * 
     * @param encryptionBytes the binary representation of an encoded value
     * @return the decrypted value
     * @throws Exception
     */
    public static String decrypt(byte[] encryptionBytes)
        throws Exception {
    	if (key == null) setup();
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] recoveredBytes = 
          cipher.doFinal(encryptionBytes);
        String recovered = 
          new String(recoveredBytes);
        return recovered;
    }
}
