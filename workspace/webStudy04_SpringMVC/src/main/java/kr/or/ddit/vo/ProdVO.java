package kr.or.ddit.vo;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.validate.InsertGroup;
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
	
	@NotBlank(groups=InsertGroup.class)
	private String prodImg; // DB 연결용
	private MultipartFile prodImage; // client 요청 수집용.
	public void setProdImage(MultipartFile prodImage) {
		this.prodImage = prodImage;
		if(prodImage!=null && !prodImage.isEmpty()) {
			this.prodImg = UUID.randomUUID().toString();
		}
	}
	
	public void saveTo(File saveFolder) throws IOException {
		if(prodImage!=null && !prodImage.isEmpty()) {
			File saveFile = new File(saveFolder, prodImg);
			prodImage.transferTo(saveFile);
		}
	}
	
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
