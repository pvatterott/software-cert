package edu.mit.compilers.IR;

public class IrRelationalOp extends IrNode implements IrCondExpression {
  private RelOpType mOpType;
  private IrExpression mLeft, mRight;
  
  public IrRelationalOp(IrExpression left, RelOpType op, IrExpression right) {
    mLeft = left;
    mOpType = op;
    mRight = right;
  }
  
  public IrExpression getLeft() {
    return mLeft;
  }
  
  public IrExpression getRight() {
    return mRight;
  }
  
  public RelOpType getOp() {
    return mOpType;
  }
  
  public void setLeft(IrExpression l) {
    mLeft = l;
  }
  
  public void setRight(IrExpression r) {
    mRight = r;
  }
  
  public enum RelOpType {
    EQ,
    NEQ,
    LT,
    GT,
    LEQ,
    GEQ;
  }
  
  @Override
  public void accept(IrNodeVisitor v) {
    v.visit(this);
  }
  
  @Override
  public String toString() {
    return "(" + mLeft.toString() + " " + mOpType.toString() + " " + mRight.toString() + ")";
  }

  @Override
  public IrNode copy() {
    IrExpression newLeft = (IrExpression)mLeft.copy();
    IrExpression newRight = (IrExpression)mRight.copy();
    IrRelationalOp copy = new IrRelationalOp(newLeft, getOp(), newRight);
    return copy;
  }

  @Override
  public String getDescription() {
    return "(" + mOpType.toString() + " " + 
           ((IrCondExpression)mLeft).getDescription() + " " +
           ((IrCondExpression)mRight).getDescription() + ")";
  }
  
  
}
