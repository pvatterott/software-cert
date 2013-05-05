package edu.mit.compilers.IR;

public class IrFor extends IrNode {
  private IrNode mInitializer;
  private IrExpression mCond;
  private IrNode mUpdate;
  
  public IrFor(IrNode initializer, IrExpression cond, IrNode update) {
    mInitializer = initializer;
    mCond = cond;
    mUpdate = update;
  }
  
  public IrNode getInitializer() {
    return mInitializer;
  }
  
  public IrExpression getCondition() {
    return mCond;
  }
  
  public IrNode getUpdate() {
    return mUpdate;
  }

  @Override
  public void accept(IrNodeVisitor v) {
    v.visit(this);
  }

  @Override
  public IrNode copy() {
    // TODO Auto-generated method stub
    return null;
  }

}
