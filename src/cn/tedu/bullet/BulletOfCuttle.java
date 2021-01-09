package cn.tedu.bullet;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import cn.tedu.*;
import cn.tedu.background.*;
import cn.tedu.bullet.*;
import cn.tedu.music.*;
import cn.tedu.tower.*;
public class BulletOfCuttle extends Bullets{

	private static BufferedImage images;
	static {
		try {
				images = ImageIO.read(BulletOfCuttle.class.getResource("../png/BulletOfCuttle.png"));

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public BulletOfCuttle(int x, int y, double angrad,int level,int cannon_shot,int ATK) {
		super(x, y, angrad, 20, 20, 15,level,cannon_shot,ATK);
		//数字的为宽,高,速度属性
	}
	public BufferedImage getImage() {
		return images;

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
		Graphics g2=g.create();
		step();
		g2.drawImage(getImage(), this.x,this.y,null);
	}


}
