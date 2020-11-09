package com.briup.big.step.step6;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class Test6 extends Configured implements Tool {
	public static void main(String[] args) throws Exception {
		//调用run方法
		ToolRunner.run(new Test6(), args);
	}

	@Override
	public int run(String[] args) throws Exception {
		Configuration conf = getConf();
		FileSystem fs = FileSystem.get(conf);

		fs.close();
		return 0;
	}
}

