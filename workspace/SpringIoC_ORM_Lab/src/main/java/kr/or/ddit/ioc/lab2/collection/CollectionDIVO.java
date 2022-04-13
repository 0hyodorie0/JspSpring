package kr.or.ddit.ioc.lab2.collection;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CollectionDIVO {
	private List<String> sampleList;
	private Set sampleSet;
	private Map<String, Object> sampleMap;
	private Properties sampleProps;
	
	private String[] sampleArray;
}
