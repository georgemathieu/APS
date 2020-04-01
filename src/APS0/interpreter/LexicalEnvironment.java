/* *****************************************************************
 * ILP9 - Implantation d'un langage de programmation.
 * by Christian.Queinnec@paracamplus.com
 * See http://mooc.paracamplus.com/ilp9
 * GPL version 3
 ***************************************************************** */
package APS0.interpreter;

import APS0.AstId;
import APS0.interpreter.interfaces.EvaluationException;
import APS0.interpreter.interfaces.ILexicalEnvironment;

public class LexicalEnvironment implements ILexicalEnvironment {

    public LexicalEnvironment (AstId variable, 
                               Object value,
                               ILexicalEnvironment next ) {
        this.variable = variable;
        this.value = value;
        this.next = next;
    }
    private final AstId variable;
    private Object value;
    private final ILexicalEnvironment next;
    
    @Override
	public AstId getKey() {
        return variable;
    }
    public Object getValue() {
        return value;
    }
    public void updateValue (Object value) {
        this.value = value;
    }
    
    
    @Override
	public boolean isPresent(AstId key) {
        if ( key.getName().equals(getKey().getName()) ) {
            return true;
        } else {
            return getNext().isPresent(key);
        }
    }

    @Override
	public ILexicalEnvironment extend(AstId variable, Object value) {
    	System.out.println("10");
        return new LexicalEnvironment(variable, value, this);
    }
    
   
    @Override
	public void update(AstId key, Object value) throws EvaluationException {
        if ( key.getName().equals(getKey().getName()) ) {
            updateValue(value);
        } else {
            getNext().update(key, value);
        }
    }

    @Override
	public Object getValue(AstId key) throws EvaluationException {
        if ( key.getName().equals(getKey().getName()) ) {
            return getValue();
        } else {
            return getNext().getValue(key);
        }
    }

    @Override
	public boolean isEmpty() {
        return false;
    }
 
    @Override
	public ILexicalEnvironment getNext() {
        return next;
    }
}
