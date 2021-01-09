package cn.tedu.tower;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import cn.tedu.World;
import cn.tedu.*;
import cn.tedu.background.*;
import cn.tedu.bullet.*;
import cn.tedu.music.*;
import cn.tedu.tower.*;
public class Cuttle extends Towers{
	private static BufferedImage[][] TowerImages=new BufferedImage[3][3];
	private static BufferedImage TowerBase;
	static {
		try{
			for(int i=0;i<TowerImages.length;i++) {
				for(int j=0;j<TowerImages[i].length;j++) {
					TowerImages[i][j]=ImageIO.read(Cuttle.class.getResource("../png/Cuttle"+i+j+".png"));
				}
			}
			TowerBase=ImageIO.read(Cuttle.class.getResource("../png/CuttleBase.png"));
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	public Cuttle(int x, int y) {
		super(x, y,250,40,220);
		//分别为坐标,左边,射程,攻击力,升级费用
	}
	int index1=0;
	int index2=0;
	public BufferedImage getImage() {
		if(index2++%5==0) {
			index1++;
		}
		return TowerImages[level][index1%3];
	}
	int index=1;
	@Override
	public Bullets getNewBullet() {
		if(shootState) {
			if(index++%20==0) {
				return (new BulletOfCuttle(this.x, this.y, this.towerAngrad, this.level,this.cannon_shot,this.ATK));	
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
		this.ATK=level*40+40;
	}
}
