$(function(){
    initEvent();
})

function initEvent() {
    $("#game_time").change(function () {
        var game_time_val = $("#game_time").val();

        if (game_time_val == "1"){
            $("#latestGameCounts").show();
            $("#begin_end_div").hide();
            $("#season_div").hide();
        }else if (game_time_val == "2"){
            $("#latestGameCounts").hide();
            $("#begin_end_div").show();
            $("#season_div").hide();
        }else if (game_time_val == "3"){
            $("#latestGameCounts").hide();
            $("#begin_end_div").hide();
            $("#season_div").show();
        }
    })

    $("#opponent").change(function () {
        var game_time_val = $("#opponent").val();

        if (game_time_val == "1"){
            $("#opponent_team").show();
            $("#opponent_league").hide();
        }else if (game_time_val == "2"){
            $("#opponent_team").hide();
            $("#opponent_league").show();
        }
    })
}