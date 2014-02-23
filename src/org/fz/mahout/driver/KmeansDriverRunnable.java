package org.fz.mahout.driver;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.util.ToolRunner;
import org.apache.mahout.clustering.kmeans.KMeansDriver;

import util.HadoopUtil;

enum MethodType { sequential,mapreduce};

public class KmeansDriverRunnable implements Runnable {
	
	private Log log= LogFactory.getLog(KmeansDriverRunnable.class);
	
	private String input;
	private String output;
	private String distanceMeasure;
	private int numClusters ;
	private int maxIter;
	private String clustering;
	private String method;
	
	private String splitter;
	
	public KmeansDriverRunnable(String input,String output,String distanceMeasure,int numClusters,
			int maxIter,String clustering,String method,String splitter){
		this.input=input;
		this.output=output;
		this.setDistanceMeasure(distanceMeasure);
		this.numClusters=numClusters;
		this.maxIter=maxIter;
		this.clustering=clustering;
		this.method=method;
		this.splitter=splitter;
	}
	
	@Override
	public void run() {
		long startTime=System.currentTimeMillis();
		String txt2vecOut="/temp/"+startTime+"/"+"txt2vecOut";
		
		/**
		 * first run the data transform progress
		 */
		int returnInt=-1;
		String[] args=null;
		try {
			args=new String[6];
			if(StringUtils.isEmpty(this.input)||StringUtils.isEmpty(this.output)||StringUtils.isEmpty(this.splitter)){
				log.info("no input and output and splitter");
					return ;
			}
			args[0]="-i";
			args[1]=this.input;
			args[2]="-o";
			args[3]=txt2vecOut;
			args[4]="-sp";
			args[5]=this.splitter;
			returnInt=ToolRunner.run(getConf(), new Text2VectorWritableJob(),args);
		} catch (Exception e) {
			log.info("transform data error\n"+e.getMessage());
			return ;
		}
		if(returnInt!=0){
			return;
		}
		/**
		 * run kmeans algorithm
		 */
		args=null;
		if("true".equals(clustering)){
			args=new String[17];
			args[16]="-cl";
		}else{
			args=new String[16];
		}
		args[0]="-i";
		args[1]=txt2vecOut;
		args[2]="-o";
		args[3]=this.output;
		args[4]="-dm";
		args[5]=getDistance(this.distanceMeasure);
		args[6]="-k";
		args[7]=String.valueOf(this.numClusters);
		args[8]="-x";
		args[9]=String.valueOf(this.maxIter);
		args[10]="-xm";
		args[11]=this.method;
		args[12]="--tempDir";
		args[13]="/temp/"+startTime+"/kmeans_temp/";
		args[14]="--clusters";
		args[15]="/temp/"+startTime+"/kmeans_clusters/";
		try {
			org.apache.mahout.common.HadoopUtil.delete(HadoopUtil.getConf(),new Path(output));
			ToolRunner.run(getConf(), new KMeansDriver(),args);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	private Configuration getConf(){
		Configuration conf=HadoopUtil.getConf();
		
		return conf;
	}

	public static void main(String[] args){
	//	new Thread(new KmeansDriverRunnable()).start();
	}

	
	public String getTempString(String input,String currentTime){
		return "/temp/"+currentTime+"/"+input;
	}
	
	private String getDistance(String dis){
		return "org.apache.mahout.common.distance."+dis;
	}
	public String getDistanceMeasure() {
		return distanceMeasure;
	}

	public void setDistanceMeasure(String distanceMeasure) {
		this.distanceMeasure = distanceMeasure;
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


	public String getSplitter() {
		return splitter;
	}

	public void setSplitter(String splitter) {
		this.splitter = splitter;
	}

	
}
