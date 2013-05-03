package edu.mit.compilers.IR;

import edu.mit.compilers.semchecker.SymbolTable;

abstract public class IrExpression extends IrNode {
  abstract public void setResultAddress(int addr);
  abstract public int getResultAddress();
  abstract public IrType getType(SymbolTable table, IrIdentifier currentFunction);
}
