package util;

import java.io.IOException;
import java.net.InetSocketAddress;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.mapred.JobClient;

/**
 * hadoop 公共类
 * @author fansy
 *
 */
public class HadoopUtil {
	private static Log log=LogFactory.getLog(HadoopUtil.class);
	public static String HOST=null;
	public static int JOBTRACKER_PORT;
	public static int NAMENODE_PORT;
	
	// JobClient
	public static JobClient jobClient;
	// Configuration 
	public static Configuration conf;
	
	
	// limite rows
	public static final int LIMITE_ROWS=5000;
	
	public static void initialConf(){
		if(conf==null){
			conf=new Configuration();
			conf.set("mapred.job.tracker", HOST+":"+JOBTRACKER_PORT);
			conf.set("fs.default.name", HOST+":"+NAMENODE_PORT);
		}
	}
	
	public static Configuration getConf(){
		if(HOST==null||JOBTRACKER_PORT==0||NAMENODE_PORT==0){
			return null;
		}
		return conf;
	}
	
	/**
	 * initial job client
	 * @return true or false  
	 */
	public static boolean initialJobClient(){
		if(HOST==null||JOBTRACKER_PORT==0||NAMENODE_PORT==0){
			return false;
		}
		log.info("Initial  job client begins...");
		boolean flag=true;
		try {
			InetSocketAddress jobTracker=new InetSocketAddress(HOST,JOBTRACKER_PORT);
			jobClient=new JobClient(jobTracker, getConf());
		} catch (IOException e) {
			flag=false;
			log.info("Job client can't be got\n"+e.getMessage());
		}
		if(flag){
			log.info("Initial  job client done!!!");
		}
		return flag;
	}
	
}
