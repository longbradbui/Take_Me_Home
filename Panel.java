import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;


public class Panel extends JPanel {
    public static final int maxColumn = 25;
    public static final int maxRow = 25;
    public static final int nodeSize = 40;
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
    int stepTaken = 0;
    int randomCol = r1.nextInt(maxColumn);
    int randomRow = r1.nextInt(maxRow);
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
        // Add regenerate button
        regenerateButton = new JButton("Generate Board");
        regenerateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                regenerateBoard();
            }
        });
        buttonPanel.add(regenerateButton);
        // Add pathfinding button
        pathfindingButton = new JButton("Find Path");
        pathfindingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pathSearching();
            }
        });
        buttonPanel.add(pathfindingButton);
        // Create Quit button
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

    public void regenerateBoard() {
        foundGoal = false;
        isContinue = true;
        startingNode = null;
        goalNode = null;
        currentNode = null;
        openNode_List.clear();
        // Clear all nodes
        for (int column = 0; column < maxColumn; column++) {
            for (int row = 0; row < maxRow; row++) {
                position[column][row].reset();
            }
        }
        randomCol = r1.nextInt(maxColumn);
        randomRow = r1.nextInt(maxRow);
        // Set starting node
        setStartingNode(3, 3);
        // Set goal node
        setGoalNode(randomCol, randomRow);
        // Add walls
        for (int i = 0; i < 100; i++) {
            setWall(getRandomCoordinate(25, 1), getRandomCoordinate(25, 1));
            setWall(getRandomCoordinate(20, 2), getRandomCoordinate(20, 2));
        }
    }

    public static int getRandomCoordinate(int maximum, int minimum) {
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

    private void evaluateNode(Node node) {
        if (!node.isWall() && !node.isChecked() && !node.isOpen()) {
            node.setOpened();
            node.parentNode = currentNode;
            openNode_List.add(node);
        }
    }

    private void pathTracking() {
        Node current = goalNode;
        while (current != startingNode) {
            current = current.parentNode;
            if (current != startingNode) {
                current.drawPath();
                stepTaken++;
                System.out.println(stepTaken);
            }
        }
    }
}