import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File; 
import java.io.FileNotFoundException;  
import java.util.Scanner; 

public class Sudoku {


    public static void main(String[] args) {
        System.out.println("Hello Welcome to Sudoku Solver");
        String filename =  menu();
        int[][] board = readfile(filename);
        printBoard(board);

    }

    public static String menu() {
        JButton open = new JButton();
        JFileChooser fc = new JFileChooser();
        fc.setCurrentDirectory(new java.io.File("/Users/ericstukenberg/Development/JavaProjects/SudokuSolver/sudokus"));
        fc.setDialogTitle("Select a Sudoku file (ordered easiest to hardest)");
        FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
        fc.setFileFilter(filter);
        if(fc.showOpenDialog(open) == JFileChooser.APPROVE_OPTION) {

        }
        System.out.println("You chose: " + fc.getSelectedFile().getAbsolutePath());
        return fc.getSelectedFile().getAbsolutePath();
    }
 
    public static int[][] readfile(String filename) {
        int[][] board = new int[9][9];
        try {
            File file = new File(filename);
            Scanner sc = new Scanner(file);
            int i = 0, j = 0;
            while (sc.hasNextInt()) {
              int data = sc.nextInt();
              if(j > 8 ) {
                  j = 0;
                  i++;
                  board[i][j] = data;
                  j++; 
              } else {
                board[i][j] = data;
                j++;
              }

            }
            sc.close();
          } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }
        return board;
    }



    public static void printBoard(int[][] board) {
      int i = 0;
      System.out.println("|-----------------------|");
      for (int[] row : board) {
        int j = 0;
        for (int val : row) {
          if(j == 0) {
            System.out.print("| ");
          }
          System.out.print(val + " ");
          j++;
          if(j % 3 == 0) {
            System.out.print("| ");
          } 
        }
        i++;
        System.out.println();
        if(i % 3 == 0 ) {
          System.out.println("|-----------------------|");
        }
      }
    }

}