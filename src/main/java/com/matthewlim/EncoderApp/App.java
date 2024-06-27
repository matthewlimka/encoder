package com.matthewlim.EncoderApp;

public class App {
	
    public static void main(String[] args) {
    	char[] referenceChars = {
    		    'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 
    		    'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 
    		    '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '(', ')', '*', 
    		    '+', ',', '-', '.', '/'
    		};
    	
    	char offsetChar = 'Z';
    	Cipher cipher = new ModifiedCaesarCipher(offsetChar, referenceChars);
    	Encoder encoder = new Encoder(cipher);
    	
    	String plainText = "HELLO WORLD";
    	String encodedText = encoder.encode(plainText);
    	String decodedText = encoder.decode(encodedText);
    }
}