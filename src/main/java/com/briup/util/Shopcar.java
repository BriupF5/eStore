package com.briup.util;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.briup.bean.Orderline;
import com.briup.bean.Product;

/**
 * 
 * 购物车
 * @author briup
 * 
 */
public class Shopcar {

	
	//购物车中包含多个订单项
	private List<Orderline> lines = new ArrayList<>();
	
	//获取商品总数
	public int getCount() {
		int count = 0;
		for(Orderline line:lines) {
			int num = line.getNum();
			count += num;
		}
		return count;
	}
	
	//获取订单总额—— property:total
	public double getTotal() {
		
		if(isEmpty()) {
			return 0;
		}
		
		double total = 0;
		for(Orderline line:lines) {
			total += line.getBookprice().doubleValue()* line.getNum().doubleValue();
		}
		return total;
	}
	public void setLines(List<Orderline> olList) {
		lines = olList;
	}
	public List<Orderline> getLines(){
		return lines;
	}
	
	//增加购物车中商品条目
	public void add(int bookId) {
		for(Orderline line:lines) {
			if(line.getBookid().intValue()==bookId) {
				line.setNum(line.getNum()+1);
			}
		}
	}
	
	//减少购物车中的商品条目
	public void sub(int bookId) {
		
		Orderline ol = null;
		
		for(Orderline line:lines) {
			if(line.getBookid().intValue()==bookId) {
				line.setNum(line.getNum()+1);
				ol = line;
			}
		}
		
		if(ol.getNum().intValue()==0) {
			lines.remove(ol);
		}
	}
	
	//向购物车中添加书籍
	public void add(Product book) {
		for(Orderline line : lines) {
			System.out.println("line:"+line.getBookid()+"\t"+book.getId());
			if(line.getBookid()==book.getId()) {
				line.setNum(line.getNum()+1);
				int oldSum = line.getSum()!=null?line.getSum():0;
				int add = oldSum+book.getPrice();
				line.setSum(add);
				return;
			}
		}
		Orderline line = new Orderline();
		line.setBookid(book.getId());
		line.setBookimg(book.getImg());
		line.setBookname(book.getName());
		line.setBookprice(book.getPrice());
		line.setNum(1);
		line.setSum(book.getPrice());
		lines.add(line);
	}
	
	//判断购物车是否为空
	public boolean isEmpty() {
		return lines.size() == 0 ;
	}

	@Override
	public String toString() {
		
		if(isEmpty()) {
			return "购物车为空！";
		}
		String str = "";
		for(Orderline line:lines) {
			str += line.getBookname()+",数量:"+line.getNum();
		}
		return str;
	}
	
	
	
	
	
	
	
	
	
	
	
}
