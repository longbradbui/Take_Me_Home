import javax.swing.*;
import java.awt.*;

public class Panel extends JPanel {
    public static final int maxColumn = 20;
    public static final int maxRow = 25;
    public static final int nodeSize = 70;
    public static final int screenWidth = nodeSize * maxColumn;
    public static final int screenHeight = nodeSize * maxRow;

    Node[][] position = new Node[maxColumn][maxRow];
    Node startingNode;
    Node goalNode;
    Node currentNode;

    public Panel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setLayout(new GridLayout(maxRow, maxColumn));
        // Place the Node
        int column = 0;
        int row = 0;
        while (row < maxRow && column < maxColumn) {
            position[column][row] = new Node(column, row);
            this.add(position[column][row]);
            column++;
            if (column == maxColumn) {
                column = 0;
                row++;
            }
        }
        // SET STARTING NODE - GOAL NODE - WALL
        setStartingNode(3, 5);
        setGoalNode(13, 10);
        setWall(10, 12);
        setWall(10, 11);
        setWall(10, 10);
        setWall(10, 9);
        setWall(10, 8);
        setWall(10, 7);
        setWall(10, 6);
        setWall(10, 5);
        setWall(10, 4);
        setWall(10, 3);
        setWall(9, 3);
        setWall(8, 3);

        // SET COST OF EACH NODE
        showCost();

    }

    private void setStartingNode(int column, int row) {
        position[column][row].locateStart();
        startingNode = position[column][row];
        currentNode = startingNode;
    }

    private void setGoalNode(int column, int row) {
        position[column][row].locateGoal();
        goalNode = position[column][row];
    }

    private void setWall(int column, int row) {
        position[column][row].locateWall();
    }

    private void showCost(){
        int column = 0;
        int row = 0;

        while (column < maxColumn && row < maxRow){
            getCost(position[column][row]);
            column++;
            if (column == maxColumn) {
                column =0;
                row++;
            }
        }
    }
    private void getCost(Node node) {
        // GET G-COST: The distance from the Starting Node.
        int dx = Math.abs(node.getColumn() - startingNode.getColumn());
        int dy = Math.abs(node.getRow() - startingNode.getRow());
        node.gCost = dx + dy;
        // GET H-COST: The distance from the Ending Node.
        dx = Math.abs(node.getColumn() - goalNode.getColumn());
        dy = Math.abs(node.getRow() - goalNode.getRow());
        node.hCost = dx + dy;
        // GET F-COST: The total cost of hCost and fCost
        node.fCost = node.gCost + node.hCost;
        // DISPLAYING THE COST ON NODE
        if (node != startingNode && node != goalNode) node.setText(" G: " + node.gCost);

    }
}
