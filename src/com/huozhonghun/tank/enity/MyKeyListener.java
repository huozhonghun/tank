package com.huozhonghun.tank.enity;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * 键盘监听对象
 *
 * @author weichenglin
 * @create $2021-06-21-上午 11:37:38
 */
public class MyKeyListener extends KeyAdapter {

	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println(KeyEvent.getKeyText(e.getKeyCode()) + "按压");
	}

	@Override
	public void keyReleased(KeyEvent e) {
		System.out.println(KeyEvent.getKeyText(e.getKeyCode()) + "放开");
	}
}
