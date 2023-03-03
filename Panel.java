import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Panel extends JPanel {
    public static final int maxColumn = 20;
    public static final int maxRow = 20;
    public static final int nodeSize = 30;
    public static final int screenWidth = nodeSize * maxColumn;
    public static final int screenHeight = nodeSize * maxRow;
    boolean foundGoal = false;
    boolean isContinue = true;
    Node[][] position = new Node[maxColumn][maxRow];
    Node startingNode;
    Node goalNode;
    Node currentNode;
    ArrayList<Node> openNode_List = new ArrayList<>();
    Random r1 = new Random();
    int randomCol = r1.nextInt(maxColumn);
    int randomRow = r1.nextInt(maxRow);

    public Panel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setLayout(new GridLayout(maxRow, maxColumn));
        this.addKeyListener(new KeyHandler(this));
        this.setFocusable(true);
        this.requestFocus();
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
        setStartingNode(3, 3);
        setGoalNode(11, 11);
        for (int i = 0; i < 100; i++) {
            setWall(getRandomWallCoor(18, 2), getRandomWallCoor(15, 3));
        }
    }

    public static int getRandomWallCoor(int maximum, int minimum) {
        return ((int) (Math.random() * (maximum - minimum))) + minimum;
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
        if (node != startingNode && node != goalNode) node.setText("G: " + node.gCost);
    }

    public void pathSearching() {
        while (!foundGoal && isContinue) {
            int column = currentNode.getColumn();
            int row = currentNode.getRow();
            currentNode.setChecked();
            openNode_List.remove(currentNode);
            if (row - 1 >= 0) evaluateNode(position[column][row - 1]);              // OPEN THE UPPER NODE
            if (column - 1 >= 0) evaluateNode(position[column - 1][row]);          //  OPEN THE LEFT NODE
            if (row + 1 < maxColumn) evaluateNode(position[column][row + 1]);     //   OPEN THE LOWER NODE
            if (column + 1 < maxColumn) evaluateNode(position[column + 1][row]); //    OPEN THE RIGHT NODE
            // FIND THE OPTIMAL NODE //
            int bestNodeIndex = 0;
            int bestNodefCOst = Integer.MAX_VALUE;
            for (int i = 0; i < openNode_List.size(); i++) {
                if (openNode_List.get(i).fCost < bestNodefCOst) {
                    bestNodeIndex = i;
                    bestNodefCOst = openNode_List.get(i).fCost;
                } else if (openNode_List.get(i).fCost == bestNodefCOst) {
                    if (openNode_List.get(i).gCost < currentNode.gCost) {
                        bestNodeIndex = i;
                    }
                }
            }
            currentNode = openNode_List.get(bestNodeIndex);
            if (currentNode == goalNode) {
                foundGoal = true;
                isContinue = false;
                pathTracking();
            }
        }
    }

    private void evaluateNode(Node node) {
        if (!node.isWall() && !node.isChecked() && !node.isOpen()) {
            node.isOpen();
            node.parentNode = currentNode;
            openNode_List.add(node);
        }
    }

    private void pathTracking() {
        Node current = goalNode;
        while (current != startingNode) {
            current = current.parentNode;
            if (current != startingNode) {
                current.isPath();
            }
        }
    }
}