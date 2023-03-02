import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Grid_Panel extends JPanel {
    public static final int maxColumn = 20;
    public static final int maxRow = 25;
    public static final int nodeSize = 70;
    public static final int screenWidth = nodeSize * maxColumn;
    public static final int screenHeight = nodeSize * maxRow;
    boolean foundGoal = false;
    boolean isContinue = true;
    int step = 0;
    Node[][] position = new Node[maxColumn][maxRow];
    Node startingNode;
    Node goalNode;
    Node currentNode;
    ArrayList<Node> openNode_List = new ArrayList<>();
    ArrayList<Node> isChecked_List = new ArrayList<>();

    public Grid_Panel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setLayout(new GridLayout(maxRow, maxColumn));
        this.addKeyListener(new KeyHandler(this));
        this.setFocusable(true);
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
        setWall(11, 12);
        setWall(12, 12);
        setWall(13, 12);
        setWall(9, 3);
        setWall(8, 3);

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

    private void showCost() {
        int column = 0;
        int row = 0;

        while (column < maxColumn && row < maxRow) {
            getCost(position[column][row]);
            column++;
            if (column == maxColumn) {
                column = 0;
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
        if (node != startingNode && node != goalNode);
    }

    public void pathSearch() {
        while (!foundGoal && isContinue) {
            int column = currentNode.getColumn();
            int row = currentNode.getRow();
            currentNode.setChecked();
            isChecked_List.add(currentNode);
            openNode_List.remove(currentNode);
            if (row - 1 >= 0) evaluateNode(position[column][row - 1]);              // OPEN THE UPPER NODE
            if (column - 1 >= 0) evaluateNode(position[column - 1][row]);          //  OPEN THE LEFT NODE
            if (row + 1 < maxColumn) evaluateNode(position[column][row + 1]);     //   OPEN THE LOWER NODE
            if (column + 1 < maxColumn) evaluateNode(position[column + 1][row]); //    OPEN THE RIGHT NODE

            // FIND THE OPTIMAL NODE
            int bestNodeIndex = 0;
            int bestNodefCOst = Integer.MAX_VALUE;
            for (int i = 0; i < openNode_List.size(); i++){
                if (openNode_List.get(i).fCost < bestNodefCOst) {
                    bestNodeIndex = i;
                    bestNodefCOst = openNode_List.get(i).fCost;
                }
                else if (openNode_List.get(i).fCost == bestNodefCOst){
                    if (openNode_List.get(i).gCost < openNode_List.get(i).gCost) {
                        bestNodeIndex = i;
                    }
                }
            }
            currentNode = openNode_List.get(bestNodeIndex);
            if (currentNode == goalNode){
                foundGoal = true;
                isContinue = false;
                pathTracking();
                System.out.println("It took " + step + " steps to find the path");
            }
            step++;
        }
    }

    private void evaluateNode(Node node) {
        if (!node.isChecked() && !node.isOpen() && !node.isWall()) {
            node.isOpen();
            node.parentNode = currentNode;
            openNode_List.add(node);
        }
    }

    private void pathTracking(){
        Node current = goalNode;
        while (current != startingNode){
            current = current.parentNode;
            if (current != startingNode) current.isPath();
        }
    }
}
