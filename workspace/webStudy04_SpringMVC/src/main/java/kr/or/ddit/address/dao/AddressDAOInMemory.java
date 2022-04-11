package kr.or.ddit.address.dao;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.AddressVO;

@Repository
public class AddressDAOInMemory implements AddressDAO {
	
	private static Map<Integer, AddressVO> addressTable;
	private static int addCnt;
	
	static {
		addressTable=new LinkedHashMap<>(); //순서
		addressTable.put(++addCnt, new AddressVO(addCnt, "손효선", "000-0000-0000", "대전 대덕구"));
		addressTable.put(++addCnt, new AddressVO(addCnt, "손지완", "000-0000-0000", "대전 동구"));
	}

	@Override
	public int insertAddress(AddressVO address) {
		address.setAddId(++addCnt);
		addressTable.put(address.getAddId(), address);
		return 1; //실제 DB연결시 리턴값: 실패 0 / 성공 1 --지금은 그냥 1 입력해논거
	}

	@Override
	public List<AddressVO> selectAddressList() {
		return new ArrayList<>(addressTable.values());
	}

	@Override
	public AddressVO selectAddress(Integer addId) {
		return addressTable.get(addId);
	}



	

}
