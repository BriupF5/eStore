package com.briup.big.step.step1;

import java.io.IOException;
import java.util.Arrays;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class UserBuyGoodsListMapper extends Mapper<LongWritable, Text, Text, Text>{
	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, Text>.Context context)
			throws IOException, InterruptedException {
		String[] split = value.toString().split("[ \\t]");
		System.out.println("...."+value);
		System.out.println("...."+Arrays.toString(split));
		System.out.println("...."+Arrays.toString(value.toString().split("\t")));
		System.out.println("-----------------------------------------------------");
		context.write(new Text(split[0]), 
				new Text(split[1]));
		
	}
}
