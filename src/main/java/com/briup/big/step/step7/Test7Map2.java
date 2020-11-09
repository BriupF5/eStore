package com.briup.big.step.step7;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 * 读取第六步产生的结果
 * 10001,20001      10
 * 
 * */
public class Test7Map2 extends Mapper<LongWritable, Text, Text, Text>{
	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, Text>.Context context)
			throws IOException, InterruptedException {
		String[] split = value.toString().split("\t");
		context.write(new Text(split[0].trim()), new Text(split[1]));
	}
}

















