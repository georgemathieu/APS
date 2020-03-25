package APS0;


public enum Op {
	ADD("add"), SUB("sub"), MUL("mul"), DIV("div"),
	AND("and"), OR("or"), NOT("not"), LT("lt"), EQ("eq");
	
	private String str;

	Op(String str) {
		this.str = str;
	}

	public String toString() {
		return this.str;
	}
}