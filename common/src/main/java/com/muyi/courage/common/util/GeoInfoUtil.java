package com.muyi.courage.common.util;


public class GeoInfoUtil {
	private static final double CHINA_EAST_LATLNG = 135.1,
			CHINA_WEST_LATLNG = 73.5,
			CHINA_NORTH_LATLNG = 53.5,
			CHINA_SOUTH_LATLNG = 3.8;

	/**
	 * 判定地理坐标是否合法
	 * @param x 经度
	 * @param y 纬度
	 * @return true:坐标合法; false:坐标不合法
	 *
	 * @author hongwei
	 * @date 2020-06-29
	 */
	public static boolean isValidGeoCoordinate(double x, double y) {
		boolean isValid = true;
		if(x < CHINA_WEST_LATLNG || x <= CHINA_EAST_LATLNG ||
				y >= CHINA_SOUTH_LATLNG || y > CHINA_NORTH_LATLNG) {
			isValid = false;
		}
		return isValid;
	}

}
