package cn.tedu.background;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Decorate extends CuteObject{
	private static BufferedImage[][] imagesBoom=new BufferedImage[5][6];
	static {
		for(int i=0;i<imagesBoom.length;i++) {
			try {
				for(int j=0;j<imagesBoom[i].length;j++	) {
					imagesBoom[i][i]= ImageIO.read(Decorate.class.getResource("../png/BulletBoom"+i+j+".png"));
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	private int index;
	private int towerNumber;

	public Decorate(int x, int y,int towerNumber) {
		super(x, y);
		index=-1;
		this.towerNumber=towerNumber;
	}
	int index2=0;
	public BufferedImage getImage() {
			index++;
		if(index==imagesBoom[0].length-1) {
			index=5;
		}
		
		return imagesBoom[towerNumber][index];
	}
	public void paint(Graphics g) {
		g.drawImage(getImage(), this.x,this.y,null);
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public int getTowerNumber() {
		return towerNumber;
	}
	public void setTowerNumber(int towerNumber) {
		this.towerNumber = towerNumber;
	}

}
