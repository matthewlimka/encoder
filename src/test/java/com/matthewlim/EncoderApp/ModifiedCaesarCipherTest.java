package com.matthewlim.EncoderApp;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

public class ModifiedCaesarCipherTest {
	
	private Cipher cipher;
	private char[] referenceChars = {
		    'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 
		    'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 
		    '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '(', ')', '*', 
		    '+', ',', '-', '.', '/'
		};
	
	@ParameterizedTest
	@MethodSource("encodeTestData")
	void testEncode(char offsetChar, String expected) {
		cipher = new ModifiedCaesarCipher(offsetChar, referenceChars);
		String plainText = "HELLO WORLD";

		String encodedText = cipher.encode(plainText);

		assertEquals(expected, encodedText);
	}
	
	@ParameterizedTest
	@ValueSource(strings = {"BGDKKN VNQKC", "FC/GGJ RJMG.", "Z0X447 -7(4W", "5URYY1 914YQ", "*NKRRU 2UXRJ", "/IFMMP XPSME"})
	void testDecode(String encodedText) {
		cipher = new ModifiedCaesarCipher('A', referenceChars);
		String expected = "HELLO WORLD";

		String decodedText = cipher.decode(encodedText);

		assertEquals(expected, decodedText);
	}
	
	private static Stream<Arguments> encodeTestData() {
		return Stream.of(
				Arguments.of('B', "BGDKKN VNQKC"),
				Arguments.of('F', "FC/GGJ RJMG."),
				Arguments.of('Z', "Z0X447 -7(4W"),
				Arguments.of('5', "5URYY1 914YQ"),
				Arguments.of('*', "*NKRRU 2UXRJ"),
				Arguments.of('/', "/IFMMP XPSME")
			);
	}
}