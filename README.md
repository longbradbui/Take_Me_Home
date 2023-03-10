#  Take_Me_Home
## Motivation
As a student majors in Computer Science,
I always want to make something that can help us to visualize how the algorithms actually run.
Taking a chance, I am making a program that **_visualizes the closest path from point A to point B_**.
Shall we just name it **Take Me Home**: The **_blue dot represents users_**,
and **_the red dot represents our destination_**.
Also, the path to destination will be added with some blocks of walls to increase the intensity.
The program's goal is to determine the closest path between us and the destination (with the most efficient time).
## Build Status
Up to date, the program is finished with the foundation and necessary features
(ie: Finding path, Board shuffling, Counting time, Quit and Restart).
Further features will be updated when announced.
## Code Style 
The project implements Object-Oriented Programming from CS143 with separation of three classes with its own functionality:
* Panel.java: In charge of the Panel board and algorithm movements. 
* Node.java: In charge of the cell behaviours. 
* Main.java: Main of the program.
## Technology and Framework
For the User Interface, I used Java Built-in Library Java Swing. For Unit Test and Method, I used with JUnit Framwork.
## Project Demonstration [SCREENSHOT]
![Screenshot 2023-03-09 203634](https://user-images.githubusercontent.com/86465921/224225212-d4fa6e3f-3955-4f62-8eda-aa00a08a2efe.png)
![Screenshot 2023-03-09 203621](https://user-images.githubusercontent.com/86465921/224225219-74145eda-cf09-4014-98fc-853a0d715892.png)
## Project Logic Run
First of all,
I will thoroughly explain what A* algorithm does and how it is used to determine the shortest path between two points.

<b><i>PHASE 1: Computing distance between Nodes.</i></b>
* Suppose that we are standing at a start and need to go to a destination. How can we "get home" with the most efficient moves?
* We simply assign Starting Node as the position we are standing and Goal Node as our destination.
* An open list to store our available movements.
* Now, shall we dive in? 
* Initially, we will start the process with the starting Node. Initialize the open list an and add the starting Node to the open list.
* Quickly, I will go over what **_G_**, **_H_** and **_F_** values mean:
> G is the distance between the current node and the starting node.
<br></br>
> H is the distance between the current node and the goal node.
<br></br>
> F is the total cost of the node (G + H).

* From the starting Node, we will evaluate the Nodes that associate with 4 directions of it (**_North_**, **_South_**, **_East_**, **_West_**).
* As we evaluate the Nodes, we also store those in a memory to retrieve the best movement.
* Of course, each Node carries for themselves an associated value (H, G and F). 
* Now, is the comparison part: Traverse in the open List in order to determine which node has the best F-Cost value.
* [ **_What if they have the same F value?_** ] -> We will move on to compare their G-Value, which has a smaller value will be preferred to the other one.
* If the most ideal Node has been picked, we will assign it as the current Node to go further.
* Also, we will set the oldCurrentNode as the parent of the newCurrentNode. 
* Repeat the process until we reach our destination.
* Then move on to Phase 2.

<b><i>PHASE 2: Backtracking from the destination to the starting point.</i></b>
* As we have reached here, all available Nodes have been evaluated and also reached the goal.
* As we mention **_parents and child in Phase 1_**, now we are backtracking.
* The goal Node is now our child. Our task is to back track from there, determine its parent Node.
* From that parent Node, we assign it to the current Node again and then do another backtracking to find its parent Node.
* Repeat the process until we reach our starting position.

<b><i>BOOM, we have found the shortest path to bring us home!!!</i></b>
## Data Structure and Programming Technique
The project covers most of the Abstract Data Type taught in CS143:
* List: An ArrayList to store the Opened Nodes from the grid, awaits for manipulation.
* Array: A 2-D array that represents grid with **_column_** and **_row_** coordinates.
* Node: The relationship of **_parentNode_** and **_currentNode_** in tracing the path.
* Boolean flag: A boolean flag to keep track the status of each Node.
* Recursion: Recursive call on evaluating Nodes
* Backtracking: Backtrack from goalNode to startingNode to determine the best Node with smallest G-cost.
## Unit Test
![Unit Test](https://user-images.githubusercontent.com/86465921/224225691-04b07130-257a-4c94-8871-f00d167204f1.png)
## Installation
    git clone https://github.com/cs-olympic/finalcs2-longbradbui.git
## Challenges 
I faced some challenges working on this project:
* Java GUI: My first project using Java Swing Library,
  I have spent a good amount of time reading the documentation
  and playing around with the layout and size preference for the panel.
  Implementing the buttons
  that trigger the function was time-consuming and hard [I keep getting error or it doesn't do anything].
  Eventually, I got to go to YouTube and watch some tutorials to get it to work.
* The algorithm: A* algorithm is brand-new to me.
  My implementation of it might not be ideal or efficient as it needs to be.
  However, I have tried my best to get it to work, and I would definitely come back for an update. 
## Credits
* [Java Swing documentation from ORACLE ](https://docs.oracle.com/javase/7/docs/api/javax/swing/package-summary.html)
* [Java Swing Sample Code](https://www.geeksforgeeks.org/introduction-to-java-swing/)
* [A* Algorithm Explanation](https://brilliant.org/wiki/a-star-search/) 
* [A* Algorithm Demonstration](https://medium.com/@nicholas.w.swift/easy-a-star-pathfinding-7e6689c7f7b2)
## Author 
Hoang Long Bui
