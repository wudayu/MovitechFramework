package com.movitech.framework.net.protocol;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author: Wu Dayu
 * @En_Name: David Wu
 * @E-mail: wudayu@gmail.com
 * @Created Time: Nov 5, 2014, 11:28:37 PM
 * @Description: DafStringResult是继承于BaseResult的数据对象，其承载了一个String对象
 *
 **/

@JsonIgnoreProperties(ignoreUnknown = true)
public class DafStringResult extends BaseResult {

	@JsonProperty(value = "objValue")
	private String str;

	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}

	@Override
	public String toString() {
		return "DafStringResult [string=" + str + "]";
	}
}
