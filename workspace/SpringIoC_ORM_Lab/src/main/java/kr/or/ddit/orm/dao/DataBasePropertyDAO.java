package kr.or.ddit.orm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.orm.vo.DataBasePropertyVO;

@Mapper
public interface DataBasePropertyDAO {
	public List<DataBasePropertyVO> selectDataBasePropertyList();
	public DataBasePropertyVO selectDataBaseProperty(String propertyName);
}
