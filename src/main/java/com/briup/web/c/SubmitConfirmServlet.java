package com.briup.web.c;

import java.io.IOException;
import java.util.Date;
import java.util.List;

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
/**
 * 提交订单
 * */
@WebServlet("/submitconfirm")
public class SubmitConfirmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String tel = request.getParameter("tel");
		String orderId = request.getParameter("id");
		
		String orSql = "select * from e_order where id = '"+orderId+"'";
		System.out.println("====="+orSql);
		boolean exists = Da.exists(orSql);
		E_order order = null;
		if(!exists){
			System.out.println("数据库混乱，严重错误");
			return;
		}
		order = Da.query(orSql, E_order.class).get(0);
		
		HttpSession session = request.getSession();
		Object obj = session.getAttribute("user");
		if (obj == null) {// 没有登入
			request.setAttribute("msg", "没有登入，请先登入");
			request.getRequestDispatcher("a/login.jsp").forward(request, response);
			return;
		}
		E_user user = (E_user)obj;
		order.setCustomerid(user.getId());
		order.setDob(new Date().getTime());
		order.setPaystatus(2);
		order.setReceiveaddress(address);
		order.setReceivename(name);
		order.setReceivephone(tel);
		Da.update(order);

		
		String olsql = "select * from orderline where orderid='"+order.getId()+"'";
		List<Orderline> olist = Da.query(olsql, Orderline.class);
		
		for(Orderline oli :olist) {
			/**
			 * 删除推荐表grms中的数据
			 * */
			String delOrder = "delete from grms where usid = '"+user.getId()+"' and proid = '"+oli.getBookid()+"'";
			System.out.println("-------------"+delOrder);
			Da.exist(delOrder);
		}
		
		response.sendRedirect("c/confirmSuccess.jsp");
	}
}
