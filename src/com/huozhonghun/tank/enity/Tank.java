package com.huozhonghun.tank.enity;

import com.huozhonghun.tank.enums.DirectionEnum;
import java.awt.*;

/**
 * 坦克对象
 *
 * @author weichenglin
 * @create $2021-06-21-下午 03:46:53
 */
public class Tank {

	// 横坐标
	private int x;

	// 纵坐标
	private int y;

	// 长度
	private int length = 50;

	// 宽度
	private int width = 50;

	// 默认方向向下
	private DirectionEnum dir = DirectionEnum.DOWN;

	// 每次移动步数
	private static final int step = 10;

	public Tank(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public DirectionEnum getDir() {
		return dir;
	}

	public void setDir(DirectionEnum dir) {
		this.dir = dir;
	}

	public void paint(Graphics g, Boolean press){
		// 画出物体的位置和大小
		g.fillRect(x,y,50,50);
		if(press){
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
			}
		}
	}
}
