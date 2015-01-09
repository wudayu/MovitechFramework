package com.movitech.framework.constant;

/**
 *
 * @author: Wu Dayu
 * @En_Name: David Wu
 * @E-mail: wudayu@gmail.com
 * @Created Time: Oct 31, 2014, 4:07:32 PM
 * @Description: WeatherCityCode包含了城市的天气代码
 *
 **/

public class WeatherCityCode {

	public static final String SHANG_HAI = "101020100";

	public static final String CHANG_ZHOU = "101191101";

	public static final String SU_ZHOU = "101190401";

	public static final String NAN_JING = "101190101";

	public static final String WU_XI = "101190201";

	public static final String KUN_SHAN = "101190404";

	public static final String[] cityCodes = new String[] { SHANG_HAI, CHANG_ZHOU, SU_ZHOU, NAN_JING, WU_XI, KUN_SHAN };

	public static final String[] cityNames = new String[] { "上海", "常州", "苏州", "南京", "无锡", "昆山" };

	public static String findCityCodeByCityName(String cityName) {
		for (int i = 0; i < cityNames.length; ++i) {
			if (cityNames[i].equals(cityName)) {
				return cityCodes[i];
			}
		}

		return null;
	}

}
