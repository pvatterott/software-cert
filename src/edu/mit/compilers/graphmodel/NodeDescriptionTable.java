package edu.mit.compilers.graphmodel;
import java.util.List;
import java.util.ArrayList;

public class NodeDescriptionTable {
  public final int ROW_LENGTH = 6;
  public final String NAN = "NaN";
  
  private List<String[]> mTable;
  
  public NodeDescriptionTable() {
    mTable = new ArrayList<String[]>();
  }
  
  public void addRow(int[] row) {
    String[] strRow = new String[ROW_LENGTH];
    for (int i = 0; i < ROW_LENGTH; i++) {
      if (row[i] < 0) {
        strRow[i] = NAN;
      } else {
        strRow[i] = Integer.toString(row[i]);
      }
    }
    
    mTable.add(strRow);
  }
  
  public void addBranchRow(int op, String cond) {
    String[] strRow = new String[ROW_LENGTH];
    strRow[0] = Integer.toString(op);
    strRow[1] = cond;
    for (int i = 2; i < ROW_LENGTH; i++) {
      strRow[i] = NAN;
    }
    
    mTable.add(strRow);
  }
  
  public String[][] getCsvFormat() {
    int size = mTable.size();
    String[][] out = new String[size][ROW_LENGTH];
    for (int i = 0; i < size; i++) {
      out[i] = mTable.get(i);
    }
    return out;
  }
}
