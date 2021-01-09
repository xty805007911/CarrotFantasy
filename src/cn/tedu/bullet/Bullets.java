package cn.tedu.bullet;

import java.awt.Graphics;
import cn.tedu.*;
import cn.tedu.background.*;
import cn.tedu.bullet.*;
import cn.tedu.music.*;
import cn.tedu.tower.*;
public abstract class Bullets {
	protected int x;
	protected int y;
	protected double angle;
	public int width;
	public int height;
	protected int speed;
	protected int level;
	protected int cannon_shot;
	protected boolean outOfDistance;
	protected int flyDistance;
	protected int ATK;
	protected boolean startBoom;
	public Bullets(int x, int y, double angrad, int width, int height, int speed,int level,int cannon_shot,int ATK) {
		super();
		this.x = x;
		this.y = y;
		this.angle = angrad;
		this.width = width;
		this.height = height;
		this.speed = speed;
		this.level=level;
		this.cannon_shot=cannon_shot;
		this.outOfDistance=false;
		this.flyDistance=0;
		this.ATK=ATK;
		this.startBoom=false;
	}
	public int getLevel() {
		return level;
	}
	public int getCannon_shot() {
		return cannon_shot;
	}
	public boolean isOutOfDistance() {
		return outOfDistance;
	}
	public int getFlyDistance() {
		return flyDistance;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public void setCannon_shot(int cannon_shot) {
		this.cannon_shot = cannon_shot;
	}
	public void setOutOfDistance(boolean outOfDistance) {
		this.outOfDistance = outOfDistance;
	}
	public void setFlyDistance(int flyDistance) {
		this.flyDistance = flyDistance;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public double getAngle() {
		return angle;
	}
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
	public int getSpeed() {
		return speed;
	}
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
	public void setAngle(double angle) {
		this.angle = angle;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public void paint(Graphics g) {
		
	}
	public int getATK() {
		return ATK;
	}
	public void setATK(int aTK) {
		ATK = aTK;
	}
	public boolean isStartBoom() {
		return startBoom;
	}
	public void setStartBoom(boolean startBoom) {
		this.startBoom = startBoom;
	}
	
	
}
