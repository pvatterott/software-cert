package edu.mit.compilers.IR;

public class IrJmp extends IrNode {
  IrExpression mCondition;
  int mJumpTarget;
  
  public IrJmp(IrExpression cond, int target) {
    mCondition = cond;
    mJumpTarget = target;
  }
  
  public IrJmp(int target) {
    mJumpTarget = target;
    mCondition = new IrLiteral(1);
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
