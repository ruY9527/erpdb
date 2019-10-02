<%--
  Created by IntelliJ IDEA.
  User: 鲍洋
  Date: 2018/9/29
  Time: 23:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<title>申请退货</title>
<link rel="stylesheet" type="text/css" href="../ui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="../ui/themes/icon.css">

<script type="text/javascript" src="../ui/jquery.min.js"></script>
<script type="text/javascript" src="../ui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="../ui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="../ui/jquery.serializejson.min.js"></script>
<%--<script type="text/javascript" src="../../js/orders_add.js" charset="UTF-8"></script>--%>
<script type="text/javascript" src="../js/returnorders_add.js"></script>
</head>
<body>
<form id="orderForm">
    供应商:<input id="supplier" name="supplierId">
    <div style="height: 2px;"></div>
</form>
<table id="grid"></table>
</body>
</html>
