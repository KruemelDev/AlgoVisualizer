package com.kruemel.algoVisualizer.algo.pathfinding;

import com.kruemel.algoVisualizer.algo.Algorithm;
import com.kruemel.algoVisualizer.algo.Util;
import com.kruemel.algoVisualizer.main.MainPanel;

import java.awt.*;
import java.util.ArrayList;

public class AStar implements Algorithm {
    Board board;
    MainPanel mp;
    Util util;
    Node startNode, currentNode, goalNode;
    ArrayList<Node> openList = new ArrayList<>();
    ArrayList<Node> pathList = new ArrayList<>();
    public boolean running = false;
    public AStar(MainPanel mp){
        this.mp = mp;
        board = new Board(16, 9, mp, this);
        util = new Util(mp);
        board.placeNodes();

        setStartNode(2, 5);
        setGoalNode(12, 2);

        calcCosts();
    }

    public void setStartNode(int col, int row){
        board.nodes[row][col].setStartNode();
        startNode = board.nodes[row][col];
        currentNode = startNode;
        openList.add(startNode);
    }
    public void setGoalNode(int col, int row){
        board.nodes[row][col].setGoalNode();
        goalNode = board.nodes[row][col];
    }

    private void calcCosts(){
        for (int row = 0; row < board.maxRow; row++) {
            for (int col = 0; col < board.maxCol; col++) {
                Node node = board.nodes[row][col];
                getCost(node);
            }
        }
    }
    private void getCost(Node node){
        node.gCost = getGCost(node);
        node.hCost = getHCost(node);
        node.fCost = getFCost(node);
        if (node == startNode || node == goalNode) return;
        node.showCost();
    }

    private int getFCost(Node node){
        return node.gCost + node.hCost;
    }
    private int getHCost(Node node){
        return Math.abs(node.row - goalNode.row) + Math.abs(node.col - goalNode.col);
    }
    private int getGCost(Node node){
        return Math.abs(node.row - startNode.row) + Math.abs(node.col - startNode.col);
    }

    @Override
    public void Step() {

    }

    @Override
    public void Run() {
        while(currentNode != goalNode){
            int row = currentNode.row;
            int col = currentNode.col;

            currentNode.checked = true;
            openList.remove(currentNode);
            if (row - 1 >= 0){
                openNode(board.nodes[row - 1][col]);
            }
            if (row + 1 < board.maxRow){
                openNode(board.nodes[row + 1][col]);
            }
            if (col - 1 >= 0){
                openNode(board.nodes[row][col - 1]);
            }
            if (col + 1 < board.maxCol){
                openNode(board.nodes[row][col + 1]);
            }
            try {
                Thread.sleep(0);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            drawOpenList();
            handleNextNode();
        }
        trackPath();
    }

    private void drawOpenList(){
        for (int row = 0; row < board.maxRow; row++) {
            for (int col = 0; col < board.maxCol; col++){
                if (board.nodes[row][col].checked) board.nodes[row][col].setBackground(Color.YELLOW);
            }
        }
    }
    private void trackPath(){
        Node current = currentNode;
        while (current != startNode){
            current = current.parent;
            pathList.addFirst(current);
        }
        for (Node node : pathList) {
            node.setPathNode();
        }
    }

    private void handleNextNode(){
        int bestNodeIndex = -1;
        int bestFCost = Integer.MAX_VALUE;
        for (int i = 0; i < openList.size(); i++){
            Node node = openList.get(i);
            if (node.fCost < bestFCost){
                bestNodeIndex = i;
                bestFCost = node.fCost;
            } else if (node.fCost == bestFCost){
                if (node.gCost < openList.get(bestNodeIndex).gCost){
                    bestNodeIndex = i;
                }
            }
        }
        if (openList.isEmpty()) return;
        System.out.println("Best Node: " + bestNodeIndex);
        currentNode = openList.get(bestNodeIndex);
    }

    private void openNode(Node node){
        if (node == null || node.checked || node.solid) return;
        node.parent = currentNode;
        openList.add(node);
    }
    @Override
    public void Reset(){
        this.mp.remove(board);
        this.running = false;
    }
    @Override
    public void Paint(Graphics2D g2d) {
        util.DrawTitle(g2d, "A* Pathfinding: ");

    }
}
