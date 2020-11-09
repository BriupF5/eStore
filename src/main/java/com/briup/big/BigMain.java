package com.briup.big;

import java.util.Timer;
import java.util.TimerTask;

import com.briup.big.main.Mall;

public class BigMain {
	/**
	 * 启动推荐引擎代码<Br>
	 * 每3分钟执行一次
	 * */
	public static void start() {
		
		Timer t = new Timer();
		
		TimerTask task = new TimerTask() {
			
			@Override
			public void run() {
				System.out.println("开始启动big推荐引擎代码");
				
				// 读取web项目中订单表里的数据，生成matrix.txt文件
				DoBIg.restart();
				//生成文本信息，保存到hdfs集群上
				UpDataHdfs.update();
				//启动 推荐引擎代码 生成数据 放到grms表中
				Mall.bigin();
				
			}
		};
		t.schedule(task, 1, 1000*60*3);
	}
}
