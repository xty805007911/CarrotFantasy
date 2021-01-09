package cn.tedu.background;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * 
 * @author 90672
 *
 */
public class Path extends BackGround{
	//point of start,end,transition
	public static final int[][] SPECAL_POINT= {
			{90,115},{90,370},{335,370},{335,279},{575,279},{575,370},{817,370},{800,100},{817,110}
	};
	private static BufferedImage image;
	private static BufferedImage start;
	static {
		try {
			image = ImageIO.read(Path.class.getResource("../png/Path.png"));
			start = ImageIO.read(Path.class.getResource("../png/start01.png"));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public Path() {
		super(120);
	}
	
	public void paint(Graphics g){
		g.drawImage(image,x,y,null);
		g.drawImage(start, SPECAL_POINT[0][0],SPECAL_POINT[0][1],null);
	}
	
	
}
