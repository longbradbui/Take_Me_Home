import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Node extends JButton {
Node parentNode;
int column;  // Column variable
int row;    //  Row variable
int gCost;  //  G-cost variable
int hCost;  //  H-Cost variable
int fCost;  //  F-Cost variable
boolean startingNode;    // Boolean flag to check if the Node is a STARTING POINT
boolean goalNode;       //  Boolean flag to check if the Node is an ENDING POINT
boolean isOpen;        //   Boolean flag to check if the Node is in OPENED STATE
boolean isChecked;    //    Boolean flag to check if the Node is a CHECKED STATE
boolean isWall;      //     Boolean flag to check if the Node is a WALL

/**
 * A Node represents a point on a grid, identified by its column and row coordinates.
 * The Node is initialized with a white background and black foreground color.
 *
 * @param column the column coordinate of the Node
 * @param row the row coordinate of the Node
 */
public Node(int column, int row) {
    // Initialize the column and row coordinates of the Node
    this.column = column;
    this.row = row;
    // Set the background color of the Node to white
    setBackground(Color.WHITE);
    // Set the foreground color of the Node to black
    setForeground(Color.BLACK);
}

/**
 * Returns the column coordinate of Node.
 *
 * @return the column coordinate of this Node
 */
public int getColumn() {
    // Return the column coordinate of this Node
    return column;
}
/**
 * Returns the row coordinate of Node.
 *
 * @return the row coordinate of this Node
 */
public int getRow() {
    // Return the column coordinate of this Node
    return row;
}

/**
 * Changes the color of this Node to BLUE and marks it as the starting node.
 */
public void locateStart() {
    // Set the background color of this Node to blue
    setBackground(Color.BLUE);
    // Set the foreground color of this Node to white
    setForeground(Color.WHITE);
    // Mark this Node as the starting node, switch the boolean flag to true
    startingNode = true;
}
/**
 * Changes the color of this Node to RED and marks it as the goal node.
 */
public void locateGoal() {
    // Set the background color of this Node to red
    setBackground(Color.RED);
    // Set the foreground color of this Node to black
    setForeground(Color.BLACK);
    // Mark this Node as the starting node, switch the boolean flag to true
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

/**
 * Returns whether this Node is a wall or not.
 *
 * @return true if this Node is a wall, false otherwise
 */
public boolean isWall() {
    // Return the value of the isWall field
    return isWall;
}

/**
 * Returns whether this Node is CHECKED or not.
 *
 * @return true if this Node is CHECKED, false otherwise
 */
public boolean isChecked() {
    // Return the value of the isChecked field
    return isChecked;
}
/**
 * Returns whether this Node is OPENED or not.
 *
 * @return true if this Node is OPENED, false otherwise
 */
public boolean isOpen() {
    // Return the value of the isOpened field
    return isOpen;
}

/**
 * Changes the color of this Node to YELLOW and marks it as checked.
 * If the Node is the STARING or GOAL node, it will not be modified.
 */
public void setChecked() {
    // Check if the Node is not the starting or goal node
    if (!startingNode && !goalNode) {
        // Set the background color of this Node to yellow
        setBackground(Color.YELLOW);
        // Set the foreground color of this Node to black
        setForeground(Color.BLACK);
    }
    // Mark this Node as CHECKED
    isChecked = true;
}

/**
 *
 * Marks this Node as opened by updating the boolean flag
 *
 */
public void setOpened() {
    // Mark this Node as opened
    isOpen = true;
}


/**
 * Changes the color of the Node to GREEN with black text, marking it as part of the shortest path.
 * Backtracking from GOAL node to STARTING node
 */
public void drawPath() {
    // Set the background color of this Node to green
    setBackground(Color.GREEN);
    // Set the foreground color of this Node to black
    setForeground(Color.BLACK);
}


/**
 * Resets the status of this Node to its default state.
 * The Node's isChecked, isOpen, and isWall flags are set to false.
 * The parentNode reference is set to null.
 * The Node's background color is set to white.
 */
public void reset() {
    // Reset the isChecked, isOpen, and isWall flags to false
    isChecked = false;
    isOpen = false;
    isWall = false;
    // Set the parentNode reference to null
    parentNode = null;
    // Set the background color of the Node to white
    setBackground(Color.WHITE);
}
}
