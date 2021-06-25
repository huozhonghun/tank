package com.huozhonghun.tank.enity;

import com.huozhonghun.tank.enums.DirectionEnum;
import com.huozhonghun.tank.enums.Group;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * 坦克框架对象
 *
 * @author weichenglin
 * @create $2021-06-21-上午 11:14:30
 */
public class TankFrame extends Frame {

	private static final int FRAME_WIDTH = 800;

	private static final int FRAME_HEIGHT = 800;


	Tank player = new Tank(150, 150, Group.GOOD, this);

	List<Bullet> bulletList = new ArrayList<Bullet>();

	public List<Tank> tankList = new ArrayList<Tank>();

	Explosion explosion = new Explosion(200, 200, this);

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
	}

	// 画出物体
	@Override
	public void paint(Graphics g) {
		Color color = g.getColor();
		g.setColor(Color.red);
		// 展示物体数量
		g.drawString("敌军坦克数量：" + tankList.size(), 20, 60);
		g.drawString("子弹数量：" + bulletList.size(), 20, 80);
		// 设为原来的颜色，保证不影响其他对象
		g.setColor(color);

		player.paint(g);
		// 遍历存活子弹
		for (int i = 0; i < bulletList.size(); i++) {
			bulletList.get(i).paint(g);
		}

		// 创建敌军坦克
		for (int i = 0; i < tankList.size(); i++) {
			tankList.get(i).paint(g);
		}

		// 坦克碰撞，子弹碰撞
		for (int i = 0; i < tankList.size(); i++) {
			for (int j = 0; j < bulletList.size(); j++) {
				tankList.get(i).collision(bulletList.get(j));
			}
		}

		explosion.paint(g);

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
					player.fire();
				default:
					break;
			}
			setTankDir();
		}

		void setTankDir(){
			if(!UM && !DM && !LM && !RM){
				player.setMoving(false);
			}else{
				player.setMoving(true);
				if(UM) player.setDir(DirectionEnum.UP);
				if(DM) player.setDir(DirectionEnum.DOWN);
				if(LM) player.setDir(DirectionEnum.LEFT);
				if(RM) player.setDir(DirectionEnum.RIGHT);
			}
		}


	}


}
