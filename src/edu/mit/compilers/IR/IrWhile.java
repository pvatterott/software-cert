package edu.mit.compilers.IR;

public class IrWhile extends IrNode{
  private IrExpression mExpr;
  
  public IrWhile(IrExpression expr) {
    mExpr = expr;
  }
  
  public IrExpression getCond() {
    return mExpr;
  }
  
  @Override
  public void accept(IrNodeVisitor v) {
    v.visit(this);
  }

  @Override
  public IrNode copy() {
    IrExpression expr = (IrExpression)mExpr.copy();
    IrWhile out = new IrWhile(expr);
    out.setNextInstr(getNextInstr());
    return out;
  }
}
