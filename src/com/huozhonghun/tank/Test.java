package com.huozhonghun.tank;

import com.huozhonghun.tank.enity.TankFrame;

/**
 * @author weic henglin
 * @create $2021-06-21-上午 10:56:50
 */
public class Test {
	public static void main(String[] args) throws InterruptedException {
		TankFrame tankFrame = new TankFrame();

		while (true){
			System.out.println(3);
			Thread.sleep(10);
			tankFrame.repaint();
		}
	}
}
