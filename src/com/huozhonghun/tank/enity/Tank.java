package com.huozhonghun.tank.enity;

import com.huozhonghun.tank.ResourceMgr;
import com.huozhonghun.tank.enums.DirectionEnum;
import java.awt.*;

/**
 * 坦克对象
 *
 * @author weichenglin
 * @create $2021-06-21-下午 03:46:53
 */
public class Tank {

	// 长度
	private static int WIDTH = 50;

	// 宽度
	private static int HEIGHT = 50;

	// 每次移动步数
	private static final int step = 10;

	// 横坐标
	private int x;

	// 纵坐标
	private int y;

	// 坦克框架
	private TankFrame tankFrame;

	// 默认方向向下
	private DirectionEnum dir = DirectionEnum.DOWN;

	// 允许移动
	private boolean moving = false;

	public Tank(int x, int y, TankFrame tankFrame) {
		this.x = x;
		this.y = y;
		this.tankFrame = tankFrame; // 为了刷新子弹位置
	}

	public void setDir(DirectionEnum dir) {
		this.dir = dir;
	}

	public void setMoving(boolean moving) {
		this.moving = moving;
	}

	public void paint(Graphics g){

		switch (dir) {
			case UP:
				g.drawImage(ResourceMgr.goodTankU, x, y, null);
				break;
			case DOWN:
				g.drawImage(ResourceMgr.goodTankD, x, y, null);
				break;
			case LEFT:
				g.drawImage(ResourceMgr.goodTankL, x, y, null);
				break;
			case RIGHT:
				g.drawImage(ResourceMgr.goodTankR, x, y, null);
				break;
			default: break;
		}

		/*// 画出物体的位置和大小
		g.setColor(Color.GREEN);
		g.fillRect(x,y,WIDTH,HEIGHT);
		g.setColor(g.getColor()); // 设置为原来的颜色*/

		move();
	}

	private void move(){
		if(moving){
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
		}
	}

	// 开火
	public void fire(){
		// 20 = 50/2 - 10/2
		tankFrame.bulletList.add(new Bullet(x + 20, y + 20, dir, this.tankFrame));
	}
}
