package com.huozhonghun.tank.enity;

import com.huozhonghun.tank.utils.ResourceMgr;
import java.awt.*;

/**
 * 爆炸
 *
 * @author weichenglin
 * @create $2021-06-25-下午 02:24:15
 */
public class Explosion {

	// 长度
	public static int WIDTH = ResourceMgr.goodTankU.getWidth();

	// 宽度
	public static int HEIGHT = ResourceMgr.goodTankU.getHeight();

	// 横坐标
	private int x;

	// 纵坐标
	private int y;

	// 坦克框架
	private TankFrame tankFrame;

	// 爆炸状态
	private int status = 0;

	public Explosion(int x, int y, TankFrame tankFrame) {
		this.x = x;
		this.y = y;
		this.tankFrame = tankFrame;
	}

	public void paint(Graphics g){
		g.drawImage(ResourceMgr.explodes[status++], x, y, null);
		if(status>=ResourceMgr.explodes.length) status=0;
	}
}
