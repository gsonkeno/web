
$(function(){
	initTime();
	initEvent();
	doSearch();
})
function initTime(){
	var	now = new Date();
	$("#gameDateBegin").val(now.format("yyyy-MM-dd"));
	$("#gameDateEnd").val(now.format("yyyy-MM-dd"));
}

function initEvent(){
	/**关键字搜索**/
	$('#teamName').bind('keypress',function(event){
        if(event.keyCode == "13")    
        {
            clearPageNoInfo();
        	doSearch();
        }
    });		
	
	$("#gameDateBegin").focus(function(){
		WdatePicker({
			startDate:'%y-#{%M}-%d',
			onpicked:doSearch()
		});
	});
	
	$("#gameDateEnd").focus(function(){
		WdatePicker({
			startDate:'%y-#{%M}-%d',
			onpicked:doSearch()
		});
	});

	$("#prevBtn").click(function () {
	    if($("#current_page").text() == 1) return;
        $("#current_page").html(parseInt($("#current_page").text()) - 1);
        doSearch();

    });

    $("#nextBtn").click(function () {
        if($("#current_page").text() == $("#total_page").text()) return;
        $("#current_page").html(parseInt($("#current_page").text()) + 1);
        doSearch();

    });

}

function clearPageNoInfo() {
    $("#total_page").html(1);
    $("#current_page").html(1);
    $("#total_size").html(0);
}

function doSearch(){
	var params ={};
	
	params.GAME_DATE_BEGIN = $("#gameDateBegin").val();
	params.GAME_DATE_END = $("#gameDateEnd").val();
	params.TEAM_NAME = $("#teamName").val();
	params.HOST_GUEST_FLAG = $("#hostGuestFlag").val();
	params.PAGE_SIZE = $("#pageSize").val();
	params.CURRENT_PAGE = $("#current_page").text();

	var url = "/nba/gameResult/getResult";
	
	$.post(url,params,function(resp){
		var gameResult =resp.list;
		console.log(gameResult);
		$('#gameResult').html($("#gameResult_tmpl").tmpl(gameResult));
		$("#total_page").html(resp.totalPage== 0 ? 1: resp.totalPage);
		$("#total_size").html(resp.totalSize);
	});
	
}