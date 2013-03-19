package edu.mit.compilers.graphmodel;

import java.util.HashSet;

public class GraphPrinter {
  public static void print(GraphNode head, int numNodes) {
    byte[][] adjMatrix = new byte[numNodes][numNodes];
    String[] statements = new String[numNodes];
    String[] conditions = new String[numNodes];
    HashSet<Integer> visited = new HashSet<Integer>();
    
    addNodeInfo(head, visited, adjMatrix, conditions, statements);
    
    printMatrix(adjMatrix);
    printNodes(conditions, statements);
  }
  
  private static void addNodeInfo(GraphNode g, HashSet<Integer> visited, byte[][] adjMatrix, String[] conditions, String[] statements) {
    int n, n2;
    n = g.mNodeNumber;
    
    visited.add(n);
    conditions[n] = g.mCondition;
    statements[n] = g.mExpression;
    
    if (g.mPositiveEdge != null) {
      n2 = g.mPositiveEdge.mNodeNumber;
      adjMatrix[n][n2] = 1;
      if (!visited.contains(n2)) {
        addNodeInfo(g.mPositiveEdge, visited, adjMatrix, conditions, statements);
      }
    }
    if (g.mNegativeEdge != null) {
      n2 = g.mNegativeEdge.mNodeNumber;
      adjMatrix[n][n2] = 2;
      if (!visited.contains(n2)) {
        addNodeInfo(g.mNegativeEdge, visited, adjMatrix, conditions, statements);
      }
    }
  }
  
  private static void printMatrix(byte[][] adjMatrix) {
    for (int row = 0; row < adjMatrix.length; row++) {
      for (int col = 0; col < adjMatrix.length; col++) {
        System.out.print(adjMatrix[row][col] + " ");
      }
      System.out.println();
    }
  }
  
  private static void printNodes(String[] conditions, String[] statements) {
    for (int n = 0; n < conditions.length; n++) {
      System.out.print(n + "\t");
      System.out.println(  "C: " + conditions[n]);
      System.out.println("\tS: " + statements[n]);
    }
  }
}
