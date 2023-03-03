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

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    public void locateStart() {
        setBackground(Color.BLUE);
        setForeground(Color.WHITE);
        startingNode = true;
    }

    public void locateGoal() {
        setBackground(Color.RED);
        setForeground(Color.BLACK);
        goalNode = true;
    }

    public void locateWall() {
        if (!startingNode && !goalNode) {
            isWall = !isWall;
            if (isWall) {
                setBackground(Color.BLACK);
                setForeground(Color.WHITE);
            } else {
                setBackground(Color.WHITE);
                setForeground(Color.BLACK);
            }
        }
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
            setBackground(Color.YELLOW);
            setForeground(Color.black);
        }
        isChecked = true;
    }

    public void isPath() {
        setBackground(Color.GREEN);
        setForeground(Color.black);
    }
    
    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
