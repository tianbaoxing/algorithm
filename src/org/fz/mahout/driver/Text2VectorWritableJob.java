package org.fz.mahout.driver;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.SequenceFileOutputFormat;
import org.apache.mahout.common.AbstractJob;
import org.apache.mahout.common.HadoopUtil;
import org.apache.mahout.math.RandomAccessSparseVector;
import org.apache.mahout.math.Vector;
import org.apache.mahout.math.VectorWritable;

public class Text2VectorWritableJob extends AbstractJob{
	private static String SPLITTER="splitter";
	@Override
	public int run(String[] arg0) throws Exception {
		addInputOption();
	    addOutputOption();
	    addOption("splitter", "sp", "original data splitter", ",");
	    if (parseArguments(arg0) == null) {
		      return -1;
		}
	    Path input=getInputPath();
	    Path output=getOutputPath();
	    
	    String splitter=getOption("splitter");
	    Configuration conf=getConf();
	    conf.set(Text2VectorWritableJob.SPLITTER, splitter);
	    HadoopUtil.delete(conf, output);
	    Job job=new Job(conf,"text2vectorWritable with input:"+input.toString());
	    job.setOutputFormatClass(SequenceFileOutputFormat.class);
	    job.setMapperClass(Text2VectorWritableMapper.class);
	    job.setMapOutputKeyClass(LongWritable.class);
	    job.setMapOutputValueClass(VectorWritable.class);
	    job.setReducerClass(Text2VectorWritableReducer.class);
	    job.setOutputKeyClass(LongWritable.class);
	    job.setOutputValueClass(VectorWritable.class);
	    job.setJarByClass(Text2VectorWritableJob.class);
	     
	    FileInputFormat.addInputPath(job, input);
	    SequenceFileOutputFormat.setOutputPath(job, output);
	    if (!job.waitForCompletion(true)) {
	        throw new InterruptedException("Text to VectorWritable Job failed processing " + input);
	      }
		return 0;
	}
	
	public static class Text2VectorWritableMapper extends Mapper<LongWritable,Text,LongWritable,VectorWritable>{
		private String splitter="" ;
		@Override
		public void setup(Context cxt){
			splitter=cxt.getConfiguration().get(Text2VectorWritableJob.SPLITTER);
		}
		
		public void map(LongWritable key,Text value,Context context)throws IOException,InterruptedException{
			String[] str=value.toString().split(splitter);
			Vector vector=new RandomAccessSparseVector(str.length);
			for(int i=0;i<str.length;i++){
				vector.set(i, Double.parseDouble(str[i]));
			}
			VectorWritable va=new VectorWritable(vector);
			context.write(key, va);
		}
	}
	
	/**
	 * Reducer: do nothing but output
	 * @author fansy
	 *
	 */
	public static class Text2VectorWritableReducer extends Reducer<LongWritable,VectorWritable,LongWritable,VectorWritable>{
		public void reduce(LongWritable key,Iterable<VectorWritable> values,Context context)throws IOException,InterruptedException{
			for(VectorWritable v:values){
				context.write(key, v);
			}
		}
	}
	
}
