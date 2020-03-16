import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Sports extends Vehicle {
	
	Image myimage;
	
	public Sports(int newx, int newy) {
		super(newx, newy); 
		width = 80;
		height = 40;
		speed = 12;
		acceleration = 4;
		try {
			myimage = ImageIO.read(new File("Sports.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void paintMe(Graphics g) {
		/*g.setColor(Color.RED);
		g.fillRect(x, y, width, height);*/
		g.drawImage(myimage, x, y, width, height, null);
	}
}
