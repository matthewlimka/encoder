package com.matthewlim.EncoderApp;

public class App {
	
    public static void main(String[] args) {
    	Encoder encoder = Encoder.getInstance();
    	encoder.setOffset('Z'); // Exception will be thrown if character is not A-Z, 0-9 or special characters ()*+,-./
    	
    	String plainText = "HELLO WORLD";
    	String encodedText = encoder.encode(plainText);
    	String decodedText = encoder.decode(encodedText);
    }
}
