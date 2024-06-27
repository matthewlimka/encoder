package com.matthewlim.EncoderApp;

public interface Cipher {
	public String encode(String plainText);
	public String decode(String encodedText);
}