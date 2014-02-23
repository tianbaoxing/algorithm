package org.fz.mahout.driver;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.util.ToolRunner;
import org.apache.mahout.cf.taste.hadoop.item.RecommenderJob;
import org.junit.Test;

import util.HadoopUtil;

public class CollaDriverTest {

	@Test
	public void testRun() throws Exception{
		String[] args={
			"-i","/temp/1388675661908/txt2vecOut",
			"-o","/temp/1388675661908/canopy",
			"-t1","20",
			"-t2","5",
			"--tempDir","/temp/1388675661908/canopy_temp"
		};
		HadoopUtil.JOBTRACKER_PORT=9001;
		HadoopUtil.HOST="ubuntu";
		HadoopUtil.NAMENODE_PORT=9000;
		Configuration conf=new Configuration();
		ToolRunner.run(conf, new RecommenderJob(), new String[]{"-i","a","-c"});
	//	ToolRunner.run(conf, new CanopyDriver(), args);
	}
}
