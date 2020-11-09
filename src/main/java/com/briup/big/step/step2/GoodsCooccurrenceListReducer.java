package com.briup.big.step.step2;

import java.io.IOException;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

/**
 * 上一个Map的输出：
 * 		key: 20001,20002,20005,20006,20007
		value : null
 * */
public class GoodsCooccurrenceListReducer extends Reducer<Text, NullWritable, Text, Text>{
	@Override
	protected void reduce(Text key, Iterable<NullWritable> values, Reducer<Text, NullWritable, Text, Text>.Context context)throws IOException, InterruptedException {
		
		String[] split = key.toString().split(",");
		
		for(String gid : split){
			//gid 20001
			for(String id : split){
				//di :20001/ 2002/2005/2006/20007
				context.write(new Text(gid), new Text(id));
			}
		}
	}
}






