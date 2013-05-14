package edu.mit.compilers.graphmodel;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class GraphPrinter {
  public static void print(AdjacenyMatrix m, NodeDescriptionTable t) {
    String[][] mTable = m.getCsvFormat();
    String[][] tTable = t.getCsvFormat();
    
    try {
      writeFile("output/adjacency.csv", mTable);
      writeFile("output/nodes.csv", tTable);
    } catch (IOException e) {
      System.out.println("Failed writing to file: " + e.getMessage());
    }
  }
  
  public static void printRanges(Map<Integer, Bound> bounds) {
    int numNodes = bounds.size();
    String[][] ranges = new String[numNodes][5];
    int typeIndicator;
    
    for (int i = 0; i < numNodes; i++) {
      Bound b = bounds.get(i);
      if (b.holdsDoubleBounds()) {
        typeIndicator = NodeDescriber.VarType.DOUBLE_LITERAL.code();
        ranges[i][0] = Integer.toString(i);
        ranges[i][1] = Integer.toString(typeIndicator);
        ranges[i][2] = Double.toString(b.getDoubleLower());
        ranges[i][3] = Integer.toString(typeIndicator);
        ranges[i][4] = Double.toString(b.getDoubleUpper());
      } else {
        typeIndicator = NodeDescriber.VarType.INT_LITERAL.code();
        ranges[i][0] = Integer.toString(i);
        ranges[i][1] = Integer.toString(typeIndicator);
        ranges[i][2] = Integer.toString(b.getIntLower());
        ranges[i][3] = Integer.toString(typeIndicator);
        ranges[i][4] = Integer.toString(b.getIntUpper());
      }
    }
    
    try {
      writeFile("output/ranges.csv", ranges);
    } catch (IOException e) {
      System.out.println("Failed writing to file: " + e.getMessage());
    }
  }
  
  private static void writeFile(String path, String[][] values) throws IOException {
    FileWriter fw = new FileWriter(path);
    BufferedWriter writer = new BufferedWriter(fw);
    
    for (int i = 0; i < values.length; i++) {
      for (int j = 0; j < values[i].length; j++) {
        writer.write(values[i][j]);
        if (j != values[i].length - 1) {
          writer.write(", ");
        }
      }
      writer.write("\n");
    }
    
    writer.close();
  }
}
