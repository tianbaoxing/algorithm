package org.fz.mahout.driver;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.util.ToolRunner;
import org.apache.mahout.cf.taste.hadoop.item.RecommenderJob;

import util.HadoopUtil;

public class CollaDriverRunnable implements Runnable {
	
	private Log log= LogFactory.getLog(CollaDriverRunnable.class);
	
	private String input;
	private String output;
	private String similarityClassname;
	private int numRecommendations;
	private int maxPerfsPerUser;
	private int maxSimilaritiesPerItem;
	private int maxPrefsPerUserInItemSimilarity;
	
	private int minPrefsPerUser;
	
	private String booleanData;
	
	public CollaDriverRunnable(String input,String output,String similarity,int num,
			int maxPer,int maxSimi ,int maxPre,int minPre,String bool){
		this.input=input;
		this.output=output;
		this.similarityClassname=similarity;
		this.booleanData=bool;
		this.numRecommendations=num;
		this.maxPerfsPerUser=maxPer;
		this.maxSimilaritiesPerItem=maxSimi;
		this.maxPrefsPerUserInItemSimilarity=maxPre;
		this.minPrefsPerUser=minPre;
	}
	
	@Override
	public void run() {
		long startTime=System.currentTimeMillis();
		String tempOut="/temp/"+startTime+"/"+"colla_temp";
		String[] args=null;
		try {
			
			args=new String[18];
			if(StringUtils.isEmpty(this.input)||StringUtils.isEmpty(this.output)){
				log.info("no input and output ");
					return ;
			}
			args[0]="-i";
			args[1]=this.input;
			args[2]="-o";
			args[3]=output;
			args[4]="-s";
			args[5]=this.similarityClassname;
			args[6]="-n";
			args[7]=String.valueOf(this.numRecommendations);
			args[8]="-b";
			args[9]="true";
			args[10]="-mxp";
			args[11]=String.valueOf(this.maxPerfsPerUser);
			args[12]="-mp";
			args[13]=String.valueOf(this.minPrefsPerUser);
			args[14]="-mppuiis";
			args[15]=String.valueOf(this.maxPrefsPerUserInItemSimilarity);
			args[16]="--tempDir";
			args[17]=tempOut;
			org.apache.mahout.common.HadoopUtil.delete(HadoopUtil.getConf(),new Path(output));
			ToolRunner.run(getConf(), new RecommenderJob(),args);
		} catch (Exception e) {
			log.info("collaborative filtering error\n"+e.getMessage());
			e.printStackTrace();
			return ;
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
