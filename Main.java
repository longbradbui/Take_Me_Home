import javax.swing.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
            JFrame grid = new JFrame();
            grid.add(new Panel());
            grid.setResizable(true);
            grid.setVisible(true);
            grid.setTitle("FIND THE SHORTEST PATH TO BRING BLUE BOX TO RED BOX");
            grid.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            grid.pack();
            grid.setLocationRelativeTo(null);
    }
}
