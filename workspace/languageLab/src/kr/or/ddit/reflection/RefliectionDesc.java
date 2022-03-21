package kr.or.ddit.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import kr.or.ddit.reflect.ReflectionTest;

/**
 * 
 * Reflection ? 
 * 	인스터스로부터 해당 인스턴스 타입, 속성(상태,변수), 행동 (메소드)들에 대한 정보를
 *  추측해가는 작업 
 *  							
 *
 */
public class RefliectionDesc {
	public static void main(String[] args) {
	//	Date today = new Date();
		
		Object obj = ReflectionTest.getObject();
		
		System.out.println(obj);
		
		Class<?> type = obj.getClass();//붕어빵에서 붕어빵틀을 찾음 ! 		
		System.out.println(type);
		
		Field[] flds = type.getDeclaredFields();
		for(Field fld : flds) {
//			fld.setAccessible(true);
			Class<?> fldType = fld.getType();//전역변수의 타입
			try {
			//	Object value = fld.get(obj);//전역변수의 값 
				String fldName=	fld.getName();
				String getterName="get" + fldName.substring(0,1).toUpperCase()+fldName.substring(1);
				Method getter = type.getDeclaredMethod(getterName);
				
				String setterName="set" + fldName.substring(0,1).toUpperCase()+fldName.substring(1);
				Method setter = type.getDeclaredMethod(setterName, fldType);
				
				if(fldType.equals(String.class)) {
					setter.invoke(obj,"캬캬캬캬캬");
				}else {
					setter.invoke(obj,33);
				}


				
				Object value = getter.invoke(obj);
				
				
				System.out.printf("private %s %s = %s;\n",
						fldType.getSimpleName(),fldName,value);				
			} catch (Exception e) {		
				e.printStackTrace();
			}
		}
	}
}
