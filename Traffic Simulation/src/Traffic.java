import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Traffic implements ActionListener, Runnable {

	JFrame frame = new JFrame("Traffic Simulation");
	Road road = new Road();
	//South Container
	JButton start = new JButton("Start");
	JButton stop = new JButton("Stop");
	JButton faster = new JButton("Speed Up");
	JButton slower = new JButton("Slow Down");
	JLabel throughput = new JLabel("  Throughput: 0.0");
	Container south = new Container();
	//West Container
	JButton semi = new JButton("Add Semi");
	JButton suv = new JButton("Add SUV");
	JButton sports = new JButton("Add Sports");
	JButton remove = new JButton("Remove All");
	Container west = new Container();
	int carCount = 0;
	long startTime = 0;
	int thread = 100;
	
	boolean running = false;
	
	public Traffic() {
		frame.setSize(1000, 550);
		frame.setLayout(new BorderLayout());
		frame.add(road, BorderLayout.CENTER);
		
		south.setLayout(new GridLayout(1,3));
		south.add(start);
		start.addActionListener(this);
		south.add(stop);
		stop.addActionListener(this);
		south.add(faster);
		faster.addActionListener(this);
		south.add(slower);
		slower.addActionListener(this);
		south.add(throughput);
		frame.add(south, BorderLayout.SOUTH);
		
		west.setLayout(new GridLayout(4, 1));
		west.add(semi);
		semi.addActionListener(this);
		west.add(suv);
		suv.addActionListener(this);
		west.add(sports);
		sports.addActionListener(this);
		west.add(remove);
		remove.addActionListener(this);
		frame.add(west,  BorderLayout.WEST);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		frame.repaint();
	}
	
	public static void main(String[] args) {
		new Traffic();
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource().equals(start)) {
			if (running == false) {
				running = true;
				road.resetCarCount();
				startTime = System.currentTimeMillis();
				Thread t = new Thread(this);
				t.start();
			}
		}
		if (event.getSource().equals(stop)) {
			running = false;
		}
		if (event.getSource().equals(remove)) {
			road.cars = new ArrayList<Vehicle>();
			frame.repaint();
		}
		if (event.getSource().equals(semi)) {
			Semi semi = new Semi(0, 30);
			road.addCar(semi);
			for (int x = 0; x < road.ROAD_WIDTH; x += 20) {
				for (int y = 40; y < 480; y += road.LANE_HEIGHT) {
					semi.setX(x);
					semi.setY(y);
					if (!road.collision(x, y, semi)) {
						frame.repaint();
						return;
					}
				}
			}
		}
		if (event.getSource().equals(suv)) {
			SUV suv = new SUV(0, 30);
			road.addCar(suv);
			for (int x = 0; x < road.ROAD_WIDTH; x += 20) {
				for (int y = 40; y < 480; y += road.LANE_HEIGHT) {
					suv.setX(x);
					suv.setY(y);
					if (!road.collision(x, y, suv)) {
						frame.repaint();
						return;
					}
				}
			}
		}
		if (event.getSource().equals(sports)) {
			Sports sports = new Sports(0, 30);
			road.addCar(sports);
			for (int x = 0; x < road.ROAD_WIDTH; x += 20) {
				for (int y = 40; y < 480; y += road.LANE_HEIGHT) {
					sports.setX(x);
					sports.setY(y);
					if (!road.collision(x, y, sports)) {
						frame.repaint();
						return;
					}
				}
			}
		}
		if (event.getSource().equals(faster)) {
			if (thread >= 10) {
				thread -= 5;
			}
		}
		if (event.getSource().equals(slower)) {
			if (thread <= 200) {
				thread += 5;
			}
		}
	}

	@Override
	public void run() {
		while (running == true) {
			road.step();
			carCount = road.getCarCount();
			double throughputCalc = carCount / (1000 * (double)(System.currentTimeMillis() - startTime));
			throughput.setText("  Throughput: " + throughputCalc);
			frame.repaint();
			try {
				Thread.sleep(thread);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	
	
 
}
