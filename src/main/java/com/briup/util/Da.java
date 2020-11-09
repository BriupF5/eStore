package com.briup.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


/**
 * 封装jdbc的操作<br>
 * id一定是主键列<br>
 * */

public class Da {
	static String driver = "com.mysql.cj.jdbc.Driver";
	static String url = "jdbc:mysql://localhost:3306/bigestore?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
	static String username = "root";
	static String password = "736197080";
	
	//后期需要关闭conn stat resultSet
	static Connection conn;
	static Statement stat;
	static ResultSet rs;
	
	
	
	static{
		//注册驱动
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	private static void getConn(){
		try {
			conn = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConn111(){
		try {
			conn = DriverManager.getConnection(url, username, password);
			return conn;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	
	
	/**
	 * 判断是否有数据
	 * */
	public static <T> boolean exists(String sql){
		boolean f = false;
		getConn();
		try {
			Statement stat = conn.createStatement();
			
			rs = stat.executeQuery(sql);
			while(rs.next()){
				f=true;
				break;
			}
			close();
			return f;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;
	}
	/**
	 * 单纯执行sql
	 * */
	public static <T> boolean exist(String sql){
		boolean f = false;
		getConn();
		try {
			Statement stat = conn.createStatement();
			
			stat.execute(sql);
			
			close();
			return f;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;
	}
	
	/**
	 * 查询<br>
	 * select id,last_name from s_emp
	 * 
	 * @param sql select 语句
	 * @param t   该select语句要封装为什么数据类型
	 * @return t类下的返回值对象
	 * */
	public static <T> List<T> query(String sql,Class<T> t){
		List<T> list = new ArrayList<T>();
		getConn();
		try {
			Statement stat = conn.createStatement();
			
			rs = stat.executeQuery(sql);
			//获得结果集的相关信息
			ResultSetMetaData md = rs.getMetaData();
			//结果集有多少列
			int columnCount = md.getColumnCount();
			while(rs.next()){
				//获得了查询以后的结果集,该结果集需要封装为某个对象
				T obj = t.newInstance();
				
				//获取当前游标指向这行数据的所有列值都获取出来
				for(int i = 1;i<=columnCount;i++){
					String columnName = md.getColumnName(i);//id  name
					Object columnValue = rs.getObject(i);//获得当前行id列对应的值
					String methodName = getMethodName("set", columnName);//setId
					if(columnValue==null){
						continue;
					}
					//通过列名获取对象中的某个属性Field对象
					Field declaredField = t.getDeclaredField(columnName);
					//获得属性的类型
					Class<?> type = declaredField.getType();
					//获取对应的setXxx方法
					Method method = t.getMethod(methodName, type);
					method.invoke(obj, columnValue);
				}
				list.add(obj);
			}
			close();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * 更新<br>
	 * 和保存类似<br>
	 * update s_emp 
	 * set last_name = ? , salary = ?
	 * where id = ?
	 * */
	public static <T> void update(T t){
		//给成员变量 conn赋值
		getConn();
		//获取对象的Class
		Class<? extends Object> c = t.getClass();
		StringBuffer sql = new StringBuffer();
		sql.append("update ");
		sql.append(getTableName(c));
		sql.append(" set ");
		String objectFields = getObjectFields(c);
		String[] split = objectFields.split("[,]");
		for(int i = 0;i<split.length;i++){
			String fieldName = split[i];
			if("id".equals(fieldName.toLowerCase())){//不处理id
				continue;
			}
			sql.append(fieldName).append("=").append("?").append(",");
		}
		//去除最后一个,
		sql = sql.delete(sql.length()-1, sql.length());
		sql.append(" where ").append("id").append("=").append("?");
		try {
			PreparedStatement ps = conn.prepareStatement(sql.toString());
			int index = 1;
			//给所有?设置值
			for(int i = 0;i<split.length;i++){
				String fieldName = split[i];
				if("id".equals(fieldName.toLowerCase())){//不处理id
					continue;
				}
				String methodName = getMethodName("get",fieldName);
				//获得参数对象 中某个属性对应的get属性方法
				Method method = c.getMethod(methodName);
				//调用该get方法
				Object invoke = method.invoke(t);
				ps.setObject(index, invoke);
				System.out.println(methodName+"===val==="+invoke);
				index++;
			}
			String methodName = getMethodName("get","id");
			//获得参数对象 中某个属性对应的get属性方法
			Method method = c.getMethod(methodName);
			Object invoke = method.invoke(t);
			ps.setObject(index, invoke);//给where id = ?这个?号设置值
			System.out.println("=-----="+sql.toString());
			System.out.println("===id==="+invoke);
			ps.execute();
			close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * 保存<br>jdbc 完成保存：<br>
	 * Statment :
	 * PreparedStatment : 
	 * 	
	 * */
	public static <T> void save(T t){
		Class<? extends Object> c = t.getClass();
		//本次获得连接
		getConn();
		StringBuffer sql = new StringBuffer("insert into ");
		String tableName = getTableName(c);
		sql.append(tableName);
		sql.append("(");
		//insert into t(id,name) values(1,'tom');
		//对象下所有的属性
		String objectFields = getObjectFields(c);
		sql.append(objectFields);
		sql.append(")");
		sql.append(" values").append("(");
		String[] split = objectFields.split("[,]");
		//有length个属性，也就是有length个?号占位符
		int length = split.length;
		for(int i =0;i<length;i++){
			sql.append("?").append(",");
		}
		//去除最后一个,
		sql = sql.delete(sql.length()-1, sql.length());
		sql.append(")");
		System.out.println(sql.toString());
		
		try {
			//获得预编译对象
			PreparedStatement ps = conn.prepareStatement(sql.toString());
			//给预编译对象ps中的每个?都设置值
			for(int i = 1;i<=length;i++){
				String fieldName = split[i-1];
				String methodName = getMethodName("get",fieldName);
				//获得参数对象 中某个属性对应的get属性方法
				Method method = c.getMethod(methodName);
				//调用该get方法
				Object invoke = method.invoke(t);
				ps.setObject(i, invoke);
			}
			//执行sql
			ps.execute();
			//调用私有方法 ，关闭所有资源
			close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	/**
	 * 删除t类的id值<br>
	 * t类对应哪个表<br>
	 * 类名和表名一致<br>
	 * @param id 要删除表中的主键值
	 * @param t 要删除的类Class对象，也就要删除这个类对应的表中数据
	 * */
	public static <T> void delete(int id,Class<T> t){
		
		StringBuffer sql = new StringBuffer("delete from ");
		String simpleName = getTableName(t);
		sql.append(simpleName);
		if(id>=0){
			sql.append(" where id = ").append(id);
		}
		//不要where条件
		try {
			getConn();
			stat = conn.createStatement();
			stat.execute(sql.toString());
			close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 辅助方法<Br>
	 * 获得表名
	 * */
	private static String getTableName(Class<?> c){
		String simpleName = c.getSimpleName();//类名，也就是表名
		return simpleName;
	}
	/**
	 * 辅助方法<Br>
	 * 获取对象下的所有属性名<br>
	 * id,name,age,gender
	 * */
	private static String getObjectFields(Class<?> c){
		//获得所有属性
		Field[] declaredFields = c.getDeclaredFields();
		//创建SB 来拼接属性
		StringBuffer f = new StringBuffer();
		
		for(int i = 0;i<declaredFields.length;i++){
			Field field = declaredFields[i];
			//可能是私有属性，所以设置可见性
			field.setAccessible(true);
			//属性名
			String name = field.getName();
			f.append(name).append(",");
		}
		//删除最后一个,号
		f = f.delete(f.length()-1, f.length());
		return f.toString();
	}
	
	/**
	 * 辅助方法<br>
	 * 使用属性名 获得对应的get方法名:
	 * 属性名 : id
	 * 返回 getId();
	 * @param type get或者set
	 * @param fieldName 属性名
	 * @return 属性对应的getXxx方法名
	 * */
	private static String getMethodName(String type ,String fieldName){
		//fieldName =  id name
		//getId  getName
		StringBuffer me = new StringBuffer();
		me.append(type);
		me.append(fieldName.substring(0, 1).toUpperCase());
		me.append(fieldName.substring(1));
		return me.toString();
	}
	/**
	 * 关闭资源
	 * */
	private static void close(){
		try {
			if(conn!=null){
				conn.close();
			}
			if(stat!=null){
				stat.close();
			}
			if(rs!=null){
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
