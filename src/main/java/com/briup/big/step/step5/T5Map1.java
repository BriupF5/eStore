package com.briup.big.step.step5;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 * 处理第四步结果
 * 	用户购买向量:4号文件 A:
    		20001	10001:1,10004:1,10005:1
            20002	10001:1,10003:1,10004:1
            20003	10002:1
            20004	10002:1,10006:1
            20005	10001:1,10004:1
            20006	10001:1,10002:1,10004:1
            20007	10001:1,10003:1,10006:1
 *   key:		20001做为连接键
 *   value:		A:10001:1,10004:1,10005:1
 * */

public class T5Map1 extends Mapper<LongWritable, Text, Text, Text>{
	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, Text>.Context context)
			throws IOException, InterruptedException {
	
		String[] split = value.toString().split("\t");
		
		context.write(new Text(split[0]), new Text("A:"+split[1]));
	}
}















