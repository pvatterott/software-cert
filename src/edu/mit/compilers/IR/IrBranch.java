package edu.mit.compilers.IR;

public class IrBranch extends IrNode {
  private IrCondExpression mCondition;
  private int mTrueBranch, mFalseBranch;
  
  public IrBranch(IrCondExpression cond) {
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
  
  public IrCondExpression getCond() {
    return mCondition;
  }
  
  @Override
  public void accept(IrNodeVisitor v) {
    v.visit(this);
  }

  @Override
  public IrNode copy() {
    IrCondExpression cond = (IrCondExpression)mCondition.copy();
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
