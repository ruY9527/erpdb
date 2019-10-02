<%--
  Created by IntelliJ IDEA.
  User: 鲍洋
  Date: 2018/9/16
  Time: 23:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>仓库库存管理</title>
    <!-- 引入css样式 -->
    <link rel="stylesheet" type="text/css" href="../ui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="../ui/themes/icon.css">
    <!--引入js插件-->
    <script type="text/javascript" src="../ui/jquery.min.js"></script>
    <script type="text/javascript" src="../ui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="../ui/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="../ui/jquery.serializejson.min.js"></script>
    <script type="text/javascript" src="../js/date.js"></script>
    <script type="text/javascript" src="../js/storedetail.js"></script>
</head>
<body>
<div class="easyui-panel" style="padding-left:4px;border-bottom:0px;">
    <div style="height:2px;"></div>
    <form id="searchForm">
        仓库名称：<input name="storeId" class="easyui-combobox" data-options="
                 url:'../store/selectAll',valueField:'sid',textField:'name'
                 ">
        商品名称：<input name="goodsId" class="easyui-combobox" data-options="
                 url:'../goods/findAll',valueField:'gsid',textField:'name'
                ">
        <button type="button" id="btnSearch">查询</button>
    </form>
    <div style="height:2px;"></div>
</div>
<table id="grid"></table>

<%--<div id="editDlg">
    <form id="editForm">
        <input name="sdid" type="hidden">

        <table>
            <tr>
                <td>仓库编号</td><td><input name="storeId"> </td>
            </tr>
            <tr>
                <td>商品编号</td><td><input name="goodsId"> </td>
            </tr>
            <tr>
                <td>数量</td><td><input name="num"> </td>
            </tr>

        </table>
        <button id="btnSave" type="button">保存</button>
    </form>
</div>--%>
</body>
</html>
