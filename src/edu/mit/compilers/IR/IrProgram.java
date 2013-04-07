package edu.mit.compilers.IR;

import java.util.ArrayList;
import java.util.List;

public class IrProgram extends IrNode {
  private List<IrFunctionDef> mFunctions;
  
  public IrProgram() {
    mFunctions = new ArrayList<IrFunctionDef>();
  }
  
  public void addFunction(IrFunctionDef fn) {
    mFunctions.add(fn);
  }
  
  public IrFunctionDef getMain() {
    return mFunctions.get(0);
  }
  
  public List<IrFunctionDef> getFunctions() {
    return mFunctions;
  }
  
  @Override
  public void accept(IrNodeVisitor v) {
    v.visit(this);
  }

  @Override
  public IrNode copy() {
    // TODO Auto-generated method stub
    return null;
  }
}
