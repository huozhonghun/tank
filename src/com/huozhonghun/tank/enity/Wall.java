package com.huozhonghun.tank.enity;

import java.awt.*;

/**
 * @author weichenglin
 * @create $2021-06-29-下午 03:41:42
 */
public class Wall extends GameObject{

	// 横坐标
	private int x;

	// 纵坐标
	private int y;

	// 长度
	private int width;

	// 宽度
	private int height;

	public Rectangle rect;

	public Wall(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;

		rect = new Rectangle(this.x, this.y, width, height);
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

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	@Override
	public void paint(Graphics g) {
		Color color = g.getColor();
		g.setColor(Color.GRAY);
		g.fillRect(x, y, width, height);
		g.setColor(color);
	}
}
