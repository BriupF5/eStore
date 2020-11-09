package com.briup.big.step.step4;

import java.io.IOException;
import java.util.Arrays;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 * 
 * Map的输入数据:
 * 	使用第一步的结果:
 *  		10001	20001,20002,20005,20006,20007
			10002	20003,20004,20006
			10003	20002,20007
			10004	20001,20002,20005,20006
			10005	20001
			10006	20004,20007
 * 	Map的输出数据:
 * 		20001	10001
 * 		商品id   用户id
 * */
public class UserBuyGoodsVectorMapper extends Mapper<LongWritable, Text, Text, Text>{
	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, Text>.Context context)
			throws IOException, InterruptedException {
		// [10001,    {20001,20002,20005,20006,20007}    ]
		String[] split = value.toString().split("\t");//长度为2
		System.out.println("11111:-----"+Arrays.toString(split));
		
		//【20001,20002,20005,20006,20007】
		String[] sids = split[1].split(",");//长度为5
		System.out.println("11111:-----"+Arrays.toString(sids));
		
		for(String sid : sids){
			context.write(new Text(sid), new Text(split[0]));
		}
	}
}
























