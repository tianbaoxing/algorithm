package org.fz.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import util.HadoopIOUtil;
import util.HadoopUtil;

import com.opensymphony.xwork2.ActionSupport;

public class HadoopReadAction extends ActionSupport {
	private static Log log=LogFactory.getLog(HadoopReadAction.class); 

	private static final long serialVersionUID = 1L;
	private String hdfsPath;
	
	private String data;
	private String info;
	
	public String execute(){
		
		if(HadoopUtil.getConf()==null){
			setInfo("集群配置错误或者未初始化,请先配置集群!!!");
			data=null;
			log.info("集群配置错误或者未初始化,请先配置集群!!!");
			return SUCCESS;
		}
		data=readHDFS(hdfsPath);
		if(data==null||"".equals(data)){
			setInfo("读取文件"+hdfsPath.toString()+"失败!!!");
			data=null;
			log.info("读取文件"+hdfsPath.toString()+"失败!!!");
		}
		return SUCCESS;
		
	}

	private String readHDFS(String file){
		
		String data=HadoopIOUtil.readHdfs(file);
		return data.replaceAll("\r\n", "<br>");
	}
	/**
	 * @return the hdfsPath
	 */
	public String getHdfsPath() {
		return hdfsPath;
	}

	/**
	 * @param hdfsPath the hdfsPath to set
	 */
	public void setHdfsPath(String hdfsPath) {
		this.hdfsPath = hdfsPath;
	}

	/**
	 * @return the data
	 */
	public String getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(String data) {
		this.data = data;
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
