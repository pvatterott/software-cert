package edu.mit.compilers.IR;

import java.util.List;
import java.util.ArrayList;

public class IrFunctionDef extends IrNode {
  IrType mType;
  IrIdentifier mName;
  List<IrDeclaration> mParams; 
  //List<IrNode> mChildren;
  
  public IrFunctionDef(IrType type, IrIdentifier name) {
    mType = type;
    mName = name;
    mParams = new ArrayList<IrDeclaration>();
    //mChildren = new ArrayList<IrNode>();
  }
  
  public void addParam(IrDeclaration param) {
    mParams.add(param);
  }
  
  //public void addChild(IrNode child) {
  //  mChildren.add(child);
  //}
}
