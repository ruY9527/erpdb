<%--
  Created by IntelliJ IDEA.
  User: 鲍洋
  Date: 2018/9/16
  Time: 23:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>订单管理</title>
    <link rel="stylesheet" type="text/css" href="../ui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="../ui/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="../ui/themes/table.css">
    <!--引入js插件-->
    <script type="text/javascript" src="../ui/jquery.min.js"></script>
    <script type="text/javascript" src="../ui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="../ui/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="../ui/jquery.serializejson.min.js"></script>
    <script type="text/javascript" src="../js/date.js"></script>
    <script type="text/javascript" src="../js/request.js"></script>
    <script type="text/javascript" src="../js/orders.js"></script>
    <script type="text/javascript" src="../js/orders_add.js"></script>
</head>
<body>
<%--<div class="easyui-panel" style="padding-left:4px;border-bottom:0px;">
    <div style="height:2px;"></div>
    <form id="searchForm">
        生成日期：<input name="createtime" >
        审核日期：<input name="checktime" >
        确认日期：<input name="starttime" >
        入库或出库日期：<input name="endtime" >
        1:采购 2:销售：<input name="type" >
        下单员：<input name="creater" >
        审核员：<input name="checker" >
        采购员：<input name="starter" >
        库管员：<input name="ender" >
        供应商或客户：<input name="supplierId" >
        合计金额：<input name="totalmoney" >
        采购: 0:未审核 1:已审核, 2:已确认, 3:已入库；销售：0:未出库 1:已出库：<input name="state" >
        运单号：<input name="waybillsn" >

        <button type="button" id="btnSearch">查询</button>
    </form>
    <div style="height:2px;"></div>
</div>--%>
<div id="ordersDlg" class="easyui-dialog" style="padding:2px;" title="订单详情" data-options="modal:true,closed:true,width:700,height:320">
    <table class="hovertable">
        <tr>
            <td class="bg">流水号</td>
            <td id="oid"></td>
            <td class="bg">供应商</td>
            <td colspan="3" id="supplierName"></td>
            <td class="bg">状态</td>
            <td id="state"></td>
        </tr>
        <tr>
            <td class="bg" width="10%">下单员</td>
            <td id="creater" width="15%"></td>
            <td class="bg" width="10%">审核员</td>
            <td id="checker" width="15%"></td>
            <td class="bg" width="10%">采购员</td>
            <td id="starter" width="15%"></td>
            <td class="bg" width="10%">仓管员</td>
            <td id="ender" width="15%"></td>
        </tr>
        <tr>
            <td class="bg noBorderBottom">下单日期</td>
            <td id="createtime" class="noBorderBottom"></td>
            <td class="bg noBorderBottom">审核日期</td>
            <td id="checktime" class="noBorderBottom"></td>
            <td class="bg noBorderBottom">采购日期</td>
            <td id="starttime" class="noBorderBottom"></td>
            <td class="bg noBorderBottom">下单日期</td>
            <td id="endtime" class="noBorderBottom"></td>
        </tr>
        <tr>
            <td class="bg noBorderBottom">运单号</td>
            <td class="noBorderBottom" id="waybillsn"></td>
            <td class="bg noBorderBottom"></td>
            <td class="noBorderBottom"></td>
            <td class="bg noBorderBottom"></td>
            <td class="noBorderBottom"></td>
            <td class="bg noBorderBottom"></td>
            <td class="noBorderBottom"></td>
        </tr>
    </table>
    <!-- 明细表单 -->
    <table id="itemgrid"></table>
</div>


<!-- 入库窗口 -->
<div id="itemDlg" style="padding: 4px;">
    <form id="itemForm">
        <input type="hidden" name="odid" id="itemId" />
        <table class="hovertable">
            <tr>
                <td class="bg" width="30%">商品编号</td>
                <td width="70%" id="goodsId"></td>
            </tr>
            <tr>
                <td class="bg">商品名称</td>
                <td id="goodsname"></td>
            </tr>
            <tr>
                <td class="bg">数量</td>
                <td id="num"></td>
            </tr>
            <tr>
                <td class="bg">仓库</td>
                <td>
                    <input name="storeId" class="easyui-combobox"
                           data-options="url:'../store/findAll',textField:'name',valueField:'sid'">
                </td>
            </tr>
        </table>
    </form>
</div>

<!-- 添加订单窗口 -->
<div id="addOrdersDlg" style="padding: 2px;">
    <form id="orderForm"><!-- <span id="addOrdersSupplier"></span> -->
        <span id="addOrdersSupplier"></span>:<input id="supplier" name="supplierId" >
        <div style="height: 2px;"></div>
    </form>
    <table id="ordersgrid"></table>
</div>
<table id="grid"></table>
</div>
<!-- 物流详情窗口 -->
<div id="waybillDlg" style="padding:2px;" class="easyui-dialog" data-options="
		title:'运单详情',width:500,height:300,modal:true,closed:true
	">
    <table id="waybillgrid"></table>
</div>
</body>
</html>
