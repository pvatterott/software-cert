package edu.mit.compilers.graphmodel;

import javax.management.RuntimeErrorException;

import edu.mit.compilers.IR.*;

public class NodeDescriber implements IrNodeVisitor {
  public final int ROW_LENGTH = 6;
  
  private String[] mStore;
  private int mCurrentIndex;
  private int mTarget;
  
  public String[] getNodeDescription(IrNode n) {
    mStore = new String[ROW_LENGTH];
    for (int i = 0; i < ROW_LENGTH; i++) {
      mStore[i] = "NaN";
    }
    
    mCurrentIndex = 0;
    n.accept(this);
    return mStore;
  }
  
  public enum VarType {
    IDENTIFIER(0),
    INT_LITERAL(1),
    DOUBLE_LITERAL(2);
    
    private final int mVal;
    private VarType(int val) {
      mVal = val;
    }
    
    public int code() {
      return mVal;
    }
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
    GEQ(20),
    CAST_INT(21),
    CAST_DOUBLE(22);
    
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
    mStore[nextIndex()] = Integer.toString(op.code());
  }
  
  private void addTarget(int id) {
    mStore[nextIndex()] = Integer.toString(id);
  }
  
  private void addIdentifier(int val) {
    mStore[nextIndex()] = Integer.toString(VarType.IDENTIFIER.code());
    mStore[nextIndex()] = Integer.toString(val);
  }
  
  private void addLiteral(int val) {
    mStore[nextIndex()] = Integer.toString(VarType.INT_LITERAL.code());
    mStore[nextIndex()] = Integer.toString(val);
  }
  
  private void addLiteral(double val) {
    mStore[nextIndex()] = Integer.toString(VarType.DOUBLE_LITERAL.code());
    mStore[nextIndex()] = Double.toString(val);
  }

  @Override
  public void visit(IrAssignment n) {
    IrIdentifier target = n.getTarget();
    IrExpression rhs = n.getValue();
    
    int id = target.getResultAddress();
    mTarget = id;
    
    if (rhs instanceof IrBinOp || rhs instanceof IrCast) {
      rhs.accept(this);
    } else {
      addOpcode(Opcode.ASSIGN);
      addTarget(mTarget);
      rhs.accept(this);
    }
  }
  
  @Override
  public void visit(IrCast n) {
    IrType res = n.getType();
    IrType.Type t = res.getType();
    if (t ==  IrType.Type.DOUBLE) {
      addOpcode(Opcode.CAST_DOUBLE);
    } else {
      addOpcode(Opcode.CAST_INT);
    }
    IrExpression e = n.getExpression();
    addTarget(mTarget);
    e.accept(this);
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
    if (n.isDouble()) {
      addLiteral(n.getDoubleVal());
    } else {
      addLiteral(n.getIntVal());
    }
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
    switch (n.getOp()) {
    case EQ:
      addOpcode(Opcode.EQ);
      break;
    case NEQ:
      addOpcode(Opcode.NEQ);
      break;
    case LT:
      addOpcode(Opcode.LT);
      break;
    case GT:
      addOpcode(Opcode.GT);
      break;
    case LEQ:
      addOpcode(Opcode.LEQ);
      break;
    case GEQ:
      addOpcode(Opcode.GEQ);
      break;
    }
    
    addTarget(mTarget);
    
    n.getLeft().accept(this);
    n.getRight().accept(this);
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
