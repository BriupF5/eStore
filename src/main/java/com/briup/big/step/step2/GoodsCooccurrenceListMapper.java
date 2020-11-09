package com.briup.big.step.step2;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 * 输入数据
 * 	10001	20001,20002,20005,20006,20007
	10002	20003,20004,20006
	10003	20002,20007
	10004	20001,20002,20005,20006
	10005	20001
	10006	20004,20007
	当前map整理的输出数据:
		key: 20001,20002,20005,20006,20007
		value : null
 * 
 * */
public class GoodsCooccurrenceListMapper extends Mapper<LongWritable, Text, Text, NullWritable>{
	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, NullWritable>.Context context)
			throws IOException, InterruptedException {
		//value : 10001		20001,20002,20005,20006,20007
		String[] split = value.toString().split("\t");
		//split[0] 10001	
		//split[1] 20001,20002,20005,20006,20007
		context.write(new Text(split[1]), NullWritable.get());
	}
}























