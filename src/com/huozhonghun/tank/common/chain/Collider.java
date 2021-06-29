package com.huozhonghun.tank.common.chain;

import com.huozhonghun.tank.enity.GameObject;

public interface Collider {
	boolean collide(GameObject gameObject1, GameObject gameObject2);
}
