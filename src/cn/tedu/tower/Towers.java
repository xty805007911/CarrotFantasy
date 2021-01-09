package cn.tedu.tower;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JEditorPane;
import javax.swing.JPanel;
import cn.tedu.*;
import cn.tedu.background.*;
import cn.tedu.bullet.*;
import cn.tedu.music.*;
import cn.tedu.tower.*;
/**
 * 
 * @author soft01
 *
 */
public abstract class Towers{
	protected int x;
	protected int y;
	protected int level;
	protected int width;
	protected int height;
	public int cannon_shot;
	protected boolean shootState;
	protected double towerAngrad;
	protected int ATK;
	protected int payFor;
	public Towers(int x, int y,int cannon_shot,int ATK,int payFor) {
		super();
		this.x = x;
		this.y = y;
		this.level=0;
		this.width=52;
		this.height=52;
		this.cannon_shot=cannon_shot;
		this.shootState=false;
		this.ATK=ATK;
		this.payFor=payFor;
	}
	public Bullets getNewBullet() {
		return null;	
	}
	public void paint(Graphics g) {
		
	}
	public BufferedImage getImage() {
		return null;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public int getLevel() {
		return level;
	}
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
	public void setLevel(int level) {
		this.level = level;
		this.ATK=level*5+5;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getCannon_shot() {
		return cannon_shot;
	}
	public void setCannon_shot(int cannon_shot) {
		this.cannon_shot = cannon_shot;
	}
	public boolean isShootState() {
		return shootState;
	}
	public void setShootState(boolean shootState) {
		this.shootState = shootState;
	}
	public double getTowerAngrad() {
		return towerAngrad;
	}
	public void setTowerAngle(double towerAngrad) {
		this.towerAngrad = towerAngrad;
	}
	public int getATK() {
		return ATK;
	}
	public void setATK(int aTK) {
		ATK = aTK;
	}
	public int getPayFor() {
		return payFor;
	}
	public void setPayFor(int payFor) {
		this.payFor = payFor;
	}
	public void setTowerAngrad(double towerAngrad) {
		this.towerAngrad = towerAngrad;
	}
	
	
}
