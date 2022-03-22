package kr.or.ddit.encrypt;

import java.security.MessageDigest;
import java.util.Base64;

/**
 * encode(부호화)/decode : 매체를 대상으로 데이터를 전송하거나 기록할때, 해당 매체가 인지할 수 있는 방식으로 데이터의 표현을 바꾸는 작업.
 *  URL encoding(Percent encoding), Base64 encoding
 * encrypt(암호화)/decrypt : 허가된 사용자 이외에는 해당 데이터를 읽거나 변형할 수 없도록 하기 위해 데이터의 표현을 바꾸는 작업
 *  1. 단방향 암호화 : 평문 복원이 불가능한 해시 함수 형태, 비밀번호 암호화에 활용., ex) SHA-512
 *  2. 양방향 암호화 : 평문 복원이 가능한 방식, DRM, 전자 서명
 *  	1) 대칭키 방식 : 비밀키 방식, 한개의 키로 암복호화, AES
 *  	2) 비대칭키 방식 : 공개키/개인키 형태의 한쌍의 키로 암복호화 수행., RSA
 */
public class EncryptDesc {
	public static void main(String[] args) throws Exception{
		String plain = "java";
		MessageDigest md = MessageDigest.getInstance("SHA-512");
		byte[] encrypted = md.digest(plain.getBytes());
		System.out.println(encrypted.length*8);
		
		String encoded = Base64.getEncoder().encodeToString(encrypted);
		 Base64.getEncoder().encodeToString(encrypted);
		System.out.println(encoded);
	}
}
