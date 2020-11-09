package com.briup.big.step.step8;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;


public class SaveDbMapper extends Mapper<LongWritable, Text, Grms, IntWritable>{
	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Grms, IntWritable>.Context context)
			throws IOException, InterruptedException {
		System.out.println("----"+value);
		String[] split = value.toString().split("\t");
		
		
		
		Grms g = new Grms(new Text(split[0]), new Text(split[1]), new Text(split[2]));
		
		
		context.write(g, new IntWritable(1));
	}
}
