package APS0;

import APS0.interfaces.IASTvisitor;

public class AstNum implements Ast{
	
	private Integer val;
	
	public AstNum(Integer n) {
		this.val = n;
	}

	@Override
	public String toPrologString() {
		return "num(" + val + ")";
	}
	
	public <Result, Data, Anomaly extends Throwable> Result accept(
			IASTvisitor<Result, Data, Anomaly> visitor, Data data) throws Anomaly {
		 return visitor.visit(this, data);
	}
	
	public Integer getValue() {
		return this.val;
	}
}
