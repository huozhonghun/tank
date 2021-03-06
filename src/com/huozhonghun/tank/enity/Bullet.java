package com.huozhonghun.tank.enity;

import com.huozhonghun.tank.enums.Group;
import com.huozhonghun.tank.utils.ResourceMgr;
import com.huozhonghun.tank.enums.DirectionEnum;
import java.awt.*;

/**
 * 子弹对象
 *
 * @author weichenglin
 * @create $2021-06-21-下午 04:20:16
 */
public class Bullet {

	// 长度
	public static int WIDTH = ResourceMgr.bulletU.getWidth();

	// 宽度
	public static int HEIGHT = ResourceMgr.bulletU.getHeight();

	// 每次移动步数
	private static final int step = 10;

	// 横坐标
	private int x;

	// 纵坐标
	private int y;

	// 默认方向向下
	private DirectionEnum dir = DirectionEnum.DOWN;

	// 对象存活状态
	private boolean survive = true;

	// 坦克框架
	private TankFrame tankFrame;

	// 坦克分类
	private Group group;

	public Rectangle rect;

	public Bullet(int x, int y, Group group, DirectionEnum dir, TankFrame tankFrame) {
		this.x = x;
		this.y = y;
		this.group = group;
		this.dir = dir;
		this.tankFrame = tankFrame;

		rect = new Rectangle(this.x, this.y, WIDTH, HEIGHT);
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

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public void paint(Graphics g){
		if(!survive){
			// 清理多余对象，防止内存泄漏
			tankFrame.bulletList.remove(this);
		}

/*		// 画出物体的位置和大小
		g.setColor(Color.RED);
		g.fillOval(x, y, WIDTH, HEIGHT);
		g.setColor(g.getColor()); // 设置为原来的颜色*/

		switch (dir) {
			case UP:
				g.drawImage(ResourceMgr.bulletU, x, y, null);
				break;
			case DOWN:
				g.drawImage(ResourceMgr.bulletD, x, y, null);
				break;
			case LEFT:
				g.drawImage(ResourceMgr.bulletL, x, y, null);
				break;
			case RIGHT:
				g.drawImage(ResourceMgr.bulletR, x, y, null);
				break;
			default: break;
		}

		move();

	}

	private void move(){
		// 判断方向来移动坦克
		switch (dir) {
			case UP:
				y -= step;
				break;
			case DOWN:
				y += step;
				break;
			case LEFT:
				x -= step;
				break;
			case RIGHT:
				x += step;
				break;
			default: break;
		}

		// 超过边界，对象状态为死亡
		if(x<0 || y<0 || x>800 || y>800) {
			survive = false;
		}

		rect.x = this.x;
		rect.y = this.y;
	}

	public void die(){
		this.survive = false;
	}

}
