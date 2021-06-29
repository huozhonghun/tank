package com.huozhonghun.tank.enity;

import com.huozhonghun.tank.utils.Audio;
import com.huozhonghun.tank.utils.ResourceMgr;
import java.awt.*;

/**
 * 爆炸
 *
 * @author weichenglin
 * @create $2021-06-25-下午 02:24:15
 */
public class Explosion extends GameObject{

	// 长度
	public static int WIDTH = ResourceMgr.explodes[0].getWidth();

	// 宽度
	public static int HEIGHT = ResourceMgr.explodes[0].getHeight();

	// 横坐标
	private int x;

	// 纵坐标
	private int y;

	// 爆炸状态
	private int status = 0;

	public Explosion(int x, int y) {
		this.x = x;
		this.y = y;
		// 爆炸发出声音
		new Thread(()->new Audio("audio/explode.wav").play()).start();
	}

	public void paint(Graphics g){
		g.drawImage(ResourceMgr.explodes[status++], x, y, null);
		if(status>=ResourceMgr.explodes.length) GameModel.getINSTANCE().gameObjects.remove(this);
	}
}
