import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File; 
import java.io.FileNotFoundException;  
import java.util.Scanner; 

public class Sudoku {

    private static int GRID_SIZE;
    private static int[][] BOARD;
    public static void main(String[] args) {
        System.out.println("Hello Welcome to Sudoku Solver");
        String filename =  menu();
        
        readfile(filename);
        GRID_SIZE = BOARD[0].length;
        printBoard();
        getInput();

    }

    public static void getInput() {
      Scanner sc = new Scanner(System.in);  
      System.out.println("Press 'S' to solve puzzle.");
      char input = sc.next().charAt(0); 
      System.out.println("input: " + input); 
      if(Character.compare(Character.toLowerCase(input),'s')) {
        sudokuSolver();
        printBoard();
      } else {
        getInput();
      }
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
 
    private static void readfile(String filename) {
        BOARD = new int[9][9];
        try {
            File file = new File(filename);
            Scanner sc = new Scanner(file);
            int i = 0, j = 0;
            while (sc.hasNextInt()) {
              int data = sc.nextInt();
              if(j > 8 ) {
                  j = 0;
                  i++;
                  BOARD[i][j] = data;
                  j++; 
              } else {
                BOARD[i][j] = data;
                j++;
              }

            }
            sc.close();
          } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }
    }


    public static void printBoard() {
      int i = 0;
      System.out.println("|-----------------------|");
      for (int[] row : BOARD) {
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

    public static boolean sudokuSolver() {
      for(int row = 0; row < 9; row++) {
        for(int col = 0; col < 9; col++) {
          if(BOARD[row][col] == 0) {
            for(int num = 1; num <= 9; num++) {
              if(checkNum(row, col, num)) {
                BOARD[row][col] = num;
                if(sudokuSolver()) {
                  return true;
                } else {
                  BOARD[row][col] = 0;
                }
              }
            }
            return false;
          }
        }
      }

      return true;
    }

    private static boolean checkNum(int row, int col, int num) {
      return !(checkRow(row, num) || checkColumn(col, num) || checkBox(row, col, num));
    }

    private static boolean checkRow( int row, int num) {
      int len = BOARD[0].length;
      for(int j = 0; j < len; j++ ) {
        if(BOARD[row][j] == num) {
          return true;
        }
      }
      return false;
    }

    private static boolean checkColumn(int col, int num) {
      int len = BOARD[0].length;
      for(int i = 0; i < len; i++ ) {
        if(BOARD[i][col] == num) {
          return true;
        }
      }
      return false;
    }

    private static boolean checkBox(int row, int col, int num) {
      int r = row - row%3;
      int c = col - col%3;
      for(int i = r; i < r+3; i++) {
        for(int j = c; j < c+3; j++) {
          if(BOARD[i][j] == num) {
            return true;
          }
        }
      }

      return false;
    }

}