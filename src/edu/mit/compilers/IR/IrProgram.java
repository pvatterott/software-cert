package edu.mit.compilers.IR;

import java.util.ArrayList;
import java.util.List;

public class IrProgram extends IrNode {
  private List<IrFunctionDef> functions;
  
  public IrProgram() {
    functions = new ArrayList<IrFunctionDef>();
  }
  
  public void addFunction(IrFunctionDef fn) {
    functions.add(fn);
  }
  
  public IrFunctionDef getMain() {
    return functions.get(0);
  }
}
