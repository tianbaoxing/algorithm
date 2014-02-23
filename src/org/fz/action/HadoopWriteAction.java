package org.fz.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import util.HadoopIOUtil;
import util.HadoopUtil;

import com.opensymphony.xwork2.ActionSupport;

public class HadoopWriteAction extends ActionSupport {
	private static Log log=LogFactory.getLog(HadoopWriteAction.class); 

	private static final long serialVersionUID = 1L;
	private String hdfsPath;
	
	private String modifiedMessage;
	private String info;
	
	public String execute(){
		
		if(HadoopUtil.getConf()==null){
			setInfo("集群配置错误或者未初始化,请先配置集群!!!");
			log.info("集群配置错误或者未初始化,请先配置集群!!!");
			return SUCCESS;
		}
		boolean flag=HadoopIOUtil.writeToHdfs(hdfsPath, modifiedMessage);
		if(!flag){
			setInfo("写入文件"+hdfsPath.toString()+"失败!!!");
			log.info("写入文件"+hdfsPath.toString()+"失败!!!");
		}
		setInfo("写入文件"+hdfsPath+"成功!!!");
		return SUCCESS;
		
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

	public String getModifiedMessage() {
		return modifiedMessage;
	}

	public void setModifiedMessage(String modifiedMessage) {
		this.modifiedMessage = modifiedMessage;
	}
}
