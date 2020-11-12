package com.briup.big.main;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.db.DBConfiguration;
import org.apache.hadoop.mapreduce.lib.db.DBOutputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import com.briup.big.step.step8.Grms;
import com.briup.big.step.step8.SaveDbMapper;
import com.briup.big.step.step8.SaveDbReduce;

public class M8 extends Configured implements Tool {
	public static void main(String[] args) throws Exception {
		//调用run方法
		ToolRunner.run(new M8(), args);
	}

	@Override
	public int run(String[] args) throws Exception {
		System.setProperty("HADOOP_USER_NAME","mynn");
		Configuration conf  = new Configuration();
		conf.set("fs.defaultFS", "hdfs://mynn:9000");
		
		Path out7=new Path("/home/briup/step7");
		
		
		Job job8 = Job.getInstance(conf, "job8");
		job8.setJarByClass(this.getClass());

		job8.setMapperClass(SaveDbMapper.class);
		job8.setMapOutputKeyClass(Grms.class);
		job8.setMapOutputValueClass(IntWritable.class);

		job8.setReducerClass(SaveDbReduce.class);
		job8.setOutputKeyClass(Grms.class);
		job8.setOutputValueClass(NullWritable.class);

		job8.setInputFormatClass(TextInputFormat.class);
		TextInputFormat.setInputPaths(job8, out7);

		job8.setOutputFormatClass(DBOutputFormat.class);
	
		//给job指定连接数据库的信息,需要导入对应数据库的jar包
		DBConfiguration.configureDB(job8.getConfiguration(), "com.mysql.cj.jdbc.Driver", "jdbc:mysql://localhost:3306/bigestore?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true", "root", "736197080");
		
		//把Reduce的输出结果，给t表的id列和name列
		DBOutputFormat.setOutput(job8, "grms", "usid","proid","num");

		job8.waitForCompletion(true);
		
		return 0;
	}
}
