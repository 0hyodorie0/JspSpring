package kr.or.ddit.address.dao;

import java.util.List;

import kr.or.ddit.vo.AddressVO;

public interface AddressDAO {
	public List<AddressVO> selectAddressList();
	public int insertAddress(AddressVO address);
}
