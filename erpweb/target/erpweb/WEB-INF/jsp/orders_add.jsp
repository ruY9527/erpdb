<%--
  Created by IntelliJ IDEA.
  User: 鲍洋
  Date: 2018/9/17
  Time: 15:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>申请采购</title>
    <!-- 引入css样式 -->
    <link rel="stylesheet" type="text/css" href="../ui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="../ui/themes/icon.css">
    <!--引入js插件-->
    <script type="text/javascript" src="../ui/jquery.min.js"></script>
    <script type="text/javascript" src="../ui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="../ui/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="../ui/jquery.serializejson.min.js"></script>
    <script type="text/javascript" src="../js/date.js"></script>
    <script type="text/javascript" src="s../js/orders_add.js" charset="UTF-8"></script>
</head>
<body>
<form id="orderForm">
    供应商:<input id="supplier" name="supplierId">
    <div style="height: 2px;"></div>
</form>
<table id="grid"></table>
</body>
</html>
