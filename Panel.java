import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;


public class Panel extends JPanel {
// Constants to define the size and dimensions of the grid and its nodes
public static final int maxColumn = 25;
public static final int maxRow = 25;
public static final int nodeSize = 40;
public static final int screenWidth = nodeSize * maxColumn;
public static final int screenHeight = nodeSize * maxRow;

// Boolean flag to track whether the goal node has been found and whether the pathfinding algorithm should continue
boolean foundGoal = false;
boolean isContinue = true;

// 2D array to store the Node objects representing the grid
Node[][] position = new Node[maxColumn][maxRow];

// Variables to store references to the STARTING and GOAL node and the CURRENT node being evaluated
Node startingNode;
Node goalNode;
Node currentNode;

// AN ArrayList to store the Nodes that are currently open during the algorithm
ArrayList<Node> openNode_List = new ArrayList<>();

// Construct a random number generator
Random r1 = new Random();
int randomCol = r1.nextInt(maxColumn);
int randomRow = r1.nextInt(maxRow);

// A block counter, count steps taken to bring from START to GOAL
int stepTaken = 0;

// Construct JPanel components
JButton regenerateButton;
JButton pathfindingButton;
JButton quitButton;
JPanel buttonPanel;

public Panel() {
    this.setPreferredSize(new Dimension(screenWidth, screenHeight));
    this.setBackground(Color.BLACK);
    this.setLayout(new BorderLayout());
    // Add grid panel
    JPanel gridPanel = new JPanel();
    gridPanel.setLayout(new GridLayout(maxRow, maxColumn));
    this.add(gridPanel, BorderLayout.CENTER);
    //
    int column = 0;
    int row = 0;
    while (row < maxRow && column < maxColumn) {
        position[column][row] = new Node(column, row);
        gridPanel.add(position[column][row]);
        column++;
        if (column == maxColumn) {
            column = 0;
            row++;
        }
    }
    setStartingNode(3, 3);
    setGoalNode(randomCol, randomRow);
    for (int i = 0; i < 100; i++) {
        setWall(getRandomCoordinate(25, 1), getRandomCoordinate(25, 1));
        setWall(getRandomCoordinate(20, 2), getRandomCoordinate(20, 2));
    }

    // Add button panel
    buttonPanel = new JPanel();
    buttonPanel.setLayout(new FlowLayout());
    buttonPanel.setPreferredSize(new Dimension(screenWidth, 45));
    this.add(buttonPanel, BorderLayout.SOUTH);
    // Add GENERATE button
    regenerateButton = new JButton("Generate Board");
    regenerateButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            regenerateBoard();
        }
    });
    buttonPanel.add(regenerateButton);
    // Add FIND PATH button
    pathfindingButton = new JButton("Find Path");
    pathfindingButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            pathSearching();
        }
    });
    buttonPanel.add(pathfindingButton);
    // Create QUIT button
    quitButton = new JButton("Quit");
    quitButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            int choice = JOptionPane.showConfirmDialog(Panel.this, "Are you sure you want to quit?", "Quit", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        }
    });
    buttonPanel.add(quitButton);
    // Set the focus on the current panel
    this.setFocusable(true);
    this.requestFocus();
}

/**
 * Resets the game board to its primitive state, clearing all nodes and regenerating the starting node, goal node,
 * and wall nodes in new positions.
 * This method is called when the "Regenerate" button is clicked in the GUI.
 */
public void regenerateBoard() {
    // Reset the board to its primitive type
    foundGoal = false;
    isContinue = true;
    startingNode = null;
    goalNode = null;
    currentNode = null;
    // Clear all nodes
    openNode_List.clear();
    for (int column = 0; column < maxColumn; column++) {
        for (int row = 0; row < maxRow; row++) {
            position[column][row].reset();
        }
    }
    randomCol = r1.nextInt(maxColumn);
    randomRow = r1.nextInt(maxRow);
    // Set STARTING node
    setStartingNode(3, 3);
    // Set GOAL node
    setGoalNode(randomCol, randomRow);
    // Set WALL nodes
    for (int i = 0; i < 100; i++) {
        setWall(getRandomCoordinate(25, 1), getRandomCoordinate(25, 1));
        setWall(getRandomCoordinate(20, 2), getRandomCoordinate(20, 2));
    }
}

/**
 *
 * Generate random number from range between min and max
 *
 * @param minimum the smallest value
 * @param maximum the greatest value
 * @return random value in the range of minimum and maximum
 *
 */
public static int getRandomCoordinate(int maximum, int minimum) {
    return ((int) (Math.random() * (maximum - minimum))) + minimum;
}

/**
 * Sets the Node at the specified column and row coordinates as the starting Node of the game board.
 *
 * @param column the column coordinate of the starting Node
 * @param row the row coordinate of the starting Node
 *
 */
private void setStartingNode(int column, int row) {
    // Locate the starting node on the game board and update the startingNode and currentNode variables
    position[column][row].locateStart();
    startingNode = position[column][row];
    // Initially, the starting Node is the current Node since we start the path searching from the starting Node
    currentNode = startingNode;

}

/**
 * Sets the Node at the specified column and row coordinates as the goal Node of the game board.
 *
 * @param column the column coordinate of the goal Node
 * @param row the row coordinate of the goal Node
 *
 */
private void setGoalNode(int column, int row) {
    // Locate the goal node on the game board
    position[column][row].locateGoal();
    goalNode = position[column][row];
}

/**
 * Sets the Node at the specified column and row coordinates as the wall Node of the game board.
 *
 * @param column the column coordinate of the goal Node
 * @param row the row coordinate of the goal Node
 *
 */
private void setWall(int column, int row) {
    // Locate the wall node on the game board
    position[column][row].locateWall();
}

/**
 * Calculates the G, H, and F costs of the specified node in the A* pathfinding algorithm and displays the G cost on
 * the node for debugging purposes.
 *
 * @param node the node for which to calculate the costs
 */
private void getCost(Node node) {
    // Calculate the G-Cost: The distance from the STARTING node
    int dx = Math.abs(node.getColumn() - startingNode.getColumn());  // Horizontal Distance from the current to start
    int dy = Math.abs(node.getRow() - startingNode.getRow());       //  Vertical Distance from the current to start
    node.gCost = dx + dy;                                          //   G-Cost: Sum of horizontal and vertical cost

    // Calculate the H-Cost: The distance from the GOAL node
    dx = Math.abs(node.getColumn() - goalNode.getColumn());        //  Horizontal Distance from the current to goal
    dy = Math.abs(node.getRow() - goalNode.getRow());             //   Vertical Distance from the current to goal
    node.hCost = dx + dy;                                        //    H-Cost: Sum of horizontal and vertical cost

    // Calculate the F-Cost: the total cost of the G and H costs
    node.fCost = node.gCost + node.hCost;
}


public void pathSearching() {
    long startTime = System.currentTimeMillis(); // STARTING TIME
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
    long endTime = System.currentTimeMillis(); // END TIME
    double elapsedTimeInSeconds = (endTime - startTime) / 1000.0; // convert to seconds
    JOptionPane.showMessageDialog(this, "Path is found in " + elapsedTimeInSeconds + " second(s)");
}

/**
 * Evaluate the specified Node in the A* pathfinding algorithm.
 * If the node is not a wall, has not been checked before and is not yet in the open node list,
 * It is added to the open node list for further evaluation and marked as open.
 *
 * @param node the node to be evaluated
 */
private void evaluateNode(Node node) {
    // Check if the node is not a wall, has not been checked before, and is not already in the open node list
    if (!node.isWall() && !node.isChecked() && !node.isOpen()) {
        // Set the node as opened, set its parent node to the current node, and add it to the open node list
        node.setOpened();
        node.parentNode = currentNode;
        openNode_List.add(node);
    }
}


/**
 * Tracks the shortest path from the GOAL node to the STARTING node and marks each node in the path as part of the path.
 * This method starts from the goal node and follows the parent nodes back to the starting node, marking each Node as
 * part of the path and incrementing the stepTaken variable.
 * The path is marked with a green background color.
 */
private void pathTracking() {
    // Start at the goal node and follow the parent nodes back to the starting node
    Node current = goalNode;
    while (current != startingNode) {
        current = current.parentNode;
        // If the current node is not the starting node, mark it as part of the path and increment the stepTaken variable
        if (current != startingNode) {
            current.drawPath();
            stepTaken++;
            // Print the stepTaken variable to the console for debugging purposes
            System.out.println(stepTaken);
        }
    }
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
}