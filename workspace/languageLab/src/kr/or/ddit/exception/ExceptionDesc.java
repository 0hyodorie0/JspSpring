package kr.or.ddit.exception;

import java.io.IOException;

/**
 * Throwable
 * ** 예외계층구조 !.... ! 
 * 
 *    Error : 컴파일 에러처럼 동적으로 처리할 수 없는 심각한 시스템 에러.
 *    Exception : 동적으로 처리가 가능한 비정상 상황
 *       1) checked exception(Exception) : 반드시 처리해야하는 예외, 처리하지 않는 경우, 컴파일 에러 발생
 *          ex) IOException, SQLException
 *       2) unchecked exception(RuntimeException) : JVM에 의해 기본 처리 구조를 가지고 있는 예외
 *         ex) NullPointerException, IllegalArgumentException
 *
 * ** 예외 처리 방법
 * 1. throws : 호출자에게 예외 처리를 위임하는 구조
 * 2. try~catch~finally -> try(Closable 하위 객체 생성 코드)~catch~finally(java 2.7이상) : 직접 처리 구조
 * 
 * ** 예외 발생 방법 : throw new 예외 인스턴스 생성
 * 
 * ** Custom Exception 정의
 */
public class ExceptionDesc {
   public static class CustomException extends RuntimeException{

      public CustomException() {
         super();
         // TODO Auto-generated constructor stub
      }

      public CustomException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
         super(message, cause, enableSuppression, writableStackTrace);
         // TODO Auto-generated constructor stub
      }

      public CustomException(String message, Throwable cause) {
         super(message, cause);
         // TODO Auto-generated constructor stub
      }

      public CustomException(String message) {
         super(message);
         // TODO Auto-generated constructor stub
      }

      public CustomException(Throwable cause) {
         super(cause);
         // TODO Auto-generated constructor stub
      }
      
   }
   
   public static void main(String[] args) {
      String value = method();
      System.out.println(value);
   }
   
   private static String method(){
      String value = "SAMPLE";
//      try {
         if(1 == 1) {
//            throw new IOException("강제 발생 예외");
            throw new CustomException("강제 발생 예외");
         }
         return value;
//      } catch (IOException e) {
//         e.printStackTrace();
//         return null;
//         throw new RuntimeException(e);
//      }
   }
}