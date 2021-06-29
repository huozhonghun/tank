package com.huozhonghun.tank.enity;

import com.huozhonghun.tank.enums.DirectionEnum;
import com.huozhonghun.tank.enums.Group;
import com.huozhonghun.tank.service.FireStrategy;
import com.huozhonghun.tank.service.impl.FourDirFireStrategy;
import com.huozhonghun.tank.service.impl.OneDirFireStrategy;
import com.huozhonghun.tank.utils.ResourceMgr;
import java.awt.*;
import java.util.Random;

/**
 * 坦克对象
 *
 * @author weichenglin
 * @create $2021-06-21-下午 03:46:53
 */
public class Tank {

	// 长度
	public static int WIDTH = ResourceMgr.goodTankU.getWidth();

	// 宽度
	public static int HEIGHT = ResourceMgr.goodTankU.getHeight();

	// 每次移动步数
	private static final int step = 2;

	// 横坐标
	private int x;

	// 纵坐标
	private int y;

	// 默认方向向下
	private DirectionEnum dir = DirectionEnum.DOWN;

	// 允许移动
	private boolean moving = false;

	// 对象存活状态
	private boolean survive = true;

	// 坦克分类
	private Group group;

	private Random random = new Random();

	public Rectangle rect;

	// 开火策略
	FireStrategy fs;

	public Tank(int x, int y, Group group) {
		this.x = x;
		this.y = y;
		this.group = group;

		rect = new Rectangle(this.x, this.y, WIDTH, HEIGHT);

		if(this.group == Group.BAD){
			fs = new OneDirFireStrategy();
		}else{
			fs = new FourDirFireStrategy();
		}

	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public void setDir(DirectionEnum dir) {
		this.dir = dir;
	}

	public DirectionEnum getDir() {
		return dir;
	}

	public void setMoving(boolean moving) {
		this.moving = moving;
	}

	public void paint(Graphics g){

		if (!survive){
			// 清理多余对象
			GameModel.getINSTANCE().tankList.remove(this);
		}

		switch (dir) {
			case UP:
				g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankU : ResourceMgr.badTankU, x, y, null);
				break;
			case DOWN:
				g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankD : ResourceMgr.badTankD, x, y, null);
				break;
			case LEFT:
				g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankL : ResourceMgr.badTankL, x, y, null);
				break;
			case RIGHT:
				g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankR : ResourceMgr.badTankR, x, y, null);
				break;
			default: break;
		}

		if(this.group.equals(Group.BAD)){

			WIDTH = ResourceMgr.badTankU.getWidth();
			HEIGHT = ResourceMgr.badTankU.getHeight();

			if(random.nextInt(100)>90){
				this.fire();
			}

			if(random.nextInt(100)>90){
				moving = true;
				this.dir = DirectionEnum.values()[random.nextInt(4)];
			}

		}

		/*// 画出物体的位置和大小
		g.setColor(Color.GREEN);
		g.fillRect(x,y,WIDTH,HEIGHT);
		g.setColor(g.getColor()); // 设置为原来的颜色*/

		move();
	}

	private void move(){
		if(moving){
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
				default: break;
			}
		}

		// 限制边界
		boundaryLimit();

		rect.x = this.x;
		rect.y = this.y;
	}

	private void boundaryLimit() {
		// 坐标从左上角开始算
		if(x < 2) x = 2;
		if(x > TankFrame.FRAME_WIDTH - this.WIDTH - 2) x = TankFrame.FRAME_WIDTH - this.WIDTH - 2;
		if(y < 65) y = 65;
		if(y > TankFrame.FRAME_HEIGHT - this.HEIGHT - 2) y = TankFrame.FRAME_HEIGHT - this.HEIGHT - 2;
	}

	// 开火
	public void fire(){
//		tankFrame.bulletList.add(new Bullet(x + WIDTH/2 - Bullet.WIDTH/2, y + HEIGHT/2 - Bullet.HEIGHT/2, group, dir, this.tankFrame));
//
//		if(this.group == Group.GOOD){
//			// 开火发出声音
//			new Thread(()->new Audio("audio/tank_fire.wav").play()).start();
//		}
		fs.fire(this);
	}

	// 坦克子弹对象碰撞
	public void collision(Bullet bullet){
		if(this.group ==  bullet.getGroup()) return;
		if(this.rect.intersects(bullet.rect)) {
			this.die();
			bullet.die();
			GameModel.getINSTANCE().explosionList.add(new Explosion(x + WIDTH/2 - Explosion.WIDTH/2, y + HEIGHT/2 - Explosion.HEIGHT/2));
		}
	}

	public void die(){
		this.survive = false;
	}

}
