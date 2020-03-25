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

public class EmptyLexicalEnvironment implements ILexicalEnvironment {
    
    public EmptyLexicalEnvironment() {}

    @Override
	public boolean isPresent(AstId key) {
        return false;
    }

    @Override
	public AstId getKey() throws EvaluationException {
        throw new EvaluationException("Really empty environment");
    }

    @Override
	public Object getValue(AstId key) throws EvaluationException {
        throw new EvaluationException("No such variable " + key.getName());
    }

    @Override
	public void update(AstId key, Object value)
            throws EvaluationException {
        throw new EvaluationException("Empty environment");
    }

    @Override
	public boolean isEmpty() {
        return true;
    }

    @Override
	public ILexicalEnvironment extend(AstId variable, Object value) {
        return new LexicalEnvironment(variable, value, this);
    }

    @Override
	public ILexicalEnvironment getNext() throws EvaluationException {
        throw new EvaluationException("Completely empty environment");
    }
}
