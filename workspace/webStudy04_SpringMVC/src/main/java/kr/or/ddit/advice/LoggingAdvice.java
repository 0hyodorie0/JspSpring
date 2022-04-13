package kr.or.ddit.advice;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoggingAdvice {
	public Object around() {
		log.info(" 테스트 로그 1");
		log.info(" 테스트 로그 2");
	}
}
