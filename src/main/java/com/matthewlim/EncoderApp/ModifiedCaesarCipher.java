package com.matthewlim.EncoderApp;

import java.util.Arrays;

public class ModifiedCaesarCipher implements Cipher {

	private char offsetChar;
	private int shift;
	private final char[] referenceChars;
	
	public ModifiedCaesarCipher(char offsetChar, char[] referenceChars) {
		this.referenceChars = referenceChars;
		setOffsetChar(offsetChar);
	}
	
	public void setOffsetChar(char offsetChar) throws IllegalArgumentException {
		if (!isValidChar(offsetChar)) {
			throw new IllegalArgumentException("Invalid offset character : " + offsetChar +
					". Accepted offset characters are " + Arrays.toString(referenceChars));
		}
		
		this.offsetChar = offsetChar;
		this.shift = getMatchingIndex(offsetChar);
	}
	
	public String encode(String plainText) {
		char[] chars = plainText.toCharArray();
		
		// Encode each character in chars
		for (int i = 0; i < chars.length; i++) {
			if (isValidChar(chars[i])) {
				// Get index of matching character in referenceChars
				int index = getMatchingIndex(chars[i]);
				
				// Get index of encoded character in referenceChars
				int adjustedIndex = (index - this.shift + referenceChars.length) % referenceChars.length;
				chars[i] = referenceChars[adjustedIndex];
			}
		}
		
		// Convert encoded characters back into a string
		String encodedText = this.offsetChar + String.valueOf(chars);
		return encodedText;
	}
	
	public String decode(String encodedText) {
		char[] chars = encodedText.substring(1).toCharArray();
		int shift = getMatchingIndex(encodedText.charAt(0));
		
		// Decode each character in chars
		for (int i = 0; i < chars.length; i++) {
			if (isValidChar(chars[i])) {
				// Get index of matching character in referenceChars
				int index = getMatchingIndex(chars[i]);
				
				// Get index of decoded character in referenceChars
				int adjustedIndex = (index + shift) % referenceChars.length;
				chars[i] = referenceChars[adjustedIndex];
			}
		}
		
		// Convert decoded characters back into a string
		return String.valueOf(chars);
	}
	
	private int getMatchingIndex(char ch) {
		for (int i = 0; i < referenceChars.length; i++) {
			if (referenceChars[i] == ch) { 
				return i;
			}
		}
		
		return -1;
	}
	
	private boolean isValidChar(char ch) {
		for (int i = 0; i < referenceChars.length; i++) {
			if (referenceChars[i] == ch) {
				return true;
			}
		}
		
		return false;
	}
}