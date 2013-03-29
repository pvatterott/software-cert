package edu.mit.compilers.IR;

public class IrJmp extends IrNode {
  IrNode mCondition;
  int mJumpTarget;
  
  public IrJmp(IrNode cond, int target) {
    mCondition = cond;
    mJumpTarget = target;
  }
  
  public IrNode getCond() {
    return mCondition;
  }
  
  public int getTarget() {
    return mJumpTarget;
  }

  @Override
  public void accept(IrNodeVisitor v) {
    v.visit(this);
  }

}
