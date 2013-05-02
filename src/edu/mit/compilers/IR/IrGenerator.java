package edu.mit.compilers.IR;

import antlr.collections.AST;
import edu.mit.compilers.IR.IrBinOp.BinOpType;
import edu.mit.compilers.IR.IrType.Type;
import edu.mit.compilers.grammar.*;

public class IrGenerator {
  public static IrNode getIr(AST ast) {
    IrNode outIr;
    AST next, lhs, rhs;

    switch (ast.getType()) {

    case CParserTokenTypes.PROGRAM:
      IrProgram prog = new IrProgram();

      next = ast.getFirstChild();
      assert (next != null);
      for (int i = 0; i < ast.getNumberOfChildren(); i++) {
        prog.addFunction((IrFunctionDef) getIr(next));
        next = next.getNextSibling();
      }

      outIr = prog;
      break;

    case CParserTokenTypes.FN:
      assert (ast.getNumberOfChildren() >= 3);
      next = ast.getFirstChild();
      IrType t = (IrType) (getIr(next));

      next = next.getNextSibling();
      IrIdentifier name = (IrIdentifier) getIr(next);

      IrFunctionDef fn = new IrFunctionDef(t, name);

      next = next.getNextSibling();
      // Handle params
      if (next.getType() == CParserTokenTypes.PARAM_LIST) {
        AST sub_next = next.getFirstChild();
        for (int i = 0; i < next.getNumberOfChildren(); i++) {
          if (sub_next.getNumberOfChildren() > 0) {
            fn.addParam((IrParam)getIr(sub_next));
          }
          sub_next = sub_next.getNextSibling();
        }

        next = next.getNextSibling();
      }

      assert (next.getType() == CParserTokenTypes.BLOCK);
      // Handle block
      if (next.getNumberOfChildren() > 0) {
        AST sub_next = next.getFirstChild();
        for (int i = 0; i < next.getNumberOfChildren(); i++) {
          fn.addChild(getIr(sub_next));
          sub_next = sub_next.getNextSibling();
        }
      }

      outIr = fn;
      break;
      
    case CParserTokenTypes.TK_while:
      next = ast.getFirstChild();
      IrCondExpression expr = (IrCondExpression)getIr(next);
      outIr = new IrWhile(expr);
      
      next = next.getNextSibling();
      if (next.getNumberOfChildren() > 0) {
        AST sub_next = next.getFirstChild();
        for (int i = 0; i < next.getNumberOfChildren(); i++) {
          outIr.addChild(getIr(sub_next));
          sub_next = sub_next.getNextSibling();
        }
      }
      break;
      
    case CParserTokenTypes.TK_for: // two one, why aren't you at your post? TK-421, do you copy?
      next = ast.getFirstChild();
      IrNode initializer = getIr(next);
      
      next = next.getNextSibling();
      expr = (IrCondExpression)getIr(next);
      
      next = next.getNextSibling();
      IrNode update = getIr(next);
      
      outIr = new IrFor(initializer, expr, update);
      
      next = next.getNextSibling();
      if (next.getNumberOfChildren() > 0) {
        AST sub_next = next.getFirstChild();
        for (int i = 0; i < next.getNumberOfChildren(); i++) {
          outIr.addChild(getIr(sub_next));
          sub_next = sub_next.getNextSibling();
        }
      }
      break;
      
    case CParserTokenTypes.TK_if:
      next = ast.getFirstChild();
      IrCondExpression cond = (IrCondExpression)getIr(next);
      IrIf ifStmt = new IrIf(cond);
      
      next = next.getNextSibling();
      if (next.getNumberOfChildren() > 0) {
        AST sub_next = next.getFirstChild();
        for (int i = 0; i < next.getNumberOfChildren(); i++) {
          ifStmt.addSatisfied(getIr(sub_next));
          sub_next = sub_next.getNextSibling();
        }
      }
      
      next = next.getNextSibling();
      if (next != null) {
        if (next != null && next.getNumberOfChildren() > 0) {
          AST sub_next = next.getFirstChild();
          for (int i = 0; i < next.getNumberOfChildren(); i++) {
            ifStmt.addUnsatisfied(getIr(sub_next));
            sub_next = sub_next.getNextSibling();
          }
        }
        ifStmt.setHasElse();
      }
      
      outIr = ifStmt;
      break;

    case CParserTokenTypes.PARAM:
      next = ast.getFirstChild();
      IrType type = (IrType)getIr(next);
      
      next = next.getNextSibling();
      IrIdentifier id = (IrIdentifier)getIr(next);
      
      outIr = new IrParam(type, id);
      break;
      
    case CParserTokenTypes.IDENTIFIER:
      outIr = new IrIdentifier(ast.getText());
      break;
      
    case CParserTokenTypes.TYPE:
      next = ast.getFirstChild();
      outIr = new IrType(IrType.fromString(next.getText()));
      break;
      
    case CParserTokenTypes.ASSIGN:
      next = ast.getFirstChild();
      IrIdentifier target = (IrIdentifier)getIr(next);
      
      next = next.getNextSibling();
      IrExpression value = (IrExpression)getIr(next);
      
      outIr = new IrAssignment(target, value);
      break;
      
    case CParserTokenTypes.DECIMAL_LITERAL:
      outIr = new IrLiteral(ast.getText());
      break;
      
    case CParserTokenTypes.TK_return:
      next = ast.getFirstChild();
      if (next != null) {
        outIr = new IrReturn((IrExpression)getIr(next));
      } else {
        outIr = new IrReturn();
      }
      break;
      
    case CParserTokenTypes.PLUS:
      lhs = ast.getFirstChild();
      rhs = lhs.getNextSibling();
      outIr = createBinOp(IrBinOp.BinOpType.ADD, lhs, rhs);
      break;
      
    case CParserTokenTypes.MINUS:
      lhs = ast.getFirstChild();
      rhs = lhs.getNextSibling();
      outIr = createBinOp(IrBinOp.BinOpType.SUB, lhs, rhs);
      break;
      
    case CParserTokenTypes.ASTERISK:
      lhs = ast.getFirstChild();
      rhs = lhs.getNextSibling();
      outIr = createBinOp(IrBinOp.BinOpType.MUL, lhs, rhs);
      break;
      
    case CParserTokenTypes.DIV:
      lhs = ast.getFirstChild();
      rhs = lhs.getNextSibling();
      outIr = createBinOp(IrBinOp.BinOpType.DIV, lhs, rhs);
      break;
      
    case CParserTokenTypes.MOD:
      lhs = ast.getFirstChild();
      rhs = lhs.getNextSibling();
      outIr = createBinOp(IrBinOp.BinOpType.MOD, lhs, rhs);
      break;
      
    case CParserTokenTypes.SHL:
      lhs = ast.getFirstChild();
      rhs = lhs.getNextSibling();
      outIr = createBinOp(IrBinOp.BinOpType.LEFT_SHIFT, lhs, rhs);
      break;
      
    case CParserTokenTypes.SHR:
      lhs = ast.getFirstChild();
      rhs = lhs.getNextSibling();
      outIr = createBinOp(IrBinOp.BinOpType.RIGHT_SHIFT, lhs, rhs);
      break;
      
    case CParserTokenTypes.LT:
      lhs = ast.getFirstChild();
      rhs = lhs.getNextSibling();
      outIr = createRelOp(IrRelationalOp.RelOpType.LT, lhs, rhs);
      break;
      
    case CParserTokenTypes.GT:
      lhs = ast.getFirstChild();
      rhs = lhs.getNextSibling();
      outIr = createRelOp(IrRelationalOp.RelOpType.GT, lhs, rhs);
      break;
      
    case CParserTokenTypes.LEQ:
      lhs = ast.getFirstChild();
      rhs = lhs.getNextSibling();
      outIr = createRelOp(IrRelationalOp.RelOpType.LEQ, lhs, rhs);
      break;
      
    case CParserTokenTypes.GEQ:
      lhs = ast.getFirstChild();
      rhs = lhs.getNextSibling();
      outIr = createRelOp(IrRelationalOp.RelOpType.GEQ, lhs, rhs);
      break;
      
    case CParserTokenTypes.EQ:
      lhs = ast.getFirstChild();
      rhs = lhs.getNextSibling();
      outIr = createRelOp(IrRelationalOp.RelOpType.EQ, lhs, rhs);
      break;
      
    case CParserTokenTypes.NEQ:
      lhs = ast.getFirstChild();
      rhs = lhs.getNextSibling();
      outIr = createRelOp(IrRelationalOp.RelOpType.NEQ, lhs, rhs);
      break;
      
    case CParserTokenTypes.LOG_OR:
      lhs = ast.getFirstChild();
      rhs = lhs.getNextSibling();
      outIr = createLogOp(IrLogicalOp.LogOpType.OR, lhs, rhs);
      break;
      
    case CParserTokenTypes.LOG_AND:
      lhs = ast.getFirstChild();
      rhs = lhs.getNextSibling();
      outIr = createLogOp(IrLogicalOp.LogOpType.AND, lhs, rhs);
      break;
      
    case CParserTokenTypes.FN_CALL:
      next = ast.getFirstChild();
      IrIdentifier callNm = (IrIdentifier)getIr(next);
      IrExtFunctionCall call = new IrExtFunctionCall(callNm);
      IrExpression nextParam;
      for (int i = 1; i < ast.getNumberOfChildren(); i++) {
        next = next.getNextSibling();
        nextParam = (IrExpression)getIr(next);
        call.addParam(nextParam);
      }
      
      outIr = call;
      break;
    
    case CParserTokenTypes.ADD_ASSIGN:
      next = ast.getFirstChild();
      target = (IrIdentifier)getIr(next);
      
      next = next.getNextSibling();
      value = (IrExpression)getIr(next);
      
      IrBinOp newRhs = new IrBinOp(target, BinOpType.ADD, value);
      outIr = new IrAssignment(target, newRhs);
      break;
      
    case CParserTokenTypes.SUB_ASSIGN:
      next = ast.getFirstChild();
      target = (IrIdentifier)getIr(next);
      
      next = next.getNextSibling();
      value = (IrExpression)getIr(next);
      
      newRhs = new IrBinOp(target, BinOpType.SUB, value);
      outIr = new IrAssignment(target, newRhs);
      break;
      
    case CParserTokenTypes.MUL_ASSIGN:
      next = ast.getFirstChild();
      target = (IrIdentifier)getIr(next);
      
      next = next.getNextSibling();
      value = (IrExpression)getIr(next);
      
      newRhs = new IrBinOp(target, BinOpType.MUL, value);
      outIr = new IrAssignment(target, newRhs);
      break;
      
    case CParserTokenTypes.DIV_ASSIGN:
      next = ast.getFirstChild();
      target = (IrIdentifier)getIr(next);
      
      next = next.getNextSibling();
      value = (IrExpression)getIr(next);
      
      newRhs = new IrBinOp(target, BinOpType.DIV, value);
      outIr = new IrAssignment(target, newRhs);
      break;
      
    case CParserTokenTypes.DECLARATION:
      next = ast.getFirstChild();
      type = (IrType)getIr(next);
      
      outIr = new IrDeclaration(type);
      while ((next = next.getNextSibling()) != null) {
        IrNode declared = getIr(next);
        outIr.addChild(declared);
      }
      break;
      
    case CParserTokenTypes.TK_int:
      outIr = new IrType(Type.INT);
      break;
      
    case CParserTokenTypes.TK_double:
      outIr = new IrType(Type.DOUBLE);
      break;
      
    default:
      //System.out.println("Skipped Node: " + ast.getText());
      outIr = (IrNode) (new IrRedacted());
    }

    return outIr;
  }
  
  private static IrBinOp createBinOp(IrBinOp.BinOpType op, AST lhs, AST rhs) {
    IrExpression leftExpr = (IrExpression)getIr(lhs);
    IrExpression righExpr = (IrExpression)getIr(rhs);
    return new IrBinOp(leftExpr, op, righExpr);
  }
  
  private static IrRelationalOp createRelOp(IrRelationalOp.RelOpType op, AST lhs, AST rhs) {
    IrExpression leftExpr = (IrExpression)getIr(lhs);
    IrExpression righExpr = (IrExpression)getIr(rhs);
    return new IrRelationalOp(leftExpr, op, righExpr);
  }
  
  private static IrLogicalOp createLogOp(IrLogicalOp.LogOpType op, AST lhs, AST rhs) {
    IrCondExpression leftExpr = (IrCondExpression)getIr(lhs);
    IrCondExpression righExpr = (IrCondExpression)getIr(rhs);
    return new IrLogicalOp(leftExpr, op, righExpr);
  }
}
