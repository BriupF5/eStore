package com.briup.web.c;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.briup.bean.Orderline;
import com.briup.bean.Product;
import com.briup.util.Da;
import com.briup.util.Shopcar;

//在list页面购买商品
@WebServlet("/buy")
public class BuyServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 获得当前用户 选择的书id
		String id = request.getParameter("id");
		// 通过id查找到数据库中的书Product对象
		// 用户想购买的书籍对象
		String prosql = "select * from product where id=" + id;
		System.out.println(prosql);
		Product pro = Da.query(prosql, Product.class).get(0);
		// 购物车在哪？ 登入成功以后 会向session中放一个购物车
		// 获取session中的购物车对象
		HttpSession session = request.getSession();
		Object obj = session.getAttribute("car");
		if (obj == null) {// 没有登入
			request.setAttribute("msg", "没有登入，请先登入");
			request.getRequestDispatcher("a/login.jsp").forward(request, response);
			return;
		}
		Shopcar car = (Shopcar) obj;
		// 把当前的商品添加到购物车中
		car.add(pro);

		System.out.println("-----显示购物车-----");
		List<Orderline> lines = car.getLines();
		for (Orderline lin : lines) {
			lin.setId(UUID.randomUUID().toString());
			Da.save(lin);
		}

		// 把最新的购物车重新放一遍
		session.setAttribute("car", car);

		// 和B模块同学商量，把点击某栏目下要显示的所有商品，都放到session中
		request.getRequestDispatcher("b/list.jsp").forward(request, response);

	}

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
