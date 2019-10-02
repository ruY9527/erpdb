<%--
  Created by IntelliJ IDEA.
  User: 鲍洋
  Date: 2018/9/16
  Time: 23:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>菜单管理</title>
    <!-- 引入css样式 -->
    <link rel="stylesheet" type="text/css" href="../ui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="../ui/themes/icon.css">
    <!--引入js插件-->
    <script type="text/javascript" src="../ui/jquery.min.js"></script>
    <script type="text/javascript" src="../ui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="../ui/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="../ui/jquery.serializejson.min.js"></script>
    <script type="text/javascript" src="../js/date.js"></script>
    <script type="text/javascript" src="../js/baseCurd.js"></script>
    <script type="text/javascript">
        var name = "menu";
        var columns = [[
            {field:'menuId',title:'菜单ID',width:100},
            {field:'menuName',title:'菜单名称',width:100},
            {field:'icon',title:'图标',width:100},
            {field:'url',title:'URL',width:100},
            {field:'pid',title:'上级菜单ID',width:100},
            {field:'-',title:'操作',width:200,formatter: function(value,row,index){
                    var oper = "<a href=\"javascript:void(0)\" onclick=\"edit(" + row.menuId + ')">修改</a>';
                    oper += ' <a href="javascript:void(0)" onclick="dele(' + row.menuId + ')">删除</a>';
                    return oper;
                }}
        ]];
    </script>
</head>
<body>
div class="easyui-panel" style="padding-left:4px;border-bottom:0px;">
<div style="height:2px;"></div>
<form id="searchForm">
    菜单名称：<input name="menuName" >
    图标：<input name="icon" >
    URL：<input name="url" >
    上级菜单ID：<input name="pid" >
    <button type="button" id="btnSearch">查询</button>
</form>
<div style="height:2px;"></div>
</div>
<table id="grid"></table>

<div id="editDlg">
    <form id="editForm">
        <input name="menuId" type="hidden">

        <table>
            <tr>
                <td>菜单名称</td><td><input name="menuName"> </td>
            </tr>
            <tr>
                <td>图标</td><td><input name="icon"> </td>
            </tr>
            <tr>
                <td>URL</td><td><input name="url"> </td>
            </tr>
            <tr>
                <td>上级菜单ID</td><td><input name="pid"> </td>
            </tr>

        </table>
        <button id="btnSave" type="button">保存</button>
    </form>
</div>
</body>
</html>
