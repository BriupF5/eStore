package com.briup.big.step.step8;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Reducer;


public class SaveDbReduce extends Reducer<Grms, IntWritable, Grms, NullWritable>{
	@Override
	protected void reduce(Grms key, Iterable<IntWritable> values, Reducer<Grms, IntWritable, Grms, NullWritable>.Context content)
			throws IOException, InterruptedException {
		System.out.println("reduce:----------"+key);
		content.write(key, NullWritable.get());
	}
}
