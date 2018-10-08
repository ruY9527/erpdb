var method="";
var listParam = "";
var saveParam = "";

$(function(){
    $('#grid').datagrid({
        url:'../'+name+'/findAll'+listParam,
        columns:columns,
        singleSelect:true,
        pagination:true,
        toolbar:[{
            iconCls:'icon-add',
            text:'添加',
            handler:function () {
                method = "add";
                $('#editDlg').dialog('open');
                $('#editForm').form('clear');  //清空表单
                //$("#editWindow").window('open');
            }
        },'-',{
            text:'导出',
            iconCls:'icon-tip',
            handler:function(){
                var submitData =  $('#searchForm').serializeJSON();
                $.download("../"+name+"/export"+listParam,submitData);
            }
        },'-',{
            text:'导入',
            iconCls:'icon-save',
            handler:function(){
                $('#importDlg').dialog('open');
            }
        }]
    });
    //条件查询的操作
    $("#btnSearch").bind('click',function () {
        //表单中的数据转化为json对象
        var formData = $("#searchForm").serializeJSON();
        //alert(JSON.stringify(formData));
        $("#grid").datagrid('load',formData);
    });

    var h  = 250;
    var w  = 300;

    //初始化编辑窗口
    $('#editDlg').dialog({
        title:'编辑',
        width:w,
        height:h,
        closed:true,
        modal:true
    });

    $('#importDlg').dialog({
        title:'导入数据',
        width:330,
        height:150,
        modal:true,
        closed:true,
        buttons:[{
            text:'导入',
            handler:function(){
                $.ajax({
                    url:'../'+name+"/doImport",
                    data:new FormData($('#importForm')[0]),
                    type:'post',
                    processData:false,
                    contentType:false,
                    dataType:'json',
                    success:function(data){
                        $.messager.alert('info',data.message,'info',function(){
                            if(data.success){
                                $('#importDlg').dialog('close');
                                $('#importForm').form('clear');
                                $('#grid').datagrid('reload');
                            }
                        });
                    }
                })
            }
        }]
    });

    $("#btn2").click(function () {
        var data = $('#grid').datagrid('getData');
        alert(JSON.stringify(data));
    });

    //编辑保存按钮的操作
    $("#btnSave").bind('click',function () {
        //表单得数据转化为json对象
        var formData = $('#editForm').serializeJSON();

        $.ajax({
            url:'../'+name+'/'+method+saveParam,
            data:formData,
            dataType:'json',
            type:'post',
            success:function(data){
                if(data.success){
                    //添加成功
                    //$('#editWindow').window('close');
                    $('#editDlg').dialog('close');
                    $('#grid').datagrid('reload');
                }
                $.messager.alert('提示',data.message);
            }
        });
    });
});

//判断是否有导入的功能
//var importForm = document.getElementById('importForm');
/*if($('#importForm')){

}*/

//删除的函数
function dele(id){
    $.messager.confirm("提示","确定要删除吗?",function(value){
        if(value){
            $.ajax({
                url:'/'+name+'/delete?id='+id,
                dataType:'json',
                success:function (data) {
                    $.messager.alert('提示',data.message);
                    if(data.success){
                        $("#grid").datagrid('reload');
                    }
                }
            });
        }
    });
}
//修改前,根据id获取对象信息的函数
function edit(id){
    //打开窗体
    //$("#editWindow").window('open');
    $('#editDlg').dialog('open');
    //清空表单中得数据
    $("#editForm").form('clear');
    //加载表单中得数据
    $("#editForm").form('load','../'+name+'/getPojoById?id='+id);
    method="update";
}