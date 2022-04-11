package kr.or.ddit.vo;

import java.io.IOException;
import java.io.Serializable;
import java.util.Base64;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.validate.InsertGroup;
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
 * 
 * DataMapper 를 이용한 테이블 조인
 * 1. 조인 쿼리 작성(사용할 테이블 결정) ex) MEMBER, PROD
 * 2. 각 테이블의 결과를 바인딩할 domain 설계 ex) MemberVO, ProdVO
 * 3. 테이블의 관계를 domain 에 반영, 주데이터를 가진 메인테이블을 기준(1)으로.
 * 		1:1 -> ProdVO has A BuyerVO
 * 		1:N -> MemberVO has Many ProdVO (Collection)
 * 4. resultType 대신 resultMap 으로 수동 바인딩(nested map).
 * 		Association : has a (1:1)
 * 		Collection : has many (1:N)
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
	@NotBlank
	@Size(max=15)
	private String memId;
	@NotBlank
	@Size(min=4, max=12)
	private transient String memPass;
	private String memName;
	@NotBlank(groups=InsertGroup.class)
	private transient String memRegno1;
	@NotBlank(groups=InsertGroup.class)
	private transient String memRegno2;
	@Pattern(regexp="\\d{4}-\\d{2}-\\d{2}")
	private String memBir;
	private String memZip;
	private String memAdd1;
	private String memAdd2;
	@Pattern(regexp="\\d{2,3}-\\d{3,4}-\\d{4}")
	private String memHometel;
	@Pattern(regexp="\\d{2,3}-\\d{3,4}-\\d{4}")
	private String memComtel;
	@Pattern(regexp="\\d{3}-\\d{3,4}-\\d{4}")
	private String memHp;
	
	@Email
	@Size(max=80)
	private String memMail;
	private String memJob;
	private String memLike;
	private String memMemorial;
	@Pattern(regexp="\\d{4}-\\d{2}-\\d{2}")
	private String memMemorialday;
	private Integer memMileage;
	private Boolean memDelete;
	
	private Set<ProdVO> prodList; // has many (1:N), has a(1:1)
	
	private byte[] memImg;
	private MultipartFile memImage;
	
	public void setMemImage(MultipartFile memImage) throws IOException {
		if(memImage==null || memImage.isEmpty()) return;
		
		this.memImage = memImage;
		this.memImg = memImage.getBytes();
	}
	
	public String getMemImgBase64() {
		if(memImg!=null) {
			return Base64.getEncoder().encodeToString(memImg);
		}else {
			return null;
		}
	}
	
	private String memRole;
}























