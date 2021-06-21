package com.huozhonghun.tank;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author weichenglin
 * @create $2021-06-21-上午 10:56:50
 */
public class Test {
	public static void main(String[] args) {
		Frame f = new Frame();
		f.setSize(800, 600);
		f.setResizable(false);
		f.setTitle("tank war");
		f.setVisible(true);

		f.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}

		});
	}
}
