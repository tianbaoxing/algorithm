package org.fz.mahout.driver;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.util.ToolRunner;
import org.apache.mahout.clustering.kmeans.KMeansDriver;
import org.junit.Test;

public class KmeansDriverTest {

	@Test
	public void testRun() throws Exception{
		String[] args={
				"-fs","fansyPC:9000","-jt","fansyPC:9001",
			"-i","/temp/1388580992582/txt2vecOut/part-r-00000",
			"-o","/temp/1388580992582/kmeans_out05",
			"-c","/temp/1388580992582/kmeans_clusters/part-randomSeed",
			"-k","2",
			"-x","1"
		};
		
		Configuration conf=new Configuration();
		/*conf.set("mapred.job.tracker", "fansyPC:9001");
		conf.set("fs.default.name", "fansyPC:9000");*/
		System.out.println(conf.get("mapred.job.tracker"));
		System.out.println(conf.get("fs.default.name"));
		ToolRunner.run(conf, new KMeansDriver(), args);
	}
}
