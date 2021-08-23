package com.huozhonghun.tank;

import com.huozhonghun.tank.enity.Tank;
import com.huozhonghun.tank.enity.TankFrame;
import com.huozhonghun.tank.enums.Group;

/**
 * @author weic henglin
 * @create $2021-06-21-上午 10:56:50
 */
public class Test {
	public static void main(String[] args) throws InterruptedException {
		TankFrame tankFrame = new TankFrame();

		for (int i = 0; i < 5; i++) {
			tankFrame.tankList.add(new Tank(80 * i, 400, Group.BAD, tankFrame));
		}

		while (true){
			Thread.sleep(10);
			tankFrame.repaint();
			System.out.println(6);
		}
	}
}
