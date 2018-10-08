$(function(){

    var url = '../orders/findByType?type=1';

    var btnText = '';

    //出入窗口的标题
    var inoutTitle = '';

    //我的采购订单查询
    if(Request['oper']=='myorders' && Request['type']*1==1){
        url = '../orders/myFindByType?type=1';
        document.title = '我的采购订单';
        //显示供应商
        $('#addOrdersSupplier').html('供应商');
    }

    //我的销售订单查询
    if(Request['oper']=='myorders' && Request['type']*1==2){
        url = '../orders/myFindByType?type=2';
        document.title = '销售订单查询';
    }

    //采购订单查询
    if(Request['oper']=='orders'){
        url = '../orders/findByType?type=1';
    }

    //如果审核业务,加上state=0,只查询出审核的订单
    if(Request['oper'] == 'doCheck'){
        url = '../orders/findByType?type=1&state=0';
        //document.title('采购订单审核');
    }

    //如果确认业务,加上state=1，只查询出审核的订单
    if(Request['oper']=='doStart'){
        url = '../orders/findByType?type=1&state=1';
        document.title='采购订单入库';
    }

    //如果入库,只查询入库的id信息
    if(Request['oper'] == 'doInStore'){
        url = '../orders/findByType?type=1&state=2';
        document.title = '采购订单入库';
        inoutTitle = '入库';
    }
    //销售业务出库 type=2 state = 0
    if(Request['oper'] == 'doOutStore'){
        url = '../orders/findByType?type=2&state=0';
        document.title = '销售订单出库';
        inoutTitle = '出库';
    }

    //销售订单查询
     if(Request['oper']=='orders'&&Request['type']*1==2){
        url = '../orders/findByType?type=2';
     }

    //入库,双击打开
    //表单数据加载
    $('#grid').datagrid({
        url: url,
        columns: getColumns(),
        singleSelect: true,
        pagination: true,
        fitColumns: true,
        onDblClickRow:function(rowIndex,rowData) {
            //显示订单的详细
            $('#oid').html(rowData.oid);
            $('#waybillsn').html(rowData.waybillsn);
            $('#supplierName').html(rname(rowData.supplier));
            $('#creater').html(rname(rowData.emp));
            $('#checker').html(rname(rowData.emp2));
            $('#starter').html(rname(rowData.emp3));
            $('#ender').html(rname(rowData.emp4));
            $('#createtime').html(formateDate(rowData.createtime));
            $('#checktime').html(formateDate(rowData.checktime));
            $('#starttime').html(formateDate(rowData.starttime));
            $('#endtime').html(formateDate(rowData.endtime));
            $('#state').html(getState(rowData.state));
            /*if(rowData.state*1==1 && rowData.type*1==2){
                //添加详情按钮
                var options =  $('#ordersDlg').dialog('options');
                var toolbar =  options.toolbar;
                toolbar.push({
                    text:'运单详情',
                    iconCls:'icon-search',
                    handler:function(){
                        $('#waybillDlg').dialog('open');
                        $('#waybillgrid').datagrid({
                            url:'../waybilldetail/findBySn?sn='+$('#waybillsn').html(),
                            columns:[[
                                {field:'execute',title:'执行日期',width:100},
                                {field:'exetime',title:'执行时间',width:100},
                                {field:'info',title:'执行信息',width:100}
                            ]],
                            rownumbers:true
                        });
                    }
                });*/
                //重新渲染工具
             /*   $('#ordersDlg').dialog({
                    toolbar:toolbar
                });
            }*/
            //打开窗口
            $('#ordersDlg').dialog('open');
            //加载数据的明细
            $('#itemgrid').datagrid('loadData',rowData.ordersdetailList);
        }
    });

    $('#itemgrid').datagrid({
        columns: [[
            {field:'odid',title:'编号',width:100},
            {field:'goodsId',title:'商品编号',width:100},
            {field:'goodsname',title:'商品名称',width:100},
            {field:'price',title:'价格',width:100},
            {field:'num',title:'数量',width:100},
            {field:'money',title:'金额',width:100},
            {field:'state',title:'状态',width:100,formatter:getDetailState}
        ]],
        fitColumns:true,
        singleSelect:true
    });




    //添加审核按钮
    if(Request['oper']=='doCheck'){
        $('#ordersDlg').dialog({
            toolbar:[{
                text:'审核',
                iconCls:'icon-search',
                handler:doCheck
            }]
        });
    }

    if(Request['oper']=='doStart'){
        $('#ordersDlg').dialog({
            toolbar:[{
                text:'确认',
                iconCls:'icon-search',
                handler:doStart
            }]
        });
    }

    //动态添加申请采购
    if(Request['oper']=='myorders'){
        var btnText = "";
        if(Request['type']==1){
            $('#addOrdersSupplier').html("供应商");
            btnText = "申请采购";
        }
        if(Request['type']==2){
            $('#addOrdersSupplier').html("客户");
            btnText = "销售订单录入";
        }
        $('#grid').datagrid({
            toolbar:[
                {
                    text:btnText,
                    iconCls:'icon-add',
                    handler:function(){
                        $('#addOrdersDlg').dialog('open');
                    }
                }
            ]
        });
    }

    //入库，双击事件
    if(Request['oper'] == 'doInStore' || Request['oper']=='doOutStore'){
        $('#itemgrid').datagrid({
            onDblClickRow:function(rowIndex,rowData){
                $('#itemId').val(rowData.odid);
                $('#goodsId').html(rowData.goodsId);
                $('#goodsname').html(rowData.goodsname);
                $('#num').html(rowData.num);
                //$('#itemDlg').dialog('open');
                //打开出入库窗口
                $('#itemDlg').dialog('open');
            }
        });
    }

    //入库窗口
    $('#itemDlg').dialog({
        height:200,
        width:300,
        title:inoutTitle,
        modal:true,
        closed:true,
        buttons:[
            {
                text:inoutTitle,
                iconCls:'icon-save',
                //handler:doInStore
                handler:doInOutStore
            }
        ]
    });

    //出入口窗口
    $('#itemDlg').dialog({
        with:300,
        height:200,
        title:inoutTitle,
        modal:true,
        closed:true,
        buttons:[{
            text:inoutTitle,
            iconCls:'icon-save',
            handler:doInOutStore
        }]
    });

    //新增定义初始化操作
    $('#addOrdersDlg').dialog({
        title:'添加订单',
        width:700,
        height:400,
        modal:true,
        closed:true
    });

});

//日期格式的转换
function formateDate(dateValue){
    if(dateValue == null){
        return "";
    }
    return new Date(dateValue).Format("yyyy-MM-dd");
}

//获取订单的状态
/**
 *  采购:0:未审核,  1已审核   2已确认   3已入库
 *  销售:0未出库    1已出库
 */
function getState(value) {
    if(Request['type']*1==1){
        switch (value*1){
            case 0 : return "未审核";
            case 1 : return "已审核";
            case 2 : return "已确认";
            case 3 : return "已入库";
            default : return "";
        }
    }
    if(Request['type']*1==2){
        switch (value*1){
            case 0 : return '未出库';
            case 1 : return '已出库';
            default : return;
        }
    }
    switch (value*1){
        case 0 : return "未审核";
        case 1 : return "已审核";
        case 2 : return "已确认";
        case 3 :return "已入库";
        default :return "";
    }
}

//获取明细状态
/**
 *  采购:0=未入库  1=已入库
 *  销售:0=未出库 1=已出库
 */
function getDetailState(value){
    if(Request['type']*1==1){
        switch (value * 1){
            case 0 : return '未入库';
            case 1 : return '已入库';
            default: return '';
        }
    }
    if(Request['type']*1==2){
        switch (value*1){
            case 0:return '未出库';
            case 1:return '已出库';
            default:return;
        }
    }

    switch (value * 1){
        case 0 : return '未入库';
        case 1 : return '已入库';
        default: return '';
    }
}

//传递进来返回name
function rname(value){
    if(value != null){
        return value.name;
    }
    return '';
}

//审核
function doCheck(){
    $.messager.confirm('确认','你确认要审核嘛?',function (yes){
        if(yes){
            //确认提交
            $.ajax({
                url : '../orders/doCheck?oid='+$('#oid').html(),
                dataType: 'json',
                type: 'post',
                success:function(data){
                    $.messager.alert('提示',data.message,'info',function(){
                        if(data.success){
                            //关闭订单详情窗口
                            $('#ordersDlg').dialog('close');
                            //刷新订单

                            $('#grid').datagrid('reload');
                        }
                    });
                }
            })

        }
    });
}


function doStart(){
    $.messager.confirm('确认','确定要确认吗?',function(yes){
        if(yes){
            $.ajax({
                url:'../orders/doStart?id='+$('#oid').html(),
                dataType:'json',
                type:'post',
                success:function(data){
                    $.messager.alert('提示',data.message,'info',function(){
                        if(data.success){
                            //关闭窗口
                            $('#ordersDlg').dialog('close');
                            //刷新列表
                            $('#grid').datagrid('reload');
                        }
                    });
                }
            });
        }
    })
}


//插入库中
function doInOutStore(){
    //询问是否要入库的操作
    var message = "";
    var u= "";
    if(Request['type']*1==1){
        message = '确定要入库吗?';
        u = '../storeOper/add';
    }
    if(Request['type']*1==2){
        message = '确定要出库吗?';
        u = '../storeOper/out';
    }

    var formdata =  $('#itemForm').serializeJSON();
    if(formdata.storeId ==''){
        $.messager.alert('提示','请选择要入库的仓库','info');
        return ;
    }

    $.messager.confirm('确认',message,function(yes){
        if(yes){
            $.ajax({
                url:u,
                data:formdata,
                dataTyep:'json',
                type:'post',
                success:function(data){
                    $.messager.alert('提示',data.message,'info',function(){
                       /* if(data.success==false){
                            return '';
                        }*/
                        //关闭窗口
                        $('#itemDlg').dialog('close');
                        //修改入库后的详细
                        $('#itemgrid').datagrid('getSelected').state=1;
                        //刷新明细状态
                        var data = $('#itemgrid').datagrid('getData');
                        $('#itemgrid').datagrid('loadData',data);

                        //判断是否所有的都有入库
                        var allIn = true;
                        $.each(data.rows,function(i,row){
                            if(row.state*1==0){
                                //只要有一个状态为0的,则表示还有明细没有入库
                                allIn = false;
                                return false;
                            }
                        });
                        if(allIn == true){
                            //关闭明细窗口
                            $('#ordersDlg').dialog('close');
                            //刷新订单表格
                            $('#grid').datagrid('reload');
                        }

                    });
                }
            })
        }
    })
}

//根据订单的类型获取显示列
function getColumns(){
    //采购
    if(Request['type']*1==1){
        return [[
            {field:'oid',title:'编号',width:100},
            {field:'createtime',title:'生成日期',width:100,formatter:formateDate},
            {field:'checktime',title:'审核日期',width:100,formatter:formateDate},
            {field:'starttime',title:'确认日期',width:100,formatter:formateDate},
            {field:'endtime',title:'入库或出库日期',width:100,formatter:formateDate},
            {field:'emp',title:'下单员',width:100,formatter:function(value){
                    if(value != null){
                        return value.name;
                    }
                    return '';
                }},
            {field:'emp2',title:'审核员',width:100,formatter:function(value){
                    if(value != null){
                        return value.name;
                    }
                    return '';
                }},
            {field:'emp3',title:'采购员',width:100,formatter:function(value){
                    if(value != null){
                        return value.name;
                    }
                    return '';
                }},
            {field:'emp4',title:'库管员',width:100,formatter:function(value){
                    if(value != null){
                        return value.name;
                    }
                    return '';
                }},
            {field:'supplier',title:'供应商',width:100,formatter:function(value){
                    return value.name;
                }},
            {field:'totalmoney',title:'合计金额',width:100},
            {field:'state',title:'状态',width:100,formatter:getState},
            {field:'waybillsn',title:'运单号',width:100}
        ]];
    }

    //销售
    if(Request['type']*1==2){
        return [[
            {field:'oid',title:'编号',width:100},
            {field:'createtime',title:'生成日期',width:100,formatter:formateDate},
            {field:'endtime',title:'出库日期',width:100,formatter:formateDate},
            {field:'emp',title:'下单员',width:100,formatter:function(value){
                    if(value != null){
                        return value.name;
                    }
                    return '';
                }},
            {field:'emp4',title:'库管员',width:100,formatter:function(value){
                    if(value != null){
                        return value.name;
                    }
                    return '';
                }},
            {field:'supplier',title:'客户',width:100,formatter:function(value){
                    return value.name;
                }},
            {field:'totalmoney',title:'合计金额',width:100},
            {field:'state',title:'状态',width:100,formatter:getState},
            {field:'waybillsn',title:'运单号',width:100}
        ]];
    }
}

