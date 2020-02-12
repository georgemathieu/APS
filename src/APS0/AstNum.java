package APS0;


public class AstNum implements Ast{
	
	private Integer val;
	
	public AstNum(Integer n) {
		this.val = n;
	}

	@Override
	public String toPrologString() {
		return "num(" + val + ")";
	}

}
