package com.huozhonghun.tank.enity;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author weichenglin
 * @create $2021-06-21-上午 11:14:30
 */
public class TankFrame extends Frame {
	public TankFrame() {
		// 窗口大小
		setSize(800, 600);
		// 窗口大小是否可调整
		setResizable(true);
		// 窗口标题
		setTitle("坦克大战");
		// 是否显示窗口
		setVisible(true);

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
		g.fillRect(100,100,50,50);
	}

}
