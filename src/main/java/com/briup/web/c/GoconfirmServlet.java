package com.briup.web.c;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.briup.bean.E_order;
import com.briup.bean.E_user;
import com.briup.bean.Orderline;
import com.briup.util.Da;
import com.briup.util.Shopcar;

@WebServlet("/goconfirm")
public class GoconfirmServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 获得用户勾选的某几个订单项
		String[] olid = request.getParameterValues("olid");

		HttpSession session = request.getSession();
		Object obj = session.getAttribute("car");
		if (obj == null) {// 没有登入
			request.setAttribute("msg", "没有登入，请先登入");
			request.getRequestDispatcher("a/login.jsp").forward(request, response);
			return;
		}
		Shopcar car = (Shopcar) obj;
		// 存放用户勾选的订单项信息
		List<Orderline> list = new ArrayList<Orderline>();

		List<Orderline> lines = car.getLines();
		System.out.println("----"+lines);
		List<Orderline> delLine = new ArrayList<>();

		for (Orderline ol : lines) {
			System.out.println(ol);
			String id = ol.getId();
			//int id = 1;
			for (int i = 0; i < olid.length; i++) {
				String idStr = "" + id;
				if (idStr.equals(olid[i])) {
					// 用户勾选了这个订单项
					list.add(ol);
					// 删除购物中 被结算了的商品
					delLine.add(ol);
				}
			}
		}

		E_user user = (E_user) session.getAttribute("user");

		// 生成订单信息
		E_order order = new E_order();
		
		long time = new Date().getTime();
		order.setId(UUID.randomUUID().toString());
		order.setDob(time);
		order.setCustomerid(user.getId());
		order.setOrderid(UUID.randomUUID().toString().substring(0, 6));
		order.setPaystatus(2);// 1,支付 2,未支付
		// 订单保存到数据库
		// 所有的订单项也要保存到数据库
		Da.save(order);
		
	
		int sum = 0;
		for (Orderline ol : list) {
			ol.setOrderid(order.getId());
			//ol
			Da.update(ol);
			sum+=ol.getSum();
		}
		order.setSum(sum);
		Da.update(order);
		
		// 把confirm.html页面需要的数据放到容器中
		request.setAttribute("order", order);
		request.setAttribute("list", list);
		request.getRequestDispatcher("c/confirm.jsp").forward(request, response);

		// 移除购物车中的已经被提交了的订单项信息
		lines.removeAll(delLine);
		System.out.println("---------------");
		lines.stream().forEach(System.out::println);
		System.out.println("---------------");
		car.setLines(lines);

		session.setAttribute("car", car);

	}

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
