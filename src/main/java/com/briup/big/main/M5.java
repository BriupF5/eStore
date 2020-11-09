package com.briup.big.main;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.MultipleInputs;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import com.briup.big.step.step5.T5Map1;
import com.briup.big.step.step5.T5Map2;
import com.briup.big.step.step5.T5Reduce;

public class M5 extends Configured implements Tool {
	public static void main(String[] args) throws Exception {
		//调用run方法
		ToolRunner.run(new M5(), args);
	}

	@Override
	public int run(String[] args) throws Exception {
		Configuration conf  = new Configuration();
		conf.set("fs.defaultFS", "hdfs://mynn:9000");
		
		
		
		Path in51=new Path("/home/briup/step3");
		Path in52=new Path("/home/briup/step4");
		Path out5=new Path("/home/briup/step5");
		
		Job j5=Job.getInstance(conf,"step5");
		j5.setJarByClass(this.getClass());

		
		
		MultipleInputs.addInputPath(j5, in51, TextInputFormat.class,T5Map2.class);
		
		MultipleInputs.addInputPath(j5, in52, TextInputFormat.class,T5Map1.class);
		
		j5.setMapOutputKeyClass(Text.class);
		j5.setMapOutputValueClass(Text.class);

		
		j5.setReducerClass(T5Reduce.class);
		j5.setOutputKeyClass(Text.class);
		j5.setOutputValueClass(Text.class);
		
		j5.setOutputFormatClass(TextOutputFormat.class);
		TextOutputFormat.setOutputPath(j5,out5);

		j5.waitForCompletion(true);
		
		return 0;
	}
}
