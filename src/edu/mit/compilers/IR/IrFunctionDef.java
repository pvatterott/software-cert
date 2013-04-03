package edu.mit.compilers.IR;

import java.util.List;
import java.util.ArrayList;

public class IrFunctionDef extends IrNode {
  private IrType mType;
  private IrIdentifier mName;
  private List<IrDeclaration> mParams; 
  private int mNumVars;
  
  public IrFunctionDef(IrType type, IrIdentifier name) {
    mType = type;
    mName = name;
    mParams = new ArrayList<IrDeclaration>();
  }
  
  public void addParam(IrDeclaration param) {
    mParams.add(param);
  }
  
  public String getName() {
    return mName.getName();
  }
  
  public void setNumVars(int num) {
    mNumVars = num;
  }
  
  public List<IrDeclaration> getParams() {
    return mParams;
  }
  
  public int getNumVars() {
    return mNumVars;
  }
  
  @Override
  public void accept(IrNodeVisitor v) {
    v.visit(this);
  }
}
