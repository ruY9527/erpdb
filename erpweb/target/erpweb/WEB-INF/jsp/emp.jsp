<%--
  Created by IntelliJ IDEA.
  User: 鲍洋
  Date: 2018/9/15
  Time: 13:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>员工管理</title>
    <!-- 引入css样式 -->
    <link rel="stylesheet" type="text/css" href="../ui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="../ui/themes/icon.css">
    <!--引入js插件-->
    <script type="text/javascript" src="../ui/jquery.min.js"></script>
    <script type="text/javascript" src="../ui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="../ui/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="../ui/jquery.serializejson.min.js"></script>
    <script type="text/javascript" src="../js/date.js"></script>
    <script type="text/javascript" src="../ui/download.js"></script>
    <script type="text/javascript" src="../js/baseCurd.js"></script>
    <script>
        var name = 'emp';
        var columns = [[
            {field:'eid',title:'编号',width:100},
            {field:'username',title:'登录名',width:100},
            {field:'name',title:'真实名字',width:100},
            {field:'gender',title:'性别',width:100,formatter:function (value,row,index) {
                value = row.gender;
                if(value * 1 == 1){
                    return '男';
                }
                if(0 == value * 1){
                    return '女';
                }
                }},
            {field:'email',title:'点子邮箱',width:100},
            {field:'tele', title:'联系电话',width:100},
            {field:'address',title:'联系地址',width:100},
            {field:'birthday',title:'出生年月',width:100,formatter:function (value) {
                    return new Date(value).Format("yyyy-MM-dd");
                }},
            {field:'dept',title:'部门编号',width:100,formatter:function (value) {
                    return value.name;
                }},
            {field:'-',title:'操作',width:200,formatter:function (value,row,index) {
                  var oper = "<a href='javascript:void(0)' onclick='edit("+row.eid+")'>修改    </a>";
                  oper += '<a href="javascript:void(0)" onclick="dele('+row.eid+')">       删除</a>';
                  return oper;
                }}
        ]]
    </script>
</head>
<body>
<div class="easyui-panel" style="padding-left:4px;border-bottom:0px;">
    <div style="height:2px;"></div>
    <form id="searchForm">
        登陆名：<input name="username" >
        真实姓名：<input name="name" >
        性别：<input name="gender" type="radio" value="">全部
        <input name="gender" type="radio" value="0">女
        <input name="gender" type="radio" value="1">男
        <br />
        邮件地址：<input name="email" >
        联系电话：<input name="tele" >
        联系地址：<input name="address" >
        <br />
        出生年月日：<input name="birthday" class="easyui-datebox"> 到 <input name="birthdays" class="easyui-datebox">
        部门：<input name="dept.did" class="easyui-combobox" data-options="
					url:'/dept/selectAll', textField:'name', valueField:'did'
				">

        <button type="button" id="btnSearch">查询</button>
    </form>
    <div style="height:2px;"></div>
</div>
<table id="grid"></table>
<div id="editDlg">
    <form id="editForm">
        <input name="eid" type="hidden">

        <table>
            <tr>
                <td>登陆名</td><td><input name="username" class="easyui-validatebox" data-options="
					required:true,missingMessage:'登陆名不能为空!'
					">
            </td>
            </tr>
            <tr>
                <td>真实姓名</td><td><input name="name"> </td>
            </tr>
            <tr>
                <td>性别</td>
                <td>
                    <input name="gender" type="radio" value="0">女
                    <input name="gender" type="radio" value="1">男
                </td>
            </tr>
            <tr>
                <td>邮件地址</td><td><input name="email" class="easyui-validatebox" data-options="
				required:true,validType:'email',invalidMessage:'Email格式不正确'
				"> </td>
            </tr>
            <tr>
                <td>联系电话</td><td><input name="tele"> </td>
            </tr>
            <tr>
                <td>联系地址</td><td><input name="address"> </td>
            </tr>
            <tr>
                <td>出生年月日</td><td><input name="birthday" class="easyui-datebox" editable="false"> </td>
            </tr>
            <tr>
                <td>部门</td><td><input name="dept.did" class="easyui-combobox" data-options="
					url:'/dept/selectAll', textField:'name', valueField:'did',required:true,select:'dept.did'">
                </td>
            </tr>

        </table>
        <button id="btnSave" type="button">保存</button>
    </form>
</div>
<!--导入exl文件 -->
<div id="importDlg" style="padding: 2px;">
    <form id="importForm" enctype="multipart/form-data">
        导入文件:<input type="file" name="file">
    </form>
</div>
</body>
</html>
