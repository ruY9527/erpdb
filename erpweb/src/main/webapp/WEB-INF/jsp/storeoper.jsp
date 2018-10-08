<%--
  Created by IntelliJ IDEA.
  User: 鲍洋
  Date: 2018/9/16
  Time: 23:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>仓库操作记录管理</title>
    <!-- 引入css样式 -->
    <link rel="stylesheet" type="text/css" href="../ui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="../ui/themes/icon.css">
    <!--引入js插件-->
    <script type="text/javascript" src="../ui/jquery.min.js"></script>
    <script type="text/javascript" src="../ui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="../ui/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="../ui/jquery.serializejson.min.js"></script>
    <script type="text/javascript" src="../js/date.js"></script>
    <script type="text/javascript" src="../js/storeOper.js"></script>
</head>
<body>
<%--<div class="easyui-panel" style="padding-left:4px;border-bottom:0px;">
    <div style="height:2px;"></div>
    <form id="searchForm">
        操作员工：<input name="empId" class="easyui-combobox"  data-options="
                     url:'../emp/selectAll',valueField:'eid',textField:'name'
                    ">

        操作日期：<input name="opertime" class="easyui-datebox"> -> <input name="opertimes" class="easyui-datebox">
        仓库：<input name="storeId" class="easyui-combobox" data-options="
                    url:'../store/findAll', valueField:'sid',textField:'name'
                ">
        商品：<input name="goodsId" class="easyui-combobox" data-options="
                    url:'../goods/findAll', valueField:'gsid',textField:'name'
                ">
        类型：<input name="type" class="easyui-box" data-options="
                    data: [{soid:'',name:'全部'},{soid:'',name:'入库'},{soid:'',name:'出库'}],valueField:'soid',textField:'name'
                ">
        <button type="button" id="btnSearch">查询</button>
    </form>
    <div style="height:2px;"></div>
</div>
<table id="grid"></table>--%>

<div class="easyui-panel" style="padding-left:4px;border-bottom:0px;">
    <div style="height:2px;"></div>
    <form id="searchForm">
        操作员号：<input name="empId" class="easyui-combobox" style="width: 250px;" data-options="
				url:'../emp/selectAll',valueField:'eid',textField:'name'
			">
        操作日期：<input name="opertime" style="width: 250px;" class="easyui-datebox">-><input name="opertimes" style="width: 250px;" class="easyui-datebox">
        <br/>
        仓库：<input name="storeId" class="easyui-combobox" style="width: 250px;" data-options="
				url:'../store/selectAll', valueField:'sid',textField:'name'
			">

        商品：<input name="goodsId"  class="easyui-combobox" style="width: 250px;" data-options="
				url:'../goods/findAll', valueField:'gsid',textField:'name'
			">
        类型：<input name="type"  class="easyui-combobox" style="width: 250px;" data-options="
				data: [{soid:'',name:'全部'},{soid:'1',name:'入库'},{soid:'2',name:'出库'}], valueField:'soid',textField:'name'
			">

        <button type="button" id="btnSearch">查询</button>
    </form>
    <div style="height:2px;"></div>
</div>
<table id="grid"></table>
<%--

<div id="editDlg">
    <form id="editForm">
        <input name="soid" type="hidden">

        <table>
            <tr>
                <td>操作员工编号</td><td><input name="empId"> </td>
            </tr>
            <tr>
                <td>操作日期</td><td><input name="opertime"> </td>
            </tr>
            <tr>
                <td>仓库编号</td><td><input name="t.storeuuid"> </td>
            </tr>
            <tr>
                <td>商品编号</td><td><input name="goodsId"> </td>
            </tr>
            <tr>
                <td>数量</td><td><input name="num"> </td>
            </tr>
            <tr>
                <td>1：入库 2：出库</td><td><input name="type"> </td>
            </tr>

        </table>
        <button id="btnSave" type="button">保存</button>
    </form>
</div>
--%>

</body>
</html>
