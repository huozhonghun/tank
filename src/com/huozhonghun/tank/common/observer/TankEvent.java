package com.huozhonghun.tank.common.observer;

import com.huozhonghun.tank.enity.Tank;

/**
 * @author linweicheng
 * @create $2021-06-29-下午 05:09:04
 */
public class TankEvent {
	Tank tank;

	public TankEvent(Tank tank) {
		this.tank = tank;
	}

	public Tank getSource() {
		return tank;
	}

}
