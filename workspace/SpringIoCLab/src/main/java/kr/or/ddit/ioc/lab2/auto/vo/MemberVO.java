package kr.or.ddit.ioc.lab2.auto.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class MemberVO {
	public void init() {
		log.info("{}, 생성 및 주입 완료", this.toString());
	}
	private String memId;
	private String memName;
	
}
