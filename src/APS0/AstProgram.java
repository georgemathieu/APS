package APS0;

import APS0.interfaces.IASTvisitor;

public class AstProgram implements Ast{
	
	private AstCmds cmds;
	
	public AstProgram(AstCmds cmds)
	{
		this.cmds = cmds;
	}
	
	@Override
	public String toPrologString() {
		return "typeProg(program("+cmds.toPrologString()+")).";
	}
	
	public <Result, Data, Anomaly extends Throwable> Result accept(
			IASTvisitor<Result, Data, Anomaly> visitor, Data data) throws Anomaly {
		 return visitor.visit(this, data);
	}
	
	public AstCmds getBody() {
		return cmds;
	}
}
