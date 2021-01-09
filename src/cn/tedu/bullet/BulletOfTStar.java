package cn.tedu.bullet;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import cn.tedu.*;
import cn.tedu.background.*;
import cn.tedu.bullet.*;
import cn.tedu.music.*;
import cn.tedu.tower.*;
public class BulletOfTStar extends Bullets{
	private static BufferedImage[] images=new BufferedImage[3];
	static {

		try {
			for(int i=0;i<images.length;i++) {
				images[i] = ImageIO.read(BulletOfTBottle.class.getResource("../png/BulletOfTStar"+i+".png"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	private boolean boom;
	public BulletOfTStar(int x, int y, double angrad,int level,int cannon_shot,int ATK) {
		super(x, y, angrad, 20, 20, 25,level,cannon_shot,ATK);
		this.ATK=ATK;
		boom=false;
		//数字的为宽,高,速度属性
	}
	public BufferedImage getImage() {
		if(boom) {
			return null;
		}
		return images[level];

	}
	//斜着飞飞
	public void step() {
		GameTools.getFlyingLine(this);
		flyDistance+=speed;
		if(flyDistance>=cannon_shot) {
			outOfDistance=true;
		}else {
			outOfDistance=false;
		}
	}

	public void paint(Graphics g) {
		step();
		g.drawImage(getImage(), this.x,this.y,null);
	}
	public boolean isBoom() {
		return boom;
	}
	public void setBoom(boolean boom) {
		this.boom = boom;
	}

}
