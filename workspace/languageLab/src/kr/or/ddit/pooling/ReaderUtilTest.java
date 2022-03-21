package kr.or.ddit.pooling;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.tomcat.dbcp.pool2.impl.GenericObjectPool;
/**
 * 
 * IO (stream handling)
 * Stream ? 연속성을 가지고 있는 일련의 바이트 집합이면서 동시에 데이터를 전송할 수 있는 단방향 통로.
 * 
 * stream handling 단계
 * 1. 매체를 핸들링할 수 있는 객체의 형태로 생성. ex) File file = new File(path);
 * 2. 매체를 대상으로 입출력스트림 개방(1차 스트림) ex) FileInputStream fis = new FileInputStream(file);
 * 3. 필요시 연결형 2차 스트림 개방 ex) InputStreamReader reader = new InputStreamReader(fis, "MS949");
 * 4. EOF를 만날때까지 read/writer 반복 수행.
 * 5. close (try with resource 구문 활용)
 * 
 * stream 종류
 * 1. 전송데이터 크기에 따른 분류
 * 		1) byte stream(~Stream)
 * 			FileInputStream/FileOutputStream, SocketInputStream/SocketOutputStream
 * 		2) character stream(~Reader/~Writer)
 * 			FileReader/FileWriter
 * 2. 스트림 생성 방법에 따른 분류
 * 		1) 1차 스트림 : 매체를 대상으로 단독으로 사용 가능한 스트림.
 * 		2) 2차 스트림(연결형 스트림) : 다른 스트림을 대상으로 연결형으로 개방하는 스트림(filtering)
 * 								FilterInputStream/FilterOutputStream 자식들..
 *
 */

public class ReaderUtilTest {

	public static void main(String[] args) {
//		String name = "/kr/or/ddit/another day.txt";
//		String name = "/kr/or/ddit/오래된 노래_utf8.txt";
		String name = "/kr/or/ddit/오래된 노래.txt";
		try(
				InputStream is = ReaderUtilTest.class.getResourceAsStream(name);
				InputStreamReader reader = new InputStreamReader(is, "MS949");
				BufferedReader br = new BufferedReader(reader);
				){
//				ReaderUtil util = new ReaderUtil();
				StringBufferFactory factory = new StringBufferFactory();
				GenericObjectPool<StringBuffer> pool = new GenericObjectPool<>(factory);
				ReaderUtilUsePool util = new ReaderUtilUsePool(pool);
				String data = util.readToString(br);
				System.out.println(data);
		}catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
