<%--
  Created by IntelliJ IDEA.
  User: 鲍洋
  Date: 2018/9/26
  Time: 12:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>管理</title>
    <link rel="stylesheet" type="text/css" href="../ui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="../ui/themes/icon.css">
    <script type="text/javascript" src="../ui/jquery.min.js"></script>
    <script type="text/javascript" src="../ui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="../ui/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="../ui/jquery.serializejson.min.js"></script>
    <script type="text/javascript" src="../admin/js/crud.js"></script>
    <script type="text/javascript">
        var name = "waybill";
        var columns=[[
            {field:'sn',title:'运单号',width:100},
            {field:'userId',title:'用户编号',width:100},
            {field:'toaddress',title:'收货地址',width:100},
            {field:'addressperson',title:'收货人',width:100},
            {field:'tele',title:'收货电话',width:100},
            {field:'info',title:'订单详情',width:100},
            {field:'state',title:'订单状态',width:100},
            {field:'-',title:'操作',width:200,formatter: function(value,row,index){
                    var oper = "<a href=\"javascript:void(0)\" onclick=\"edit(" + row.sn + ')">修改</a>';
                    oper += ' <a href="javascript:void(0)" onclick="del(' + row.sn + ')">删除</a>';
                    return oper;
            }}
        ]]
    </script>
</head>
<body>
<div class="easyui-panel" style="padding-left:4px;border-bottom:0px;">
    <div style="height:2px;"></div>
    <form id="searchForm">
        用户ID：<input name="userId" >
        收货地址：<input name="toaddress" >
        收货人：<input name="addressperson" >
        收件人电话：<input name="tele" >
        运单详情：<input name="info" >
        运单状态：<input name="state" >

        <button type="button" id="btnSearch">查询</button>
    </form>
    <div style="height:2px;"></div>
</div>
<table id="grid"></table>

<div id="editDlg">
    <form id="editForm">
        <input name="sn" type="hidden">

        <table>
            <tr>
                <td>用户ID</td><td><input name="userId"> </td>
            </tr>
            <tr>
                <td>收货地址</td><td><input name="toaddress"> </td>
            </tr>
            <tr>
                <td>收货人</td><td><input name="addressperson"> </td>
            </tr>
            <tr>
                <td>收件人电话</td><td><input name="tele"> </td>
            </tr>
            <tr>
                <td>运单详情</td><td><input name="info"> </td>
            </tr>
            <tr>
                <td>运单状态</td><td><input name="state"> </td>
            </tr>

        </table>
        <button id="btnSave" type="button">保存</button>
    </form>
</div>
</body>
</html>
