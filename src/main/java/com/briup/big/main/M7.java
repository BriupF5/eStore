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

import com.briup.big.step.step7.Test7Map1;
import com.briup.big.step.step7.Test7Map2;
import com.briup.big.step.step7.Test7Reduce;

public class M7 extends Configured implements Tool {
	public static void main(String[] args) throws Exception {
		//调用run方法
		ToolRunner.run(new M7(), args);
	}

	@Override
	public int run(String[] args) throws Exception {
		Configuration conf  = new Configuration();
		conf.set("fs.defaultFS", "hdfs://mynn:9000");
		
		
		Path in71=new Path("/home/briup/matrix.txt");
		Path in72=new Path("/home/briup/step6");
		Path out7=new Path("/home/briup/step7");
		
		
		Job j7=Job.getInstance(conf,"step7");
		j7.setJarByClass(this.getClass());


		MultipleInputs.addInputPath(j7, in71, TextInputFormat.class,Test7Map1.class);
		
		MultipleInputs.addInputPath(j7, in72, TextInputFormat.class,Test7Map2.class);
		
		j7.setMapOutputKeyClass(Text.class);
		j7.setMapOutputValueClass(Text.class);

		j7.setReducerClass(Test7Reduce.class);
		j7.setOutputKeyClass(Text.class);
		j7.setOutputValueClass(Text.class);
		j7.setOutputFormatClass(TextOutputFormat.class);
		TextOutputFormat.setOutputPath(j7,out7);
		j7.waitForCompletion(true);
		
		return 0;
	}
}
