package com.huozhonghun.tank.common.chain;

import com.huozhonghun.tank.enity.Bullet;
import com.huozhonghun.tank.enity.GameObject;
import com.huozhonghun.tank.enity.Wall;

/**
 * 子弹坦克碰撞器
 *
 * @author weichenglin
 * @create $2021-06-29-下午 01:39:39
 */
public class BulletWallCollider implements Collider{

	// 碰撞成功返回true
	@Override
	public boolean collide(GameObject gameObject1, GameObject gameObject2) {
		if (gameObject1 instanceof Bullet && gameObject2 instanceof Wall){
			Bullet bullet = (Bullet) gameObject1;
			Wall wall = (Wall) gameObject2;
			if(bullet.rect.intersects(wall.rect)){
				bullet.die();
				return true;
			}
		}else if(gameObject1 instanceof Wall && gameObject2 instanceof Bullet){
			collide(gameObject2, gameObject1);
		}
		return false;
	}
}
