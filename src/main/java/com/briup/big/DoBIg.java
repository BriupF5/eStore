package com.briup.big;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import com.briup.bean.E_order;
import com.briup.bean.Orderline;
import com.briup.util.Da;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

/**
 * 定时读取order表中的数据 生成推荐引擎需要的基础数据<br>
 * 
 * */
public class DoBIg {
	/**
	 * 读取web项目中订单表里的数据，生成matrix.txt文件
	 * */
	public static void restart(){
		
		System.out.println("...读取订单信息生成big处理的基础数据...");
		try {
			//清空先前数据库
			String sqlTruncate = "truncate table grms";
			Da.exist(sqlTruncate);

			Configuration conf  = new Configuration();
			conf.set("fs.defaultFS", "hdfs://mynn:9000");
			FileSystem fs = FileSystem.get(conf);
			Path path = new Path("/home/briup/matrix.txt");//获取了hdfs集群上 /a.txt文件的输入流
			if (path.toString()!=null) {
				fs.delete(path, true);
			}
			String sql = "select * from e_order";
			List<E_order> list = Da.query(sql, E_order.class);
			FSDataOutputStream fos = fs.create(new Path("/home/briup/matrix.txt"));
//			File file = new File("/");
//			File matrix = new File(file+"home/briup/","matrix.txt");
//			FileOutputStream fos = new FileOutputStream(matrix);
			if(list!=null && list.size()>0){
				for(E_order e : list){
					
					String olsql = "select * from orderline where orderid = '"+e.getId()+"'";
					List<Orderline> ollist = Da.query(olsql, Orderline.class);
					if(ollist!=null && ollist.size() > 0 ){
						for(Orderline ol : ollist){
							
							fos.write((""+e.getCustomerid()).getBytes());
							fos.write("\t".getBytes());
							fos.write((""+ol.getBookid()).getBytes());
							fos.write("\t".getBytes());
							fos.write("1".getBytes());
							fos.write("\r\n".getBytes());
							fos.flush();
						
						}
					}
				}
			}
			fos.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	public static void main(String[] args) throws Exception {
		restart();
	}
}
