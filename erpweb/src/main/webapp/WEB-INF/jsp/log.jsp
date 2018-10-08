<%--
  Created by IntelliJ IDEA.
  User: 鲍洋
  Date: 2018/10/4
  Time: 20:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>操作记录</title>
    <!-- 引入css样式 -->
    <link rel="stylesheet" type="text/css" href="../ui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="../ui/themes/icon.css">
    <!--引入js插件-->
    <script type="text/javascript" src="../ui/jquery.min.js"></script>
    <script type="text/javascript" src="../ui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="../ui/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="../ui/jquery.serializejson.min.js"></script>
    <script type="text/javascript" src="../js/date.js"></script>
    <script type="text/javascript">
        $(function(){
               $('#grid').datagrid({
                   url:'../myLog/findAll',
                   columns:[[
                       {field:'logId',title:'日志id',width:150},
                       {field:'type',title:'日志类型',width:50},
                       {field:'title',title:'日志标题',width:100},
                       {field:'remoteAddr',title:'请求地址',width:100},
                       {field:'requestUri',title:'请求URL',width:100},
                       {field:'method',title:'提交方式',width:50},
                       {field:'params',title:'提交参数',width:200},
                       {field:'exception',title:'异常',width:50,formatter:function(value){
                               if(value ==null){
                                   return "无";
                               }
                               return value;
                           }},
                       {field:'operateDate',title:'开始时间',width:150,formatter:function(value){
                           if(value ==null){
                               return ;
                           }
                               return   new Date(value).Format("yyyy-MM-dd hh:mm:ss");
                           }},
                       {field:'timeout',title:'结束时间',width:100},
                       {field:'userId',title:'用户id',width:50}
                   ]],
                   singleSelect:true,
                   pagination:true
               });
        });
    </script>
</head>
<body>
<table id="grid"></table>
</body>
</html>
