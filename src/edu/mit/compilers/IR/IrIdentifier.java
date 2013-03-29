package edu.mit.compilers.IR;

public class IrIdentifier extends IrExpression {
  private String mName;
  private int mAddrOfResult;
  
  public IrIdentifier() {
    
  }

  public IrIdentifier(String name) {
    mName = name;
  }
  
  public String getName() {
    return mName;
  }

  @Override
  public void accept(IrNodeVisitor v) {
    v.visit(this);
  }

  @Override
  public void setResultAddress(int addr) {
    mAddrOfResult = addr;
  }

  @Override
  public int getResultAddress() {
    return mAddrOfResult;
  }
}
