package edu.mit.compilers.IR;

public class IrType extends IrNode {
  public enum Type {
    VOID,
    DOUBLE,
    INT;
  }
  private Type mType;
  
  public IrType(Type type) {
    mType = type;
  }
  
  public Type getType() {
    return mType;
  }
  
  public static Type fromString(String t) {
    if (t.equals("int")) {
      return Type.INT;
    } else if (t.equals("void")) {
      return Type.VOID;
    } else if (t.equals("double")) {
      return Type.DOUBLE;
    } else {
      throw new RuntimeException("Unknown String Type");
    }
  }
  
  @Override
  public boolean equals(Object obj) {
    if (obj == this) 
      return true;
    if (!(obj instanceof IrType))
      return false;
    
    IrType other = (IrType)obj;
    return other.getType() == this.getType();
  }
  
  @Override
  public void accept(IrNodeVisitor v) {
    v.visit(this);
  }
  
  @Override
  public String toString() {
    switch (mType) {
    case INT:
      return "int";
    case DOUBLE:
      return "double";
    case VOID:
      return "void";
    default:
      return "NONE";
    }
  }

  @Override
  public IrNode copy() {
    return new IrType(mType);
  }
}
