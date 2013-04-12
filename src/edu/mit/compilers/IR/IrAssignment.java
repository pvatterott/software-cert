package edu.mit.compilers.IR;

public class IrAssignment extends IrNode {
  private IrIdentifier mTarget;
  private IrExpression mValue;
  
  public IrAssignment(IrIdentifier target, IrExpression value) {
    mTarget = target;
    mValue = value;
  }
  
  public IrIdentifier getTarget() {
    return mTarget;
  }
  
  public IrExpression getValue() {
    return mValue;
  }

  @Override
  public void accept(IrNodeVisitor v) {
    v.visit(this);
  }
  
  @Override
  public String toString() {
    return mTarget.toString() + " = " + mValue.toString();
  }

  @Override
  public IrNode copy() {
    IrIdentifier newTarget = (IrIdentifier) mTarget.copy();
    IrExpression newValue = (IrExpression) mValue.copy();
    IrAssignment out = new IrAssignment(newTarget, newValue);
    out.setNextInstr(getNextInstr());
    return out;
  }
  
  
}
