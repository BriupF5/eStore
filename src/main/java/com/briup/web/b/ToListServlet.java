package com.briup.web.b;

import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.briup.bean.Catedet;
import com.briup.bean.E_user;
import com.briup.bean.Grms;
import com.briup.bean.Product;
import com.briup.util.Da;

@WebServlet("/toList")
public class ToListServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Object obj = session.getAttribute("user");
		if (obj == null) {// 没有登入
			request.setAttribute("msg", "没有登入，请先登入");
			request.getRequestDispatcher("a/login.jsp").forward(request, response);
			return;
		}
		
		//如果catid值不为空,表示用户点击了一级栏目
		String catid = request.getParameter("catid");
		//如果detid值不为空,表示用户点击了二级栏目
		String detid = request.getParameter("detid");
		
		//用来存放 list.jsp需要显示的数据
		List<Product> list = new ArrayList<Product>();
		//用来存放 当前页面显示的所有书籍的 有关出版社信息
		Set<String> publishList = new HashSet<>();
		
		//二级栏目dao
		//CatedetMapper detDao = BriupUtil.getMapper(CatedetMapper.class);
		//产品dao
		//ProductMapper proDao = BriupUtil.getMapper(ProductMapper.class);
		
		if(catid!=null && !"".equals(catid)) {
			//用户点击了一级栏目
			String detsql = "select *  from catedet where categoryid = "+catid;
			System.out.println("一一一一"+detsql);
			//属于一级栏目的所有二级栏目
			List<Catedet> detList = Da.query(detsql, Catedet.class);
			for(Catedet det : detList) {
				String prosql ="select * from product where categorytwoid = "+det.getId();
				//二级栏目下所有的 产品
				List<Product> proList = Da.query(prosql, Product.class);
				//添加到 所有的list中
				list.addAll(proList);
			}
		}else if(detid!=null && !"".equals(detid)) {
			//用户点击了二级栏目
			String prosql = "select * from product where categorytwoid="+detid;
			//二级栏目下所有的 产品
			List<Product> proList = Da.query(prosql, Product.class);
			//添加到 所有的list中
			list.addAll(proList);
		}else {
			System.out.println("点击有误");
			request.getRequestDispatcher("/b/index.jsp").forward(request, response);
			return;
		}
		
		//遍历所有产品得到所有产品的出版社
		for(Product p :list) {
			publishList.add(p.getPublish());
		}
		
		//所有书籍和所有 出版社 都设置到容器中 传递到页面
		request.getSession().setAttribute("list", list);
		request.getSession().setAttribute("publishList", publishList);
		
		
		E_user user = (E_user)obj;
		
		//获取大数据 推荐引擎的前4个数据 按照推荐程度排序
		//读取表grms
		String sql = "select * from grms where usid='"+user.getId()+"'";
		List<Grms> query1 = Da.query(sql, Grms.class);
		Set<Grms> query = new TreeSet<>(query1);
		System.out.println("************************"+query.size()+"********************");
		System.out.println("-----------------"+query);
		
		Map<Grms, Product> map = new TreeMap<>();
		for(Grms g : query){
			String gpsql = "select * from product where id = "+g.getProid();
			Product product = Da.query(gpsql, Product.class).get(0);
			map.put(g, product);
		}
		
		
		request.getSession().setAttribute("grms", map);
		System.out.println("推荐数据 : "+map);
		request.getRequestDispatcher("/b/list.jsp").forward(request, response);
		
		
		
		
		
		
		
		
		
		
	}

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
