package com.briup.big.step.step2;

public class T2 {
	public static void main(String[] args) throws Exception {
		//假装数据已经到了Reduce中，这里就是Reduce
		String key = "20001,20003,20005,20006";
		String[] split = key.split(",");
		for(String gid :split){
			//gid//20001
			for(String id :split){
				// id 20001 / 20003/ 20005/ 20006
				System.out.println(gid+","+id);
			}
			
		}
		
		//计算key中所有物品之间的共现关系
		//20001  20001
		//20001  20003
		//20001  20005
		//20001  20006
		
		//20003  20001
		//20003  20003
		//20003  20005
		//20003  20006
		
	}
}
