package APS0;

import APS0.interfaces.IASTvisitor;

public class AstEcho implements Ast{
	
	private Ast ast;
	
	public AstEcho(Ast a) {
		this.ast = a;
	}
	@Override
	public String toPrologString() {
		return "echo("+ast.toPrologString()+")";
	}
	
	public <Result, Data, Anomaly extends Throwable> Result accept(
			IASTvisitor<Result, Data, Anomaly> visitor, Data data) throws Anomaly {
		 return visitor.visit(this, data);
	}
}
