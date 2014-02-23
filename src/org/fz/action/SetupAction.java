package org.fz.action;

import util.HadoopUtil;

import com.opensymphony.xwork2.ActionSupport;

public class SetupAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private String jobtracker;
	private int jobtrackerPort;
	private String namenode;
	private int namenodePort;
	
	private String info;

	public String execute(){
		HadoopUtil.conf=null;
		HadoopUtil.HOST=null;
		HadoopUtil.jobClient=null;
		if(jobtracker.equals(namenode)||jobtracker==namenode){
			HadoopUtil.HOST=jobtracker;
		}else{
			setInfo("jobtracker和namenode配置不一致");
			return SUCCESS;	
		}
		HadoopUtil.JOBTRACKER_PORT=jobtrackerPort;
		HadoopUtil.NAMENODE_PORT=namenodePort;
		HadoopUtil.initialConf();
		if(HadoopUtil.getConf()==null){
			setInfo("初始化configuration 失败");
			return SUCCESS;	
		}
		boolean flag=HadoopUtil.initialJobClient() ;
		if(!flag){
			setInfo("初始化 job Client失败");
			return SUCCESS;	
		}
		
		setInfo("集群配置成功！！！");
		return SUCCESS;	
	}
	public String getJobtracker() {
		return jobtracker;
	}
	public void setJobtracker(String jobtracker) {
		this.jobtracker = jobtracker;
	}

	public String getNamenode() {
		return namenode;
	}
	public void setNamenode(String namenode) {
		this.namenode = namenode;
	}
	public int getJobtrackerPort() {
		return jobtrackerPort;
	}
	public void setJobtrackerPort(int jobtrackerPort) {
		this.jobtrackerPort = jobtrackerPort;
	}
	public int getNamenodePort() {
		return namenodePort;
	}
	public void setNamenodePort(int namenodePort) {
		this.namenodePort = namenodePort;
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
