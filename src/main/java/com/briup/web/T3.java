package com.briup.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class T3 {
	/**
	 * 去首页
	 * */
	@RequestMapping("/")
	public String b(){
		return "/a/login.jsp";
	}
	@RequestMapping("/aaa")
	public String aaa(){
		System.out.println("..............");
		return "/a/login.jsp";
	}
}
