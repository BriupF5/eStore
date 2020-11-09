package com.briup.big.step.step6;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class Test6Reduce extends Reducer<Text, Text, Text, Text>{
	@Override
	protected void reduce(Text key, Iterable<Text> values, Reducer<Text, Text, Text, Text>.Context context)throws IOException, InterruptedException {
		
		int sum = 0;
		for(Text t : values){
			//3 
			int parseInt = Integer.parseInt(t.toString());
			sum+=parseInt;
		}
		context.write(key, new Text(""+sum));
	}
}
