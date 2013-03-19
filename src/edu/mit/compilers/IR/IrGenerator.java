package edu.mit.compilers.IR;

import antlr.collections.AST;
import edu.mit.compilers.grammar.*;

public class IrGenerator {
  public static IrNode getIr(AST ast) {
    IrNode outIr;
    AST next;

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
            fn.addParam((IrDeclaration)getIr(sub_next));
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
      IrExpression expr = (IrExpression)getIr(next);
      outIr = new IrWhile(expr);
      
      next = next.getNextSibling();
      if (next.getType() == CParserTokenTypes.BLOCK) {
      // Handle block
      if (next.getNumberOfChildren() > 0) {
        AST sub_next = next.getFirstChild();
        for (int i = 0; i < next.getNumberOfChildren(); i++) {
          outIr.addChild(getIr(sub_next));
          sub_next = sub_next.getNextSibling();
        }
      }
      } else {
        outIr.addChild(getIr(next));
      }
      
      break;

    case CParserTokenTypes.PARAM:
      next = ast.getFirstChild();
      IrType type = (IrType)getIr(next);
      
      next = next.getNextSibling();
      IrIdentifier id = (IrIdentifier)getIr(next);
      
      outIr = new IrDeclaration(type, id);
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
      StringBuilder expTxt = new StringBuilder();
      
      for (int i = 1; i < ast.getNumberOfChildren(); i++) {
        next = next.getNextSibling();
        expTxt.append(next.getText());
      }
      
      outIr = new IrAssignment(target, new IrExpression(expTxt.toString()));
      break;
      
    case CParserTokenTypes.EXPR:
      StringBuilder outStr = new StringBuilder();
      next = ast.getFirstChild();
      while (next != null) {
        outStr.append(next.getText());
        next = next.getNextSibling();
      }

      outIr = new IrExpression(outStr.toString());
      break;
      
    case CParserTokenTypes.DECIMAL_LITERAL:
      outIr = new IrExpression(ast.getText());
      break;
      
    case CParserTokenTypes.TK_return:
      next = ast.getFirstChild();
      if (next != null) {
        outIr = new IrReturn((IrExpression)getIr(next));
      } else {
        outIr = new IrReturn();
      }
      break;
      
    default:
      //System.out.println("Skipped Node: " + ast.getText());
      outIr = (IrNode) (new IrRedacted());
    }

    return outIr;
  }
}
