<%--
  Created by IntelliJ IDEA.
  User: 鲍洋
  Date: 2018/9/14
  Time: 15:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>DEPT</title>
    <link rel="stylesheet" type="text/css" href="../ui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="../ui/themes/icon.css">

    <script type="text/javascript" src="../ui/jquery.min.js"></script>
    <script type="text/javascript" src="../ui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="../ui/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="../ui/jquery.serializejson.min.js"></script>
    <script type="text/javascript" src="../ui/download.js"></script>
    <script type="text/javascript" src="../js/baseCurd.js" charset="UTF-8"></script>
    <script type="text/javascript">
            var name = "dept";
            var columns=[[
            {field:'did',title:'部门编号',width:100},
            {field:'name',title:'部门名称',width:100},
            {field:'tele',title:'部门电话',width:100},
            {field:'-',title:'操作',width:100,formatter:function(value,row,index){
                    var oper = '&nbsp;&nbsp;<a href="javascript:void(0)" onclick="edit('+row.did+')">   修改</a>';
                    oper += '&nbsp;&nbsp;<a href="javascript:void(0)" onclick="dele('+row.did+')">   删除</a>';
                    return oper;
                }}
        ]]
    </script>
</head>
<body>
<div class="easyui-panel" style="padding-left:40px;border-bottom: 0px"></div>
<form id="searchForm">
    <table>
        <tr>
            <td>部门名称</td>
            <td><input type="text" name="name"></td>

            <td>部门电话</td>
            <td><input type="text" name="tele"></td>
            <td>
                <button id="btnSearch" type="button">查询</button>
            </td>
        </tr>

    </table>

</form>
<table id="grid"></table>

<div id="editDlg" class="easyui-window" title="部门编辑"
     style="width: 300px;height: 200px" data-options="closed:true">
    <form id="editForm">
        <input type="hidden" name="did">
        <table>
            <tr>
                <td>名称:<input type="text" name="name" /></td>
            </tr>
            <tr>
                <td>电话:<input type="text" name="tele" /></td>
            </tr>
        </table>
        <button type="button" id="btnSave">保存</button>
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
