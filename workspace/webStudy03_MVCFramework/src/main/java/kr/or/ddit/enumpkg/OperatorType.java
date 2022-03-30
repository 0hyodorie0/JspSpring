package kr.or.ddit.enumpkg;

import kr.or.ddit.servlet06.NumericOperator;

public enum OperatorType {
	PLUS('+', new NumericOperator() {
		public long operate(int leftOp, int rightOp) {
			return leftOp + rightOp;
		};
	}), 
	MINUS('-', (leftOp, rightOp)->{ return leftOp - rightOp; }),
	MULTIPLY('*', (leftOp, rightOp)->{ return leftOp * rightOp; }), 
	DIVIDE('/', (leftOp, rightOp)->{ return leftOp / rightOp; }),
	MODULAR('%', (leftOp, rightOp)->{ return leftOp % rightOp; });
	
	private NumericOperator realOperator;
	
	private OperatorType(char sign, NumericOperator realOperator) {
		this.sign = sign;
		this.realOperator = realOperator;
	}
	
	private char sign;
	public char getSign() {
		return sign;
	}
	
	public long operate(int leftOp, int rightOp) {
		return realOperator.operate(leftOp, rightOp);
	}
	
	private static final String PTRN = "%d %s %d = %d";
	
	public String expression(int leftOp, int rightOp){
		return String.format(PTRN, leftOp, sign, rightOp, operate(leftOp, rightOp));	
	}	
}



















