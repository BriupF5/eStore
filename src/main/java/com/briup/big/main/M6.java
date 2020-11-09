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

import com.briup.big.step.step6.Test6Mapper;
import com.briup.big.step.step6.Test6Reduce;

public class M6 extends Configured implements Tool {
	public static void main(String[] args) throws Exception {
		//调用run方法
		ToolRunner.run(new M6(), args);
	}

	@Override
	public int run(String[] args) throws Exception {
		Configuration conf  = new Configuration();
		conf.set("fs.defaultFS", "hdfs://mynn:9000");
		
		Path in6=new Path("/home/briup/step5");
		Path out6=new Path("/home/briup/step6");
		
		Job j6=Job.getInstance(conf,"step6");
		j6.setJarByClass(this.getClass());

		j6.setMapperClass(Test6Mapper.class);
		j6.setMapOutputKeyClass(Text.class);
		j6.setMapOutputValueClass(Text.class);
		
		TextInputFormat.addInputPath(j6,in6);

		j6.setReducerClass(Test6Reduce.class);
		j6.setOutputKeyClass(Text.class);
		j6.setOutputValueClass(Text.class);
		
		
		j6.setOutputFormatClass(TextOutputFormat.class);
		TextOutputFormat.setOutputPath(j6,out6);
		j6.waitForCompletion(true);
		
		return 0;
	}
}
