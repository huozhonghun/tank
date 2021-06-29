package com.huozhonghun.tank.enity;

import com.huozhonghun.tank.common.config.TankProperty;
import com.huozhonghun.tank.enums.DirectionEnum;
import com.huozhonghun.tank.utils.Audio;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Objects;

/**
 * 坦克框架对象
 *
 * @author weichenglin
 * @create $2021-06-21-上午 11:14:30
 */
public class TankFrame extends Frame {

	public static final int FRAME_WIDTH = Objects.isNull(TankProperty.get("FRAME_WIDTH")) ? 0 :  Integer.valueOf(TankProperty.get("FRAME_WIDTH").toString());

	public static final int FRAME_HEIGHT = Objects.isNull(TankProperty.get("FRAME_HEIGHT")) ? 0 : Integer.valueOf(TankProperty.get("FRAME_HEIGHT").toString());

	private GameModel gm = GameModel.getINSTANCE();

	public TankFrame() {
		// 窗口大小
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
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

		// 发出声音
		new Thread(()->new Audio("audio/war1.wav").play()).start();
	}

	// 画出物体
	@Override
	public void paint(Graphics g) {
		gm.paint(g);
	}

	Image offScreenImage = null;
	@Override
	public void update(Graphics g) {
		if(offScreenImage == null) {
			offScreenImage = this.createImage(FRAME_WIDTH, FRAME_HEIGHT);
		}
		Graphics gOffScreen = offScreenImage.getGraphics();
		Color c = gOffScreen.getColor();
		gOffScreen.setColor(Color.BLACK);
		gOffScreen.fillRect(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
		gOffScreen.setColor(c);
		paint(gOffScreen);
		g.drawImage(offScreenImage, 0, 0, null);
	}

	class MyKeyListener extends KeyAdapter {

		boolean UM = false;
		boolean DM = false;
		boolean LM = false;
		boolean RM = false;

		@Override
		public void keyPressed(KeyEvent e) {
			int keyCode = e.getKeyCode();

			switch(keyCode) {
				case KeyEvent.VK_LEFT:
					LM = true;
					break;
				case KeyEvent.VK_UP:
					UM = true;
					break;
				case KeyEvent.VK_RIGHT:
					RM = true;
					break;
				case KeyEvent.VK_DOWN:
					DM = true;
					break;
				default:
					break;
			}

			setTankDir();

		}

		@Override
		public void keyReleased(KeyEvent e) {
			int keyCode = e.getKeyCode();
			switch(keyCode) {
				case KeyEvent.VK_LEFT:
					LM = false;
					break;
				case KeyEvent.VK_UP:
					UM = false;
					break;
				case KeyEvent.VK_RIGHT:
					RM = false;
					break;
				case KeyEvent.VK_DOWN:
					DM = false;
					break;
				case KeyEvent.VK_CONTROL:
					gm.getPlayer().fire();
				default:
					break;
			}
			setTankDir();
		}

		void setTankDir(){
			Tank player = gm.getPlayer();
			if(!UM && !DM && !LM && !RM){
				player.setMoving(false);
			}else{
				player.setMoving(true);
				if(UM) player.setDir(DirectionEnum.UP);
				if(DM) player.setDir(DirectionEnum.DOWN);
				if(LM) player.setDir(DirectionEnum.LEFT);
				if(RM) player.setDir(DirectionEnum.RIGHT);
				// 按下键盘发出声音
				new Thread(()->new Audio("audio/tank_move.wav").play()).start();
			}
		}


	}


}
