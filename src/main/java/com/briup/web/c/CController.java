package com.briup.web.c;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.briup.bean.E_order;
import com.briup.bean.E_user;
import com.briup.bean.Orderline;
import com.briup.util.Da;

@Controller
public class CController {
	
	/**
	 * 查看自己订单
	 * */
	@RequestMapping("/tocomfirm")
	public String tocomfirm(Model model,HttpSession session) {
		Object obj = session.getAttribute("user");
		if(obj==null) {
			model.addAttribute("msg", "请先登入");
			return "a/login.jsp";
		}
		E_user u =(E_user)obj;
		Integer id = u.getId();
		String ordersql = "select * from e_order where customerid = "+id;
		List<E_order> list = Da.query(ordersql, E_order.class);
		
		@SuppressWarnings("unchecked")
		Map<E_order, List<Orderline>> map = new HashedMap();
		//model.addAttribute("list", list);
		for(E_order o: list) {
			String orLineSql = "select * from orderline where orderid= '"+o.getId()+"'";
			List<Orderline> ollist = Da.query(orLineSql, Orderline.class);
			map.put(o, ollist);
		}
		model.addAttribute("map", map);
		return "c/confirmList.jsp";
	}
	
}
