package APS0;

import APS0.interfaces.IASTvisitor;

public class AstId implements Ast{
	
	private String name;
	
	public AstId(String s) {
		this.name = s;
	}

	@Override
	public String toPrologString() {
		return "var(" + name + ")";
	}
	
	public <Result, Data, Anomaly extends Throwable> Result accept(
			IASTvisitor<Result, Data, Anomaly> visitor, Data data) throws Anomaly {
		 return visitor.visit(this, data);
	}

	public String getName() {
		return name;
	}
}
