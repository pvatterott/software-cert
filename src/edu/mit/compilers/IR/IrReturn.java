package edu.mit.compilers.IR;

public class IrReturn extends IrNode {
  private IrExpression mRetExpr;
  private boolean mHasExpr;
  
  public IrReturn() {
    mHasExpr = false;
  }
  
  public IrReturn(IrExpression expr) {
    mRetExpr = expr;
    mHasExpr = true;
  }
  
  public boolean hasReturn() {
    return mHasExpr;
  }
  
  public IrExpression getExpr() {
    return mRetExpr;
  }
  
  @Override
  public void accept(IrNodeVisitor v) {
    v.visit(this);
  }
}
