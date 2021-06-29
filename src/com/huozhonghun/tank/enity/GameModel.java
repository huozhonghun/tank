package com.huozhonghun.tank.enity;

import com.huozhonghun.tank.common.chain.ColliderChain;
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

	public List<GameObject> gameObjects = new ArrayList<GameObject>();

	private static int tankCount = 0;

	private static int bulletCount = 0;

	private GameModel() {

		player = new Tank(150, 150, Group.GOOD);

		// gameObjects.add(player);

		for (int i = 0; i < 5; i++) {
			gameObjects.add(new Tank(80 * i, 400, Group.BAD));
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
		gameObjects.forEach(o -> {
			if(o instanceof Tank){
				tankCount++;
			}else if(o instanceof Bullet){
				bulletCount++;
			}
		});
		// 展示物体数量
		g.drawString("敌军坦克数量：" + tankCount, 10, 50);
		g.drawString("子弹数量：" + bulletCount, 10, 70);
		// 设为原来的颜色，保证不影响其他对象
		g.setColor(color);

		player.paint(g);
		// 遍历物体对象
		for (int i = 0; i < gameObjects.size(); i++) {
			gameObjects.get(i).paint(g);
		}

		// 物体碰撞检测
		for (int i = 0; i < gameObjects.size(); i++) {
			for (int j = i+1; j < gameObjects.size(); j++) {
				// 为了方便控制使用多个碰撞器, 使用了责任链
				new ColliderChain().collide(gameObjects.get(i), gameObjects.get(j));
			}
		}

	}

}
