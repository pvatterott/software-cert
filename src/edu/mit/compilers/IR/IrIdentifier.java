package edu.mit.compilers.IR;

public class IrIdentifier extends IrExpression {
  private String mName;
  private int mAddrOfResult;
  
  public IrIdentifier() {
    mAddrOfResult = -1;
  }

  public IrIdentifier(String name) {
    mName = name;
    mAddrOfResult = -1;
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
  
  @Override
  public String toString() {
    return mName + " (" + mAddrOfResult + ")";
  }

  @Override
  public IrNode copy() {
    IrIdentifier copy = new IrIdentifier(mName);
    copy.setResultAddress(mAddrOfResult);
    return copy;
  }
}
