package com.huozhonghun.tank.common.observer;

/**
 * @author linweicheng
 * @create $2021-06-29-下午 05:09:04
 */
public class TankHandle implements TankSource {
	@Override
	public void Fire(TankEvent event) {
		event.getSource().fire();
	}
}
