package com.briup.big.step.step3;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

/**
 * Map输出 key: 20007  value : 20007
 * Map输出 key: 20007  value : 20004
 * Map输出 key: 20007  value : 20003
 * Map输出 key: 20007  value : 20001
 * 
 * 混洗:
 * 		key相同的留一个，key相同的value组成数组
 * 		key:20007
 *      values:[20007,20004,20003,20001,20004];
 *  当前的数据也就是输入到Reduce的数据
 *  
 *  Reduce:输出结果:
 *    20007		20007:1,20004:2,20003:1,20001:1
 * */
public class GoodsCooccurrenceMatrixReducer extends Reducer<Text, Text, Text, Text>{
	@Override
	protected void reduce(Text key, Iterable<Text> values, Reducer<Text, Text, Text, Text>.Context context)throws IOException, InterruptedException {
		//key 共现商品id  value出现的次数
		Map<String, Integer> map = new TreeMap<>();
		for(Text t : values){
			String ts = t.toString();
			boolean containsKey = map.containsKey(ts);
			if(containsKey){//map中包含当前的key，value累加
				Integer integer = map.get(ts);
				map.put(ts, integer+1);
			}else{//不包含key  ， 新加。
				map.put(ts, 1);
			}
		}
		//map的结果 {20007=1,20004=2,20003=1,20001=1}
		//-----------把map中的数据拼接为字符串，输出--------
		//map处理为---> 20007:1,20004:2,20003:1,20001:1
		String ms = map.toString();
		System.out.println("map---------------------"+ms);
		//20007=1,20004=2,20003=1,20001=1
		String str = ms.substring(1, ms.length()-1);
		//20007:1,20004:2,20003:1,20001:1
		str = str.replaceAll("=", ":");
		System.out.println(key+"---------------------"+str);
		//数据写出
		context.write(key, new Text(str));
	}
	public static void main(String[] args) throws Exception {
		String ms = "{20007=1,20004=2,20003=1,20001=1}";
		String msg = ms.substring(1, ms.length()-1);
		System.out.println(msg);
	}
}



















































