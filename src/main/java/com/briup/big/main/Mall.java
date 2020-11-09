package com.briup.big.main;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

/**
 * 这个需要传到linux下运行，
 * 运行以后每5分钟会执行一次<Br>
 * 会先删除所有的stepn的文件夹数据<Br>
 * 然后启动
 * */
public class Mall {
	/**
	 * 开始执行8步
	 * */
	public static void bigin() {
		try {

			Configuration conf  = new Configuration();
			conf.set("fs.defaultFS", "hdfs://mynn:9000");
			FileSystem fs = FileSystem.get(conf);
			for(int i = 1;i<=7;i++){
				Path step = new Path("/home/briup/step"+i);
				fs.delete(step,true);
			}
			fs.close();
			System.out.println("时间到了运行一次删除stepN数据...end...");
			
			M1.main(new String[0]);
			M2.main(new String[0]);
			M3.main(new String[0]);
			M4.main(new String[0]);
			M5.main(new String[0]);
			M6.main(new String[0]);
			M7.main(new String[0]);
			M8.main(new String[0]);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
