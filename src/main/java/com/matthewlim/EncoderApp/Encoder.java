package com.matthewlim.EncoderApp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Encoder {

	private static Encoder instance = null;
	private final Logger logger = LogManager.getLogger(Encoder.class);
	
	private char offset;
	private final char[] referenceChars = {
		    'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 
		    'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 
		    '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '(', ')', '*', 
		    '+', ',', '-', '.', '/'
		};

	private Encoder() {}

	public static Encoder getInstance() {
		if (Encoder.instance == null) {
			Encoder.instance = new Encoder();
		}
		return Encoder.instance;
	}

	public void setOffset(char offsetChar) throws IllegalArgumentException {
		if (!isValidOffsetChar(offsetChar)) {
			throw new IllegalArgumentException("Invalid offset character : " + offsetChar +
					". Accepted offset characters are A-Z, 0-9, and special characters: ()*+,-./");
		}
		this.offset = offsetChar;
	}

	public String encode(String plainText) {
		logger.info("Plain text to encode: " + plainText);
		char[] textChars = plainText.toCharArray();
		int offsetValue = getOffsetValue(this.offset);
		
		logger.info("Offset char: " + this.offset + ", offset value: " + offsetValue);

		// Encode each character in textChars
		for (int i = 0; i < textChars.length; i++) {
			// Skip encoding if current character is ' '
			if (textChars[i] != ' ') {
				// Get index of matching character in referenceChars
				int index = getMatchingIndex(textChars[i]);
				
				// Get index of encoded character in referenceChars
				int adjustedIndex = (index - offsetValue + 44) % 44;
				textChars[i] = referenceChars[adjustedIndex];
			}
			logger.info(plainText.charAt(i) + " -> " + textChars[i]);
		}

		// Convert encoded characters back into a string
		String encodedText = this.offset + String.valueOf(textChars);
		logger.info("Encoded text: " + encodedText + "\n");
		return encodedText;
	}

	public String decode(String encodedText) {
		logger.info("Encoded text to decode: " + encodedText);
		char[] textChars = encodedText.substring(1).toCharArray();
		int offsetValue = getOffsetValue(encodedText.charAt(0));
		
		logger.info("Offset char: " + encodedText.charAt(0) + ", offset value: " + offsetValue);

		// Decode each character in textChars
		for (int i = 0; i < textChars.length; i++) {
			// Skip decoding if current character is ' '
			if (textChars[i] != ' ') {
				// Get index of matching character in referenceChars
				int index = getMatchingIndex(textChars[i]);
				
				// Get index of encoded character in referenceChars
				int adjustedIndex = (index + offsetValue) % 44;
				textChars[i] = referenceChars[adjustedIndex];
			}
			logger.info(encodedText.charAt(i+1) + " -> " + textChars[i]);
		}

		// Convert decoded characters back into a string
		String plainText = String.valueOf(textChars);
		logger.info("Plain text: " + plainText + "\n");
		return plainText;
	}
	
	private int getOffsetValue(char offsetChar) {
		int offsetValue = 0;
		
		// Determine offset value based on the category of offsetChar
		if (isCapitalLetter(offsetChar)) {
			offsetValue = offsetChar - 'A'; // Range 0 - 25 for A-Z
		} else if (isDigit(offsetChar)) {
			offsetValue = offsetChar - '0' + 26; // Range 26 - 35 for 0-9
		} else if (isSpecialChar(offsetChar)) {
			offsetValue = offsetChar - '(' + 36; // Range 36 - 43 for special chars
		}
		
		return offsetValue;
	}
	
	private int getMatchingIndex(char ch) {
		int index = 0;
		
		// Determine matching index based on the category of ch 
		if (isCapitalLetter(ch)) {
			index = (int) ch - 'A'; // Index 0 - 25 for A-Z
		} else if (isDigit(ch)) {
			index = (int) ch - '0' + 26; // Index 26 - 35 for 0-9
		} else if (isSpecialChar(ch)) {
			index = (int) ch - '(' + 36; // Index 36 - 43 for special chars
		}
		return index;
	}
	
    private boolean isCapitalLetter(char ch) {
        return ch >= 65 && ch <= 90;
    }

    private boolean isDigit(char ch) {
        return ch >= 48 && ch <= 57;
    }

    private boolean isSpecialChar(char ch) {
        return ch >= 40 && ch <= 47;
    }
    
    private boolean isValidOffsetChar(char ch) {
        return isCapitalLetter(ch) || isDigit(ch) || isSpecialChar(ch);
    }
}