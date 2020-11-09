package com.briup.big.step.step4;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

/**
 * Map输出数据:
 * 		20001	10001
 * 		20001	10001 
 * 		20001	10001
 * 		20001	10001
 * 混洗:
 * 		20001:[10001,10001,10001,10001,10002]
 *       key            values
 * Reduce输出:
 * 		20001	10001:4,10002:1
 * */
public class UserBuyGoodsVectorReducer extends Reducer<Text, Text, Text, Text>{
	@Override
	protected void reduce(Text key, Iterable<Text> values, Reducer<Text, Text, Text, Text>.Context context)throws IOException, InterruptedException {
		
		Map<String, Integer> map = new TreeMap<String, Integer>();
		for(Text t : values){
			String v = t.toString();//
			boolean containsKey = map.containsKey(v);
			if(containsKey){//有,累加
				Integer integer = map.get(v);
				map.put(v, integer+1);
			}else{//新加
				map.put(v, 1);
			}
		}
		String ms = map.toString();
		String str = ms.substring(1, ms.length()-1);
		str = str.replaceAll("=", ":");
		context.write(key, new Text(str));
		
	}
}























