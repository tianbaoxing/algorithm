package org.fz.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.fz.mahout.driver.CanopyDriverRunnable;

import util.HadoopUtil;
import util.MonitorUtil;

import com.opensymphony.xwork2.ActionSupport;

public class CanopyAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private static Log log=LogFactory.getLog(CanopyAction.class); 
	
	private String input;
	private String output;
	private String distanceMeasure;
	private double t1 ;
	private double t2;
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
		CanopyDriverRunnable canopyDriver=new CanopyDriverRunnable( input, output, distanceMeasure, t1,
				 t2, clustering, method, splitter);
		MonitorUtil.monitorJobs=null;
		new Thread(canopyDriver).start();
		if("true".equals(clustering)){
			jobNums=3;
		}else{
			jobNums=2;
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

	public double getT1() {
		return t1;
	}

	public void setT1(double t1) {
		this.t1 = t1;
	}

	public double getT2() {
		return t2;
	}

	public void setT2(double t2) {
		this.t2 = t2;
	}
}
