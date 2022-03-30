package kr.or.ddit.vo;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import kr.or.ddit.validate.UpdateGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
//@Getter
//@Setter
@EqualsAndHashCode(of="prodId")
@ToString(exclude= {"prodDetail", "prodImg"})
public class ProdVO implements Serializable {
	private String prodId;
	@NotBlank
	private String prodName;
	private String prodLgu;
	private String lprodNm;
	private String prodBuyer;
	private String buyerName;
	private Integer prodCost;
	private Integer prodPrice;
	private Integer prodSale;
	private String prodOutline;
	private String prodDetail;
	private String prodImg;
	private Integer prodTotalstock;
	private String prodInsdate;
	private Integer prodProperstock;
	private String prodSize;
	private String prodColor;
	private String prodDelivery;
	private String prodUnit;
	private Integer prodQtyin;
	private Integer prodQtysale;
	private Integer prodMileage;
	
	private Integer memcnt;
	private Integer rnum;
	private String prodRate;
	@NotBlank(groups=UpdateGroup.class)
	private BuyerVO buyer;
	
}
