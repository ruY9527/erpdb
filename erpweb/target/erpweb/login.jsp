<%--
  Created by IntelliJ IDEA.
  User: 鲍洋
  Date: 2018/9/15
  Time: 23:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ERP管理系统</title>
    <link rel="stylesheet" type="text/css" href="css/login.css"/>
    <link rel="stylesheet" type="text/css" href="ui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="ui/themes/icon.css">
    <script type="text/javascript" src="ui/jquery.min.js"></script>
    <script type="text/javascript" src="ui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="ui/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="ui/jquery.serializejson.min.js"></script>
    <script type="text/javascript">
        function login(){
            var formData = $("#loginform").serializeJSON();
            $.ajax({
                url:'login/login',
                data:formData,
                dataType:'json',
                success:function(data){
                    if(data.success){
                        //登录成功
                        location.href='index.jsp';
                    } else {
                        $.messager.alert('提示',data.message,'info');
                    }
                }
            });
        }
    </script>
</head>
<body>
<div class='signup_container'>
    <div class="w-load"><div class="spin"></div></div>
    <h1 class='signup_title'>ERP管理系统</h1>
    <div id="userInfo">
        <span style="float:left; margin-left:40px; height:200px; border:0px solid red"><img src='images/erp.jpg' id='admin'/></span>
        <span style="float:left; margin-left:40px; height:200px; border:0px solid red">
            <div id="signup_forms" class="signup_forms clearfix">
                        <form class="signup_form_form" id="loginform" method="post" >

                                <div class="form_row first_row">
                                    <label for="signup_name">请输入用户名</label>
                                    <input type="text" name="username" placeholder="请输入用户名" id="signup_name" >
                                </div>
                                <div class="form_row">
                                    <label for="signup_password">请输入密码</label>
                                    <input type="password" name="pwd" placeholder="请输入密码" id="signup_password" >
                                </div>
                       </form>
            </div>
            <div id="foo"></div>
                	<br/>
        </span>
    </div>



    <div class="login-btn-set"><div class='rem'>记住我</div> <a href='javascript:login()' id="btnLogin" class='login-btn'></a></div>
</div>
</body>
</html>
