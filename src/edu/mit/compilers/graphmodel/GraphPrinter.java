package edu.mit.compilers.graphmodel;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

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
