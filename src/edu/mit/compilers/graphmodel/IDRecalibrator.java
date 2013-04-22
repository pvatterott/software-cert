package edu.mit.compilers.graphmodel;

import edu.mit.compilers.IR.*;

public class IDRecalibrator implements IrNodeVisitor {
  private int mOffset;
  
  public void recalibrate(IrNode n, int offset) {
    mOffset = offset;
    n.accept(this);
  }
  
  @Override
  public void visit(IrAssignment n) {
    n.getTarget().accept(this);
    n.getValue().accept(this);
  }

  @Override
  public void visit(IrBinOp n) {
    n.getLeft().accept(this);
    n.getRight().accept(this);
    n.setResultAddress(n.getResultAddress() + mOffset);
  }

  @Override
  public void visit(IrDeclaration n) {
    n.getName().accept(this);
  }

  @Override
  public void visit(IrExtFunctionCall n) {
    for (IrExpression param : n.getParams()) {
      param.accept(this);
    }
    n.setResultAddress(n.getResultAddress() + mOffset);
  }

  @Override
  public void visit(IrFunctionDef n) {
    for (IrDeclaration dec : n.getParams()) {
      dec.accept(this);
    }
    
  }

  @Override
  public void visit(IrIdentifier n) {
    int old = n.getResultAddress();
    n.setResultAddress(old + mOffset);
  }

  @Override
  public void visit(IrLiteral n) {
    // Ignore
  }

  @Override
  public void visit(IrProgram n) {
    // Ignore
  }

  @Override
  public void visit(IrRedacted n) {
    // Ignore
  }

  @Override
  public void visit(IrReturn n) {
    n.getExpr().accept(this);
  }

  @Override
  public void visit(IrType n) {
    // Ignore
  }

  @Override
  public void visit(IrWhile n) {
    // Ignore
  }

  @Override
  public void visit(IrIf n) {
    // Ignore
  }

  @Override
  public void visit(IrLabel n) {
    // Ignore
  }

  @Override
  public void visit(IrBranch n) {
    n.getCond().accept(this);
  }

  @Override
  public void visit(IrRelationalOp n) {
    n.getLeft().accept(this);
    n.getRight().accept(this);
  }

  @Override
  public void visit(IrLogicalOp n) {
    n.getLeft().accept(this);
    n.getRight().accept(this);
  }

  @Override
  public void visit(IrFor n) {
    // Ignore
  }

}
