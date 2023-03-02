import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.add(new Panel());
        window.setResizable(true);
        window.setVisible(true);
        window.setTitle("Find the shortest path to take me home");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.pack();
        window.setLocationRelativeTo(null);
    }
}
