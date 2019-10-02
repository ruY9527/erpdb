<%--
  Created by IntelliJ IDEA.
  User: 鲍洋
  Date: 2018/9/16
  Time: 22:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>商品管理</title>
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
        var name = "goods";
        var columns = [[
            {field:'gsid',title:'编号',width:100},
            {field:'name',title:'名称',width:100},
            {field:'origin',title:'产地',width:100},
            {field:'producer',title:'厂家',width:100},
            {field:'unit',title:'计量单位',width:100},
            {field:'inprice',title:'进货价格',width:100},
            {field:'outprice',title:'销售价格',width:100},
            {field:'goodsType',title:'商品类型',width:100,formatter:function(value){
                    return value.name;
                }},
            {field:'-',title:'操作',width:200,formatter: function(value,row,index){
                    var oper = "<a href=\"javascript:void(0)\" onclick=\"edit(" + row.gsid + ')">修改</a>';
                    oper += ' <a href="javascript:void(0)" onclick="dele(' + row.gsid + ')">删除</a>';
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
        产地：<input name="origin" >
        厂家：<input name="producer" >
        计量单位：<input name="unit" >
        进货价格：<input name="inprice" >
        销售价格：<input name="outprice" >
        商品类型：<input name="goodsType.gid" class="easyui-combobox" data-options="
			url:'../goodsType/selectAll', valueField:'gid', textField:'name'
		">

        <button type="button" id="btnSearch">查询</button>
    </form>
    <div style="height:2px;"></div>
</div>
<table id="grid"></table>
<div id="editDlg">
    <form id="editForm">
        <input name="t.uuid" type="hidden">

        <table>
            <tr>
                <td>名称</td><td><input name="name"> </td>
            </tr>
            <tr>
                <td>产地</td><td><input name="origin"> </td>
            </tr>
            <tr>
                <td>厂家</td><td><input name="producer"> </td>
            </tr>
            <tr>
                <td>计量单位</td><td><input name="unit"> </td>
            </tr>
            <tr>
                <td>进货价格</td><td><input name="inprice" class="easyui-numberbox" data-options="min:0,precision:2,prefix:'￥'"> </td>
            </tr>
            <tr>
                <td>销售价格</td><td><input name="outprice" class="easyui-numberbox" data-options="min:0,precision:2,prefix:'￥'"> </td>
            </tr>
            <tr>
                <td>商品类型</td><td><input name="goodsType.gid" class="easyui-combobox" data-options="
			url:'../goodsType/selectAll ', valueField:'gid', textField:'name'
				"> </td>
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
