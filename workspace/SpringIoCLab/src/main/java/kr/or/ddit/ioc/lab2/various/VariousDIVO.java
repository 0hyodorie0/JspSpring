package kr.or.ddit.ioc.lab2.various;

import org.springframework.core.io.Resource;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class VariousDIVO {
	public void init() {
		log.info("{}, 생성 및 주입 완료", this.toString());
	}
	private int number;
	private double dblValue;
	private boolean boolData;
	
	private char ch;
	private String str;
	
	private Resource cpRes;
}
