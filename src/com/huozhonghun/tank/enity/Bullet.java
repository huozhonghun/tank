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

	public Bullet(int x, int y, DirectionEnum dir) {
		this.x = x;
		this.y = y;
		this.dir = dir;
	}

	public void paint(Graphics g){
		// 画出物体的位置和大小
//		Color color = g.getColor();
//		color.getRed();
//		g.fillOval(x,y,width,height);
//		g.setColor(color);
		Color c = g.getColor();
		g.setColor(Color.RED);
		g.fillOval(x, y, WIDTH, HEIGHT);

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
