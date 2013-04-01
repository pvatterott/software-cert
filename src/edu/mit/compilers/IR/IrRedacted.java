package edu.mit.compilers.IR;

public class IrRedacted extends IrNode {
  @Override
  public void accept(IrNodeVisitor v) {
    v.visit(this);
  }
}
