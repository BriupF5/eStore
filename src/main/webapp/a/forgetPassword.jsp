<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>briup安全中心</title>
<base href="/">
<link rel="stylesheet" href="css/common.css"/>
<link rel="stylesheet" href="css/style.css" />
<link rel="stylesheet" href="css/icons.css" />
<link rel="stylesheet" href="css/table.css" />
<link rel="stylesheet" href="css/newmain.css" />

</head>
<body>
	<!--顶部-->
	<div class="top">
		<c:if test="${! empty user }">
			<div class="top_center">
	    		<ul class="top_lr">
	            	<li><a>欢迎  ${user.username} </a></li>
	            </ul>
	            <ul class="top_bars">
	            	<li><a href="logout">退出</a>|</li>
	                <li><a href="tocomfirm">我的订单<span class="jt_down"></span></a>|</li>
	                 <li><a href="http://www.briup.com">关注杰普<span class="jt_down"></span></a></li>
	            </ul>
	        </div>
        </c:if>
		
		<c:if test="${empty user }">
	    	<div class="top_center">
	    		<ul class="top_lr">
	            	<li><a href="a/login.jsp" style="color: red;">亲,请登入</a></li>
	                <li><a href="a/register.jsp">免费注册</a></li>
	            </ul>
	            <ul class="top_bars">
	                <li><a href="tocomfirm">我的订单<span class="jt_down"></span></a>|</li>
	                <li><a href="http://www.briup.com">关注杰普<span class="jt_down"></span></a></li>
	            </ul>
	        </div>
        </c:if>
        
    </div>
    <!--头部-->
    <div class="header3">
    	<a href="b/index.jsp"><img src="images/logo.png"></a>
    	<div class="h3_center">
        	<div class="search_box">
            	<input type="text"/>
                <span>搜索</span>
            </div>
        </div>
    </div>
    <!--头部导航-->
     <div class="nav_top">
    	<div class="nav_top_center">
            <div>
                全部图书分类
            </div>
            <ul>
            	<c:forEach items="${map }" var="entry">
	                <li><a href="toList?catid=${entry.key.id}">${entry.key.name }</a></li>
            	</c:forEach>
            </ul>
        </div>
    </div>
    <div class="container4">
    	<div class="register_box">
    			<div class="head">
    				<h3>找回密码 : 通过用户名和手机号相同就可以重置密码</h3>
					<h4>提示：根据用户输入的用户名，查询数据库得到原始手机号,</h4>
					<h4>然后使用原始手机号和用户输入的手机号对比</h4>
					<h4>如果一致,更新新密码到数据库</h4>
					<h4>但是本次没有考虑手机号的问题</h4>
					<h4>${msg }</h4>
    			</div>
    			<div class="security">
    				<form action="findpassword" method="post">
    					<ul class="list">
    						<li>
    								<input id="J_euserName" class="long" name="username" value="请输入名字" type="text" onclick="this.value=''"/>
    								<div class="name">用户名</div>
    								<span id="J_eUserNameTipImg" class="icon"></span>
    								<div id="J_eUserNameTipInfo" class="ltip"></div>
    								<span class="placeholder" style="position: absolute;z-index: 20;color: rgb(153,153,153);top: 14px;left: 345px;line-height: 37px;"></span>
    						</li>
    						<li>
    								<input id="J_euserName" class="long" name="phone" value="15062465004" type="text" onclick="this.value=''"/>
    								<div class="name">手机号</div>
    								<span id="J_eUserNameTipImg" class="icon"></span>
    								<div id="J_eUserNameTipInfo" class="ltip"></div>
    								<span class="placeholder" style="position: absolute;z-index: 20;color: rgb(153,153,153);top: 14px;left: 345px;line-height: 37px;"></span>
    						</li>
    						<li>
    								<input id="J_euserName" class="long" name="password" value="" type="text" />
    								<div class="name">新密码</div>
    								<span id="J_eUserNameTipImg" class="icon"></span>
    								<div id="J_eUserNameTipInfo" class="ltip"></div>
    								<span class="placeholder" style="position: absolute;z-index: 20;color: rgb(153,153,153);top: 14px;left: 345px;line-height: 37px;"></span>
    						</li>
    						<li>
    								<input id="J_euserName" class="long" name="password2" value="" type="text" />
    								<div class="name">确认密码</div>
    								<span id="J_eUserNameTipImg" class="icon"></span>
    								<div id="J_eUserNameTipInfo" class="ltip"></div>
    								<span class="placeholder" style="position: absolute;z-index: 20;color: rgb(153,153,153);top: 14px;left: 345px;line-height: 37px;"></span>
    						</li>
    						<li>
    							<div class="name"></div>
    							<input id="J_eUserNameSubmit" type="submit" value="修改" class="btn_red">
    							<!-- <a id="J_eUserNameSubmit" class="btn_red"  href="javascript:void(0);" onclick="return false">下一步</a> -->
    						</li>
    					</ul>
    				</form>
    			</div>
    	</div>
    </div>
 
    <!--脚部-->
    <div class="footer3">
    	<div class="f3_top">
        	<div class="f3_center">
                <div class="ts1">品目繁多 愉悦购物</div>
                <div class="ts2">正品保障 天天低价</div>
                <div class="ts3">极速物流 特色定制</div>
                <div class="ts4">优质服务 以客为尊</div>
            </div>
        </div>
        <div class="f3_middle">
        	<ul class="f3_center">
            	<li class="f3_mi_li01">
                	<h1>购物指南</h1>
                    <p>常见问题</p>
                    <p>找回密码</p>
                    <p>会员介绍</p>
                    <p>购物演示</p>
                </li>
                <li class="f3_mi_li01">
                	<h1>配送方式</h1>
                    <p>常见问题</p>
                    <p>找回密码</p>
                    <p>会员介绍</p>
                    <p>购物演示</p>
                </li>
                <li class="f3_mi_li01">
                	<h1>支付方式</h1>
                    <p>常见问题</p>
                    <p>找回密码</p>
                    <p>会员介绍</p>
                    <p>购物演示</p>
                </li>
                <li class="f3_mi_li01">
                	<h1>售后服务</h1>
                    <p>常见问题</p>
                    <p>找回密码</p>
                    <p>会员介绍</p>
                    <p>购物演示</p>
                </li>
                <li class="f3_mi_li01">
                	<h1>服务保障</h1>
                    <p>常见问题</p>
                    <p>找回密码</p>
                    <p>会员介绍</p>
                    <p>购物演示</p>
                </li>
                <li class="f3_mi_li06">
                	<h1>客服中心</h1>
                    <img src="images/qrcode_jprj.jpg" width="80px" height="80px">
                    <p>抢红包、疑问咨询、优惠活动</p>
                </li>
            </ul>
        </div>
         <div class="f3_bottom">
        	<p class="f3_links">
                <a href="#">关于我们</a>|
                <a href="#">联系我们</a>|
                <a href="#">友情链接</a>|
                <a href="#">供货商入驻</a> 
           	</p>
            <p>沪ICP备14033591号-8 杰普briup.com版权所有 杰普软件科技有限公司 </p>
          	<img src="images/police.png">
        </div>
    </div>

</body>
</html>