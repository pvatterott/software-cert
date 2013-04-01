package edu.mit.compilers.IR;

abstract public class IrExpression extends IrNode {
  abstract public void setResultAddress(int addr);
  abstract public int getResultAddress();
}
