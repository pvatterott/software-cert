package edu.mit.compilers.IR;

public class IrIdentifier extends IrNode {
  private String mName;
  
  public IrIdentifier(String name) {
    mName = name;
  }
  
  public String getName() {
    return mName;
  }
}
