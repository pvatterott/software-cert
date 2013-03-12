package edu.mit.compilers.IR;

public class IrType extends IrNode {
  public enum Type {
    VOID,
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
    } else {
      throw new RuntimeException("Unknown String Type");
    }
  }
}
