package com.huozhonghun.tank.common.chain;

import com.huozhonghun.tank.enity.GameObject;
import com.huozhonghun.tank.enity.Tank;

/**
 * 子弹坦克碰撞器
 *
 * @author weichenglin
 * @create $2021-06-29-下午 01:39:39
 */
public class TankTankCollider implements Collider{

	// 碰撞成功返回true
	@Override
	public boolean collide(GameObject gameObject1, GameObject gameObject2) {
		if (gameObject1 instanceof Tank && gameObject2 instanceof Tank){
			Tank tank1 = (Tank) gameObject1;
			Tank tank2 = (Tank) gameObject2;
			if(tank1.rect.intersects(tank2.rect)){
				// 让坦克回到原先的位置
				tank1.goBack();
				tank2.goBack();
				return true;
			}
		}
		return false;
	}
}
