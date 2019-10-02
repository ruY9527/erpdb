<%--
  Created by IntelliJ IDEA.
  User: 鲍洋
  Date: 2018/9/16
  Time: 23:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>订单明细管理</title>
    <!-- 引入css样式 -->
    <link rel="stylesheet" type="text/css" href="../ui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="../ui/themes/icon.css">
    <!--引入js插件-->
    <script type="text/javascript" src="../ui/jquery.min.js"></script>
    <script type="text/javascript" src="../ui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="../ui/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="../ui/jquery.serializejson.min.js"></script>
    <script type="text/javascript" src="../js/date.js"></script>
    <script type="text/javascript" src="../js/baseCurd.js"></script>
    <script type="text/javascript">
        var name = "orderdetail";
        var columns = [[
            {field:'odid',title:'编号',width:100},
            {field:'goodsId',title:'商品编号',width:100},
            {field:'goodsname',title:'商品名称',width:100},
            {field:'price',title:'价格',width:100},
            {field:'num',title:'数量',width:100},
            {field:'money',title:'金额',width:100},
            {field:'endtime',title:'结束日期',width:100},
            {field:'ender',title:'库管员',width:100},
            {field:'storeId',title:'仓库编号',width:100},
            {field:'state',title:'采购：0=未入库，1=已入库  销售：0=未出库，1=已出库',width:100},
            {field:'ordersId',title:'订单编号',width:100},

            {field:'-',title:'操作',width:200,formatter: function(value,row,index){
                    var oper = "<a href=\"javascript:void(0)\" onclick=\"edit(" + row.odid + ')">修改</a>';
                    oper += ' <a href="javascript:void(0)" onclick="dele(' + row.odid + ')">删除</a>';
                    return oper;
                }}
        ]]
    </script>
</head>
<body>
<div class="easyui-panel" style="padding-left:4px;border-bottom:0px;">
    <div style="height:2px;"></div>
    <form id="searchForm">
        商品编号：<input name="goodsId" >
        商品名称：<input name="goodsname" >
        价格：<input name="price" >
        数量：<input name="num" >
        金额：<input name="money" >
        结束日期：<input name="endtime" >
        库管员：<input name="ender" >
        仓库编号：<input name="storeId" >
        采购：0=未入库，1=已入库  销售：0=未出库，1=已出库：<input name="state" >
        订单编号：<input name="ordersId" >

        <button type="button" id="btnSearch">查询</button>
    </form>
    <div style="height:2px;"></div>
</div>
<table id="grid"></table>

<div id="editDlg">
    <form id="editForm">
        <input name="odid" type="hidden">

        <table>
            <tr>
                <td>商品编号</td><td><input name="goodsId"> </td>
            </tr>
            <tr>
                <td>商品名称</td><td><input name="goodsname"> </td>
            </tr>
            <tr>
                <td>价格</td><td><input name="price"> </td>
            </tr>
            <tr>
                <td>数量</td><td><input name="num"> </td>
            </tr>
            <tr>
                <td>金额</td><td><input name="money"> </td>
            </tr>
            <tr>
                <td>结束日期</td><td><input name="endtime"> </td>
            </tr>
            <tr>
                <td>库管员</td><td><input name="ender"> </td>
            </tr>
            <tr>
                <td>仓库编号</td><td><input name="storeId"> </td>
            </tr>
            <tr>
                <td>采购：0=未入库，1=已入库  销售：0=未出库，1=已出库</td><td><input name="state"> </td>
            </tr>
            <tr>
                <td>订单编号</td><td><input name="ordersId"> </td>
            </tr>

        </table>
        <button id="btnSave" type="button">保存</button>
    </form>
</div>

</body>
</html>
