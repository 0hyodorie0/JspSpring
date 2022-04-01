package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import kr.or.ddit.validate.UpdateGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(of = "prodId")
@ToString(exclude = { "prodDetail", "prodImg" })
public class ProdVO implements Serializable {
	private Integer rnum;
	private Integer memCnt;

	private String prodRate;
	@NotBlank(groups = UpdateGroup.class)
	private String prodId;
	@NotBlank
	private String prodName;
	@NotBlank
	private String prodLgu;
	private String lprodNm;
	@NotBlank
	private String prodBuyer;
	private String buyerName;
	@NotNull
	private Integer prodCost;
	@NotNull
	private Integer prodPrice;
	@NotNull
	private Integer prodSale;
	@NotBlank
	private String prodOutline;
	private String prodDetail;
	@NotBlank
	private String prodImg;
	@NotNull
	private Integer prodTotalstock;
	private String prodInsdate;
	@NotNull
	private Integer prodProperstock;
	private String prodSize;
	private String prodColor;
	private String prodDelivery;
	private String prodUnit;
	private Integer prodQtyin;
	private Integer prodQtysale;
	private Integer prodMileage;

	// has a
	private BuyerVO buyer;

	// has many
	private List<MemberVO> memberList;
}
