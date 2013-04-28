package edu.mit.compilers.IR;

public class IrCast extends IrExpression {
  IrType mType;
  IrExpression mExpr;
  int mAddr;
  
  public IrCast(IrType type, IrExpression expr) {
    mType = type;
    mExpr = expr;
  }
  
  public IrType getType() {
    return  mType;
  }
  
  public IrExpression getExpression() {
    return mExpr;
  }

  @Override
  public void setResultAddress(int addr) {
    mAddr = addr;
  }

  @Override
  public int getResultAddress() {
    return mAddr;
  }

  @Override
  public void accept(IrNodeVisitor v) {
    v.visit(this);
  }

  @Override
  public IrNode copy() {
    IrType t = (IrType)mType.copy();
    IrExpression e = (IrExpression)mExpr.copy();
    IrCast copy = new IrCast(t, e);
    copy.setResultAddress(mAddr);
    return copy;
  }

}
