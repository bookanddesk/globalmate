function quanp(){
var single_hh = $(window).height();
var single_ww = $(window).width();
$('.at').height(single_hh);
$('.at').width(single_ww);
}
quanp();
$(window).resize(function(){
	if (typeof indexSlides != 'undefined' && indexSlides.reformat) 
		indexSlides.reformat();
		quanp();
});

$(document).ready(function(){
	$(".at-two").hide();
	$(".at-three").hide();
	$(".at-for").hide();
  $(".ato-d a").click(function(){
    $(".at-one").slideUp("0");
	$(".at-two").show();
	$(".at-three").show();
	$(".at-for").show();
	
	
  });
});

$(document).ready(function(){
  $(".ato-d a").click(function(){
  var scroll_offset = $("#at-one").offset();  //得到pos这个div层的offset，包含两个值，top和left
  $("body,html").animate({
   scrollTop:scroll_offset.top  //让body的scrollTop等于pos的top，就实现了滚动
   },600);
  });
});

$(document).ready(function(){
  $("#at-twogd").click(function(){
  var scroll_offset = $("#at-three").offset();  //得到pos这个div层的offset，包含两个值，top和left
  $("body,html").animate({
   scrollTop:scroll_offset.top  //让body的scrollTop等于pos的top，就实现了滚动
   },600);
  });
});


$(document).ready(function(){
  $("#at-threegd").click(function(){
  var scroll_offset = $("#at-for").offset();  //得到pos这个div层的offset，包含两个值，top和left
  $("body,html").animate({
   scrollTop:scroll_offset.top  //让body的scrollTop等于pos的top，就实现了滚动
   },600);
  });
});