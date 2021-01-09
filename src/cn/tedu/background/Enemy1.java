package cn.tedu.background;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * the first enemy
 * @author soft01
 *
 */
public class Enemy1 extends CuteObject{
	private static BufferedImage[][] images=new BufferedImage[11][2];
	public static final int ENEMYNUM=15;
	public static int EnemyIndex;
	private static int ImageIndex;
	private int ImageIndex2;
	public static int HPs;
	public static int waveCount;
	public static int EnemyIndexForWaveCount;
	private static BufferedImage coldImage;
	private boolean cold;
	static {
		waveCount=0;
		HPs=0;
		EnemyIndex=0;
		EnemyIndexForWaveCount=0;
		try {
			for(int i=0;i<images.length;i++) {
				images[i][0] = ImageIO.read(Enemy1.class.getResource("../png/landpink"+i+"_1.png"));
				images[i][1] = ImageIO.read(Enemy1.class.getResource("../png/landpink"+i+"_2.png"));
				coldImage = ImageIO.read(Enemy1.class.getResource("../png/cold.png"));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Enemy1() {
		super(65,65,8,HPs);
		//分别为宽,高,速度,生命值
		cold=false;
		EnemyIndex+=1;
		EnemyIndexForWaveCount+=1;
		ImageIndex=EnemyIndex/ENEMYNUM;
		ImageIndex2=ImageIndex;
		waveCount=EnemyIndexForWaveCount/ENEMYNUM+1;
		if(ImageIndex==11) {
			ImageIndex=0;
			ImageIndex2=0;
			EnemyIndex=0;
		}
		
	}
	
	//let picture bee moved
	private int index=0;
	private int index1=0;
	@Override
	public BufferedImage getImage() {
		if(this.HP>0) {
			if(index1++%15==0) {
				index++;
			}
			return images[ImageIndex2][index%2];
			
		}
		return null;
	}
	public void paint(Graphics g){
		g.drawImage(getImage(),this.x,this.y,null);
		if(cold) {
			g.drawImage(coldImage,this.x,this.y+25,null);
		}
	}
	public static int getHPs() {
		return HPs;
	}
	public static void setHPs(int hPs) {
		HPs = hPs;
	}
	public boolean isCold() {
		return cold;
	}
	public void setCold(boolean cold) {
		this.cold = cold;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
		if(this.speed<=0) {
			this.speed=1;
		}
	}
	
}
