package cn.tedu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import cn.tedu.music.Music;
import cn.tedu.background.*;
import cn.tedu.bullet.*;
import cn.tedu.music.*;
import cn.tedu.tower.*;

public class World extends JPanel{
	private static BufferedImage[][] image=new BufferedImage[5][2];
	private static BufferedImage getPower;
	private static BufferedImage deleteTower;
	private static BufferedImage gameover;
	private static BufferedImage startgame[]=new BufferedImage[4];
	private static BufferedImage decorateImage[] =new BufferedImage[7];
	public static boolean OperateOrNot;
	static {
		OperateOrNot=true;
		try{
			for(int i=0;i<image.length;i++) {
				for(int j=0;j<image[i].length;j++) {
					image[i][j]=ImageIO.read(World.class.getResource("png/select"+i+j+".png"));
				}
			}
			for(int i=0;i<startgame.length;i++) {
				startgame[i]=ImageIO.read(World.class.getResource("png/gamestart"+i+".png"));
			}
			for(int i=0;i<decorateImage.length;i++) {
				decorateImage[i]=ImageIO.read(World.class.getResource("png/decorate"+i+".png"));
			}

			gameover=ImageIO.read(World.class.getResource("png/gameover.png"));
			getPower=ImageIO.read(World.class.getResource("png/getPower.png"));
			deleteTower=ImageIO.read(World.class.getResource("png/delete.png"));
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	private boolean SELECT_TOWER=false;//鼠标点击选择tower时为true
	public static final int WIDTH=960;
	public static final int HEIGHT=640;
	private List<CuteObject> enemies= new ArrayList<CuteObject>();
	private BG bg=new BG();
	private Path path=new Path();
	private Carrot carrot=new Carrot();
	private GameTools gametools=new GameTools();
	private List<Towers> towers= Collections.synchronizedList(new ArrayList<Towers>());
	private List<Bullets> bullets=Collections.synchronizedList(new ArrayList<Bullets>());
	private List<Decorate> decorate=Collections.synchronizedList(new ArrayList<Decorate>());
	private boolean getPowerOrNot=false;
	private boolean deleteTowerOrNot=false;
	public static int score=200;
	public static final int STARTGAME=0;
	public static final int RUNNING=1;
	public static final int GAMEOVER=2;
	private int gameState=0;
	Music music=new Music();

	//生成怪物的方法
	private int waite=300;
	private int index=0;
	private boolean EnterSwitch=false;
	private int waveCount=0;//波次
	public void getMonster() {
		/*
		 * 该方法生成新的怪物（wait自增，每当第二个if的waite%335==0为true时开始生成怪，每当生成40个怪之后停止
		 * 由ENEMYNUM决定一波有多少个怪
		 */
		if(!EnterSwitch) {
			waite++;
			if(waite%100==0) {
				EnterSwitch=true;
				Enemy1.HPs+=5;
			}
		}
		if(EnterSwitch) {
			if(index++%7==0) {
				CuteObject cute=new Enemy1();
				enemies.add(cute);
				waveCount=Enemy1.waveCount;
				if(Enemy1.EnemyIndex%Enemy1.ENEMYNUM==Enemy1.ENEMYNUM-1) {
					EnterSwitch=false;
				}
			}
		}

	}
	//移动和图片切换
	public void step() {
		for(CuteObject obj:enemies) {
			obj.move();
			gametools.turn(obj);
		}
	}
	//删除越界元素
	public void dropEnemeis() {

		Iterator<CuteObject> it=enemies.iterator();
		while(it.hasNext()) {
			CuteObject enemy=it.next();
			if((enemy.getX()<path.SPECAL_POINT[8][0]+10&&enemy.getX()>path.SPECAL_POINT[8][0]-10)
					&&
					(enemy.getY()<path.SPECAL_POINT[8][1]+10&&enemy.getY()>path.SPECAL_POINT[8][1]-10)) {
				carrot.setHP(carrot.getHP()-1);
				it.remove();

			}
		}

		Iterator<Bullets> it2=bullets.iterator();
		while(it2.hasNext()) {
			Bullets b=it2.next();
			if((b.getX()<=0-b.width||b.getX()>=WIDTH+b.width)
					||
					(b.getY()<=0-b.height||b.getY()>=HEIGHT+b.height)) {
				it2.remove();
			}

		}




	}
	//画出选择防御塔的图片
	public void paintSelectTowerImage(Graphics g) {

		if(SELECT_TOWER) {
			int index=0;
			if(score>=100) {
				index=1;
			}else {
				index=0;
			}
			g.drawImage(image[0][index], selectTowerX-25,selectTowerY+10,null);
			if(score>=160) {
				index=1;
			}else {
				index=0;
			}
			g.drawImage(image[1][index], selectTowerX+25,selectTowerY+10,null);
			if(score>=220) {
				index=1;
			}else {
				index=0;
			}
			g.drawImage(image[2][index], selectTowerX+75,selectTowerY+10,null);
			if(score>=160) {
				index=1;
			}else {
				index=0;
			}
			g.drawImage(image[3][index], selectTowerX-75,selectTowerY+10,null);
			if(score>=180) {
				index=1;
			}else {
				index=0;
			}
			g.drawImage(image[4][index], selectTowerX-125,selectTowerY+10,null);

		}
		//升级防御塔图标
		if(choseTower!=null&&getPowerOrNot) {
			g.drawImage(getPower, choseTower.getX()+5,choseTower.getY()-45,null);

		}
		if(choseTower!=null&&deleteTowerOrNot) {
			g.drawImage(deleteTower, choseTower.getX()+8,choseTower.getY()+60,null);
		}
	}
	//设置防御塔旋转的角度
	public void setAngle() {
		int i=0;
		boolean ck=false;
		if(enemies.size()==0) {
			for(Towers t:towers) {
				t.setShootState(false);
			}
			return;
		}else if(enemies.size()!=0) {
			for(Towers t:towers) {
				for(;i<enemies.size();) {
					Enemy1 enemy=(Enemy1) enemies.get(i);
					ck=(gametools.canShoot(enemy, t)?true:false);
					if(ck) {
						t.setShootState(true);
						t.setTowerAngle(gametools.getAngle(enemy, t));
						break;
					}else{
						t.setShootState(false);
						if(i==enemies.size()-1) {
							break;
						}
						i++;
					}
				}
			}
		}	
	}
	//管理子弹
	public void manageBullets() {
		try {
			List<Bullets> list=new ArrayList<Bullets>();
			for(Towers t:towers) {
				Bullets b=t.getNewBullet();
				if(b!=null) {
					list.add(b);
				}
			}
			bullets.addAll(list);

			Iterator<Bullets> it=bullets.iterator();

			while(it.hasNext()) {
				Bullets b=it.next();
				if(b.isOutOfDistance()&&!(b instanceof BulletOfFan)) {
					it.remove();

				}
			}


		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//碰撞
	public void isBangAction() {
		List<Decorate> list=new ArrayList<Decorate>();
		List<Bullets> blist=new ArrayList<Bullets>();
		List<Enemy1> elist=new ArrayList<Enemy1>();
		Iterator<Bullets> it2=bullets.iterator();

		while(it2.hasNext()) {
			Bullets b=it2.next();
			Enemy1 enemy = null;
			Iterator<CuteObject> it=enemies.iterator();
			while(it.hasNext()) {
				enemy=(Enemy1) it.next();
				if(enemy.getHP()<=0) {
					elist.add(enemy);
					score+=4;
				}
				boolean ck=gametools.bangAction(enemy, b);

				if(ck) {
					if(b instanceof BulletOfTBottle) {
						list.add(new Decorate(b.getX(), b.getY(),0));
						blist.add(b);
					}else if(b instanceof BulletOfStar) {
						BulletOfStar st=(BulletOfStar)b;
						if(st.isBoom()) {
							list.add(new Decorate(b.getX(), b.getY()+50,3));
							blist.add(b);
						}else {
							st.setBoom(true);
							st.setX(st.getX()-120);
							st.setY(st.getY()-120);
							st.setWidth(st.getWidth()+120);
							st.setHeight(st.getHeight()+120);
						}
					}else if(b instanceof BulletOfFan) {
						list.add(new Decorate(b.getX(), b.getY(),1));
					}else if(b instanceof BulletOfCuttle) {
						list.add(new Decorate(b.getX(), b.getY(),2));
						blist.add(b);
					}else if(b instanceof BulletOfTStar) {
						BulletOfTStar st=(BulletOfTStar)b;
						if(st.isBoom()) {
							if(!enemy.isCold()) {
								enemy.setSpeed(enemy.getSpeed()-(b.getLevel()+1)*4);
							}
							enemy.setCold(true);
							list.add(new Decorate(b.getX(), b.getY()+50,4));
							blist.add(b);

						}else {
							st.setBoom(true);
							st.setX(st.getX()-120);
							st.setY(st.getY()-120);
							st.setWidth(st.getWidth()+120);
							st.setHeight(st.getHeight()+120);
						}
					}
					enemy.setHP(enemy.getHP()-b.getATK());
				}
			}

		}

		decorate.addAll(list);
		bullets.removeAll(blist);
		enemies.removeAll(elist);
	}

	private Towers choseTower=null;
	private int selectTowerX;//选择防御塔时的坐标
	private int selectTowerY;
	public void action() {
		music.play();
		MouseAdapter l = new MouseAdapter(){
			int x;
			int y;
			public void mouseMoved(MouseEvent e){
				//监听鼠标的x坐标和y坐标
				x= e.getX();
				y= e.getY();

			}

			public void mouseClicked(MouseEvent e){
				if(gameState==STARTGAME) {
					gameState=RUNNING;
				}else if(gameState==RUNNING) {
					if(gametools.canOperate(x, y)) {//判断鼠标点击是否在合法范围内
						if(SELECT_TOWER==false&&getPowerOrNot==false&&deleteTowerOrNot==false) {
							if((choseTower=gametools.hasTower(towers, x, y))!=null) {//判断是否有防御塔,有防御塔则可以调出升级页面
								deleteTowerOrNot=true;
								OperateOrNot=false;
								if(score<choseTower.getPayFor()||choseTower.getLevel()==2) {
									System.out.println("钱不够或已经满级");
								}else {
									getPowerOrNot=true;
								}

							}else {
								selectTowerX = x;
								selectTowerY = y;
								SELECT_TOWER=true;
								OperateOrNot=false;
								//开始选择防御塔后,不再判断是否可以进行Operate,任何坐标都可以通过第一层if
							}
						}else{
							//判断是否选择防御塔
							if(SELECT_TOWER) {
								//判断是否选择防御塔TBottle
								if((x>selectTowerX-25&&x<selectTowerX+25)&&(y>selectTowerY+10&&y<selectTowerY+50)&&score>=100) {
									towers.add(new TBottle((selectTowerX/52)*52,(selectTowerY/52)*52));
									score-=100;
								}
								//判断是否选择防御塔Fan
								if((x>selectTowerX+25&&x<selectTowerX+75)&&(y>selectTowerY+10&&y<selectTowerY+50)&&score>=160) {
									towers.add(new Fan((selectTowerX/52)*52,(selectTowerY/52)*52));
									score-=160;
								}
								//判断是否选择防御塔Cuttle
								if((x>selectTowerX+75&&x<selectTowerX+125)&&(y>selectTowerY+10&&y<selectTowerY+50)&&score>=160) {
									towers.add(new Cuttle((selectTowerX/52)*52,(selectTowerY/52)*52));
									score-=220;
								}
								//判断是否选择防御塔Star
								if((x>selectTowerX-75&&x<selectTowerX-25)&&(y>selectTowerY+10&&y<selectTowerY+50)&&score>=160) {
									towers.add(new Star((selectTowerX/52)*52,(selectTowerY/52)*52));
									score-=160;
								}
								//判断是否选择防御塔TblueStar
								if((x>selectTowerX-125&&x<selectTowerX-75)&&(y>selectTowerY+10&&y<selectTowerY+50)&&score>=160) {
									towers.add(new TblueStar((selectTowerX/52)*52,(selectTowerY/52)*52));
									score-=180;
								}
								SELECT_TOWER=false;
							}
							//判断是否选择升级
							if(choseTower!=null) {
								if(getPowerOrNot&&(x>choseTower.getX()-5&&x<choseTower.getX()+70)&&(y<=choseTower.getY()+10&&y>=choseTower.getY()-70)) {

									if(choseTower.getLevel()<2) {
										choseTower.setLevel(choseTower.getLevel()+1);
										System.out.println(choseTower.getATK());
									}
								}
								if(deleteTowerOrNot&&(x>choseTower.getX()-5&&x<choseTower.getX()+60)&&(y<=choseTower.getY()+110&&y>=choseTower.getY()+50)) {
									score+=(int)(choseTower.getPayFor()*0.7*(choseTower.getLevel()+1));
									towers.remove(choseTower);
								}
								getPowerOrNot=false;
								deleteTowerOrNot=false;
								choseTower=null;
							}
						}
					}
				}
			}
			public void mouseExited(MouseEvent e){
				//监听鼠标离开界面
			}
			public void mouseEntered(MouseEvent e){
				//监听鼠标回到界面
			}
		};
		this.addMouseListener(l);
		this.addMouseMotionListener(l);


		Timer timer = new Timer();
		timer.schedule(new TimerTask(){
			public void run(){
				if(gameState==RUNNING) {
					getMonster();
					step();
					setAngle();
					dropEnemeis();
					manageBullets();
					isBangAction();
				}
				repaint();
			}
		},65,65);
	}
	public void paintSomeDecorate(Graphics g) {
		g.drawImage(decorateImage[4], 384,254,null);
		g.drawImage(decorateImage[4], 454,254,null);
		g.drawImage(decorateImage[4], 524,254,null);
		g.drawImage(decorateImage[6], 314,254,null);
		g.drawImage(decorateImage[6], 594,254,null);
		g.drawImage(decorateImage[0], 428,110,null);
		g.drawImage(decorateImage[1], 298,125,null);
		g.drawImage(decorateImage[1], 558,125,null);
		g.drawImage(decorateImage[2], 205,160,null);
		g.drawImage(decorateImage[2], 713,160,null);
		g.drawImage(decorateImage[3], 200,440,null);
		g.drawImage(decorateImage[3], 656,440,null);
	}
	public void paint(Graphics g) {
		if(gameState==GAMEOVER) {
			g.drawImage(gameover, 105,80,null);
			
			return;
		}
		if(gameState==STARTGAME) {
			g.drawImage(startgame[0], 0,0,null);
			g.drawImage(startgame[1], 0,0,null);
			g.drawImage(startgame[2], 330,400,null);
			g.drawImage(startgame[3], 0,0,null);
			return;
		}



		bg.paint(g);
		paintSomeDecorate(g);
		path.paint(g);

		carrot.paint(g);

		Iterator<CuteObject> it4=enemies.iterator();
		while(it4.hasNext()) {
			CuteObject obj=it4.next();
			obj.paint(g);
		}


		g.drawImage(decorateImage[5], 320,380,null);
		g.drawImage(decorateImage[5], 550,380,null);

		Iterator<Decorate> it3=decorate.iterator();
		while(it3.hasNext()) {
			Decorate d=it3.next();
			d.paint(g);
			if(d.getIndex()==5) {
				it3.remove();

			}
		}


		Iterator<Towers> it=towers.iterator();
		while(it.hasNext()) {
			Towers ts=it.next();
			ts.paint(g);
		}


		Iterator<Bullets> it2=bullets.iterator();
		while(it2.hasNext()) {
			Bullets b=it2.next();
			b.paint(g);
			System.out.println(b.getATK());
		}



		paintSelectTowerImage(g);
		Font f=new Font(Font.DIALOG,Font.BOLD,20);
		g.setFont(f);
		g.setColor(Color.ORANGE);
		g.drawString("胡萝卜:"+score, 420, 25);
		g.drawString("波次:"+waveCount, 420, 60);
		g.drawString("生命值:"+carrot.getHP(), 800, 25);
		System.out.println(carrot.getHP()+"============================");
		if(carrot.getHP()<=0) {
			gameState=GAMEOVER;
		}	

	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("CarrotFantasy");
		World world = new World();
		frame.add(world);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(WIDTH, HEIGHT);
		frame.setLocationRelativeTo(null); 
		frame.setVisible(true); 

		world.action();
	}
}
