package exam.edu.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA256Hashing {
	public static final String SHA_256 = "SHA-256";
	public static final String SHA3_256 = "SHA3-256";
	private static String secret = "secret";

	public static String getSecret() {
		return secret;
	}

	public static void setSecret(String secret) {
		SHA256Hashing.secret = secret;
	}

	public static String HashWithJavaMessageDigestWithSecret(final String originalString)
			throws NoSuchAlgorithmException {
		final MessageDigest digest = MessageDigest.getInstance(SHA_256);
		String content = originalString + secret;
		final byte[] encodedhash = digest.digest(content.getBytes(StandardCharsets.UTF_8));
		return bytesToHex(encodedhash);
	}

	public static String HashWithJavaMessageDigest(final String originalString) throws NoSuchAlgorithmException {
		final MessageDigest digest = MessageDigest.getInstance(SHA_256);
		final byte[] encodedhash = digest.digest(originalString.getBytes(StandardCharsets.UTF_8));
		return bytesToHex(encodedhash);
	}

	public static String bytesToHex(byte[] hash) {
		StringBuilder hexString = new StringBuilder(2 * hash.length);
		for (byte h : hash) {
			String hex = Integer.toHexString(0xff & h);
			if (hex.length() == 1)
				hexString.append('0');
			hexString.append(hex);
		}
		return hexString.toString();
	}

	/* works with JDK9+ only */
	public static String hashWithJavaMessageDigestJDK9(final String originalString) throws NoSuchAlgorithmException {
		final MessageDigest digest = MessageDigest.getInstance(SHA3_256);
		final byte[] hashbytes = digest.digest(originalString.getBytes(StandardCharsets.UTF_8));
		return bytesToHex(hashbytes);
	}

//	public static void main(String[] args) {
//		String s = "abc";
//		try {
//			String s2 = SHA256Hashing.HashWithJavaMessageDigest(s);
//			System.out.println(s2);
//			String s1 = "ba7816bf8f01cfea414140de5dae2223b00361a396177a9cb410ff61f20015ad";
//			System.out.println(s1.equals(s2));
//		} catch (NoSuchAlgorithmException e) {
//			e.printStackTrace();
//		}
//	}
}
