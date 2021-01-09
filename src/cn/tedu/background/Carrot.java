package cn.tedu.background;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Carrot extends CuteObject{
	private static BufferedImage[] images=new BufferedImage[18];
	static {
		for(int i=0;i<images.length;i++) {
			try {
				images[i] = ImageIO.read(Path.class.getResource("../png/hlb"+i+".png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public Carrot() {
		super(101, 75, 20, 0, 0);
	}
	private double CarrotIndex=0;
	private int CarrotIndex1=0;
	@Override
	public BufferedImage getImage() {
		if(HP<=0) {
			return null;
		}
		if(HP==20) {
			CarrotIndex1++;
			if(CarrotIndex1%100>=0&&CarrotIndex1%100<50) {
				return images[7];
			}else {
				CarrotIndex+=0.2;
				return images[(int)CarrotIndex%9+7];
			}
		}
		int index=6;
		switch(HP) {
		case 19:
			index=6;
			break;
		case 18:
		case 17:
		case 16:
			index=5;
			break;
		case 15:
		case 14:
		case 13:
			index=4;
			break;
		case 12:
		case 11:
		case 10:
			index=3;
			break;
		case 9:
		case 8:
		case 7:
			index=2;
			break;
		case 6:
		case 5:
		case 4:
			index=1;
			break;
		case 3:
		case 2:
		case 1:
		case 0:
			index=0;
			break;
		}
		System.out.println(index+"---------------------------");
		return images[index];
	}

	public void paint(Graphics g){
		g.drawImage(getImage(), Path.SPECAL_POINT[7][0],Path.SPECAL_POINT[7][1],null);
	}

}
