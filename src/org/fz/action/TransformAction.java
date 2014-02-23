/**
 * 
 */
package org.fz.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.mapred.JobStatus;
import org.fz.model.JobInfo;

import util.HadoopUtil;
import util.MonitorUtil;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author fansy
 * @date 2013-12-18
 */
public class TransformAction extends ActionSupport {
	
	private static final long serialVersionUID = 1L;
	private Log log=LogFactory.getLog(TransformAction.class);
	private boolean flag;
	private List<JobInfo> jobInfosList=null;
	
	private int jobNums;
	
	public String execute(){

		/*if(!MonitorUtil.ISRUNNING){
			log.info("the cluster is running!!!");
			return ERROR;
		}*/
		try {
			
			if(MonitorUtil.monitorJobs!=null&&MonitorUtil.monitorJobs.size()>0){
				if(MonitorUtil.checkRunOver()){
					flag=true;
					jobInfosList=new ArrayList<JobInfo>(MonitorUtil.monitorJobs.values());
					return SUCCESS;
				}
				JobStatus jobStatus= MonitorUtil.getNewJobStatus();
				if(jobStatus==null){ //  集群是第一次启动，jobStatus是空
					flag=false;
					jobInfosList=new ArrayList<JobInfo>(MonitorUtil.monitorJobs.values());
					return SUCCESS;
				}
				/**
				 * 判断任务状态
				 */
				if(	MonitorUtil.monitorJobs.containsKey(jobStatus.getJobID().toString())){
					String jobName=HadoopUtil.jobClient.getJob(jobStatus.getJobID()).getJobName();
					if(jobStatus.getRunState()==JobStatus.RUNNING){
						log.info("jobid:"+jobStatus.getJobID().toString()+",status:"+JobStatus.RUNNING);
						MonitorUtil.monitorJobs.put(jobStatus.getJobID().toString(), new JobInfo(jobStatus.getJobID().toString(),
						jobName,jobStatus.mapProgress(),jobStatus.reduceProgress(),"running"));
					}else if(jobStatus.getRunState()==JobStatus.FAILED){
						log.info("jobid:"+jobStatus.getJobID().toString()+",status:"+JobStatus.FAILED);
						MonitorUtil.monitorJobs.put(jobStatus.getJobID().toString(), new JobInfo(jobStatus.getJobID().toString(),
								jobName,jobStatus.mapProgress(),jobStatus.reduceProgress(),"failed"));
					}else if(jobStatus.getRunState()==JobStatus.PREP){
						log.info("jobid:"+jobStatus.getJobID().toString()+",status:"+JobStatus.PREP);
					}else if(jobStatus.getRunState()==JobStatus.SUCCEEDED){
						log.info("jobid:"+jobStatus.getJobID().toString()+",status:"+JobStatus.SUCCEEDED);
						MonitorUtil.monitorJobs.put(jobStatus.getJobID().toString(), new JobInfo(jobStatus.getJobID().toString(),
								jobName,jobStatus.mapProgress(),jobStatus.reduceProgress(),"successed"));
					}else{
						log.info("unknown jobStatus:"+jobStatus.getRunState()+" ----------------------");
					}
				}else{
					log.info("not running any furthur jobs******************");
				}
			}else{
				//--** initial monitorJobs
				try{
					MonitorUtil.initialMonitorJobs(jobNums);
				}catch(Exception e){
					log.info("initialMonitorJobs error:\n"+e.getMessage());
					return ERROR;
				}
			}
			jobInfosList=new ArrayList<JobInfo>(MonitorUtil.monitorJobs.values());
		} catch (IOException e) {
			log.info("monitor is error :\n"+e.getMessage());
			return ERROR;
		}
		flag=false;
		return SUCCESS;
	}
	
	public boolean getFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public List<JobInfo> getJobInfosList() {
		return jobInfosList;
	}
	public void setJobInfosList(List<JobInfo> jobInfosList) {
		this.jobInfosList = jobInfosList;
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
