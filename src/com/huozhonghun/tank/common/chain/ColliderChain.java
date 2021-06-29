package com.huozhonghun.tank.common.chain;

import com.huozhonghun.tank.enity.GameObject;
import java.util.LinkedList;
import java.util.List;

/**
 * 碰撞器责任链
 *
 * @author weichenglin
 * @create $2021-06-29-下午 02:16:34
 */
public class ColliderChain implements Collider{

	List<Collider> colliders = new LinkedList<>();

	public ColliderChain() {
		add(new BulletTankCollider());
		add(new TankTankCollider());
	}

	private void add(Collider collider){
		colliders.add(collider);
	}

	@Override
	public boolean collide(GameObject gameObject1, GameObject gameObject2) {
		for (int i = 0; i < colliders.size(); i++) {
			if(colliders.get(i).collide(gameObject1, gameObject2)){
				// 撞上了，直接停止
				return true;
			}
		}
		return false;
	}
}
