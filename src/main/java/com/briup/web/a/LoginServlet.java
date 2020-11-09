package com.briup.web.a;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.briup.bean.E_user;
import com.briup.util.Da;
import com.briup.util.Shopcar;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		
		//查询表  ：E_user
		
		//获得映射接口实现类对象
		String sql = "select * from e_user where username='"+name+"' and password='"+password+"'";
		
		//使用where条件进行查询
		List<E_user> list = Da.query(sql, E_user.class);
		//用户名或密码错误
		if(list==null || list.size()==0) {
			//用户名密码错误
			request.setAttribute("msg", "用户名或密码错误!");
			request.getRequestDispatcher("a/login.jsp").forward(request, response);
			return;
		}else {
			//用户名密码正确
			HttpSession session = request.getSession();
			//根据用户输入的用户名和密码 得到的用户信息
			E_user user = list.get(0);
			//把用户的信息放到session中
			//其他模块可以使用这个user 是否有值 判断是否有用户登入
			session.setAttribute("user",user );
			//创建购物车 ，购物车放到session中
			Shopcar car = new Shopcar();
			session.setAttribute("car", car);
						
			request.getRequestDispatcher("b/index.jsp").forward(request, response);
			return;
		}
	}

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
