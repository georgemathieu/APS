package APS0;

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
		return "Const("+nom.toPrologString()+","+type.toPrologString()+","+valeur.toPrologString()+")";
	}

}
