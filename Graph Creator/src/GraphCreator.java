import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class GraphCreator implements ActionListener, MouseListener {

	JFrame frame = new JFrame("Graph Creator");
	GraphPanel panel = new GraphPanel();
	JButton nodeB = new JButton("Node");
	JButton edgeB = new JButton("Edge");
	JTextField labelsTF = new JTextField("A");
	JTextField firstNode = new JTextField("First");
	JTextField secondNode = new JTextField("Second");
	JButton connectedB = new JButton("Test Connected");
	Container west = new Container();
	Container east = new Container();
	Container south = new Container();
	JTextField salesmanStartTF = new JTextField("A");
	JButton salesmanB = new JButton("Shortest Path");
	final int NODE_CREATE = 0;
	final int EDGE_FIRST = 1;
	final int EDGE_SECOND = 2;
	int state = NODE_CREATE;
	Node first = null;
	char nodeLabel = 65;
	boolean works = false;
	ArrayList<ArrayList<Node>> completed = new ArrayList<ArrayList<Node>>();
	ArrayList<Integer> totalNumber = new ArrayList<Integer>();
	ArrayList<Node> check = new ArrayList<Node>();
	ArrayList<String> connectedList = new ArrayList<String>();

	public GraphCreator() {
		//setting up the GUI
		frame.setSize(800, 600);
		frame.setLayout(new BorderLayout());
		frame.add(panel, BorderLayout.CENTER);
		west.setLayout(new GridLayout(3, 1));
		west.add(nodeB);
		nodeB.addActionListener(this);
		nodeB.setBackground(Color.GREEN);
		west.add(edgeB);
		edgeB.addActionListener(this);
		edgeB.setBackground(Color.LIGHT_GRAY);
		west.add(labelsTF);
		frame.add(west, BorderLayout.WEST);
		east.setLayout(new GridLayout(3, 1));
		east.add(firstNode);
		east.add(secondNode);
		east.add(connectedB);
		connectedB.addActionListener(this);
		frame.add(east, BorderLayout.EAST);
		panel.addMouseListener(this);
		south.setLayout(new GridLayout(1, 2));
		south.add(salesmanStartTF);
		south.add(salesmanB);
		salesmanB.addActionListener(this);
		frame.add(south, BorderLayout.SOUTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		//make the label based on a variable rather than a constant
		labelsTF.setText("" + nodeLabel);
	}

	public static void main(String[] args) {
		new GraphCreator();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		System.out.println(e.getX() + "," + e.getY());
		if (state == NODE_CREATE) {
			//adding a node
			panel.addNode(e.getX(), e.getY(), labelsTF.getText());
			//make the label increase one letter until it reaches z, then restart
			if (nodeLabel < 90) {
				nodeLabel++;
			}
			else {
				nodeLabel = 65;
			}
			labelsTF.setText("" + nodeLabel);
		} else if (state == EDGE_FIRST) {
			//starting the edge
			Node n = panel.getNode(e.getX(), e.getY());
			if (n != null) {
				first = n;
				state = EDGE_SECOND;
				n.setHighlighted(true);
			}
		} else if (state == EDGE_SECOND) {
			//connecting the edge to the second node
			Node n = panel.getNode(e.getX(), e.getY());
			if (n != null && !first.equals(n)) {
				String s = labelsTF.getText();
				boolean valid = true;
				for (int a = 0; a < s.length(); a++) {
					if (!Character.isDigit(s.charAt(a))) {
						valid = false;
					}
				}
				if (valid == true) {
					first.setHighlighted(false);
					panel.addEdge(first, n, labelsTF.getText());
					first = null;
					state = EDGE_FIRST;
				} else {
					JOptionPane.showMessageDialog(frame, "You can only have digits in edge labels.");
				}
			}
		}
		frame.repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(nodeB)) {
			//selecting node button
			System.out.println("Node!");
			nodeB.setBackground(Color.GREEN);
			edgeB.setBackground(Color.LIGHT_GRAY);
			state = NODE_CREATE;
		}
		if (e.getSource().equals(edgeB)) {
			//selecting edge button
			System.out.println("Edge!");
			edgeB.setBackground(Color.GREEN);
			nodeB.setBackground(Color.LIGHT_GRAY);
			state = EDGE_FIRST;
			panel.stopHighlighting();
			frame.repaint();
		}
		if (e.getSource().equals(connectedB)) {
			//checking if two nodes are connected
			if (!panel.nodeExists(firstNode.getText())) {
				JOptionPane.showMessageDialog(frame, "First node is not in your graph.");
			} else if (!panel.nodeExists(secondNode.getText())) {
				JOptionPane.showMessageDialog(frame, "Second node is not in your graph.");
			} else {
				//Queue is adding things and then pulling them out in the order you added them
				Queue queue = new Queue();
				connectedList.add(panel.getNode(firstNode.getText()).getLabel());
				ArrayList<String> edges = panel.getConnectedLabels(firstNode.getText());
				for (int a = 0; a < edges.size(); a++) {
					queue.enqueue(edges.get(a));
				}
				while (!queue.isEmpty()) {
					String currentNode = queue.dequeue();
					if (!connectedList.contains(currentNode)) {
						connectedList.add(currentNode);
					}
					edges = panel.getConnectedLabels(currentNode);
					for (int a = 0; a < edges.size(); a++) {
						if (!connectedList.contains(edges.get(a))) {
							queue.enqueue(edges.get(a));
						}
					}
				}
				if (connectedList.contains(secondNode.getText())) {
					JOptionPane.showMessageDialog(frame, "Connected!");
				} else {
					JOptionPane.showMessageDialog(frame, "Not Connected.");
				}
			}
		}
		if (e.getSource().equals(salesmanB)) {
			//removing nodes that aren't connected to the starting node
			for (int i = 0; i < panel.nodeList.size(); i++) {
				check.add(panel.nodeList.get(i));
			}
			Queue queue = new Queue();
			for (int j = 0; j < panel.nodeList.size(); j++) {
				connectedList.add(panel.getNode(salesmanStartTF.getText()).getLabel());
				ArrayList<String> edges = panel.getConnectedLabels(salesmanStartTF.getText());
				for (int i = 0; i < edges.size(); i++) {
					queue.enqueue(edges.get(i));
				}
				while (queue.isEmpty() == false) {
					String currentNode = queue.dequeue();
					if (!connectedList.contains(currentNode)) {
						connectedList.add(currentNode);
					}
					edges = panel.getConnectedLabels(currentNode);
					for (int i = 0; i < edges.size(); i++) {
						//removing nodes that aren't connected
						if (!connectedList.contains(edges.get(i))) {
							queue.enqueue(edges.get(i));
						}
					}
				}
				if (!connectedList.contains(panel.nodeList.get(j).getLabel())) {
					check.remove(panel.nodeList.get(j));
				}
			}
			//if any nodes aren't connected then it's impossible to reach all of the nodes
			if (!(check.size() == panel.nodeList.size())) {
				JOptionPane.showMessageDialog(frame, "Not all the nodes can be reached.");
				check.clear();
				return;
			}
			if (!(panel.getNode(salesmanStartTF.getText()) == null)) {
				travelling(panel.getNode(salesmanStartTF.getText()), new ArrayList<Node>(), 0);
				//if not all the nodes can be reached
				if (works == false) {
					JOptionPane.showMessageDialog(frame, "Not all the nodes can be reached.");
					check.clear();
					return;
				}
				//lowest cost path
				if (totalNumber.size() > 0) {
					int low = totalNumber.get(0);
					int lowIndex = 0;
					String totalNodes = "";
					for (int i = 0; i < totalNumber.size(); i++) {
						if (totalNumber.get(i) < low) {
							lowIndex = i;
							low = totalNumber.get(i);
						}
					}
					for (int i = 0; i < completed.get(lowIndex).size(); i++) {
						totalNodes = totalNodes + completed.get(lowIndex).get(i).getLabel() + ", ";
					}
					StringBuilder allNodes = new StringBuilder(totalNodes);
					allNodes.deleteCharAt(totalNodes.length() - 1);
					allNodes.deleteCharAt(totalNodes.length() - 2);
					//printing path and labels
					JOptionPane.showMessageDialog(frame, "The lowest cost is " + low + " dollars using the nodes: " + allNodes.toString() + ".");
					totalNumber.clear();
					completed.clear();
				}
			} else {
				JOptionPane.showMessageDialog(frame, "You are not starting on an existing node.");
			}
		}
	}

	//finding all the paths from the starting nodes
	public void travelling(Node n, ArrayList<Node> path, int total) {
		path.add(n);
		if (path.size() == check.size()) {
			works = true;
			ArrayList<Node> complete = new ArrayList<Node>();
			for (int i = 0; i < path.size(); i++) {
				complete.add(path.get(i));
			}
			completed.add(complete);
			totalNumber.add(total);
			if (path.size() > 0) {
				path.remove(path.size() - 1);
			}
			check.clear();
			return;
		} else {
			for (int a = 0; a < panel.edgeList.size(); a++) {
				Edge e = panel.edgeList.get(a);
				if (e.getOtherEnd(n) != null) {
					if (path.contains(e.getOtherEnd(n)) == false) {
						travelling(e.getOtherEnd(n), path, total + Integer.parseInt(e.getLabel()));
					}
				}
			}
			if (path.size() > 0) {
				path.remove(path.size() - 1);
			}

		}
	}

	/*
	 * Adjacency Matrix
	 * 
	 * A B C A 1 1 1 B 1 1 0 C 1 0 1
	 * 
	 */

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}
}
