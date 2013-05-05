package edu.mit.compilers.IR;

public class IrBranch extends IrNode {
  private IrExpression mCondition;
  private int mTrueBranch, mFalseBranch;
  
  public IrBranch(IrExpression cond) {
    mCondition = cond;
  }

  public void setTrueBranch(int trueBranch) {
    mTrueBranch = trueBranch;
  }
  
  public void setFalseBranch(int falseBranch) {
    mFalseBranch = falseBranch;
  }
  
  public int getTrueBranch() {
    return mTrueBranch;
  }
  
  public int getFalseBranch() {
    return mFalseBranch;
  }
  
  public IrExpression getCond() {
    return mCondition;
  }
  
  @Override
  public void accept(IrNodeVisitor v) {
    v.visit(this);
  }

  @Override
  public IrNode copy() {
    IrExpression cond = (IrExpression)mCondition.copy();
    IrBranch out = new IrBranch(cond);
    out.setFalseBranch(mFalseBranch);
    out.setTrueBranch(mTrueBranch);
    return out;
  }
  
  @Override
  public String toString() {
    return "BR: " + mCondition.toString() + ", " + mTrueBranch + " " + mFalseBranch;
  }

}
