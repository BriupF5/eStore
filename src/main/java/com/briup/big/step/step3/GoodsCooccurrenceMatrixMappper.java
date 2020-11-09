package com.briup.big.step.step3;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class GoodsCooccurrenceMatrixMappper extends Mapper<LongWritable, Text, Text, Text>{
	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, Text>.Context context)
			throws IOException, InterruptedException {
		//20007	20007
		String[] split = value.toString().split("\t");
		
		context.write(new Text(split[0]),
				new Text(split[1]));
		
	}
}
