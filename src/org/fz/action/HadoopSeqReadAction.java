package org.fz.action;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import util.HadoopIOUtil;
import util.HadoopUtil;

import com.opensymphony.xwork2.ActionSupport;
/**
 * 本来打算使用mr程序来读取和转换的，这里的读取就直接使用了一般的java程序
 * 后续更改为mr程序的
 * @author fansy
 *
 */
public class HadoopSeqReadAction extends ActionSupport {
	private static Log log=LogFactory.getLog(HadoopSeqReadAction.class); 

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
		try {
			data=HadoopIOUtil.readSeqHdfs(hdfsPath);
			data=data.replaceAll("\n", "<br>");
		} catch (IOException e) {
			log.info("读取序列文件"+hdfsPath.toString()+"失败!!!\n"+e.getMessage());
			e.printStackTrace();
		}
		if(data==null||"".equals(data)){
			setInfo("读取序列文件"+hdfsPath.toString()+"失败!!!");
			data=null;
			log.info("读取序列文件"+hdfsPath.toString()+"失败!!!");
		}
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
