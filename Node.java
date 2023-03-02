import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Node extends JButton implements ActionListener {
    Node parentNode;
    int column;
    int row;
    boolean startingNode;
    boolean goalNode;
    int gCost;
    int hCost;
    int fCost;
    boolean isOpen;
    boolean isChecked;
    boolean isWall;

    public Node(int column, int row) {
        this.column = column;
        this.row = row;
        setBackground(Color.WHITE);
        setForeground(Color.BLACK);
        addActionListener(this);
    }

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        setBackground(Color.GREEN);
        System.out.println(fCost);
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    public void locateStart() {
        setText("Start");
        setBackground(Color.BLUE);
        setForeground(Color.WHITE);
        startingNode = true;
    }

    public void locateGoal() {
        setText("Goal");
        setBackground(Color.RED);
        setForeground(Color.BLACK);
        goalNode = true;
    }

    public void locateWall() {
        setBackground(Color.BLACK);
        setForeground(Color.WHITE);
        isWall = true;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public boolean isWall() {
        return isWall;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked() {
        if (!startingNode && !goalNode) {
            setBackground(Color.orange);
            setForeground(Color.black);
        }
        isChecked = true;
    }

    public void isPath() {
        setBackground(Color.GREEN);
        setForeground(Color.black);
    }
}