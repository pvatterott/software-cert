package edu.mit.compilers.semchecker;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import edu.mit.compilers.IR.IrIdentifier;
import edu.mit.compilers.IR.IrType;

public class SymbolTable {
  Map<IrIdentifier, Map<IrIdentifier, IrType>> mLiteralDefs;
  Map<IrIdentifier, IrType> mFunctionReturnTypes;
  Map<IrIdentifier, List<IrType>> mFunctionParamTypes;
  
  public SymbolTable() {
    mLiteralDefs = new HashMap<IrIdentifier, Map<IrIdentifier,IrType>>();
    mFunctionReturnTypes = new HashMap<IrIdentifier, IrType>();
    mFunctionParamTypes = new HashMap<IrIdentifier, List<IrType>>();
  }
  
  public void addFunctionParamType(IrIdentifier function, IrType type) {
    List<IrType> types = mFunctionParamTypes.get(function);
    if (types == null) {
      types = new ArrayList<IrType>();
      mFunctionParamTypes.put(function, types);
    }
    types.add(type);
  }
  
  public IrType getFunctionParamType(IrIdentifier function, int index) {
    List<IrType> types = mFunctionParamTypes.get(function);
    return types.get(index);
  }
  
  public void addLiteralType(IrIdentifier function, IrIdentifier var, IrType type) {
    Map<IrIdentifier,IrType> map = mLiteralDefs.get(function);
    if (map == null) {
      map = new HashMap<IrIdentifier, IrType>();
      mLiteralDefs.put(function, map);
    }
      
    if (map.containsKey(var)) {
      throw new CheckException("Multiple Definition of \'" + var.getName()
                               + "\' in \'" + function.getName() + "\'");
    }
   
    map.put(var, type);
  }
  
  public IrType getLiteralType(IrIdentifier function, IrIdentifier var) {
    Map<IrIdentifier,IrType> map = mLiteralDefs.get(function);
    if (map == null)
      return null;
    
    return map.get(var);
  }
  
  public void addFunctionReturnType(IrIdentifier function, IrType type) {
    if (mFunctionReturnTypes.containsKey(function)) {
      throw new CheckException("Mutliple Definition of \'" + function.getName() + "\'");
    }
    mFunctionReturnTypes.put(function, type);
  }
  
  public IrType getFunctionReturnType(IrIdentifier function) {
    return mFunctionReturnTypes.get(function);
  }
}
