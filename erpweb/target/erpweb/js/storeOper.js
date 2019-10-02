$(function(){
    $('#grid').datagrid({
        url:'../storeOper/findAll',
        columns : [[
            {field:'soid',title:'编号',width:100},
            {field:'emp',title:'操作员工编号',width:100,formatter:rename},
            {field:'opertime',title:'操作日期',width:100,formatter:formatDate},
            {field:'store',title:'仓库编号',width:100,formatter:rename},
            {field:'goods',title:'商品编号',width:100,formatter:rename},
            {field:'num',title:'数量',width:100},
            {field:'type',title:'操作类型',width:100,formatter:getType}
        ]],
        SingleSelect:true,
        pagination:true,
        fitColumns:true
    });

    //点击查询按钮
    $('#btnSearch').bind('click',function(){
        var formData =  $('#searchForm').serializeJSON();
        $('#grid').datagrid('load',formData);
    });

});


function formatDate(value){
    if(value !=null){
        return new Date(value).Format('yyyy-MM-dd hh:mm:ss');
    }
       return '';
}

function rename(value){
    if(value != null){
        return value.name;
    }
    return '';
}

function getType(value){
    if(value*1==1){
        return '入库';
    }
    if(value*1==2){
        return '出库';
    }
}