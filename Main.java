import javax.swing.*;
public class Main {
    public static void main(String[] args) {
            JFrame grid = new JFrame();           // Creates a new JFrame object named "grid".
            grid.add(new Panel());               //  Adds a new Panel object to the JFrame.
            grid.setResizable(true);            //   Sets the JFrame to be resizable with mouse
            grid.setVisible(true);             //    Sets the JFrame to be visible
            grid.setLocationRelativeTo(null); //     Sets the location of the JFrame to be centered on the screen.
            grid.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // The program exits when we close the Window
            grid.setTitle("FIND THE SHORTEST PATH TO BRING BLUE BOX TO RED BOX"); // Set the title of the Window
    }
}
