package com.huozhonghun.tank.enity;

import com.huozhonghun.tank.enums.DirectionEnum;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * 坦克框架对象
 *
 * @author weichenglin
 * @create $2021-06-21-上午 11:14:30
 */
public class TankFrame extends Frame {


	Tank tank1 = new Tank(0,0);
	Tank tank2 = new Tank(100,100);

	// 移动
	private boolean moving = false;

	public TankFrame() {
		// 窗口大小
		setSize(800, 800);
		// 窗口大小是否可调整
		setResizable(true);
		// 窗口标题
		setTitle("坦克大战");
		// 是否显示窗口
		setVisible(true);

		// 添加键盘监听对象
		this.addKeyListener(new MyKeyListener());

		// 点击关闭退出窗口
		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}

		});
	}

	@Override
	public void paint(Graphics g) {
		tank1.paint(g, moving);
		tank2.paint(g,moving);
	}

	class MyKeyListener extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent e) {
			int keyCode = e.getKeyCode();
			moving = true;
			switch(keyCode) {
				case KeyEvent.VK_LEFT:
					tank1.setDir(DirectionEnum.LEFT);
					break;
				case KeyEvent.VK_UP:
					tank1.setDir(DirectionEnum.UP);
					break;
				case KeyEvent.VK_RIGHT:
					tank1.setDir(DirectionEnum.RIGHT);
					break;
				case KeyEvent.VK_DOWN:
					tank1.setDir(DirectionEnum.DOWN);
					break;
				default:
					break;
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			moving = false;
		}

	}


}
