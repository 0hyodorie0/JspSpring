package kr.or.ddit.ioc.lab2.collection;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Slf4j
public class CollectionDIVO {
	public void init() {
		log.info("{} 생성 및 주입 완료", this.toString());
	}
	private List<String> sampleList;
	private Set sampleSet;
	private Map<String, Object> sampleMap;
	private Properties sampleProps;
	
	private String[] sampleArray;
}
