$(function(){

    $('#tree').tree({
        //url:'../roleEmpSet/findAll?rid=1',  //数据来源
        animate:true,   //是否动画显示
        checkbox:true
    });

    $('#grid').datagrid({
        url:'../emp/selectAll',
        columns:[[
            {field:'eid',title:'编号',width:100},
            {field:'name',title:'名称',width:100}
        ]],
        singleSelect:true,
        onClickRow:function(rowIndex,rowData){
            $('#tree').tree({
                url:'../roleEmpSet/findAll?eid='+rowData.eid,
                animate:true,
                checkbox:true
            })
        }
    });

    $('#btnSave').bind('click',function(){
        //获取所有节点
        var nodes =  $('#tree').tree('getChecked');
        //拼接每一个节点
        var checkedStr = new Array();
        $.each(nodes,function (i,node) {
            checkedStr.push(node.id);
        });
        //将数组转化为分割的字符串
        checkedStr = checkedStr.join(",");
        var formdata = {};
        formdata.eid =  $('#grid').datagrid('getSelected').eid;
        formdata.checkedStr = checkedStr;
        $.ajax({
            url:'../roleEmpSet/updateRoleEmp',
            data:formdata,
            type:'post',
            dataType:'json',
            success:function(rtn){
                $.messager.alert('提示',rtn.message,'info');
            }
        });
    })
});