package edu.mit.compilers.semchecker;

import edu.mit.compilers.IR.IrAssignment;
import edu.mit.compilers.IR.IrBinOp;
import edu.mit.compilers.IR.IrBranch;
import edu.mit.compilers.IR.IrCast;
import edu.mit.compilers.IR.IrDeclaration;
import edu.mit.compilers.IR.IrExpression;
import edu.mit.compilers.IR.IrExtFunctionCall;
import edu.mit.compilers.IR.IrFor;
import edu.mit.compilers.IR.IrFunctionDef;
import edu.mit.compilers.IR.IrIdentifier;
import edu.mit.compilers.IR.IrIf;
import edu.mit.compilers.IR.IrLabel;
import edu.mit.compilers.IR.IrLiteral;
import edu.mit.compilers.IR.IrLogicalOp;
import edu.mit.compilers.IR.IrNode;
import edu.mit.compilers.IR.IrNodeVisitor;
import edu.mit.compilers.IR.IrParam;
import edu.mit.compilers.IR.IrProgram;
import edu.mit.compilers.IR.IrRedacted;
import edu.mit.compilers.IR.IrRelationalOp;
import edu.mit.compilers.IR.IrReturn;
import edu.mit.compilers.IR.IrType;
import edu.mit.compilers.IR.IrWhile;

abstract public class SemanticCheck implements IrNodeVisitor{
  protected boolean mFailed;
  protected String mErrorMessage;
  
  public boolean check(IrProgram p, SymbolTable table) {
    mFailed = false;
    mErrorMessage = null;
    p.accept(this);
    return mFailed;
  }
  
  public String getError() {
    return mErrorMessage;
  }
  
  public void visit(IrAssignment n) {
    n.getTarget().accept(this);
    n.getValue().accept(this);
  }
  
  public void visit(IrBinOp n) {
    n.getLeft().accept(this);
    n.getRight().accept(this);
  }
  
  public void visit(IrBranch n) {
    n.getCond().accept(this);
  }
  
  public void visit(IrRelationalOp n) {
    n.getLeft().accept(this);
    n.getRight().accept(this);
  }
  
  public void visit(IrLogicalOp n) {
    n.getLeft().accept(this);
    n.getRight().accept(this);
  }
  
  public void visit(IrDeclaration n) {
    for (IrNode c : n.getChildren()) {
      c.accept(this);
    }
  }
  
  public void visit(IrExtFunctionCall n) {
    n.getName().accept(this);
    for (IrExpression p : n.getParams()) {
      p.accept(this);
    }
  }
  
  public void visit(IrFunctionDef n) {
    for (IrParam p : n.getParams()) {
      p.accept(this);
    }
    
    for (IrNode c : n.getChildren()) {
      c.accept(this);
    }
  }
  
  public void visit(IrIdentifier n) {}
  
  public void visit(IrLiteral n) {}
  
  public void visit(IrProgram n) {
    for (IrFunctionDef f : n.getFunctions()) {
      f.accept(this);
    }
  }
  
  public void visit(IrRedacted n) {}
  
  public void visit(IrReturn n) {
    n.getExpr().accept(this);
  }
  
  public void visit(IrType n) {}
  
  public void visit(IrWhile n) {
    n.getCond().accept(this);
    
    for (IrNode c : n.getChildren()) {
      c.accept(this);
    }
  }
  
  public void visit(IrFor n) {
    n.getCondition().accept(this);
    n.getInitializer().accept(this);
    n.getUpdate().accept(this);
    
    for (IrNode c : n.getChildren()) {
      c.accept(this);
    }
  }
  
  public void visit(IrIf n) {
    
  }
  
  public void visit(IrParam n) {
    n.getName().accept(this);
  }
  
  public void visit(IrLabel n) {}
  
  public void visit(IrCast n) {
    n.getExpression().accept(this);
  }
}
