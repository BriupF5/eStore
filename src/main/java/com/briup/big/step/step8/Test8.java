package com.briup.big.step.step8;

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

/**
 * 读取文件<br>	artist.txt<Br>
 * 数据写到数据库中 t   id  name 
 * 
 * yarn jar e2.jar com.briup.big.step.step8.Test8
https://www.cnblogs.com/codeOfLife/p/5464613.html
 * */
public class Test8 extends Configured implements Tool {
	public static void main(String[] args) throws Exception {
		//调用run方法
		ToolRunner.run(new Test8(), args);
	}

	@Override
	public int run(String[] args) throws Exception {
		Configuration conf = getConf();
		Job job = Job.getInstance(conf, "t2");
		job.setJarByClass(this.getClass());

		job.setMapperClass(SaveDbMapper.class);
		job.setMapOutputKeyClass(Grms.class);
		job.setMapOutputValueClass(IntWritable.class);

		job.setReducerClass(SaveDbReduce.class);
		job.setOutputKeyClass(Grms.class);
		job.setOutputValueClass(NullWritable.class);

		job.setInputFormatClass(TextInputFormat.class);
		TextInputFormat.setInputPaths(job, new Path("/st7"));

		job.setOutputFormatClass(DBOutputFormat.class);
	
		//给job指定连接数据库的信息,需要导入对应数据库的jar包
		DBConfiguration.configureDB(job.getConfiguration(), "com.mysql.jdbc.Driver", "jdbc:mysql://192.168.43.125:3306/bigestore?serverTimezone=Hongkong", "zs", "123");
		
		//把Reduce的输出结果，给t表的id列和name列
		DBOutputFormat.setOutput(job, "grms", "usid","proid","num");

		job.waitForCompletion(true);
		return 0;
	}
}

