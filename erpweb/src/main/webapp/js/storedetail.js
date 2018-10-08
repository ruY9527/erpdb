$(function () {
    //列表
   $('#grid').datagrid({
       url:'../storedetail/findAll',
       columns:[[
           {field:'sdid',title:'编号',width:100},
           {field:'store',title:'仓库编号',width:100,formatter:function(value){
               if(value != null){
                   return value.name;
               }
                  return '';
               }},
           {field:'goods',title:'商品编号',width:100,formatter:function(value){
               if(value !=null){
                   return value.name;
               }
               }},
           {field:'num',title:'数量',width:100}
           /*{field:'-',title:'操作',width:200,formatter: function(value,row,index){
                   var oper = "<a href=\"javascript:void(0)\" onclick=\"edit(" + row.sdid + ')">修改</a>';
                   oper += ' <a href="javascript:void(0)" onclick="dele(' + row.sdid + ')">删除</a>';
                   return oper;
               }}*/
       ]],
       singSelect:true,
       paginaton:true
   });

   //点击查询按钮
    $('#btnSearch').bind('click',function(){
        var formData =  $('#searchForm').serializeJSON();
        $('#grid').datagrid('load',formData);
    });
});