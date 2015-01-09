package com.movitech.framework.net.protocol;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author: Wu Dayu
 * @En_Name: David Wu
 * @E-mail: wudayu@gmail.com
 * @Created Time: Nov 5, 2014, 11:28:03 PM
 * @Description: BaseResult是由服务器返回的通用Json所对应的对象，其所包含的字段在每个服务器返回的字段中都包含
 *
 **/

@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseResult {

	@JsonProperty(value = "result")
	private boolean result;

	@JsonProperty(value = "value")
	private String value;

	public boolean getResultSuccess() {
		return result;
	}

	public String getResultMsg() {
		return value;
	}

	@Override
	public String toString() {
		return "result = " + result + ", value = " + value;
	}
}
