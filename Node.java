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
    boolean solidNode;
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
        setBackground(Color.RED);
    }
    public void setStartingNode(){
        setBackground(Color.GREEN);
        setForeground(Color.WHITE);
        setText("START");
        startingNode = true;
    }
    public void setGoalNode(){
        setBackground(Color.YELLOW);
        setForeground(Color.BLACK);
        setText("GOAL");
        goalNode = true;
    }
}
