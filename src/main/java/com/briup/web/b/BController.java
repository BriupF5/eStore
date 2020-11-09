package com.briup.web.b;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.briup.bean.Product;
import com.briup.util.Da;

@Controller
public class BController {
	/**
	 * @param id 书籍的id
	 * */
	@RequestMapping("/toviewBook")
	public String toviewBook(String id,Model model) {
		
		String prosql = "select * from product where id = "+id;
		Product pro = Da.query(prosql, Product.class).get(0);
		
		model.addAttribute("pro", pro);
		
		return "b/viewBook.jsp";
	}
	
}
