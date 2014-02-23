package org.fz.action;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import util.HadoopIOUtil;
import util.HadoopUtil;

import com.opensymphony.xwork2.ActionSupport;
/**
 * 使用java一般方法，而不是用mr形式
 * 以后补充
 * @author fansy
 *
 */
public class HadoopSeq2TxtAction extends ActionSupport {
	private static Log log=LogFactory.getLog(HadoopSeq2TxtAction.class); 

	private static final long serialVersionUID = 1L;
	private String hdfsPath;
	private String localPath;
	private String info;
	
	public String execute(){
		
		if(HadoopUtil.getConf()==null){
			setInfo("集群配置错误或者未初始化,请先配置集群!!!");
			log.info("集群配置错误或者未初始化,请先配置集群!!!");
			return SUCCESS;
		}
		boolean flag=hadoopSeq2Txt();
		if(!flag){
			setInfo("转换文件"+hdfsPath.toString()+"失败!!!");
			log.info("转换文件"+hdfsPath.toString()+"失败!!!");
		}
		setInfo("转换文件"+hdfsPath+"到"+localPath+"成功!!!");
		return SUCCESS;
		
	}

	private boolean hadoopSeq2Txt(){
		boolean flag=true;
		try {
			String data=HadoopIOUtil.readSeqHdfs(hdfsPath);
			HadoopIOUtil.writeToLocal(localPath, data);
		} catch (IOException e) {
			log.info("转换文件"+hdfsPath.toString()+"失败!!!");
			flag=false;
		}
		
		return flag;
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

	/**
	 * @return the localPath
	 */
	public String getLocalPath() {
		return localPath;
	}

	/**
	 * @param localPath the localPath to set
	 */
	public void setLocalPath(String localPath) {
		this.localPath = localPath;
	}
}
