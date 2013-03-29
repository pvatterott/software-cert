package edu.mit.compilers.IR;

public class IrLiteral extends IrExpression {
  int mVal;

  public IrLiteral(String text) {
     mVal = Integer.parseInt(text);
  }

  @Override
  public void accept(IrNodeVisitor v) {
    v.visit(this);
  }

  @Override
  public void setResultAddress(int addr) {
    throw new IllegalArgumentException("Cannot assign address to literal");
  }

  @Override
  public int getResultAddress() {
    return mVal;
  }
}
