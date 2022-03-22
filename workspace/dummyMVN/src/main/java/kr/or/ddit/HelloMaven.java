package kr.or.ddit;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

public class HelloMaven {

	public static void main(String[] args) throws IllegalAccessException, InvocationTargetException {
		System.out.println("Hello Maven!");
		System.out.println(StringUtils.isBlank("   "));
		BeanUtils.populate(null, null);
	}

}
