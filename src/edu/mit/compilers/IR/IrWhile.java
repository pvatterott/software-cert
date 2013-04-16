package edu.mit.compilers.IR;

public class IrWhile extends IrNode{
  private IrCondExpression mExpr;
  
  public IrWhile(IrCondExpression expr) {
    mExpr = expr;
  }
  
  public IrCondExpression getCond() {
    return mExpr;
  }
  
  @Override
  public void accept(IrNodeVisitor v) {
    v.visit(this);
  }

  @Override
  public IrNode copy() {
    IrCondExpression expr = (IrCondExpression)mExpr.copy();
    IrWhile out = new IrWhile(expr);
    out.setNextInstr(getNextInstr());
    return out;
  }
}
