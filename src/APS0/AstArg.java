package APS0;

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
	
}
