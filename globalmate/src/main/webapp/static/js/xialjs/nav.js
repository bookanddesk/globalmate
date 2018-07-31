$(function () {
    var st = 180;
    $('.attribute_right_list ul li').mouseenter(function () {
        $(this).find('ul').stop(false, true).slideDown(st);
    }).mouseleave(function () {
        $(this).find('ul').stop(false, true).slideUp(st);
    });
});

$(document).ready(function(){
  $(".pick_number ul li").click(function(){
	$(".pick_number ul li").removeClass("number_add");
	$(this).addClass("number_add");
	$(this).find(".number_puk").attr("style", "display:block").parent().siblings().find(".number_puk").attr("style", "display:none");
  });
});
