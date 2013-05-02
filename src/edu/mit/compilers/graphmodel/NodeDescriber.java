package edu.mit.compilers.graphmodel;

import javax.management.RuntimeErrorException;

import edu.mit.compilers.IR.*;

public class NodeDescriber implements IrNodeVisitor {
  public final int IS_IDENTIFIER = 0;
  public final int IS_LITERAL = 1;
  public final int ROW_LENGTH = 6;
  
  private int[] mStore;
  private int mCurrentIndex;
  private int mTarget;
  
  public int[] getNodeDescription(IrNode n) {
    mStore = new int[ROW_LENGTH];
    for (int i = 0; i < ROW_LENGTH; i++) {
      mStore[i] = -1;
    }
    
    mCurrentIndex = 0;
    n.accept(this);
    return mStore;
  }
  
  public enum Opcode {
    BRANCH(0),
    RETURN(1),
    ASSIGN(2),
    ADD(3),
    SUB(4),
    MUL(5),
    DIV(6),
    MOD(7),
    BIN_OR(8),
    BIN_AND(9),
    BIN_XOR(10),
    LOG_OR(11),
    LOG_AND(12),
    RIGHT_SHIFT(13),
    LEFT_SHIFT(14),
    EQ(15),
    NEQ(16),
    LT(17),
    GT(18),
    LEQ(19),
    GEQ(20);
    
    private final int mVal;
    private Opcode(int val) {
      mVal = val;
    }
    
    public int code() {
      return mVal;
    }
  }
  
  private int nextIndex() {
    return mCurrentIndex++;
  }
  
  private void addOpcode(Opcode op) {
    mStore[nextIndex()] = op.code();
  }
  
  private void addTarget(int id) {
    mStore[nextIndex()] = id;
  }
  
  private void addIdentifier(int val) {
    mStore[nextIndex()] = IS_IDENTIFIER;
    mStore[nextIndex()] = val;
  }
  
  private void addLiteral(int val) {
    mStore[nextIndex()] = IS_LITERAL;
    mStore[nextIndex()] = val;
  }

  @Override
  public void visit(IrAssignment n) {
    IrIdentifier target = n.getTarget();
    IrExpression rhs = n.getValue();
    
    int id = target.getResultAddress();
    mTarget = id;
    
    if (rhs instanceof IrBinOp) {
      rhs.accept(this);
    } else {
      addOpcode(Opcode.ASSIGN);
      addTarget(mTarget);
      rhs.accept(this);
    }
  }

  @Override
  public void visit(IrBinOp n) {
    switch (n.getOp()) {
    case ADD:
      addOpcode(Opcode.ADD);
      break;
    case DIV:
      addOpcode(Opcode.DIV);
      break;
    case LEFT_SHIFT:
      addOpcode(Opcode.LEFT_SHIFT);
      break;
    case MOD:
      addOpcode(Opcode.MOD);
      break;
    case MUL:
      addOpcode(Opcode.MUL);
      break;
    case RIGHT_SHIFT:
      addOpcode(Opcode.RIGHT_SHIFT);
      break;
    case SUB:
      addOpcode(Opcode.SUB);
      break;
    }
    
    addTarget(mTarget);
    
    n.getLeft().accept(this);
    n.getRight().accept(this);
  }

  @Override
  public void visit(IrDeclaration n) {
    // Not relevant
  }

  @Override 
  public void visit(IrExtFunctionCall n) {
    // TODO Auto-generated method stub

  }

  @Override
  public void visit(IrFunctionDef n) {
    // Not relevant
  }

  @Override
  public void visit(IrIdentifier n) {
    int id = n.getResultAddress();
    addIdentifier(id);
  }

  @Override
  public void visit(IrLiteral n) {
    int val = n.getResultAddress();
    addLiteral(val);
  }

  @Override
  public void visit(IrProgram n) {
    // Not relevant
  }

  @Override
  public void visit(IrRedacted n) {
    // Not relevant
  }

  @Override
  public void visit(IrReturn n) {
    addOpcode(Opcode.RETURN);
    IrExpression cond = n.getExpr();
    if (cond instanceof IrBinOp) {
      addIdentifier(cond.getResultAddress());
    } else {
      cond.accept(this);
    }
  }

  @Override
  public void visit(IrType n) {
    // Not relevant
  }

  @Override
  public void visit(IrWhile n) {
    // Not relevant
  }

  @Override
  public void visit(IrIf n) {
    // Not relevant
  }

  @Override
  public void visit(IrLabel n) {
    // Not relevant
  }


  @Override
  public void visit(IrBranch n) {
    /*addOpcode(Opcode.BRANCH);
    IrExpression cond = n.getCond();
    if (cond instanceof IrBinOp) {
      addIdentifier(cond.getResultAddress());
    } else {
      cond.accept(this);
    }*/
    throw new RuntimeErrorException(new Error());
  }

  @Override
  public void visit(IrRelationalOp n) {
    // TODO Auto-generated method stub
    throw new RuntimeErrorException(new Error());
  }

  @Override
  public void visit(IrLogicalOp n) {
    // TODO Auto-generated method stub
    throw new RuntimeErrorException(new Error());
  }

  @Override
  public void visit(IrFor n) {
    // Not relevant
  }

  @Override
  public void visit(IrParam n) {
    // Not relevant
  }

}
