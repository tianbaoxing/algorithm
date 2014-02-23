package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URI;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.SequenceFile;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.util.ReflectionUtils;

/**
 * hadoop IO utils
 * @author fansy
 *
 */
public class HadoopIOUtil {
	private static Log log = LogFactory.getLog(HadoopIOUtil.class);
	
	public static Configuration getConf(){
		Configuration conf=HadoopUtil.getConf();
		if(conf==null){
			HadoopUtil.initialConf();
		}
		return HadoopUtil.getConf();
	}
	/**
	 * read hdfs file to String
	 * @param file
	 * @return
	 */
	public static String readHdfs(String path){
		String data="";
		Path filePath= new Path(path);
		FileSystem fs =null;  
        FSDataInputStream in = null;  
        try {  
          fs=FileSystem.get(filePath.toUri(), getConf()); 
          in=fs.open(filePath);
          data= in.readUTF();  
        }catch(Exception e){
        	log.info("read hdfs file "+path+" error:\n"+e.getMessage());
        }finally {  
        	try {
	        	if(in!=null){
					in.close();
	        	}
	        	if(fs!=null){
	        		fs.close();
	        	}
        	} catch (IOException e) {
        		log.info("close FileSystem error:\n"+e.getMessage());
			}
        }  
		return data;
	}
	/**
	 * write string to hdfs file
	 * @param path
	 * @param data
	 * @return
	 */
	public static boolean writeToHdfs(String path,String data){
		boolean flag = true;
	    Path filePath=new Path(path);  
	    FileSystem fs;  
	    FSDataOutputStream out=null;  
	    try {  
	        fs = FileSystem.get(filePath.toUri(),getConf());
	        out = fs.create(filePath);  
	        out.writeUTF(data);  
	    } catch(Exception e){  
	        log.info("write to hdfs file"+filePath.toString()+" :"+e.getMessage());
	        flag=false;
	    }finally {  
	        try {
	        	if(out!=null){
	        		out.close();
	        	}
			} catch (IOException e) {
				log.info("close hdfs file "+filePath.toString()+" wrong\n"+e.getMessage());
				flag=false;
			}  
	    } 
		return flag;
	}
	/**
	 * upload local file to hdfs 
	 * @param localFile
	 * @param hdfsFile
	 * @return
	 */
	public static boolean uploadToHdfs(String localFile, String hdfsFile){
		boolean flag= true;
		Path in=new Path(localFile);
		Path out=new Path(hdfsFile);
		FileSystem fs=null;
		try {
			fs = FileSystem.get(URI.create(hdfsFile),getConf());
			fs.copyFromLocalFile(in,out);
		} catch (Exception e) {
			log.info("upload file "+localFile+" to hdfs "+hdfsFile.toString()+" wrong\n"+e.getMessage());
			flag=false;
		}
		try {
			if(fs!=null){
				fs.close();
			}
		} catch (IOException e) {
			log.info("close FileSystem error:\n"+e.getMessage());
			flag=false;
		}
		log.info("upload to hdfs done ...");
		return flag;
	}
	/**
	 * download file from hdfs
	 * @param hdfsFileName
	 * @param localFileName
	 * @return
	 */
	public static boolean downloadFromHdfs(String hdfsFileName,String localFileName){
		boolean flag=true;
		FileSystem fs=null;
		Path in=new Path(hdfsFileName);
		Path out=new Path(localFileName);
		try {
			fs=FileSystem.get(URI.create(hdfsFileName),getConf());
			fs.copyToLocalFile(in, out);
		} catch (Exception e) {
			log.info("copy to local file error:\n"+e.getMessage());
			flag=false;
		}
		try {
			if(fs!=null){
				fs.close();
			}
		} catch (IOException e) {
			log.info("close FileSystem error:\n"+e.getMessage());
			flag=false;
		}
		return flag;
	}
	
	public static String readLocalFile(String localFile){
		StringBuffer buff=new StringBuffer();
		FileReader reader =null; 
		BufferedReader br = null;
		String line = null;
		try{
			reader=new FileReader(localFile);
			br=new BufferedReader(reader);
			while((line = br.readLine()) != null) {
				buff.append(line).append("\n");
			}
		}catch(Exception e){
			log.info("read local file "+localFile+" error:\n"+e.getMessage());
		}finally{
			try {
				if(br!=null){
					br.close();
				}
				if(reader!=null){
					reader.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return buff.toString();
	}
	
	
	// sequential file process
	
	/**
	 * read sequential file to string
	 * only support common data type 
	 * like Text ,LongWritable,IntWritable ,DoubleWritable,NullWritable,FloatWritable
	 * @param hdfsFile
	 * @return
	 * @throws IOException 
	 */
	public static String readSeqHdfs(String hdfsFile) throws IOException{
		StringBuffer buff =new  StringBuffer();
		Configuration conf=getConf();
		FileSystem fs = FileSystem.get(URI.create(hdfsFile), conf);  
        Path path = new Path(hdfsFile);  
        SequenceFile.Reader reader = null;  
        try {  
          reader = new SequenceFile.Reader(fs, path, conf);  
          Writable key = (Writable)  
            ReflectionUtils.newInstance(reader.getKeyClass(), conf);  
          Writable value = (Writable)  
            ReflectionUtils.newInstance(reader.getValueClass(), conf);  
          while (reader.next(key, value)) {  
            buff.append("key:"+key.toString()+"\tvalue:"+value.toString()); 
            buff.append("\n");
          }  
        }catch(IOException e){
        	log.info("read sequential file "+hdfsFile.toString()+"\n"+e.getMessage());
        	throw e;
        } finally {  
          IOUtils.closeStream(reader);  
          if(fs!=null){
        	  fs.close();
          }
        }  
		return buff.toString();
	}
	
	/**
	 * download sequential file to hdfs destination file 
	 * only support common data type 
	 * like Text ,LongWritable,IntWritable ,DoubleWritable,NullWritable,FloatWritable
	 * @param hdfsFile
	 * @param localFile
	 */
	public static boolean readSeqHdfs(String hdfsFile,String hdfsDesFile){
		boolean flag=true;
		Configuration conf=getConf();
          
        FileSystem fsIn =null;  
        FileSystem fsOut =null;   
        Path pathIn = new Path(hdfsFile);  
        Path pathOut = new Path(hdfsDesFile);  
          
        SequenceFile.Reader reader = null;  
        FSDataOutputStream out =null;
        try {  
        	fsIn=FileSystem.get(URI.create(hdfsFile), conf); 
        	fsOut=FileSystem.get(URI.create(hdfsDesFile), conf);
        	out= fsOut.create(pathOut);  
            reader = new SequenceFile.Reader(fsIn, pathIn, conf);  
            Writable key = (Writable)  
              ReflectionUtils.newInstance(reader.getKeyClass(), conf);  
            Writable value = (Writable)  
              ReflectionUtils.newInstance(reader.getValueClass(), conf);  
            while (reader.next(key, value)) {  
                // read  
                String kv=key.toString()+"\t"+value.toString();   
                out.writeChars(kv);  
            }  
        } catch(IOException e){  
        	log.info("read sequential hdfs file wrong\n"+e.getMessage());
            flag=false;  
        }finally {  
          IOUtils.closeStream(reader);  
        }  
        return flag;  
	}
	/**
	 * test 往文件hdfsFile中写入 <10,this is a test>
	 * @param hdfsFile
	 */
	public static void writeSeqToHdfs(String hdfsFile){
		Configuration conf=getConf();
		FileSystem fs=null;  
	    SequenceFile.Writer writer=null;  
	    Path path=new Path(hdfsFile);  
	      
	    try {  
	      fs=FileSystem.get(conf);  
	      writer=SequenceFile.createWriter(fs, conf, path, LongWritable.class, Text.class);  
	        
	      writer.append(new LongWritable(10), new Text("this is a test"));
	    } catch (IOException e) {  
	      e.printStackTrace();  
	    }finally{  
	      IOUtils.closeStream(writer);  
	    }  
	}
	/**
	 * test 往文件hdfsFile中写入 <10,this is a test>
	 * @param hdfsFile
	 */
	public static void writeSeqToHdfs(String hdfsFile,Configuration conf){
		FileSystem fs=null;  
	    SequenceFile.Writer writer=null;  
	    Path path=new Path(hdfsFile);  
	      
	    try {  
	      fs=FileSystem.get(conf);  
	      writer=SequenceFile.createWriter(fs, conf, path, LongWritable.class, Text.class);  
	        
	      writer.append(new LongWritable(10), new Text("this is a test"));
	      writer.append(new LongWritable(11), new Text("this is a test2"));
	    } catch (IOException e) {  
	      e.printStackTrace();  
	    }finally{  
	      IOUtils.closeStream(writer);  
	    }  
	}
	/**
	 * not yet
	 * @param data
	 * @param keyType
	 * @param valueType
	 * @param hdfsFile
	 */
	public static void writeSeqToHdfs(Map<Object,Object> data ,String keyType,String valueType,String hdfsFile){
		
	}
	/**
	 * 写入字符串到本地文件
	 * @param localPath
	 * @param data
	 */
	public static boolean writeToLocal(String localPath,String data){
		boolean flag=true;
		File file=new File(localPath);
		if(!file.exists()){
			try {
				new File(file.getParent()).mkdirs();
				file.createNewFile();
			} catch (Exception e) {
				log.info("创建文件失败\n"+e.getMessage());
			}
			
		}
		PrintStream ps=null;
		try {
			ps = new PrintStream(new FileOutputStream(file));
			ps.print(data);
		} catch (FileNotFoundException e) {
			log.info("写入本地文件"+localPath+"失败\n"+e.getMessage());
			flag=false;
		}finally{
			ps.close();
		}
		return flag;
	}
	
}
