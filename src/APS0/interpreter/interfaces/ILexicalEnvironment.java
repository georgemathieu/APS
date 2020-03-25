/* *****************************************************************
 * ILP9 - Implantation d'un langage de programmation.
 * by Christian.Queinnec@paracamplus.com
 * See http://mooc.paracamplus.com/ilp9
 * GPL version 3
 ***************************************************************** */
package APS0.interpreter.interfaces;


import APS0.interfaces.IEnvironment;

import APS0.AstId;

public interface ILexicalEnvironment 
extends IEnvironment<AstId, Object, EvaluationException> {
    @Override
	ILexicalEnvironment extend(AstId variable, Object value);
    @Override
	ILexicalEnvironment getNext() throws EvaluationException;
}
