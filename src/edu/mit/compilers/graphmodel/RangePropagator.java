package edu.mit.compilers.graphmodel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.mit.compilers.IR.*;
import edu.mit.compilers.IR.IrType.Type;
import edu.mit.compilers.semchecker.SymbolTable;

public class RangePropagator implements IrNodeVisitor{
  private SymbolTable mTable;
  private Map<Integer, Bound> mBounds;
  
  public void propagate(List<IrNode> program, SymbolTable table) {
    mTable = table;
    mBounds = new HashMap<Integer, Bound>();
    
    for (IrNode n : program) {
      n.accept(this);
    }
  }
  
  public Map<Integer, Bound> getBounds() {
    return mBounds;
  }
  
  public void visit(IrAssignment n) {
    Bound b;
    IrIdentifier target = n.getTarget();
    IrExpression  value = n.getValue();
    value.accept(this);
    int loc = target.getResultAddress();
    
    if (target.hasBounds()) {
      b = target.getBounds();
      mBounds.put(loc, b);
      return;
    }
    
    if (mBounds.containsKey(loc)) {
      b = mBounds.get(loc);
      target.setBounds(b);
      return;
    }
    
    if (value instanceof IrIdentifier) {
      IrIdentifier id = (IrIdentifier)value;
      b = getIdBounds(id); 
      target.setBounds(b);
      mBounds.put(loc, b);
    } else if (value instanceof IrBinOp) {
      IrBinOp op = (IrBinOp)value;
      b = getBoundsFromBinOp(op);
      target.setBounds(b);
      mBounds.put(loc, b);
    } else if (value instanceof IrCast) {
      IrCast cast = (IrCast)value;
      b = getCastedBounds(cast);
      mBounds.put(loc, b);
    } else {
      throw new RuntimeException("Unexpected RHS in assignment");
    }
  }
  
  Bound getCastedBounds(IrCast cast) {
    IrType t = cast.getType();
    IrExpression expr = cast.getExpression();
    Bound other;
    
    if (expr instanceof IrIdentifier) {
      IrIdentifier id = (IrIdentifier)expr;
      other = getIdBounds(id);
    } else if (expr instanceof IrLiteral) {
      other = getLitBound((IrLiteral)expr);
    } else {
      throw new RuntimeException("Unexpected expression type in cast");
    }
    
    if (t.getType() == Type.DOUBLE) {
      return other.castDouble();
    } else {
      return other.castInt();
    }
  }
  
  Bound getBoundsFromBinOp(IrBinOp n) {
    switch (n.getOp()) {
    case ADD:
    case SUB:
    case MUL:
    case DIV:
      return simplifyOp(n);
    case NEQ:
    case EQ:
    case GEQ:
    case GT:
    case LEQ:
    case LT:
    case AND:
    case OR:
      return new Bound(-1, 1);
    default:
      throw new RuntimeException("Unknown binary op");
    }
  }
  
  public Bound simplifyOp(IrBinOp n) {
    IrExpression lhs, rhs;
    lhs = n.getLeft();
    rhs = n.getRight();
    Bound bLeft, bRight;
    IrLiteral lit;
    
    if (lhs instanceof IrIdentifier) {
      bLeft = ((IrIdentifier) lhs).getBounds();
      if (rhs instanceof IrIdentifier) {
        bRight = ((IrIdentifier) rhs).getBounds();
      } else {
        lit = (IrLiteral)rhs;
        bRight = getLitBound(lit);
      }
    } else {
      lit = (IrLiteral)lhs;
      bLeft = getLitBound(lit);
      if (rhs instanceof IrIdentifier) {
        bRight = ((IrIdentifier) rhs).getBounds();
      } else {
        return mTable.getDefaultBound();
      }
    }
    
    switch (n.getOp()) {
    case ADD:
      return bLeft.add(bRight);
    case SUB:
      return bLeft.sub(bRight);
    case MUL:
      return bLeft.mul(bRight);
    case DIV:
      return bLeft.div(bRight);
    default:
      throw new RuntimeException("Unknown binary op");
    }
  }
  
  Bound getLitBound(IrLiteral lit) {
    if (lit.isDouble()) {
      return new Bound(lit.getDoubleVal(), lit.getDoubleVal());
    } else {
      return new Bound(lit.getIntVal(), lit.getIntVal());
    }
  }
  
  Bound getIdBounds(IrIdentifier id) {
    Bound b;
    if (id.hasBounds()) {
      return id.getBounds();
    } else if (mBounds.containsKey(id.getResultAddress())) {
      b = mBounds.get(id.getResultAddress());
    } else {
      b = mTable.getDefaultBound();
    }
    id.setBounds(b);
    return b;
  }
  
  public void visit(IrBinOp n) {
    n.getLeft().accept(this);
    n.getRight().accept(this);
  }
  
  public void visit(IrBranch n) {
    n.getCond().accept(this);
  }
  
  public void visit(IrDeclaration n) {
    for (IrNode c : n.getChildren()) {
      c.accept(this);
    }
  }
  
  public void visit(IrExtFunctionCall n) {
    n.getName().accept(this);
    for (IrExpression p : n.getParams()) {
      p.accept(this);
    }
  }
  
  public void visit(IrFunctionDef n) {
    for (IrParam p : n.getParams()) {
      p.accept(this);
    }
    
    for (IrNode c : n.getChildren()) {
      c.accept(this);
    }
  }
  
  public void visit(IrIdentifier n) {
    int addr = n.getResultAddress();
    if (n.hasBounds() && !mBounds.containsKey(addr)) {
      Bound b = n.getBounds();
      mBounds.put(addr, b);
    }
  }
  
  public void visit(IrLiteral n) {}
  
  public void visit(IrProgram n) {
    for (IrFunctionDef f : n.getFunctions()) {
      f.accept(this);
    }
  }
  
  public void visit(IrRedacted n) {}
  
  public void visit(IrReturn n) {
    n.getExpr().accept(this);
  }
  
  public void visit(IrType n) {}
  
  public void visit(IrWhile n) {
    n.getCond().accept(this);
    
    for (IrNode c : n.getChildren()) {
      c.accept(this);
    }
  }
  
  public void visit(IrFor n) {
    n.getCondition().accept(this);
    n.getInitializer().accept(this);
    n.getUpdate().accept(this);
    
    for (IrNode c : n.getChildren()) {
      c.accept(this);
    }
  }
  
  public void visit(IrIf n) {
    
  }
  
  public void visit(IrParam n) {
    n.getName().accept(this);
  }
  
  public void visit(IrLabel n) {}
  
  public void visit(IrCast n) {
    n.getExpression().accept(this);
  }
}
