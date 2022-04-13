package kr.or.ddit.address.dao;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import kr.or.ddit.vo.AddressVO;

public class AddressDAOInMemory implements AddressDAO {

	private static Map<Integer, AddressVO> addressTable;
	private static int addCnt;
	static {
		addressTable = new LinkedHashMap<>();
		addressTable.put(++addCnt, new AddressVO(addCnt, "김민호", "000-000-0000", "대전 모처1"));
		addressTable.put(++addCnt, new AddressVO(addCnt, "박세준", "000-000-0000", "대전 모처2"));
	}
	
	@Override
	public List<AddressVO> selectAddressList() {
		return new ArrayList<>(addressTable.values());
	}

	@Override
	public int insertAddress(AddressVO address) {
		address.setAddId(++addCnt);
		
		addressTable.put(address.getAddId(), address);
		
		return 1;
	}
	
	
	
	
	
	
	
	
	
	

}
