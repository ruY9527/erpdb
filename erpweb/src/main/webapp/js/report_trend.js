$(function(){
    $('#grid').datagrid({
        queryParams:{},
        columns:[[
            {field:'month',title:'月份',width:100},
            {field:'y',title:'销售额',width:100}
        ]],
        singleSelect:true,
        onLoadSuccess:function (data) {
            showChart();
        }
    });

    $('#btnSearch').bind('click',function(){
        var dataform =  $('#searchForm').serializeJSON();
        //提交查询条件
        $('#grid').datagrid({
            url:'../count/trendReport',
            queryParams:dataform
        });
    });
});


//画图
function showChart(data){
    var monthData = new Array();
    for(var i =1;i<=12;i++){
        monthData.push(i+"月");
    }
    $('#trendChart').highcharts({
        tite:{
            text: $('#years').combobox('getValue') + '年销售趋势分析',
            x:-20  //center
        },
        xAxis: {
            categories:monthData
        },
        yAxis: {
            title:{
                text:'销售额(元)'
            },
            plotLines:[{
                value:0,
                width:1,
                color:'#808080'
            }]
        },
        tooltip:{
            valueSuffix:'元'
        },
        legend:{
            layout:'vertical',
            align:'center',
            verticalAlign:'bottom',
            borderWidth:0
        },
        series:[
            {name:'全部商品',data:$('#grid').datagrid('getRows')}
        ]
      /*  plotOptions:{
            pie:{
                allowPointSelect: true,
                cursor:'pointer',
                dataLabels:{
                    enabled:true,
                    format:'<b>{point:name}</b>:{point.percentage:.1f}%',
                },
                showInlegend:true
            }
        },*/
      /*  series:[{
            name:'比例',
            colorByPoint:true,
            data:data
        }]*/
    });
}