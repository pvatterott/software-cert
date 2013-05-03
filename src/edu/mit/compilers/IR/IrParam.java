package edu.mit.compilers.IR;

public class IrParam extends IrNode {
  private IrType mType;
  private IrIdentifier mName;
  
  public IrParam(IrType type, IrIdentifier name) {
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
    IrType type = (IrType)mType.copy();
    IrIdentifier name = (IrIdentifier)mName.copy();
    IrParam copy = new IrParam(type, name);
    copy.setNextInstr(getNextInstr());
    return copy;
  }
}
