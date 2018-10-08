$(function(){
    $('#grid').datagrid({
        url:'../count/orderReport',
        columns:[[
            {field:'name',title:'商品类型',width:100},
            {field:'y',title:'销售金额',width:100}
        ]],
        singleSelect:true,
        onLoadSuccess:function(data){
            showChart(data.rows);
        }
    });

    $('#btnSearch').bind('click',function(){
        var formdata =  $('#searchForm').serializeJSON();
        if(formdata.endDate !=''){
            //如果有截至日期,则要补上当天的最后一天
            formdata.endDate = formdata.endDate + " 23:59:59";
        }
        $('#grid').datagrid('load',formdata);
    });

});

//画图
function showChart(data){
    $('#pieChart').highcharts({
        chart:{
            plotBackgroundColor:null,
            plotBorderWith:null,
            plotShadow:false,
            type:'pie'
        },
        tite:{
            text:'统计销售'
        },
        tooltip:{
            pointFormat:'{series:name}:<b>{point.percentage:.1f}%</b>'
        },
        plotOptions:{
            pie:{
                allowPointSelect: true,
                cursor:'pointer',
                dataLabels:{
                    enabled:true,
                    format:'<b>{point:name}</b>:{point.percentage:.1f}%',
                },
                showInlegend:true
            }
        },
        series:[{
            name:'比例',
            colorByPoint:true,
            data:data
        }]
    });
}