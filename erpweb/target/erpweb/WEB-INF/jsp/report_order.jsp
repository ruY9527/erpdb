<%--
  Created by IntelliJ IDEA.
  User: 鲍洋
  Date: 2018/9/23
  Time: 9:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>销售统计</title>
    <link href="../ui/themes/default/easyui.css" rel="stylesheet" type="text/css" >
    <link href="../ui/themes/icon.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="../ui/jquery.min.js"></script>
    <script type="text/javascript" src="../ui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="../ui/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="../ui/jquery.serializejson.min.js"></script>
    <script type="text/javascript" src="../ui/highcharts.js"></script>
    <script type="text/javascript" src="../js/report.js"></script>
</head>
<body class="easyui-layout">
<div data-options="region:'center',title:'销售统计表'" style="padding: 4px; background-color: #eee">
   <form id="searchForm">
       开始日期:<input  name="startDate" class="easyui-datebox">
       结束日期:<input name="endDate" class="easyui-datebox">
                <button type="button" id="btnSearch">查询</button>
   </form>
<div style="height: 4px;"></div>
<table id="grid"></table>
</div>
<div data-options="region:'east',title:'销售统计图',split:true" style="width:600px">
<div id="pieChart" style="min-width: 310px;height: 400px;max-width: 600px;margin: 0 auto"></div>
</div>
</body>
</html>
