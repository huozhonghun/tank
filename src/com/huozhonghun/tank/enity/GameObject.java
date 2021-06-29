package com.huozhonghun.tank.enity;

import java.awt.*;

/**
 * 游戏物体对象
 * @author weichenglin
 * @create $2021-06-29-上午 11:58:14
 */
public abstract class GameObject {
	// 横坐标
	private int x;

	// 纵坐标
	private int y;

	public abstract void paint(Graphics g);
}
