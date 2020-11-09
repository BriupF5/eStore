package com.briup.big.step.step7;

import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class Test7 extends Configured implements Tool {
	public static void main(String[] args) throws Exception {
		//调用run方法
		ToolRunner.run(new Test7(), args);
	}

	@Override
	public int run(String[] args) throws Exception {
		
		return 0;
	}
}
