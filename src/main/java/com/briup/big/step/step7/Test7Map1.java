package com.briup.big.step.step7;

import java.io.IOException;
import java.util.Arrays;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 * 读取原始数据文件<br>
 *  10001     20001   1
 * */
public class Test7Map1 extends Mapper<LongWritable, Text, Text, Text>{
	
	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, Text>.Context context)
			throws IOException, InterruptedException {
		
		String[] split = value.toString().split("[ \\t]");
		System.out.println("--------"+Arrays.toString(split));
		System.out.println("--------"+Arrays.toString(value.toString().split("\t")));
		String k = split[0]+","+split[1];
		context.write(new Text(k.trim()), new Text("1"));
		//    10001,20001 		1
	}
	
}

















