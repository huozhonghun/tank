package com.huozhonghun.tank.enity;

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
	private static int WIDTH = 10;

	// 宽度
	private static int HEIGHT = 10;

	// 每次移动步数
	private static final int step = 5;

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

	public Bullet(int x, int y, DirectionEnum dir, TankFrame tankFrame) {
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.tankFrame = tankFrame;
	}

	public void paint(Graphics g){
		if(!survive){
			// 清理多余对象，防止内存泄漏
			tankFrame.bulletList.remove(this);
		}

		// 画出物体的位置和大小
		g.setColor(Color.RED);
		g.fillOval(x, y, WIDTH, HEIGHT);
		g.setColor(g.getColor()); // 设置为原来的颜色
		// 超过边界，对象状态为死亡
		if(x<0 || y<0 || x>800 || y>800) {
			survive = false;
		}
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
	}

}
