package edu.mit.compilers.IR;

public class IrDeclaration extends IrNode {
  private IrType mType;
  private IrIdentifier mName;
  
  public IrDeclaration(IrType type, IrIdentifier name) {
    mType = type;
    mName = name;
  }
  
  public IrType getType() {
    return mType;
  }
  
  public IrIdentifier getName() {
    return mName;
  }
  
  @Override
  public void accept(IrNodeVisitor v) {
    v.visit(this);
  }

  @Override
  public IrNode copy() {
    IrType newType = (IrType)mType.copy();
    IrIdentifier newName = (IrIdentifier)mName.copy();
    return new IrDeclaration(newType, newName);
  }
  
  
}
