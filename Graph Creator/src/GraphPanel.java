import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GraphPanel extends JPanel {
	//arraylist for keeping track of all the nodes
	ArrayList<Node> nodeList = new ArrayList<Node>();
	//arraylist for keeping track of all the edges
	ArrayList<Edge> edgeList = new ArrayList<Edge>();
	//the radius of the nodes on the panel
	int circleRadius = 20;
	//an arraylist of arraylists of booleans that indicates whether nodes are connected or not
	ArrayList<ArrayList<Boolean>> adjacency = new ArrayList<ArrayList<Boolean>>();
	
	public GraphPanel() {
		super();
	}
	
	//arraylist for storing all the labels of of all nodes
	public ArrayList<String> getConnectedLabels(String label) {
		ArrayList<String> toReturn = new ArrayList<String>();
		int b = getIndex(label);
		for (int a = 0; a < adjacency.size(); a++) {
			 if (adjacency.get(b).get(a) && !nodeList.get(a).getLabel().equals(label)) {
				 toReturn.add(nodeList.get(a).getLabel());
			 }
		}
		return toReturn;
	}
	
	//printing the adjacency table
	public void printAdjacency() {
		for (int a = 0; a < adjacency.size(); a++) {
			for (int b = 0; b < adjacency.get(0).size(); b++) {
				System.out.print(adjacency.get(a).get(b) + "\t");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	//adding a node
	public void addNode(int newx, int newy, String newlabel) {
		nodeList.add(new Node(newx, newy, newlabel));
		adjacency.add(new ArrayList<Boolean>());
		for (int a = 0; a < adjacency.size() - 1; a++) {
			adjacency.get(a).add(false);
		}
		for (int a = 0; a < adjacency.size(); a++) {
			adjacency.get(adjacency.size() - 1).add(false);
		}
		printAdjacency();
	}
	
	//adding an edge
	public void addEdge(Node first, Node second, String newlabel) {
		edgeList.add(new Edge(first, second, newlabel));
		int firstIndex = 0;
		int secondIndex = 0;
		for (int a = 0; a < nodeList.size(); a++) {
			if (first.equals(nodeList.get(a))) {
				firstIndex = a;
			}
			if (second.equals(nodeList.get(a))) {
				secondIndex = a;
			}
		}
		adjacency.get(firstIndex).set(secondIndex, true);
		adjacency.get(secondIndex).set(firstIndex, true);
		printAdjacency();
	}
	
	//seeing which node the mouse release was in
	public Node getNode(int x, int y) {
		for (int a = 0; a < nodeList.size(); a++) {
			Node node = nodeList.get(a);
			//a squared plus b squared = c squared
			double radius = Math.sqrt(Math.pow(x - node.getX(), 2) + Math.pow(y - node.getY(), 2));
			if (radius < circleRadius) {
				return node;

			}
		}
		return null;
	}
	
	//getting the label of a node
	public Node getNode(String s) {
		for (int a = 0; a < nodeList.size(); a++) {
			Node node = nodeList.get(a);
			if (s.equals(node.getLabel())) {
				return node;
			}
		}
		return null;
	}
	
	//seeing what the index of the node is in the arraylist
	public int getIndex(String s) {
		for (int a = 0; a < nodeList.size(); a++) {
			Node node = nodeList.get(a);
			if (s.equals(node.getLabel())) {
				return a;
			}
		}
		return -1;
	}
	
	//indicating whether the node being asked for exists or not
	public boolean nodeExists(String s) {
		for (int a = 0; a < nodeList.size(); a++) {
			if (s.equals(nodeList.get(a).getLabel())) {
				return true;
			}
		}
		return false;
	}
	
	//painting the shapes onto the GUI
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		//draw my stuff
		for (int a = 0; a < nodeList.size(); a++) {
			g.setColor(Color.BLACK);
			//highlighting first node if the state is edge second
			if (nodeList.get(a).getHighlighted()) {
				g.setColor(Color.RED);
			}
			g.fillOval(nodeList.get(a).getX() - circleRadius, 
					   nodeList.get(a).getY() - circleRadius, 
					   circleRadius * 2, 
					   circleRadius * 2);
			g.setColor(Color.WHITE);
			g.drawString(nodeList.get(a).getLabel(), nodeList.get(a).getX() - 3, nodeList.get(a).getY() + 5);
		}
		for (int a = 0; a < edgeList.size(); a++) {
			g.setColor(Color.BLACK);
			g.drawLine(edgeList.get(a).getFirst().getX(), 
					   edgeList.get(a).getFirst().getY(), 
					   edgeList.get(a).getSecond().getX(), 
					   edgeList.get(a).getSecond().getY());
			g.setColor(Color.WHITE);
			g.drawString(edgeList.get(a).getFirst().getLabel(), edgeList.get(a).getFirst().getX() - 3, edgeList.get(a).getFirst().getY() + 5);
			g.drawString(edgeList.get(a).getSecond().getLabel(), edgeList.get(a).getSecond().getX() - 3, edgeList.get(a).getSecond().getY() + 5);
			g.setColor(Color.BLACK);
			int fx = edgeList.get(a).getFirst().getX();
			int fy = edgeList.get(a).getFirst().getY();
			int sx = edgeList.get(a).getSecond().getX();
			int sy = edgeList.get(a).getSecond().getY();
			g.drawString(edgeList.get(a).getLabel(), 
					Math.min(fx, sx) + (Math.abs(sx - fx) / 2), 
					Math.min(fy, sy) + (Math.abs(sy - fy) / 2));
		}
	}

	//stop the highlighting if edge second state is off
	public void stopHighlighting() {
		for (int a = 0; a < nodeList.size(); a++) {
			nodeList.get(a).setHighlighted(false);
		}
	}
}
