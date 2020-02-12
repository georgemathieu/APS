package APS0;

public class AstIF implements Ast{
	
	Ast condition;
	Ast consequence;
	Ast alternant;
	
	
	public AstIF(Ast condition, Ast consequence, Ast alternant) {
		this.condition = condition;
		this.consequence = consequence;
		this.alternant = alternant;
	}


	@Override
	public String toPrologString() {
		return "if("+condition.toPrologString()+","+consequence.toPrologString()+","+alternant.toPrologString()+")";
	}

}
