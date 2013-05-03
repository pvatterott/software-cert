package edu.mit.compilers.IR;

import java.util.List;
import java.util.ArrayList;

public class IrFunctionDef extends IrNode {
  private IrType mType;
  private IrIdentifier mName;
  private List<IrParam> mParams; 
  private int mNumVars;
  
  public IrFunctionDef(IrType type, IrIdentifier name) {
    mType = type;
    mName = name;
    mParams = new ArrayList<IrParam>();
  }
  
  public void addParam(IrParam param) {
    mParams.add(param);
  }
  
  public String getStrName() {
    return mName.getName();
  }
  
  public IrIdentifier getName() {
    return mName;
  }
  
  public IrType getType() {
    return mType;
  }
  
  public void setNumVars(int num) {
    mNumVars = num;
  }
  
  public List<IrParam> getParams() {
    return mParams;
  }
  
  public int getNumVars() {
    return mNumVars;
  }
  
  @Override
  public void accept(IrNodeVisitor v) {
    v.visit(this);
  }

  @Override
  public IrNode copy() {
    IrType newType = (IrType)mType.copy();
    IrIdentifier newName = (IrIdentifier)mName.copy();
    IrFunctionDef newDef = new IrFunctionDef(newType, newName);
    for (IrParam dec : mParams) {
      newDef.addParam((IrParam)dec.copy());
    }
    newDef.setNumVars(mNumVars);
    return newDef;
  }
}
