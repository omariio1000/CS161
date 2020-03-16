import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SUV extends Vehicle {

	Image myimage;
	
	public SUV(int newx, int newy) {
		super(newx, newy);
		width = 120;
		height = 60;
		speed = 8;
		acceleration = 2;
		try {
			myimage = ImageIO.read(new File("SUV.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void paintMe(Graphics g) {
		/*g.setColor(Color.GREEN);
		g.fillRect(x, y, width, height);*/
		g.drawImage(myimage, x, y, width, height, null);
	}
}
