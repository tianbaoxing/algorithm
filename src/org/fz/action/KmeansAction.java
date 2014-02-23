package org.fz.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.fz.mahout.driver.KmeansDriverRunnable;

import util.HadoopUtil;
import util.MonitorUtil;

import com.opensymphony.xwork2.ActionSupport;

public class KmeansAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private static Log log=LogFactory.getLog(KmeansAction.class); 
	
	private String input;
	private String output;
	private String distanceMeasure;
	private int numClusters ;
	private int maxIter;
	private String clustering;
	private String method;
	private String splitter;
	
	private int jobNums;
	
	private String info;
	
	public String execute(){
		if(HadoopUtil.getConf()==null){
			setInfo("集群配置错误或者未初始化,请先配置集群!!!");
			log.info("集群配置错误或者未初始化,请先配置集群!!!");
			return "initial";
		}
		KmeansDriverRunnable kmeansDriver=new KmeansDriverRunnable( input, output, distanceMeasure, numClusters,
				 maxIter, clustering, method, splitter);
		MonitorUtil.monitorJobs=null;
		new Thread(kmeansDriver).start();
		if("true".equals(clustering)){
			jobNums=maxIter+2;
		}else{
			jobNums=maxIter+1;
		}
		return SUCCESS;
	}



	public String getInput() {
		return input;
	}



	public void setInput(String input) {
		this.input = input;
	}



	public String getOutput() {
		return output;
	}



	public void setOutput(String output) {
		this.output = output;
	}



	public String getDistanceMeasure() {
		return distanceMeasure;
	}



	public void setDistanceMeasure(String distanceMeasure) {
		this.distanceMeasure = distanceMeasure;
	}



	public int getNumClusters() {
		return numClusters;
	}



	public void setNumClusters(int numClusters) {
		this.numClusters = numClusters;
	}



	public int getMaxIter() {
		return maxIter;
	}



	public void setMaxIter(int maxIter) {
		this.maxIter = maxIter;
	}



	public String isClustering() {
		return clustering;
	}



	public void setClustering(String clustering) {
		this.clustering = clustering;
	}



	public String getMethod() {
		return method;
	}



	public void setMethod(String method) {
		this.method = method;
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



	public String getSplitter() {
		return splitter;
	}



	public void setSplitter(String splitter) {
		this.splitter = splitter;
	}



	/**
	 * @return the jobNums
	 */
	public int getJobNums() {
		return jobNums;
	}



	/**
	 * @param jobNums the jobNums to set
	 */
	public void setJobNums(int jobNums) {
		this.jobNums = jobNums;
	}
}
