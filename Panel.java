import javax.swing.*;
import java.awt.*;

public class Panel extends JPanel {
    public static final int maxColumn = 20;
    public static final int maxRow = 20;
    public static final int nodeSize = 65;
    public static final int screenWidth = nodeSize * maxColumn;
    public static final int screenHeight = nodeSize * maxRow;

    Node [][] position = new Node[maxColumn][maxRow];
    Node startingNode;
    Node goalNode;
    Node currentNode;
    public Panel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setLayout(new GridLayout(maxRow, maxColumn));
        // Place the Node
        int column = 0;
        int row = 0;
        while (row < maxRow && column < maxColumn){
            position[column][row] = new Node(column, row);
            this.add(position[column][row]);
            column ++;
            if (column == maxColumn) {
                column = 0;
                row ++;
            }
        }
        // SET STARTING NODE AND GOAL NODE
        setStartingNode(3, 5);
        setGoalNode(9, 7);
    }
    private void setStartingNode(int column, int row){
        position[column][row].setStartingNode();
        startingNode = position[column][row];
        currentNode = startingNode;
    }
    private void setGoalNode(int column, int row){
        position[column][row].setGoalNode();
        goalNode = position[column][row];
    }
}
