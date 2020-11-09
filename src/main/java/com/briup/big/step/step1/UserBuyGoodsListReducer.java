package com.briup.big.step.step1;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class UserBuyGoodsListReducer extends Reducer<Text, Text, Text, Text>{
	@Override
	protected void reduce(Text key, Iterable<Text> values, Reducer<Text, Text, Text, Text>.Context context)throws IOException, InterruptedException {
		//10001 : [2001,2003,2004,2006]
		StringBuffer sb = new StringBuffer();
		for(Text t: values){
			sb.append(t.toString()).append(",");
		}
		sb.delete(sb.length()-1, sb.length());
		
		context.write(key, new Text(sb.toString()));
	}
}
