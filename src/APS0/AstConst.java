package APS0;

import APS0.interfaces.IASTvisitor;

public class AstConst implements AstDec{
	
	AstId nom;
	AstType type;
	Ast valeur;
	
	public AstConst(AstId nom, AstType type, Ast valeur) {
		this.nom = nom;
		this.type = type;
		this.valeur = valeur;
	}

	@Override
	public String toPrologString() {
		String t;
		if(type.getNom().size() == 1) {
			t = type.toPrologString();
		} else
		{
			t = type.toPrologStringF();
		}
		return "const("+nom.toPrologString()+","+t+","+valeur.toPrologString()+")";
	}
	
	public <Result, Data, Anomaly extends Throwable> Result accept(
			IASTvisitor<Result, Data, Anomaly> visitor, Data data) throws Anomaly {
		 return visitor.visit(this, data);
	}

}
