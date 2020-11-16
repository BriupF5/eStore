package com.briup.web.a;

import com.briup.bean.E_user;
import com.briup.util.Da;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

@WebServlet("/userinfo")
public class UserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession();
        Object obj = session.getAttribute("user");
        E_user user = (E_user)obj;
        String password = request.getParameter("password");
        String zip = request.getParameter("zip");
        String address = request.getParameter("address");
        String telephone = request.getParameter("telephone");
        String email = request.getParameter("email");

//        boolean exists = Da.exists("select * from e_user where username = '"+user.getUsername()+"'");
//        if(exists){//该用户名已经被用了
//            //用户名密码错误
//            request.setAttribute("msg", "用户名已经被使用");
//            request.getRequestDispatcher("b/index.jsp").forward(request, response);
//            return;
//        }

        E_user u = new E_user();
        u.setAddress(address);
        u.setDob(new Date());
        u.setEmail(email);
        u.setPassword(password);
        u.setPhone(telephone);
        u.setUsername(user.getUsername());
        u.setId(user.getId());
        u.setZip(zip);

        Da.change(u);

        request.getRequestDispatcher("b/index.jsp").forward(request, response);

    }
}
