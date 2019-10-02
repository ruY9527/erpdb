<%--
  Created by IntelliJ IDEA.
  User: 鲍洋
  Date: 2018/9/23
  Time: 11:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>销售趋势表报</title>
    <link href="../ui/themes/default/easyui.css" rel="stylesheet" type="text/css" >
    <link href="../ui/themes/icon.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="../ui/jquery.min.js"></script>
    <script type="text/javascript" src="../ui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="../ui/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="../ui/jquery.serializejson.min.js"></script>
    <script type="text/javascript" src="../ui/highcharts.js"></script>
    <script type="text/javascript" src="../js/report_trend.js"></script>
</head>
<body class="easyui-layout">
<div style="padding-left: 4px;background-color: #eee" data-options="region:'center',title:'销售统计报表'">
    <div style="height: 2px"></div>
        <form id="searchForm">
            年份:<input  name="years" id="years" class="easyui-combobox" data-options="
                 url:'../json/year.json', valueField:'years',textField:'years',method:'get'
               ">
            <button type="button" id="btnSearch">查询</button>
        </form>
        <div style="height: 2px;"></div>
        <table id="grid"></table>
</div>
<div data-options="region:'east',title:'销售趋势图',split:true" style="width:700px;">
    <div id="trendChart"></div>
</div>

</body>
</html>
