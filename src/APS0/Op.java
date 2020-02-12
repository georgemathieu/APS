package APS0;


public enum Op {
	ADD("add"), SUB("sub"), MUL("mul"), DIV("div");
	private String str;

	Op(String str) {
		this.str = str;
	}

	public String toString() {
		return this.str;
	}
}