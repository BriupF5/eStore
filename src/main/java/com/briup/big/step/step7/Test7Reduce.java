package com.briup.big.step.step7;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

/**
 * 
 * 10001,20001  [1]
 * 10001,20002  [1,2,3]
 * 
 * 
 * */
public class Test7Reduce extends Reducer<Text, Text, Text, Text>{
	
	@Override
	protected void reduce(Text key, Iterable<Text> values, Reducer<Text, Text, Text, Text>.Context context)throws IOException, InterruptedException {
		//判断迭代器values中的数据个数
		int num = 0;
		String v = "";//用来记录value的值
		for(Text t: values){
			num++;
			v = t.toString();
		}
		if(num==1){//正常数据 可以输出
			String[] strs = key.toString().split("[,]");
			key.set(strs[0] + "\t" + strs[1]);
			context.write(key, new Text(v));
		}else{//用户已经购买过的商品，不需要输出
			return;
		}
	}
	

}














