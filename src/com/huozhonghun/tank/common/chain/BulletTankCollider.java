package com.huozhonghun.tank.common.chain;

import com.huozhonghun.tank.enity.*;

/**
 * 子弹坦克碰撞器
 *
 * @author weichenglin
 * @create $2021-06-29-下午 01:39:39
 */
public class BulletTankCollider implements Collider{

	// 碰撞成功返回true
	@Override
	public boolean collide(GameObject gameObject1, GameObject gameObject2) {
		if (gameObject1 instanceof Bullet && gameObject2 instanceof Tank){
			Bullet bullet = (Bullet) gameObject1;
			Tank tank = (Tank) gameObject2;
			if(tank.getGroup() ==  bullet.getGroup()) return false;
			if(tank.rect.intersects(bullet.rect)) {
				tank.die();
				bullet.die();
				GameModel.getINSTANCE().gameObjects.add(new Explosion(tank.getX() + tank.WIDTH/2 - Explosion.WIDTH/2, tank.getY() + tank.HEIGHT/2 - Explosion.HEIGHT/2));
				return true;
			}
		}else if(gameObject2 instanceof Bullet && gameObject1 instanceof Tank){
			collide(gameObject2, gameObject1);
		}
		return false;
	}
}
