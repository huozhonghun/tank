package com.huozhonghun.tank.service.impl;

import com.huozhonghun.tank.enity.Bullet;
import com.huozhonghun.tank.enity.GameModel;
import com.huozhonghun.tank.enity.Tank;
import com.huozhonghun.tank.enums.Group;
import com.huozhonghun.tank.service.FireStrategy;
import com.huozhonghun.tank.utils.Audio;

/**
 * @author linweicheng
 * @create $2021-06-27-17:07
 */
public class OneDirFireStrategy implements FireStrategy {

	@Override
	public void fire(Tank t) {

		GameModel.getINSTANCE().gameObjects.add(new Bullet(t.getX() + t.WIDTH/2 - Bullet.WIDTH/2, t.getY() + t.HEIGHT/2 - Bullet.HEIGHT/2, t.getGroup(), t.getDir()));

		if(t.getGroup() == Group.GOOD){
			// 开火发出声音
			new Thread(()->new Audio("audio/tank_fire.wav").play()).start();
		}
	}
}
