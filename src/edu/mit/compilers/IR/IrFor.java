package edu.mit.compilers.IR;

public class IrFor extends IrNode {
  private IrNode mInitializer;
  private IrCondExpression mCond;
  private IrNode mUpdate;
  
  public IrFor(IrNode initializer, IrCondExpression cond, IrNode update) {
    mInitializer = initializer;
    mCond = cond;
    mUpdate = update;
  }
  
  public IrNode getInitializer() {
    return mInitializer;
  }
  
  public IrCondExpression getCondition() {
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
