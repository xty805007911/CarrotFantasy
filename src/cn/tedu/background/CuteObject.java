package cn.tedu.background;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;


/**
 * All enemies' superclass
 * @author 90672
 *
 */
public abstract class CuteObject {
	
	protected int x;
	protected int y;
	protected int width;
	protected int height;
	protected int ySpeed;
	protected int xSpeed;
	protected int speed;
	protected int HP;
	public CuteObject(int width, int height,int speed,int HP) {
		super();
		this.x = 90;
		this.y = 127;
		this.width = width;
		this.height = height;
		this.speed=speed;
		this.ySpeed =speed;
		this.xSpeed = 0;
		this.HP=HP;
	}

	public CuteObject(int width, int height,int HP,int xSpeed,int ySpeed) {
		super();
		this.x = 90;
		this.y = 127;
		this.width = width;
		this.height = height;
		this.ySpeed =ySpeed;
		this.xSpeed =xSpeed;
		this.HP=HP;
	}
	
	public CuteObject(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	public abstract BufferedImage getImage();

	public void paint(Graphics g){
		g.drawImage(getImage(),this.x,this.y,null);
	}
	public void move() {
		this.x+=xSpeed;
		this.y+=ySpeed;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getySpeed() {
		return ySpeed;
	}
	public void setySpeed(int ySpeed) {
		this.ySpeed = ySpeed;
	}
	public int getxSpeed() {
		return xSpeed;
	}
	public void setxSpeed(int xSpeed) {
		this.xSpeed = xSpeed;
	}
	public int getHP() {
		return HP;
	}
	public void setHP(int hP) {
		HP = hP;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	
}
