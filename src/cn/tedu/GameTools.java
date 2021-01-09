package cn.tedu;

import java.util.List;

import cn.tedu.background.*;
import cn.tedu.bullet.*;
import cn.tedu.music.*;
import cn.tedu.tower.*;


//游戏中用到的算法（转弯、碰撞、瞄准等等）
public class GameTools {

	public void turn(CuteObject enemy) {
		int speed=enemy.getSpeed();
		if(((enemy.getX()<Path.SPECAL_POINT[1][0]+10)&&(enemy.getX()>Path.SPECAL_POINT[1][0]-10))
				&&
				((enemy.getY()<Path.SPECAL_POINT[1][1]+10)&&(enemy.getY()>Path.SPECAL_POINT[1][1]-10))) {
			enemy.setxSpeed(speed);
			enemy.setySpeed(0);
		}
		if((enemy.getX()<Path.SPECAL_POINT[2][0]+10&&enemy.getX()>Path.SPECAL_POINT[2][0]-10)
				&&
				(enemy.getY()<Path.SPECAL_POINT[2][1]+10&&enemy.getY()>Path.SPECAL_POINT[2][1]-10)) {
			enemy.setySpeed(-speed);
			enemy.setxSpeed(0);
		}
		if((enemy.getX()<Path.SPECAL_POINT[3][0]+10&&enemy.getX()>Path.SPECAL_POINT[3][0]-10)
				&&
				(enemy.getY()<Path.SPECAL_POINT[3][1]+10&&enemy.getY()>Path.SPECAL_POINT[3][1]-10)) {
			enemy.setxSpeed(speed);
			enemy.setySpeed(0);
		}
		if((enemy.getX()<Path.SPECAL_POINT[4][0]+10&&enemy.getX()>Path.SPECAL_POINT[4][0]-10)
				&&
				(enemy.getY()<Path.SPECAL_POINT[4][1]+10&&enemy.getY()>Path.SPECAL_POINT[4][1]-10)) {
			enemy.setySpeed(speed);
			enemy.setxSpeed(0);
		}
		if((enemy.getX()<Path.SPECAL_POINT[5][0]+10&&enemy.getX()>Path.SPECAL_POINT[5][0]-10)
				&&
				(enemy.getY()<Path.SPECAL_POINT[5][1]+10&&enemy.getY()>Path.SPECAL_POINT[5][1]-10)) {
			enemy.setxSpeed(speed);
			enemy.setySpeed(0);
		}
		if((enemy.getX()<Path.SPECAL_POINT[6][0]+10&&enemy.getX()>Path.SPECAL_POINT[6][0]-10)
				&&
				(enemy.getY()<Path.SPECAL_POINT[6][1]+10&&enemy.getY()>Path.SPECAL_POINT[6][1]-10)) {
			enemy.setySpeed(-speed);
			enemy.setxSpeed(0);
		}
	}
	
	
	//判断是否存在防御塔,如果已经有防御塔则返回true
	public Towers hasTower(List<Towers> towers,int x,int y) {
		for(Towers t:towers) {
			if(x>t.getX()-10&&x<t.getX()+t.getWidth()+10) {
				if(y>t.getY()-10&&y<t.getY()+t.getHeight()+10) {
					return t;
				}
			}
		}
		return null;
	}

//判断是否可以进行防御塔的操作
	public boolean canOperate(int x,int y) {
		//将可以操作的范围划分为6个矩形,分别判断坐标点是否在这6个矩形内
		if(!World.OperateOrNot) {
			World.OperateOrNot=true;
			return true;
		}
		
		if((x>165&&x<315)&&(y>209&&y<355)) {
			return true;
		}
		if((x>315&&x<645)&&(y>209&&y<250)) {
			return true;
		}
		if((x>645&&x<780)&&(y>209&&y<355)) {
			return true;
		}
		if((x>165&&x<415)&&(y>470&&y<520)) {
			return true;
		}
		if((x>415&&x<560)&&(y>370&&y<520)) {
			return true;
		}
		if((x>545&&x<780)&&(y>470&&y<520)) {
			return true;
		}
		
		return false;
	}

	//计算是否在射程内的方法
	public boolean canShoot(Enemy1 enemy,Towers tower) {
		int num1=tower.getY()-enemy.getY();
		int num2=tower.getX()-enemy.getX();
		return (num1*num1+num2*num2)<=tower.cannon_shot*tower.cannon_shot;
	}
	
	//计算进入射程后炮台的旋转弧度
		public double getAngle(Enemy1 enemy,Towers tower) {
			double angrad;
			int opposideSide=tower.getY()-enemy.getY();//对边(当角度大于逆时针180度到270度时变为邻边
			int adjacentSide=tower.getX()-enemy.getX();//邻边(当角度大于逆时针180度到270度时变为对边
			if(opposideSide>0&&adjacentSide>0) {
				angrad=Math.PI/2-Math.atan2(opposideSide,adjacentSide);//弧度
			}else if(opposideSide<0&&adjacentSide>0) {
				angrad=-Math.atan2(opposideSide,adjacentSide)+Math.PI/2;
			}else if(opposideSide<0&&adjacentSide<0) {
				angrad=Math.atan2(adjacentSide,opposideSide)-Math.PI*2;
			}else {
				angrad=-Math.atan2(opposideSide,adjacentSide)+Math.PI/2;
			}
			return angrad;
		}
		
		
		//计算子弹飞行的路径
		public static void getFlyingLine(Bullets b) {
			double angle=b.getAngle();
			b.setX((int)(b.getX()-b.getSpeed()*(Math.sin(angle))));
			b.setY((int)(b.getY()-b.getSpeed()*(Math.cos(angle))));
		}
		
		//碰撞算法,检测子弹是否和怪物碰撞
		public boolean bangAction(CuteObject e,Bullets b) {
			Enemy1 enemy=(Enemy1)e;
			int bxw=b.getX()+b.getWidth();
			int bx=b.getX();
			int byh=b.getY()+b.getHeight();
			int by=b.getY();
			
			if(enemy.getX()<=bxw&&enemy.getX()+enemy.getWidth()>=bx) {
				if(enemy.getY()<=byh&&enemy.getY()+enemy.getHeight()>=by) {
					return true;
				}
			}
			return false;
		}
		
}
