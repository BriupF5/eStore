package com.briup.big.step.step6;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class Test6Mapper extends Mapper<LongWritable, Text, Text, Text>{
	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, Text>.Context context)
			throws IOException, InterruptedException {
		
		String[] split = value.toString().split("\t");
		context.write(new Text(split[0].trim()), new Text(split[1].trim()));
		//   100001,20001                             3
		
	}
}
