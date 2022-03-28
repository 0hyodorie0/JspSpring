package kr.or.ddit.reference;

public class NullPointerExample {
	public static class SampleVO{
		public Object getInstance() {
			return null;
		}
	}
	
	private static SampleVO sample;
	
	public static void main(String[] args) {
//		NullPointerException
		
		Object instance = sample.getInstance();
		
		System.out.println(instance);
	}
}
