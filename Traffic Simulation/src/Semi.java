import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Semi extends Vehicle {

	Image myimage;
	
	public Semi(int newx, int newy) {
		super(newx, newy);
		width = 200;
		height = 80;
		speed = 5;
		acceleration = 1;
		try {
			myimage = ImageIO.read(new File("Semi.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void paintMe(Graphics g) {
		/*g.setColor(Color.BLUE);
		g.fillRect(x, y, width, height);*/
		g.drawImage(myimage, x, y - 20, width, height, null);
	}
	
}
