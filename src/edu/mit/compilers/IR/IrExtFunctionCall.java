package edu.mit.compilers.IR;

public class IrExtFunctionCall extends IrNode {
  @Override
  public void accept(IrNodeVisitor v) {
    v.visit(this);
  }
}
