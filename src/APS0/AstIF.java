package APS0;

import APS0.interfaces.IASTvisitor;

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
	
	public <Result, Data, Anomaly extends Throwable> Result accept(
			IASTvisitor<Result, Data, Anomaly> visitor, Data data) throws Anomaly {
		 return visitor.visit(this, data);
	}
	
	public Ast getCondition(){
		return condition;
	}
	
	public Ast getConsequence(){
		return consequence;
	}
	
	public Ast getAlternant(){
		return alternant;
	}
}
