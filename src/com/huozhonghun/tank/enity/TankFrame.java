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

	// 定义横纵坐标
	private int x,y=10;

	// 默认方向向下
	private DirectionEnum dir = DirectionEnum.DOWN;

	// 默认无操作
	private Boolean press = false;

	// 每次移动步数
	private static final int step = 10;



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
		// 画出物体的位置和大小
		g.fillRect(x,y,50,50);
		if(press){
			// 判断方向来移动坦克
			switch (dir) {
				case UP:
					y -= step;
					break;
				case DOWN:
					y += step;
					break;
				case LEFT:
					x -= step;
					break;
				case RIGHT:
					x += step;
					break;
			}
		}
	}

	class MyKeyListener extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent e) {
			int keyCode = e.getKeyCode();
			press = true;
			switch(keyCode) {
				case KeyEvent.VK_LEFT:
					dir = DirectionEnum.LEFT;
					break;
				case KeyEvent.VK_UP:
					dir = DirectionEnum.UP;
					break;
				case KeyEvent.VK_RIGHT:
					dir = DirectionEnum.RIGHT;
					break;
				case KeyEvent.VK_DOWN:
					dir = DirectionEnum.DOWN;
					break;
				default:
					break;
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			press = false;
//			int keyCode = e.getKeyCode();
//			switch(keyCode) {
//				case KeyEvent.VK_LEFT:
//					dir = DirectionEnum.LEFT;
//					break;
//				case KeyEvent.VK_UP:
//					dir = DirectionEnum.UP;
//					break;
//				case KeyEvent.VK_RIGHT:
//					dir = DirectionEnum.RIGHT;
//					break;
//				case KeyEvent.VK_DOWN:
//					dir = DirectionEnum.DOWN;
//					break;
//				default:
//					break;
//			}
		}

	}


}
