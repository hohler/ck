package com.github.mauricioaniche.ck.metric;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.Assignment;
import org.eclipse.jdt.core.dom.Assignment.Operator;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.ConditionalExpression;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.NullLiteral;
import org.eclipse.jdt.core.dom.ParenthesizedExpression;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;

import com.github.mauricioaniche.ck.CKNumber;
import com.github.mauricioaniche.ck.CKReport;

public class NONA extends ASTVisitor implements Metric {

	private int nullAssignments;	

	@Override
	public boolean visit(Assignment node) {
		Expression left = node.getLeftHandSide();
		Expression right = node.getRightHandSide();
		Operator o = node.getOperator();
		
		
		if(left instanceof ConditionalExpression) {
			visit((ConditionalExpression) left);
		}
		if(right instanceof ConditionalExpression) {
			visit((ConditionalExpression) right);
		}
		
		if(left instanceof ParenthesizedExpression) {
			Expression e = ((ParenthesizedExpression) left).getExpression();
			if(e instanceof ConditionalExpression) visit((ConditionalExpression) e);
		}

		if(right instanceof ParenthesizedExpression) {
			Expression e = ((ParenthesizedExpression) right).getExpression();
			if(e instanceof ConditionalExpression) visit((ConditionalExpression) e);
		}

		/*
		if(left instanceof ParenthesizedExpression || left instanceof ConditionalExpression) {
			
			return visit((ConditionalExpression) left);
		}
		if(right instanceof ParenthesizedExpression || right instanceof ConditionalExpression) return visit((ConditionalExpression) right);
		*/
		
		if(o == Operator.ASSIGN) {
			if(left instanceof NullLiteral || right instanceof NullLiteral) nullAssignments++;
		}
		
		return false;
	}

	@Override
	public boolean visit(VariableDeclarationFragment node) {
		Expression e = node.getInitializer();
		if(e instanceof NullLiteral) nullAssignments++;
		return false;
	}
	

	@Override
	public boolean visit(ConditionalExpression node) {
		ASTNode parent = node.getParent();
		
		if(!((parent instanceof ParenthesizedExpression && parent.getParent() instanceof Assignment) || parent instanceof Assignment)) return false;
		
		Expression thenExp = node.getThenExpression();
		Expression elseExp = node.getElseExpression();
		if(thenExp instanceof NullLiteral) nullAssignments++;
		if(elseExp instanceof NullLiteral) nullAssignments++;
		return false;
	}

	@Override
	public void execute(CompilationUnit cu, CKNumber number, CKReport report) {
		cu.accept(this);
	}

	@Override
	public void setResult(CKNumber result) {
		result.setNona(nullAssignments);
	}
}
