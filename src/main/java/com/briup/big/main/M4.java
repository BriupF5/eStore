package com.briup.big.main;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import com.briup.big.step.step4.UserBuyGoodsVectorMapper;
import com.briup.big.step.step4.UserBuyGoodsVectorReducer;

public class M4 extends Configured implements Tool {
	public static void main(String[] args) throws Exception {
		//调用run方法
		ToolRunner.run(new M4(), args);
	}

	@Override
	public int run(String[] args) throws Exception {
		System.setProperty("HADOOP_USER_NAME","mynn");
		Configuration conf  = new Configuration();
		conf.set("fs.defaultFS", "hdfs://mynn:9000");
		
		
		
		
		Path in4=new Path("/home/briup/step1");
		Path out4=new Path("/home/briup/step4");
		
		Job j4=Job.getInstance(conf,"step4");
		j4.setJarByClass(this.getClass());

		j4.setMapperClass(UserBuyGoodsVectorMapper.class);
		j4.setMapOutputKeyClass(Text.class);
		j4.setMapOutputValueClass(Text.class);
		j4.setInputFormatClass(TextInputFormat.class);
		TextInputFormat.addInputPath(j4,in4);

		j4.setReducerClass(UserBuyGoodsVectorReducer.class);
		j4.setOutputKeyClass(Text.class);
		j4.setOutputValueClass(Text.class);
		j4.setOutputFormatClass(TextOutputFormat.class);
		TextOutputFormat.setOutputPath(j4,out4);

		j4.waitForCompletion(true);
		
		return 0;
	}
}
