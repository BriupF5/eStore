package com.briup.big;

import java.io.File;

import com.briup.util.Da;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

/**
 * 把数据上传到hdfs
 * */
public class UpDataHdfs {
	public static void update(){
		try {
			System.setProperty("HADOOP_USER_NAME","mynn");
			Configuration conf  = new Configuration();
			conf.set("fs.defaultFS", "hdfs://mynn:9000");
			FileSystem fs = FileSystem.get(conf);

			for(int i = 1;i<=7;i++){
				Path step = new Path("/home/briup/step"+i);
				fs.delete(step,true);
			}
			
//			File f = new File("/");
//			File mat = new File(f+"home/briup/","matrix.txt");
//			Path p = new Path(mat.toString());
//			fs.copyFromLocalFile(p,path);
//
			fs.close();
			System.out.println("...end...");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//------------------------------------------------------------
	/**
	 *  删除临时文件夹
	 * */
	public static void ttt(){
		try {
			System.setProperty("HADOOP_USER_NAME","mynn");
			Configuration conf  = new Configuration();
			conf.set("fs.defaultFS", "hdfs://mynn:9000");
			FileSystem fs = FileSystem.get(conf);
			for(int i = 1;i<=7;i++){
				Path step = new Path("/home/briup/step"+i);
				fs.delete(step,true);
			}
			fs.close();
			System.out.println("...end...");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void nnn(int i ){
		try {

			System.setProperty("HADOOP_USER_NAME","mynn");
			Configuration conf  = new Configuration();
			conf.set("fs.defaultFS", "hdfs://aaa:9000");
			FileSystem fs = FileSystem.get(conf);
			Path step = new Path("/home/briup/step"+i);
			fs.delete(step,true);
			fs.close();
			System.out.println("...end...");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) throws Exception {
		//nnn(7);
		ttt();
	}
}
