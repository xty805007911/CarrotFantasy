package cn.tedu.background;
/**
 * BackGround
 * @author 90672
 *
 */

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BG extends BackGround{
	private static BufferedImage image;
	static {
		try {
			image = ImageIO.read(BG.class.getResource("../png/BG1.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public BG() {
		super();
	}
	
	public void paint(Graphics g){
		g.drawImage(image,x,y,null);
	}
	
}
