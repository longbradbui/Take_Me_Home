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
    boolean wall;
    int gCost;
    int hCost;
    int fCost;
    boolean isFree;
    boolean isChecked;
    public Node (int column, int row){
        this.column= column;
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
    public int getColumn(){
        return column;
    }
    public int getRow(){
        return row;
    }
    public void locateStart(){
        setBackground(Color.BLUE);
        setForeground(Color.WHITE);
        startingNode = true;
    }
    public void locateGoal(){
        setBackground(Color.RED);
        setForeground(Color.BLACK);
        goalNode = true;
    }
    public void locateWall(){
        setBackground(Color.BLACK);
        setForeground(Color.WHITE);
        wall = true;
    }
}
