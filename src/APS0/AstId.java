package APS0;


public class AstId implements Ast{
	
	private String name;
	
	public AstId(String s) {
		this.name = s;
	}

	@Override
	public String toPrologString() {
		return "var(" + name + ")";
	}

}
