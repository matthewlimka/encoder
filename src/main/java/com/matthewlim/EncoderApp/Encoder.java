package com.matthewlim.EncoderApp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Encoder {

	private final Logger logger = LogManager.getLogger(Encoder.class);
	private Cipher cipherMethod;
	
	public Encoder(Cipher cipherMethod) {
		this.cipherMethod = cipherMethod;
	}

	public void setCipherMethod(Cipher cipherMethod) {
		this.cipherMethod = cipherMethod;
	}
	
	public String encode(String plainText) {
		logger.info("Plain text to encode: " + plainText);
		String encodedText = cipherMethod.encode(plainText);
		logger.info("Encoded text: " + encodedText + "\n");
		return encodedText;
	}

	public String decode(String encodedText) {
		logger.info("Encoded text to decode: " + encodedText);
		String plainText = cipherMethod.decode(encodedText);
		logger.info("Plain text: " + plainText + "\n");
		return plainText;
	}
}