package edu.mit.compilers.IR;

public class IrDeclaration extends IrNode {
  private IrType mType;
  private int mLowerBound, mUpperBound;
  
  public IrDeclaration(IrType type) {
    mType = type;
  }
  
  public IrType getType() {
    return mType;
  }
  
  public void setRange(int lowerBound, int upperBound) {
    mLowerBound = lowerBound;
    mUpperBound = upperBound;
  }
  
  public int getLowerBound() {
    return mLowerBound;
  }
  
  public int getUpperBound() {
    return mUpperBound;
  }
  
  @Override
  public void accept(IrNodeVisitor v) {
    v.visit(this);
  }

  @Override
  public IrNode copy() {
    IrType newType = (IrType)mType.copy();
    IrDeclaration out = new IrDeclaration(newType);
    out.setNextInstr(getNextInstr());
    return out;
  }
  
  
}
