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

      next = ast.getFirstChild();
      IrIdentifier name = (IrIdentifier) getIr(next);

      IrFunctionDef fn = new IrFunctionDef(t, name);

      next = ast.getFirstChild();
      // Handle params
      if (next.getType() == CParserTokenTypes.PARAM_LIST) {
        AST sub_next = next.getFirstChild();
        for (int i = 0; i < next.getNumberOfChildren(); i++) {
          fn.addParam((IrDeclaration)getIr(sub_next));
          sub_next = sub_next.getNextSibling();
        }

        next = ast.getFirstChild();
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

    case CParserTokenTypes.PARAM:
      next = ast.getFirstChild();
      IrType type = (IrType)getIr(next);
      
      next = next.getNextSibling();
      
      
      
      break;
      
    case CParserTokenTypes.TYPE:
      next = ast.getFirstChild();
      outIr = new IrType(IrType.fromString(next.getText()));
      break;
      
    default:
      outIr = (IrNode) (new IrRedacted());
    }

    return outIr;
  }
}
