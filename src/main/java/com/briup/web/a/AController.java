package com.briup.web.a;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.briup.bean.E_user;
import com.briup.util.Da;

@Controller
public class AController {
	/**
	 * 找回密码
	 * */
	@RequestMapping("/findpassword")
	public String findpassword(E_user user,String password2,Model model) {
		
		String password = user.getPassword();
		if(!password.equals(password2)) {
			model.addAttribute("msg", "两次密码不一致");
			return "forward:a/forgetPassword.jsp";
		}
		//更新到数据库
		Da.update(user);
		
		model.addAttribute("msg", "密码修改成功:"+password2.substring(0,password2.length()>3?3:password2.length())+"***"+password2.substring(password2.length()-3>0?password2.length()-3:0));
		return "a/login.jsp";
	}
	
	/**
	 * 退出
	 * */
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("user");
		session.removeAttribute("car");
		return "a/login.jsp";
	}
	
	
}
