package APS0;

import APS0.interfaces.IASTvisitor;

public class AstBool implements Ast {
	private String b;
	
	public AstBool(String sval)
	{
		this.b = sval;
	}

	@Override
	public String toPrologString() {
		if (b == "true")
		return "true";
		else
		return "false";
	}
	
	public <Result, Data, Anomaly extends Throwable> Result accept(
			IASTvisitor<Result, Data, Anomaly> visitor, Data data) throws Anomaly {
		 return visitor.visit(this, data);
	}
	
	public String getValue(){
		return b;
	}
}
