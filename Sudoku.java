import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
// import javax.swing.JButton;
// import javax.swing.JFileChooser;

public class Sudoku {


    public static void main(String[] args) {
        System.out.println("hello");
        menu();

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
        return "";
    }


}