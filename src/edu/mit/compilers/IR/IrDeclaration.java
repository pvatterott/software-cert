package edu.mit.compilers.IR;

public class IrDeclaration extends IrNode {
  private IrType mType;
  
  public IrDeclaration(IrType type) {
    mType = type;
  }
  
  public IrType getType() {
    return mType;
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
