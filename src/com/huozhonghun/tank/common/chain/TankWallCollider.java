package com.huozhonghun.tank.common.chain;

import com.huozhonghun.tank.enity.GameObject;
import com.huozhonghun.tank.enity.Tank;
import com.huozhonghun.tank.enity.Wall;

/**
 * 子弹坦克碰撞器
 *
 * @author weichenglin
 * @create $2021-06-29-下午 01:39:39
 */
public class TankWallCollider implements Collider{

	// 碰撞成功返回true
	@Override
	public boolean collide(GameObject gameObject1, GameObject gameObject2) {
		if (gameObject1 instanceof Tank && gameObject2 instanceof Wall){
			Tank tank = (Tank) gameObject1;
			Wall wall = (Wall) gameObject2;
			if(tank.rect.intersects(wall.rect)){
				// 让坦克回到原先的位置
				tank.goBack();
				return true;
			}
		}else if(gameObject1 instanceof Wall && gameObject2 instanceof Tank){
			collide(gameObject2, gameObject1);
		}
		return false;
	}
}
