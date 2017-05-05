package com.github.mauricioaniche.ck.metric;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.InfixExpression;
import org.eclipse.jdt.core.dom.InfixExpression.Operator;
import org.eclipse.jdt.core.dom.NullLiteral;
import org.eclipse.jdt.core.dom.ParenthesizedExpression;

import com.github.mauricioaniche.ck.CKNumber;
import com.github.mauricioaniche.ck.CKReport;

public class NONC extends ASTVisitor implements Metric {

	private int nullChecks;
	
	@Override
	public boolean visit(InfixExpression node) {
		Expression left = node.getLeftOperand();
		Expression right = node.getRightOperand();
		
		if(left instanceof InfixExpression) this.visit((InfixExpression) left);
		if(right instanceof InfixExpression) this.visit((InfixExpression) right);
		
		if(left instanceof ParenthesizedExpression) {
			this.visit((ParenthesizedExpression) left);
		}
		
		if(right instanceof ParenthesizedExpression) {
			this.visit((ParenthesizedExpression) right);
		}
		
		if(!(left instanceof InfixExpression && !(right instanceof InfixExpression))) {
			Operator o = node.getOperator();
			
			if(o == Operator.EQUALS || o == Operator.NOT_EQUALS) {
				if(left instanceof NullLiteral || right instanceof NullLiteral) {
					nullChecks++;
				}
			}
		}
		return false;
	}
	
	
	@Override
	public boolean visit(ParenthesizedExpression node) {
		Expression e = node.getExpression();
		if(e instanceof InfixExpression) this.visit((InfixExpression) e);
		return false;
	}


	@Override
	public void execute(CompilationUnit cu, CKNumber number, CKReport report) {
		cu.accept(this);
	}

	@Override
	public void setResult(CKNumber result) {
		result.setNonc(nullChecks);
	}
}
