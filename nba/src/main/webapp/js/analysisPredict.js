// 基于准备好的dom，初始化echarts实例
var myChart = echarts.init(document.getElementById('latestGameEchartL'));
$(function(){
    initEvent();
    initEcharts();
})

function initEvent() {
    /**左右球队关键字搜索**/
    $('#teamNameL,#teamNameR').bind('keypress', function (event) {
        if (event.keyCode == "13") {
            doSearchLatestGames(event);
        }
    });
    /**左右球队最近比赛场次**/
    $("#gameCountsL,#gameCountsR").change(function (event) {
        doSearchLatestGames(event);
    })
}

function initEcharts(){


    option = {
        title: {
            text: '最近比分',
            subtext: ''
        },
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            data:['分数']
        },
        toolbox: {
            show: true,
            feature: {
                dataZoom: {
                    yAxisIndex: 'none'
                },
                dataView: {readOnly: false},
                magicType: {type: ['line', 'bar']},
                restore: {},
                saveAsImage: {}
            }
        },
        xAxis:  {
            type: 'category',
            // data: ['周一','周二','周三','周四','周五','周六','周日']
            boundaryGap: false

        },
        yAxis: {
            type: 'value',
            axisLabel: {
                formatter: '{value} '
            }
        },
        series: [
            {
                name:'分数',
                type:'line',
                //data:[11, 11, 15, 13, 12, 13, 10],
                label: {normal: {
                    show: true
                }},
                // markPoint: {
                //     data: [
                //         {type: 'max', name: '最大值'},
                //         {type: 'min', name: '最小值'}
                //     ]
                // },
                markLine: {
                    data: [
                        {type: 'average', name: '平均值'}
                    ]
                }
            }
        ]
    };


    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
}

function  doSearchLatestGames(event) {
    var elemId = $(event.target).attr("id")
    //表示左半部分界面触发事件
    if (elemId.endsWith("L")) {
        var  gameCountsL = $("#gameCountsL").val();
        var  teamNameL = $("#teamNameL").val();

        var params = {};
        params.GAME_COUNTS_L = gameCountsL;
        params.TEAM_NAME_L = teamNameL;

        var url = "/nba/gameResult/getLatestResult";

        $.post(url,params,function(resp){
            var gameResult =resp.list;
            var dates = resp.dates;
            var scores = resp.scores;

            console.log(gameResult);
            $('#latestGameResultL').html($("#gameResult_tmpl").tmpl(gameResult));
            myChart.setOption({
                xAxis:  {
                    type: 'category',
                    boundaryGap: false,
                    data: dates
                },
                series: [
                    {
                        data:scores
                    }
                ]
            })
        });
    }

}