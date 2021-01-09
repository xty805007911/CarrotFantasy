package cn.tedu.background;
import cn.tedu.World;
import cn.tedu.background.*;
import cn.tedu.bullet.*;
import cn.tedu.music.*;
import cn.tedu.tower.*;

/**
 * Path and BackGroud's superclass
 * @author Ayanami
 *
 */
public abstract class BackGround {
	protected int x;
	protected int y;
	protected int width;
	protected int height;
	public BackGround() {
		super();
		this.x = 0;
		this.y = 0;
		this.width = World.WIDTH;
		this.height = World.HEIGHT;
	}
	public BackGround(int y) {
		super();
		this.x = 0;
		this.y = y;
		this.width = World.WIDTH;
		this.height =World.HEIGHT;
	}
	
	
}
