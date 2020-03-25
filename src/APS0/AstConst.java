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
		String t;
		if(type.getNom().size() == 1) {
			t = type.toPrologString();
		} else
		{
			t = type.toPrologStringF();
		}
		return "const("+nom.toPrologString()+","+t+","+valeur.toPrologString()+")";
	}

}
