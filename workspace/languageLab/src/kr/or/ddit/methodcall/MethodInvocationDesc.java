package kr.or.ddit.methodcall;
/**
  	메소드 호출시에 호출메소드에 아규먼트를 전달하는 방법 
  
   1. Call by value(값에 의한 호출)
   2. Call by reference(참조에 의한 호출)
 
 */
public class MethodInvocationDesc {
	public static void main(String[] args) {
		int number = 23; //기본형 변수	
		
		StringBuffer obj = new StringBuffer("original"); // 참조형변수(실제데이터가 아닌 주소값) 주소에 대한 참조 변수  	
		String str = "original";
		System.out.println(obj); 
		
		method(number, obj , str);		
		System.out.println(number);
		System.out.println(obj);
		System.out.println(str);
	}	
		
		
		private static void method(int arg1, StringBuffer arg2, String arg3) {
			arg1 += 5 ;
			arg2.append("append");
			//호출하기전 객체와 호출되었을 때 주소의 객체가 하나임 
			//그 하나의 메서드의 상태를 변경 시키는 것이다. 
			arg3 += "append";
	}
}
