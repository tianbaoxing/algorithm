/**
 * 
 */
package util;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobStatus;
import org.apache.hadoop.mapreduce.JobID;
import org.fz.model.JobInfo;

import com.google.common.io.Closeables;

/**
 * @author fansy
 * @date 2013-12-18
 */
public class MonitorUtil {
	private static Log log=LogFactory.getLog(MonitorUtil.class);
	
	public static String lastJobId;
	public static Map<String ,JobInfo> monitorJobs;
	
	public static void initialMonitorJobs(int jobNums) throws IOException{
		
		JobStatus[] jobStatusAll=HadoopUtil.jobClient.getAllJobs();
		JobStatus jobStatus=null;
		int id =0;
		String jobIden="";
		/**
		 * 防止当前云平台是第一次启动，这个时候没有任务列表，获取的jobStatus是空;
		 */
		if(jobStatusAll==null||jobStatusAll.length<=0){
			//修改TaskTracker代码，把集群启动时间写入hdfs，然后在这里读取出来
			id=0;
			jobIden=readJTStartTime();
			
		}else{
			jobStatus=jobStatusAll[jobStatusAll.length-1];
			id=jobStatus.getJobID().getId();
			jobIden=jobStatus.getJobID().getJtIdentifier();
		}
		
		log.info("initial monitorJobs with the start jobID :"+id);
		 
		monitorJobs=new LinkedHashMap<String,JobInfo>();
		String jobId="";
		for(int i=0;i<jobNums;i++){
			jobId= new JobID(jobIden,id+1+i).toString();
			
			monitorJobs.put(jobId, new JobInfo(jobId));
		}
		lastJobId=jobId;
		log.info("initial monitor jobs map done !!!");
	}
	/**
	 * 获取最新的jobStatus
	 * @return
	 * @throws IOException
	 */
	public static JobStatus getNewJobStatus() throws IOException{
		JobStatus[] jobStatusAll=new JobClient(new InetSocketAddress(HadoopUtil.HOST,
				HadoopUtil.JOBTRACKER_PORT), HadoopUtil.getConf()).getAllJobs();
		if(jobStatusAll.length<=0){
			return null;
		}
		JobStatus jobStatus=jobStatusAll[jobStatusAll.length-1];
		return jobStatus;
	}
	/**
	 * 集群是否正在运行
	 */
	public static boolean ISRUNNING=false;

	/**
	 * 算法那是否运行完毕
	 * @return
	 */
	public static boolean checkRunOver(){
		boolean flag= false;
		JobInfo jobInfo= monitorJobs.get(lastJobId);
		if(jobInfo==null){
			return flag;
		}
		if("successed".equals(jobInfo.getRunState())){
			flag=true;
		}
		return flag;
	}
	
	private static String readJTStartTime() throws IOException{
		Path path= new Path("/private/jobtracker/starttime");
	    FileSystem fs = FileSystem.get(path.toUri(), HadoopUtil.getConf());
	    FSDataInputStream in = fs.open(path);
	    try {
	      return in.readUTF();
	    } finally {
	      Closeables.closeQuietly(in);
	    }
	    
	}
}
