<%--
  Created by IntelliJ IDEA.
  User: 鲍洋
  Date: 2018/9/27
  Time: 13:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户角色管理</title>
    <!-- 引入css样式 -->
    <link rel="stylesheet" type="text/css" href="../ui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="../ui/themes/icon.css">
    <!--引入js插件-->
    <script type="text/javascript" src="../ui/jquery.min.js"></script>
    <script type="text/javascript" src="../ui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="../ui/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="../ui/jquery.serializejson.min.js"></script>
    <script type="text/javascript" src="../js/date.js"></script>
    <script type="text/javascript" src="../js/roleEmp.js"></script>
</head>
<body class="easyui-layout">
<div data-options="region:'west',title:'用户列表'" style="padding: 4px;width: 300px;">
    <table id="grid"></table>
</div>
<div data-options="region:'center',title:'角色设置'" style="padding: 4px;">
    <ul id="tree"></ul>
    <button id="btnSave">保存</button>
</div>
</body>
</body>
</html>
