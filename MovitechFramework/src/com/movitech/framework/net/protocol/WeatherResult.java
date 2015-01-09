package com.movitech.framework.net.protocol;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.movitech.framework.model.DafWeather;

/**
 *
 * @author: Wu Dayu
 * @En_Name: David Wu
 * @E-mail: wudayu@gmail.com
 * @Created Time: Oct 31, 2014, 3:52:30 PM
 * @Description: WeatherResult是根据气象台接口所单独设定的数据对象，接收天气Object Json
 *
 **/

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherResult {

	@JsonProperty(value = "weatherinfo")
	private DafWeather weather;

	public DafWeather getWeather() {
		return weather;
	}

	public void setWeather(DafWeather weather) {
		this.weather = weather;
	}

	@Override
	public String toString() {
		return "WeatherResult [weather=" + weather + "]";
	}

}
