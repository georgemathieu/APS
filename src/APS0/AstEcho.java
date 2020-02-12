package APS0;

public class AstEcho implements Ast{
	
	private Ast ast;
	
	public AstEcho(Ast a) {
		this.ast = a;
	}
	@Override
	public String toPrologString() {
		return "echo("+ast.toPrologString()+")";
	}
	
}
