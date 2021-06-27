package com.huozhonghun.tank.common.config;

import java.io.IOException;
import java.util.Properties;

/**
 * @author linweicheng
 * @create $2021-06-27-16:37
 */
public class TankProperty {
	private static Properties properties = new Properties();

	static {
		try {
			properties.load(TankProperty.class.getClassLoader().getResourceAsStream("config"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Object get(String key) {
		if(properties == null) return null;
		return properties.get(key);
	}

	public static void main(String[] args) {
		System.out.println(TankProperty.get("FRAME_WIDTH"));
	}
}
