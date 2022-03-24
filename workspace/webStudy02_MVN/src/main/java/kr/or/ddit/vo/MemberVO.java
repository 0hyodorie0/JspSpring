package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * JavaBean/ValueObject/DTO/Model 관련 규약
 * 1. 값을 가질수 있는 프로퍼티 정의
 * 2. 프로퍼티 캡슐화
 * 3. 일정한 방법에 따라 값을 변경하고 조회할 수 있는 인터페이스 제공( getter/setter )
 * 	   [get/set]프로퍼티명을 첫문자만 대문자로.
 * 4. 객체의 상태를 비교할 수 있는 방법 제공(equals)
 * 5. 객체 상태 확인 방법 제공(toString)
 * 6. 직렬화 가능 객체로 선언.
 * 
 * 회원 관리용 Domain Layer
 */
@Data
@EqualsAndHashCode(of="memId")
@ToString(exclude= {"memPass", "memRegno1", "memRegno2"})
public class MemberVO implements Serializable{
	
	public MemberVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MemberVO(String memId, String memPass) {
		super();
		this.memId = memId;
		this.memPass = memPass;
	}
	private int rnum;
	private String memId;
	private transient String memPass;
	private String memName;
	private transient String memRegno1;
	private transient String memRegno2;
	private String memBir;
	private String memZip;
	private String memAdd1;
	private String memAdd2;
	private String memHometel;
	private String memComtel;
	private String memHp;
	private String memMail;
	private String memJob;
	private String memLike;
	private String memMemorial;
	private String memMemorialday;
	private Integer memMileage;
	private Boolean memDelete;
	
	private Set<ProdVO> prodList; // has many (1:N), has a(1:1)
	
		
}









