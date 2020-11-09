package com.briup.web.b;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.briup.bean.Cate;
import com.briup.bean.Catedet;
import com.briup.bean.Pricescope;
import com.briup.bean.Product;
import com.briup.util.Da;

//用来获得首页数据,application监听器，tomcat启动就会执行
@WebListener
public class IndexDataListener implements ServletContextListener {
	@Override
	public void contextInitialized(ServletContextEvent event) {
		System.out.println("开始...");
		// 获得最大容器application
		ServletContext app = event.getServletContext();
		// 查询所有的产品
		String proSql = "select * from product";
		
		// 没有where条件
		List<Product> prolist = Da.query(proSql, Product.class);
		// 放到容器中
		app.setAttribute("prolist", prolist);
		// 查询所有的杰普快报 Report.java

		// 获得一级栏目 和 一级栏目对应的二级栏目
		
		List<Cate> catelist = Da.query("select * from cate", Cate.class);
		

		// 二级栏目的dao层对象
		// 存在 一级栏目 和 一级栏目对应的二级栏目
		Map<Cate, List<Catedet>> map = new HashMap<>();
		for (Cate c : catelist) {
			String sql = "select * from catedet where categoryid="+c.getId();
			// 当前栏目下的二级栏目
			List<Catedet> detlist = Da.query(sql, Catedet.class);
			// from cateDet where category_id = 一级栏目的id
			map.put(c, detlist);
		}
		app.setAttribute("map", map);

		List<Pricescope> prilist =Da.query("select * from pricescope", Pricescope.class);
		app.setAttribute("prilist", prilist);
		System.out.println("--------------初始数据Map------------");
		Set<Cate> keySet = map.keySet();
		for(Cate c : keySet){
			System.out.println(c);
			System.out.println(map.get(c));
		}
		System.out.println("--------------初始数据Map2------------");
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {

	}

}
