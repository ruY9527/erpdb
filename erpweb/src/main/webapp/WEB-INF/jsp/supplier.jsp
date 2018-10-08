<%--
  Created by IntelliJ IDEA.
  User: 鲍洋
  Date: 2018/9/16
  Time: 21:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>供应商管理</title>
    <!-- 引入css样式 -->
    <link rel="stylesheet" type="text/css" href="../ui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="../ui/themes/icon.css">
    <!--引入js插件-->
    <script type="text/javascript" src="../ui/jquery.min.js"></script>
    <script type="text/javascript" src="../ui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="../ui/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="../ui/jquery.serializejson.min.js"></script>
    <script type="text/javascript" src="../js/date.js"></script>
    <script type="text/javascript" src="../js/request.js"></script>
    <script type="text/javascript" src="../ui/download.js"></script>
    <script type="text/javascript" src="../js/baseCurd.js"></script>
    <script type="text/javascript">
        if(Request['type']*1 == 1){
            document.title = "供应商管理";
            listParam = "?type=1";
            saveParam = "?type=1";
        }
        if(Request['type']*1 == 2){
            document.title = "客户管理";
            listParam = "?type=2";
            saveParam = "?type=2";
        }
        var name ="supplier";
        var height = 300;
        var width = 300;
        var columns=[[
            {field:'suid',title:'编号',width:100},
            {field:'name',title:'名称',width:100},
            {field:'address',title:'联系地址',width:100},
            {field:'contact',title:'联系人',width:100},
            {field:'tele',title:'联系电话',width:100},
            {field:'email',title:'邮件地址',width:150},
            {field:'-',title:'操作',width:200,formatter:function(value,row,index){
                var oper = '<a href="javascript:void(0)" onclick="edit('+row.suid+')">修改</a>';
                oper += '<a href="javascript:void(0)" onclick="dele('+row.suid+')">   删除</a>';
                return oper;
                }}
        ]];
    </script>
</head>
<body>
<div class="easyui-panel" style="padding-left:4px;border-bottom:0px;">
    <div style="height:2px;"></div>
    <form id="searchForm">

        名称：<input name="name" >
        联系地址：<input name="address" >
        联系人：<input name="contact" >
        联系电话：<input name="tele" >
        邮件地址：<input name="email" >

        <button type="button" id="btnSearch">查询</button>
    </form>
    <div style="height:2px;"></div>
    <table id="grid"></table>
    <div id="editDlg">
        <form id="editForm">
            <input name="suid" type="hidden">

            <table>
                <tr>
                    <td>名称</td><td><input name="name"> </td>
                </tr>
                <tr>
                    <td>联系地址</td><td><input name="address"> </td>
                </tr>
                <tr>
                    <td>联系人</td><td><input name="contact"> </td>
                </tr>
                <tr>
                    <td>联系电话</td><td><input name="tele"> </td>
                </tr>
                <tr>
                    <td>邮件地址</td><td><input name="email"> </td>
                </tr>

            </table>
            <button id="btnSave" type="button">保存</button>
        </form>
    </div>
</div>
<!--导入exl文件 -->
<div id="importDlg" style="padding: 2px;">
    <form id="importForm" enctype="multipart/form-data">
        导入文件:<input type="file" name="file">
    </form>
</div>
</body>
</html>
