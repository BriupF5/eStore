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

import com.briup.big.step.step3.GoodsCooccurrenceMatrix;
import com.briup.big.step.step3.GoodsCooccurrenceMatrixMappper;
import com.briup.big.step.step3.GoodsCooccurrenceMatrixReducer;

public class M3 extends Configured implements Tool {
	public static void main(String[] args) throws Exception {
		//调用run方法
		ToolRunner.run(new M3(), args);
	}

	@Override
	public int run(String[] args) throws Exception {
		Configuration conf  = new Configuration();
		conf.set("fs.defaultFS", "hdfs://mynn:9000");
		
		
		Path in3=new Path("/home/briup/step2");
		Path out3=new Path("/home/briup/step3");
		
		
		Job j3=Job.getInstance(conf,GoodsCooccurrenceMatrix.class.getSimpleName());
		j3.setJarByClass(this.getClass());

		j3.setMapperClass(GoodsCooccurrenceMatrixMappper.class);
		j3.setMapOutputKeyClass(Text.class);
		j3.setMapOutputValueClass(Text.class);
		j3.setInputFormatClass(TextInputFormat.class);
		TextInputFormat.addInputPath(j3,in3);

		j3.setReducerClass(GoodsCooccurrenceMatrixReducer.class);
		j3.setOutputKeyClass(Text.class);
		j3.setOutputValueClass(Text.class);
		j3.setOutputFormatClass(TextOutputFormat.class);
		TextOutputFormat.setOutputPath(j3,out3);
		
		j3.waitForCompletion(true);
		
		return 0;
	}
}
