package com.matthewlim.EncoderApp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EncoderTest {

	private Encoder encoder;
	private Cipher mockCipher;

	@BeforeEach
	void setUp() {
		mockCipher = mock(Cipher.class);
		encoder = new Encoder(mockCipher);
	}
	
	@Test
	void testEncode() {
		String plainText = "HELLO WORLD";
		String encodedText = "ENCODED TEXT";
		
		when(mockCipher.encode(plainText)).thenReturn(encodedText);

		String result = encoder.encode(plainText);
		
		verify(mockCipher, times(1)).encode(plainText);
		assertEquals(encodedText, result);
	}
	
	@Test
	void testDecode() {
		String encodedText = "ENCODED TEXT";
		String plainText = "HELLO WORLD";

		when(mockCipher.decode(encodedText)).thenReturn(plainText);
		
		String result = encoder.decode(encodedText);
		
		verify(mockCipher, times(1)).decode(encodedText);
		assertEquals(plainText, result);
	}
}