package com.huozhonghun.tank.enity;

import com.huozhonghun.tank.enums.Group;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author weichenglin
 * @create $2021-06-29-上午 11:12:07
 */
public class GameModel {

	// 单例
	private static final GameModel INSTANCE = new GameModel();

	private Tank player;

	public List<Tank> tankList = new ArrayList<Tank>();

	public List<Bullet> bulletList = new ArrayList<Bullet>();

	public List<Explosion> explosionList = new ArrayList<Explosion>();

	private GameModel() {

		player = new Tank(150, 150, Group.GOOD);;

		for (int i = 0; i < 5; i++) {
			tankList.add(new Tank(80 * i, 400, Group.BAD));
		}
	}

	public static GameModel getINSTANCE() {
		return INSTANCE;
	}

	public Tank getPlayer() {
		return player;
	}

	// 画出物体
	public void paint(Graphics g) {
		Color color = g.getColor();
		g.setColor(Color.red);
		// 展示物体数量
		g.drawString("敌军坦克数量：" + tankList.size(), 10, 50);
		g.drawString("子弹数量：" + bulletList.size(), 10, 70);
		// 设为原来的颜色，保证不影响其他对象
		g.setColor(color);

		player.paint(g);
		// 遍历存活子弹
		for (int i = 0; i < bulletList.size(); i++) {
			bulletList.get(i).paint(g);
		}

		// 展示爆炸
		for (int i = 0; i < explosionList.size(); i++) {
			explosionList.get(i).paint(g);
		}

		// 创建敌军坦克
		for (int i = 0; i < tankList.size(); i++) {
			tankList.get(i).paint(g);
		}

		// 坦克碰撞，子弹碰撞
		for (int i = 0; i < tankList.size(); i++) {
			for (int j = 0; j < bulletList.size(); j++) {
				tankList.get(i).collision(bulletList.get(j));
			}
		}

	}

}
