package com.briup.big.main;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import com.briup.big.step.step2.GoodsCooccurrenceList;
import com.briup.big.step.step2.GoodsCooccurrenceListMapper;
import com.briup.big.step.step2.GoodsCooccurrenceListReducer;

public class M2 extends Configured implements Tool {
	public static void main(String[] args) throws Exception {
		//调用run方法
		ToolRunner.run(new M2(), args);
	}

	@Override
	public int run(String[] args) throws Exception {
		Configuration conf  = new Configuration();
		conf.set("fs.defaultFS", "hdfs://mynn:9000");
		FileSystem fs = FileSystem.get(conf);
		
		Path in2=new Path("/home/briup/step1");
		Path out2=new Path("/home/briup/step2");
		
		Job j2=Job.getInstance(conf,GoodsCooccurrenceList.class.getSimpleName());
		j2.setJarByClass(this.getClass());

		j2.setMapperClass(GoodsCooccurrenceListMapper.class);
		j2.setMapOutputKeyClass(Text.class);
		j2.setMapOutputValueClass(NullWritable.class);
		
		j2.setInputFormatClass(TextInputFormat.class);
		TextInputFormat.addInputPath(j2,in2);
		
		j2.setReducerClass(GoodsCooccurrenceListReducer.class);
		j2.setOutputKeyClass(Text.class);
		j2.setOutputValueClass(Text.class);
		j2.setOutputFormatClass(TextOutputFormat.class);
		TextOutputFormat.setOutputPath(j2,out2);
		j2.waitForCompletion(true);
		fs.close();
		return 0;
	}
}
