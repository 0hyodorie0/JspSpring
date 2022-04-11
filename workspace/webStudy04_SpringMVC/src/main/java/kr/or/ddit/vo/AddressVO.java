package kr.or.ddit.vo;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import kr.or.ddit.validate.UpdateGroup;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor //jsp랑 controller를 연결할 빈껍데기를 만들기 위해
@AllArgsConstructor //AddressVO에 있는 모든 값을 생성하기 위해 
@Data
public class AddressVO {
	// 아이디는 고정값
	@NotNull(groups=UpdateGroup.class) //수정(update)할때 null값이면 안된다
	@Min(value=1, groups=UpdateGroup.class) //수정(update)할때 최솟값은 1이다
	private Integer addId;
	// 아래는 수정하면 바뀔 수 있는 값
	@NotBlank
	private String addName;
	@NotBlank
	private String addHp;
	@NotBlank
	private String address;
}
