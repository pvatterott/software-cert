package edu.mit.compilers.IR;

public class IrLogicalOp extends IrNode implements IrCondExpression {
  private LogOpType mOpType;
  private IrCondExpression mLeft, mRight;
  
  public IrLogicalOp(IrCondExpression left, LogOpType op, IrCondExpression right) {
    mLeft = left;
    mOpType = op;
    mRight = right;
  }
  
  public IrCondExpression getLeft() {
    return mLeft;
  }
  
  public IrCondExpression getRight() {
    return mRight;
  }
  
  public LogOpType getOp() {
    return mOpType;
  }
  
  public enum LogOpType {
    AND,
    OR;
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
    IrCondExpression newLeft = (IrCondExpression)mLeft.copy();
    IrCondExpression newRight = (IrCondExpression)mRight.copy();
    IrLogicalOp copy = new IrLogicalOp(newLeft, getOp(), newRight);
    return copy;
  }

  @Override
  public String getDescription() {
    return "(" + mOpType.toString() + " " + 
        mLeft.getDescription() + " " +
        mRight.getDescription() + ")";
  }
}
