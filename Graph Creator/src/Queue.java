import java.util.ArrayList;

public class Queue {
	
	ArrayList<String> queue = new ArrayList<String>();
	
	public void enqueue(String s) {
		//add to queue
		queue.add(s);
	}
	
	public String dequeue() {
		//pull out the first item in the queue
		String s = queue.get(0);
		queue.remove(0);
		return s;		
	}
	
	public boolean isEmpty() {
		//if the queue is empty then return that
		return queue.isEmpty();
	}
	
}
