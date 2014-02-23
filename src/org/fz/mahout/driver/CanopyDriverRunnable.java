package org.fz.mahout.driver;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.util.ToolRunner;
import org.apache.mahout.clustering.canopy.CanopyDriver;

import util.HadoopUtil;

public class CanopyDriverRunnable implements Runnable {
	
	private Log log= LogFactory.getLog(CanopyDriverRunnable.class);
	
	private String input;
	private String output;
	private String distanceMeasure;
	private double t1 ;
	private double t2;
	private String clustering;
	private String method;
	
	private String splitter;
	
	public CanopyDriverRunnable(String input,String output,String distanceMeasure,double t1,
			double t2,String clustering,String method,String splitter){
		this.input=input;
		this.output=output;
		this.setDistanceMeasure(distanceMeasure);
		this.t1=t1;
		this.t2=t2;
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
		 * run canopy algorithm
		 */
		args=null;
		if("true".equals(clustering)){
			args=new String[15];
			args[14]="-cl";
		}else{
			args=new String[14];
		}
		args[0]="-i";
		args[1]=txt2vecOut;
		args[2]="-o";
		args[3]=this.output;
		args[4]="-dm";
		args[5]=getDistance(this.distanceMeasure);
		args[6]="-t1";
		args[7]=String.valueOf(this.t1);
		args[8]="-t2";
		args[9]=String.valueOf(this.t2);
		args[10]="-xm";
		args[11]=this.method;
		args[12]="--tempDir";
		args[13]="/temp/"+startTime+"/canopy_temp/";
		try {
			org.apache.mahout.common.HadoopUtil.delete(HadoopUtil.getConf(),new Path(output));
			ToolRunner.run(getConf(), new CanopyDriver(),args);
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
