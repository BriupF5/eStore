package com.briup.big.step.step5;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 * 第三步结果
 	商品共现矩阵:3号文件  B:
 			20001	20001:3,20002:2,20005:2,20006:2,20007:1
            20002	20001:2,20002:3,20005:2,20006:2,20007:2
            20003	20003:1,20004:1,20006:1
            20004	20003:1,20004:2,20006:1,20007:1
            20005	20001:2,20002:2,20005:2,20006:2,20007:1
            20006	20001:2,20002:2,20003:1,20004:1,20005:2,20006:3,20007:1
            20007	20001:1,20002:2,20004:1,20005:1,20006:1,20007:3
  key:		20001做为连接键
 * value:	B:20001:3,20002:2,20005:2,20006:2,20007:1
 * 
 * */
public class T5Map2  extends Mapper<LongWritable, Text, Text, Text>{
	

	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, Text>.Context context)
			throws IOException, InterruptedException {
		
		String[] split = value.toString().split("\t");
		
		context.write(new Text(split[0]), new Text("B:"+split[1]));
		
	}
}















