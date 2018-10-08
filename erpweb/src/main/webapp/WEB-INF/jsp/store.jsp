<%--
  Created by IntelliJ IDEA.
  User: 鲍洋
  Date: 2018/9/16
  Time: 23:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>仓库管理</title>
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
    <script type="text/javascript">
        var name="store";
        var columns=[[
            {field:'sid',title:'编号',width:100},
            {field:'name',title:'名称',width:100},
            {field:'emp',title:'员工编号(姓名)',width:100,formatter:function (value) {
                    return value.eid+"("+value.name+")";
                }},
           /* {field:'emp',title:'员工名字',width:100,formatter:function (value) {
                    return value.name;
                }},*/

            {field:'-',title:'操作',width:200,formatter: function(value,row,index){
                    var oper = "<a href=\"javascript:void(0)\" onclick=\"edit(" + row.sid + ')">修改</a>';
                    oper += ' <a href="javascript:void(0)" onclick="dele(' + row.sid + ')">删除</a>';
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
        员工编号：<input name="emp.eid" >

        <button type="button" id="btnSearch">查询</button>
    </form>
    <div style="height:2px;"></div>
</div>
<table id="grid"></table>

<div id="editDlg">
    <form id="editForm">
        <input name="sid" type="hidden">

        <table>
            <tr>
                <td>名称</td><td><input name="name"> </td>
            </tr>
            <tr>
                <td>员工姓名</td><td><input name="emp.eid" class="easyui-combobox" data-options="
                                   url:'../emp/selectAll',valueField:'eid',textField:'name' ">
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
