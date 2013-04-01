package edu.mit.compilers.IR;

public class IrLiteral extends IrExpression {
  private int mVal;

  public IrLiteral(String text) {
     mVal = Integer.parseInt(text);
  }
  
  public IrLiteral(int num) {
    mVal = num;
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
  
  @Override
  public String toString() {
    return Integer.toString(mVal);
  }
}
