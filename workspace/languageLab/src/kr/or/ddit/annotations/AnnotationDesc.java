package kr.or.ddit.annotations;


import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.sun.scenario.effect.Reflection;

import kr.or.ddit.annotations.stereotype.FirstAnnotation;
import kr.or.ddit.annotations.stereotype.RequestMapping;
import kr.or.ddit.annotations.stereotype.SecondAnnotation;
import kr.or.ddit.annotations.stereotype.ThirdAnnotation;
import kr.or.ddit.utils.ReflectionUtils;

/**
 * Annotation
 * 
 * 주석
 * comment : 사람
 * annotation : 사람과 시스템에게 일정한 정보를 제공할 목적
 * 
 * 1. annotation 종류
 * 1) Marker annotation
 * 2) Single value annotation : value 속성 하나만 설정하는 경우, 속성명을 생략 가능
 * 3) Multi value annotation : 속성명을 기반으로 여러 속성값을 설정하는 경우
 * 
 * 2. custom annotation 정의 및 트레이싱
 * 
 * 
 *
 */
@FirstAnnotation
@SecondAnnotation("SINGLE")
@ThirdAnnotation("THIRD")
public class AnnotationDesc {
	public static void main(String[] args) {
	 	String basePackage = "kr.or.ddit";
//	 	List<Class<?>> classes = ReflectionUtils.getAllClassesAtBasePackages(basePackage);
//	 	for(Class<?> type : classes) {
//	 		System.out.println(type.getName());
//	 	}
	 	Map<Class<?>, SecondAnnotation> classes = ReflectionUtils.getClassesWithAnnotationAtBasePackages(SecondAnnotation.class, basePackage);
	 	for(Entry<Class<?>, SecondAnnotation> entry : classes.entrySet()) {
	 		Class<?> eachType = entry.getKey();
	 		Object eachInstance = eachType.newInstance();
	 		
	 		SecondAnnotation eachAnnotation = entry.getValue();
	 		String value = eachAnnotation.value();
	 		System.out.printf("%s : %s\n", eachType.getName(), value);
	 		 Map<Method, RequestMapping> methods = ReflectionUtils.getMethodsWithAnnotationAtClass(eachType, RequestMapping.class, String.class);
	 		 for( Method eachMethod : methods.keySet() ) {
	 			 RequestMapping methodAnnotation = methods.get(eachMethod);
	 			 
	 			 String viewName = (String) eachMethod.invoke(eachInstance);
	 			 
	 			 
	 			 System.out.printf("%s.%s, %s, %s ==> %s\n"
	 					 , eachType.getName(), eachMethod.getName()
	 					 , methodAnnotation.value(), methodAnnotation.method()
	 					 , viewName
	 			 );
	 		 }
	 	}
	}
	
	private static void annotationSimpleTracing() {
		Annotation[] annotations = AnnotationDesc.class.getAnnotations();
	 	System.out.println(annotations.length);
	 	for(Annotation single : annotations) {
	 		Class<? extends Annotation> annotationType = single.annotationType();
	 		System.out.println(single.annotationType().getName());
	 		if(annotationType.equals(SecondAnnotation.class)) {
	 			String value = ((SecondAnnotation)single).value();
	 			System.out.println(value);
	 		}else if(annotationType.equals(ThirdAnnotation.class)) {
	 			ThirdAnnotation third = ((ThirdAnnotation)single);
	 			System.out.printf("value : %s, number : %d\n", third.value(),third.number());
	 		}
	 	}
	}
}
