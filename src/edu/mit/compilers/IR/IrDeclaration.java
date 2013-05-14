package edu.mit.compilers.IR;

import edu.mit.compilers.graphmodel.Bound;

public class IrDeclaration extends IrNode {
  private IrType mType;
  private Bound mBound;
  
  public IrDeclaration(IrType type) {
    mType = type;
  }
  
  public IrType getType() {
    return mType;
  }
  
  public void setBounds(Bound b) {
    mBound = b;
  }
  
  public Bound getBound() {
    return mBound;
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
