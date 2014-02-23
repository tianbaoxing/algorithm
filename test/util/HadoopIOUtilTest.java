package util;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.junit.BeforeClass;
import org.junit.Test;

import util.HadoopIOUtil;

public class HadoopIOUtilTest {

	private static HadoopIOUtil hiu;
	
	@BeforeClass
	public static void before(){
		hiu=new HadoopIOUtil();
	}
	
	@Test
	public void testWrite(){
		String data="abcddfsfd\nsdfkljsdkfljk\nsdf";
		String hdfsFile="/dev/a.txt";
		hiu.writeToHdfs(hdfsFile, data);
	}
	@Test
	public void testRead(){
		String hdfsFile="/dev/a.txt";
		String aa=hiu.readHdfs(hdfsFile).toString();
		System.out.println(aa);
	}
	@Test
	public void testDownload(){
		String local="e:/test.txt";
		String hdfs="/dev/a.txt";
		hiu.downloadFromHdfs(hdfs, local);
	}
	@Test
	public void testUpload(){
		String local="e:/a.txt";
		String hdfs="/dev/a.txt";
		hiu.uploadToHdfs(local, hdfs);
	}
	@Test
	public void testReadLocal(){
		String local="e:/test.txt";
		System.out.println(hiu.readLocalFile(local));
	}
	@Test
	public void tesWriteSeq(){
		String hdfs="/dev/b.seq";
		Configuration conf=new Configuration();
		conf.set("mapred.job.tracker", "ubuntu:9001");
		conf.set("fs.default.name", "ubuntu:9000");
		hiu.writeSeqToHdfs(hdfs,conf);
	}
	@Test
	public void testReadSeq(){
		String hdfs="/dev/b.seq";
		String aa="";
		try {
			aa=hiu.readSeqHdfs(hdfs);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(aa);
	}
	@Test
	public void testReadSeqWrite(){
		String hdfs="/dev/b.seq";
		String hdfsDes="/dev/b.txt";
		hiu.readSeqHdfs(hdfs, hdfsDes);
		
	}
}
