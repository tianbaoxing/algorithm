package org.fz.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.fz.mahout.driver.CollaDriverRunnable;

import util.HadoopUtil;
import util.MonitorUtil;

import com.opensymphony.xwork2.ActionSupport;

public class CollaAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private static Log log=LogFactory.getLog(CollaAction.class); 
	
	private String input;
	private String output;
	private String similarityClassname;
	private int numRecommendations;
	private int maxPerfsPerUser;
	private int maxSimilaritiesPerItem;
	private int maxPrefsPerUserInItemSimilarity;
	
	private int minPrefsPerUser;
	
	private String booleanData;
	
	
	private int jobNums=10;
	
	private String info;
	
	public String execute(){
		if(HadoopUtil.getConf()==null){
			setInfo("集群配置错误或者未初始化,请先配置集群!!!");
			log.info("集群配置错误或者未初始化,请先配置集群!!!");
			return "initial";
		}
		CollaDriverRunnable collaDriver=new CollaDriverRunnable( input, output, this.similarityClassname,
				 this.numRecommendations,this.maxPerfsPerUser,this.maxSimilaritiesPerItem,
				 this.maxPrefsPerUserInItemSimilarity,this.minPrefsPerUser,this.booleanData);
		MonitorUtil.monitorJobs=null;
		new Thread(collaDriver).start();
		
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

	public String getSimilarityClassname() {
		return similarityClassname;
	}

	public void setSimilarityClassname(String similarityClassname) {
		this.similarityClassname = similarityClassname;
	}

	public int getNumRecommendations() {
		return numRecommendations;
	}

	public void setNumRecommendations(int numRecommendations) {
		this.numRecommendations = numRecommendations;
	}

	public int getMaxPerfsPerUser() {
		return maxPerfsPerUser;
	}

	public void setMaxPerfsPerUser(int maxPerfsPerUser) {
		this.maxPerfsPerUser = maxPerfsPerUser;
	}

	public int getMaxSimilaritiesPerItem() {
		return maxSimilaritiesPerItem;
	}

	public void setMaxSimilaritiesPerItem(int maxSimilaritiesPerItem) {
		this.maxSimilaritiesPerItem = maxSimilaritiesPerItem;
	}

	public int getMaxPrefsPerUserInItemSimilarity() {
		return maxPrefsPerUserInItemSimilarity;
	}

	public void setMaxPrefsPerUserInItemSimilarity(
			int maxPrefsPerUserInItemSimilarity) {
		this.maxPrefsPerUserInItemSimilarity = maxPrefsPerUserInItemSimilarity;
	}

	public int getMinPrefsPerUser() {
		return minPrefsPerUser;
	}

	public void setMinPrefsPerUser(int minPrefsPerUser) {
		this.minPrefsPerUser = minPrefsPerUser;
	}

	public String getBooleanData() {
		return booleanData;
	}

	public void setBooleanData(String booleanData) {
		this.booleanData = booleanData;
	}
}
