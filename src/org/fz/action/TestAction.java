package org.fz.action;

import com.opensymphony.xwork2.ActionSupport;

public class TestAction extends ActionSupport {

	private String info;
	public String execute(){
		// 修改这里 
		info="hi,<br>i am fansy";
		return "test";
	}
	/**
	 * @return the info
	 */
	public String getInfo() {
		return info;
	}
	/**
	 * @param info the info to set
	 */
	public void setInfo(String info) {
		this.info = info;
	}
}
