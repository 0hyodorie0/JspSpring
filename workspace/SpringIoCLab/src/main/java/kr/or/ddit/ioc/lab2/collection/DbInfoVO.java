package kr.or.ddit.ioc.lab2.collection;

import lombok.Data;

@Data
public class DbInfoVO {
	private String driverClassName;
	private String url;
	private String user;
	private String password;
	
	private int initialSize;
	private long maxWait;
	private int maxTotal;
}
