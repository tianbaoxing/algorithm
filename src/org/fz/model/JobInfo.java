package org.fz.model;

/**
 * @author fansy
 * @date 2013-12-18
 */
public class JobInfo {

	private String jobId;
	private String jobName;
	private float mapProgress;
	private float redProgress;
	private String runState; // running state : running,failed,successed, notRunning
	
	public JobInfo(){
		
	}
	
	public JobInfo(String jobId){
		this.jobId=jobId;
		this.jobName="--";
		this.mapProgress=0.0f;
		this.redProgress=0.0f;
		this.runState="--";
	}
	public JobInfo(String jobId,String jobName,float map,float red){
		this.jobId=jobId;
		this.jobName=jobName;
		this.mapProgress=map;
		this.redProgress=red;
		this.runState="--";
	}
	public JobInfo(String jobId,String jobName,float map,float red,String runState){
		this.jobId=jobId;
		this.jobName=jobName;
		this.mapProgress=map;
		this.redProgress=red;
		this.runState=runState;
	}

	public String getJobId() {
		return jobId;
	}
	public String getJobName() {
		return jobName;
	}
	public float getMapProgress() {
		return mapProgress;
	}
	public float getRedProgress() {
		return redProgress;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public void setMapProgress(float mapProgress) {
		this.mapProgress = mapProgress;
	}
	public void setRedProgress(float redProgress) {
		this.redProgress = redProgress;
	}
	
	public String getRunState() {
		return runState;
	}

	public void setRunState(String runState) {
		this.runState = runState;
	}

	@Override
	public String toString(){
		return "jobId:"+this.jobId+",jobName:"+this.jobName+",map:"+this.mapProgress
				+",reduce:"+this.redProgress+",runState:"+this.runState;
	
	}
}
