package com.briup.big.step.step4;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
/**
 *
 *  d.计算用户的购买向量
 *  	使用第一步的结果:
 *  		10001	20001,20002,20005,20006,20007
			10002	20003,20004,20006
			10003	20002,20007
			10004	20001,20002,20005,20006
			10005	20001
			10006	20004,20007
		计算处理得到结果:
			20001	10001:1,10004:1,10005:1
            20002	10001:1,10003:1,10004:1
            20003	10002:1
            20004	10002:1,10006:1
            20005	10001:1,10004:1
            20006	10001:1,10002:1,10004:1
            20007	10001:1,10003:1,10006:1
 * */
public class UserBuyGoodsVector extends Configured implements Tool {
	public static void main(String[] args) throws Exception {
		//调用run方法
		ToolRunner.run(new UserBuyGoodsVector(), args);
	}

	@Override
	public int run(String[] args) throws Exception {
		Configuration conf = getConf();
		FileSystem fs = FileSystem.get(conf);

		fs.close();
		return 0;
	}
}

