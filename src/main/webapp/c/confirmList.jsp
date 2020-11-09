<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
	<title>订单列表页面</title>
<base href="/">
<link rel="stylesheet" href="css/fullCar.css" />
<link rel="stylesheet" href="css/common.css"/>
<link rel="stylesheet" href="css/style.css" />
<link rel="stylesheet" href="css/icons.css" />
<link rel="stylesheet" href="css/table.css" />
<style type="text/css">
a{
	text-decoration:none;
}
input{
	width: 30px;
}


.divs .span{
	cursor: pointer;
	border:1px solid #ccc;

}

.empty{
	border:1px solid #ccc;
	width: 80%;
	margin:-10px auto;
	height: 5px;
	overflow: auto;
	margin-bottom: 10px
	
}
.c_header{
	overflow: hidden;
	height: 40px;
	line-height: 40px;
	background-color: #EEEEEE;
	border-bottom: 1px solid #ccc

}
.c_header .leftt{
	float: left;

}
.c_header .rightt{
	float: right
}

.c_header div,.c_header span{
	font-size: 14px;
	margin-right: 70px
}
.c_book .row{
	height: 85px;
	border-top: 2px solid red;
	line-height: 85px;
	background-color: #FFFDF0
}
.c_book .row>div{
	/* float: left;
	border:1px solid red; */
	/* width: 300px; */
	margin-right: 28px;


}
.c_book .row>div div{
	float: left;
	
	
}

.row_right div{
	width: 110px;
	text-align: center;
}

#jian,#sum{
	cursor: pointer;
	font-size: 16px;
}

.center_bottom{
	width: 530px;
	position: relative;
	left: 900px;

}
.center_bottom span{
	margin-left: 30px
}
.center_bottom font{
	color: red;
	font-size: 20px
}
.center_bottom button{
	background-color: red;
	width: 90px;
	height: 30px;
	font-size: 16px
}

</style>

</head>
<body>
	<!--顶部-->
	<div class="top">
    	<div class="top_center">
            <ul class="top_bars">
            	<li><a href="logout">退出</a>|</li>
                <li style="background-color:#70A3CE"><a href="tocomfirm">我的订单</a>|</li>
               <li><a href="http://www.briup.com">关注杰普<span class="jt_down"></span></a></li>
            </ul>
        </div>
    </div>
    <!--头部-->
    <div class="header3">
    	<a href="b/index.jsp"><img src="images/logo.png"  class="oneImg"></a>
       
    </div>
    
<!--中间部分div-->
<c:forEach items="${map }" var="entry"> 

	<div class="empty">
		<div class="c_header">
			<div class="leftt">
				<span style="margin-left: 70px">订单号:${entry.key.orderid }</span>
			</div>
			<div class="rightt">
				<span><a href="#"><button>去支付</button></a></span>
				<span><a href="#"><button>删除订单</button></a></span>
				<span>数量</span>
				<span  style="margin-right: 0px">总价</span>
			</div>
		</div>
		<div class="c_book">
			<c:forEach items="${entry.value }" var="ol">
				<div class="row">
					<div style="float: left">
						<div style="margin-left: 80px;">
							<img src="${ol.bookimg }" style="float: left" width="70" height="80">
							<span>${ol.bookname }</span>
						</div>
					</div>
					<div style="float: right" class="row_right">
						<div style="margin-right: 10px">${ol.num }</div>
						<div>
							${ol.sum }
						</div>
						
					</div>
				</div>
			</c:forEach>
			
		</div>
	</div>
</c:forEach>


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
            <p>沪ICP备14033591号-8 杰普软件briup.com版权所有 杰普软件科技有限公司 </p>
          	<img src="images/police.png">
        </div>
    </div>
</body>
</html>