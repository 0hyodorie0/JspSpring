package kr.or.ddit.exception;

/**
 * PK 로 상세조회시 조회 대상이 존재하지 않을때, 발생시킬 예외.
 *
 */
public class PKNotFoundException extends RuntimeException{

	public PKNotFoundException() {
		super();
	}

	public PKNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public PKNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public PKNotFoundException(String message) {
		super(message);
	}

	public PKNotFoundException(Throwable cause) {
		super(cause);
	}

}
