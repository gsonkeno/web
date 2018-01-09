$(function(){
	initEvents();
})

function initEvents(){
	$('body').on('click',".nav-item a",function(){
		$(this).addClass('active').parent().siblings().children().removeClass('active');
	})
}