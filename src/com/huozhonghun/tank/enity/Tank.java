package com.huozhonghun.tank.enity;

import com.huozhonghun.tank.enums.DirectionEnum;
import com.huozhonghun.tank.enums.Group;
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

	// 坦克框架
	private TankFrame tankFrame;

	// 默认方向向下
	private DirectionEnum dir = DirectionEnum.DOWN;

	// 允许移动
	private boolean moving = false;

	// 对象存活状态
	private boolean survive = true;

	// 坦克分类
	private Group group;

	private Random random = new Random();

	public Tank(int x, int y, Group group, TankFrame tankFrame) {
		this.x = x;
		this.y = y;
		this.group = group;
		this.tankFrame = tankFrame; // 为了刷新子弹位置
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

	public void setDir(DirectionEnum dir) {
		this.dir = dir;
	}

	public void setMoving(boolean moving) {
		this.moving = moving;
	}

	public void paint(Graphics g){

		if (!survive){
			// 清理多余对象
			tankFrame.tankList.remove(this);
		}

		switch (dir) {
			case UP:
				g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankU : ResourceMgr.badTankU, x, y, null);
				break;
			case DOWN:
				g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankD : ResourceMgr.goodTankD, x, y, null);
				break;
			case LEFT:
				g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankL : ResourceMgr.goodTankL, x, y, null);
				break;
			case RIGHT:
				g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankR : ResourceMgr.goodTankR, x, y, null);
				break;
			default: break;
		}

		if(this.group.equals(Group.BAD)){

			WIDTH = ResourceMgr.badTankU.getWidth();
			HEIGHT = ResourceMgr.badTankU.getHeight();

			if(random.nextInt(20)>18){
				this.fire();
			}

			if(random.nextInt(20)>18){
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
	}

	// 开火
	public void fire(){
		tankFrame.bulletList.add(new Bullet(x + WIDTH/2 - Bullet.WIDTH/2, y + HEIGHT/2 - Bullet.HEIGHT/2, group, dir, this.tankFrame));
	}

	// 坦克子弹对象碰撞
	public void collision(Bullet bullet){
		if(this.group ==  bullet.getGroup()) return;
		Rectangle rect1 = new Rectangle(this.x, this.y, WIDTH, HEIGHT);
		Rectangle rect2 = new Rectangle(bullet.getX(), bullet.getY(), Bullet.WIDTH, Bullet.HEIGHT);
		if(rect1.intersects(rect2)) {
			this.die();
			bullet.die();
			tankFrame.explosionList.add(new Explosion(x + WIDTH/2 - Explosion.WIDTH/2, y + HEIGHT/2 - Explosion.HEIGHT/2, tankFrame));
		}
	}

	public void die(){
		this.survive = false;
	}

}
