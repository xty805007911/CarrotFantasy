package cn.tedu.tower;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import cn.tedu.*;
import cn.tedu.background.*;
import cn.tedu.bullet.*;
import cn.tedu.music.*;
import cn.tedu.tower.*;
public class Fan extends Towers{
	private static BufferedImage[] TowerImages=new BufferedImage[3];
	private static BufferedImage TowerBase;
	
	static {
		try{
			for(int i=0;i<TowerImages.length;i++) {

					TowerImages[i]=ImageIO.read(TBottle.class.getResource("../png/Fan"+i+"0.png"));
				
			}
			TowerBase=ImageIO.read(TBottle.class.getResource("../png/FanBase.png"));
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	public Fan(int x, int y) {
		super(x, y, 350,1,160);
		//数字分别为射程,攻击力,升级费用
	}
	public BufferedImage getImage() {
		return TowerImages[level];
	}
	
	int index=1;
	@Override
	public Bullets getNewBullet() {
		if(shootState) {
			if(index++%12==0) {
				return (new BulletOfFan(this.x, this.y, this.towerAngrad, this.level,this.cannon_shot,this.ATK));
				
			}
		}
		return null;
	}
	
	public void paint(Graphics g) {
		g.drawImage(TowerBase, x,y+5,null);
		g.drawImage(getImage(), this.x,this.y,null);
	}
	public void setLevel(int level) {
		World.score-=payFor;
		this.level = level;
		this.ATK=level+2;
	}
}
