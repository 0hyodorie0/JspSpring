package kr.or.ddit.utils;

import java.security.MessageDigest;
import java.util.Base64;

public class PasswordUtils {
	public static String encodePassword(String plain){
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			byte[] encrypted = md.digest(plain.getBytes());
			String encoded = Base64.getEncoder().encodeToString(encrypted);
			return encoded;
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static boolean passwordMatcher(String plain, String encoded) {
		boolean matched = encodePassword(plain).equals(encoded);
		return matched;
	}
}
