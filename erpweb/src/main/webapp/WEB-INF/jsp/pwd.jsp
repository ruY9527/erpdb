<%--
  Created by IntelliJ IDEA.
  User: 鲍洋
  Date: 2018/9/16
  Time: 21:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>员工重置密码</title>
    <!-- 引入css样式 -->
    <link rel="stylesheet" type="text/css" href="../ui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="../ui/themes/icon.css">
    <!--引入js插件-->
    <script type="text/javascript" src="../ui/jquery.min.js"></script>
    <script type="text/javascript" src="../ui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="../ui/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="../ui/jquery.serializejson.min.js"></script>
    <script type="text/javascript" src="../js/date.js"></script>
    <script type="text/javascript" src="../js/pwd.js" ></script>
</head>
<body>
<table id="grid"></table>
<div id="editDlg" class="easyui-dialog" style="padding:8px">
    <form id="editForm">
        <input name="eid" type="hidden" />
        <table>
            <tr>
                <td>新密码:</td>
                <td>
                    <input type="text" name="pwd" id="texPwd" />
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
