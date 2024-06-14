package com.matthewlim.EncoderApp;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

class EncoderTest {

	private Encoder encoder;

	@BeforeEach
	void setUp() {
		encoder = Encoder.getInstance();
	}
	
	@ParameterizedTest
	@MethodSource("encodeTestData")
	void testEncode(char offsetCharIntValue, String expected) {
		String plainText = "HELLO WORLD";
		encoder.setOffset(offsetCharIntValue);

		String encodedText = encoder.encode(plainText);

		assertEquals(expected, encodedText);
	}
	
	@ParameterizedTest
	@ValueSource(strings = {"BGDKKN VNQKC", "FC/GGJ RJMG.", "Z0X447 -7(4W", "5URYY1 914YQ", "*NKRRU 2UXRJ", "/IFMMP XPSME"})
	void testDecode(String encodedText) {
		String expected = "HELLO WORLD";

		String decodedText = encoder.decode(encodedText);

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