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
//防御塔子类
public class TBottle extends Towers{
	private static BufferedImage[] TowerImages=new BufferedImage[3];
	private static BufferedImage TowerBase;
	static {
		try{
			for(int i=0;i<TowerImages.length;i++) {
					TowerImages[i]=ImageIO.read(TBottle.class.getResource("../png/Bottle"+i+"0.png"));
			}
			TowerBase=ImageIO.read(TBottle.class.getResource("../png/BottleBase.png"));
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	public TBottle(int x, int y) {
		super(x, y,300,4,100);
		//分别为坐标,左边,射程,攻击力,升级费用
	}
	public BufferedImage getImage() {
		return TowerImages[level];
	}
	int index=1;
	@Override
	public Bullets getNewBullet() {
		if(shootState) {
			if(index++%7==0) {
				return (new BulletOfTBottle(this.x, this.y, this.towerAngrad, this.level,this.cannon_shot,this.ATK));
				
			}
		}
		return null;
	}
	
	public void paint(Graphics g) {
		g.drawImage(TowerBase, x,y+5,null);
		Graphics2D g2d=(Graphics2D) g.create();
		g2d.rotate(-towerAngrad, this.x+this.width/2, this.y+this.height/2);
		g2d.drawImage(getImage(), this.x,this.y,null);
		
	}
	public void setLevel(int level) {
		World.score-=payFor;
		this.level = level;
		this.ATK=level*4+3;
	}
}
