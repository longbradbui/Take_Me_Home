import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Node extends JButton {
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

    public boolean isWall() {
        return isWall;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setChecked() {
        if (!startingNode && !goalNode) {
            setBackground(Color.YELLOW);
            setForeground(Color.black);
        }
        isChecked = true;
    }

    public void setOpened() {
        isOpen = true;
    }

    public void drawPath() {
        setBackground(Color.GREEN);
        setForeground(Color.black);
    }

    public void reset() {
        isChecked = false;
        isOpen = false;
        isWall = false;
        parentNode = null;
        setBackground(Color.WHITE);
    }
}
