package com.briup.big.main;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import com.briup.big.step.step1.UserBuyGoodsList;
import com.briup.big.step.step1.UserBuyGoodsListMapper;
import com.briup.big.step.step1.UserBuyGoodsListReducer;

public class M1 extends Configured implements Tool {
	public static void main(String[] args) throws Exception {
		//调用run方法
		ToolRunner.run(new M1(), args);
	}

	@Override
	public int run(String[] args) throws Exception {
		System.setProperty("HADOOP_USER_NAME","mynn");
		Configuration conf  = new Configuration();
		conf.set("fs.defaultFS", "hdfs://mynn:9000");
		FileSystem fs = FileSystem.get(conf);
		
		Path in1=new Path("/home/briup/matrix.txt");
		Path out1=new Path("/home/briup/step1");
		
		Job j1=Job.getInstance(conf,UserBuyGoodsList.class.getSimpleName());
		j1.setJarByClass(this.getClass());

		j1.setMapperClass(UserBuyGoodsListMapper.class);
		j1.setMapOutputKeyClass(Text.class);
		j1.setMapOutputValueClass(Text.class);
		j1.setInputFormatClass(TextInputFormat.class);
		TextInputFormat.addInputPath(j1,in1);

		j1.setReducerClass(UserBuyGoodsListReducer.class);
		j1.setOutputKeyClass(Text.class);
		j1.setOutputValueClass(Text.class);
		j1.setOutputFormatClass(TextOutputFormat.class);
		TextOutputFormat.setOutputPath(j1,out1);
		j1.waitForCompletion(true);
		
		fs.close();
		return 0;
	}
}
