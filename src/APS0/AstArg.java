package APS0;

import APS0.interfaces.IASTvisitor;

public class AstArg implements Ast{
	
	AstId nom;
	AstType type;
	
	
	public AstArg(AstId nom, AstType type) {
		this.nom = nom ;
		this.type = type;
	}


	@Override
	public String toPrologString() {
		return "("+nom.toPrologString()+","+type.toPrologString()+")";
	}
	
	public <Result, Data, Anomaly extends Throwable> Result accept(
			IASTvisitor<Result, Data, Anomaly> visitor, Data data) throws Anomaly {
		 return visitor.visit(this, data);
	}
}
