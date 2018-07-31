$(function () {
    var st = 100;
    $('.city p').click(function () {
		$(".city p").removeClass("hovera").addClass('hover');
		$('.city ul').show(0);
    })
		$(".city ul").click(function () {
                $(this).hide(0);
				$(".city p").removeClass("hover").addClass('hovera');
            });
			
		$('.city').mouseleave(function () {
        $('.city ul').hide(0);
		$(".city p").removeClass("hover").addClass('hovera');
    });	
    });
	
$(function(){
$(".use-title li").click(function () {
                $(".use-title li").each(function () {
                    $(this).removeClass("current");
                });
                $(this).addClass("current");
            });
$('.use-title li').click(function(){
  var index=$('.use-title li').index(this);
      if(index==0){
     $('.use1').show();
  $('.use2').hide();
  $('.use3').hide();
   }
   if(index==1){
     $('.use2').show();
  $('.use1').hide();
  $('.use3').hide();
   }
   if(index==2){
     $('.use3').show();
  $('.use2').hide();
  $('.use1').hide();
   }
  });
});

	
$(function(){
$(".bill-date li").click(function () {
                $(".bill-date li").each(function () {
                    $(this).removeClass("current");
                });
                $(this).addClass("current");
            });
$('.bill-date li a').click(function(){
  var index=$('.bill-date li a').index(this);
      if(index==0){
     $('#date1').show();
  $('#date2').hide();
  $('#date3').hide();
  $('#date4').hide();
   }
   if(index==1){
     $('#date2').show();
  $('#date1').hide();
  $('#date3').hide();
  $('#date4').hide();
   }
   if(index==2){
     $('#date3').show();
  $('#date2').hide();
  $('#date1').hide();
  $('#date4').hide();
   }
   if(index==3){
     $('#date4').show();
  $('#date3').hide();
  $('#date2').hide();
  $('#date1').hide();
   }
  }); 
});


$(function(){
$(".value_title li").click(function () {
                $(".value_title li").each(function () {
                    $(this).removeClass("current");
                });
                $(this).addClass("current");
            });
$('.value_title li').click(function(){
  var index=$('.value_title li').index(this);
      if(index==0){
     $('#bao1').show();
  $('#bao2').hide();
   }
   if(index==1){
     $('#bao2').show();
  $('#bao1').hide();
   }
  }) 
})



$(function () {
    var st = 100;
    $('.code_t').mouseenter(function () {
		$('.code_t p').show(1);
    })
		$(".code_t p").click(function () {
                $(this).hide(1);
            });
			
		$('.code_t').mouseleave(function () {
        $('.code_t p').hide(1);
    });	
  });
  
 $(function () {
    var st = 100;
    $('.code_l').mouseenter(function () {
		$('.code_l p').show(1);
    })
		$(".code_l p").click(function () {
                $(this).hide(1);
            });
			
		$('.code_l').mouseleave(function () {
        $('.code_l p').hide(1);
    });	
  });
 
 
$(function () {
    var st = 100;
    $('.bulletin li i').click(function () {
		$(this).toggleClass("icon-angle-up icon-angle-down");
		$('.bulletin ol').toggle();
    });
   });
   
   
   
 $(document).ready(function(){
    var $swap = $('.user_left'); 
    var movetotop; 
    $swap.hover(function() {
        clearInterval(movetotop); 
        },function(){     movetotop=setInterval(function() { 
                   var li_height = $swap.find('.dl_gd').height();
                   $swap.find('.dl_gd:first').animate({marginTop:-li_height + 'px'},800,function() {                                 
                    $swap.find('.dl_gd:first').css('marginTop',0).appendTo($swap); 
                                    });
                   },3000);
        }).trigger('mouseleave');
});


$(function () {
    var st = 180;
    $('.fenx li').mouseenter(function () {
        $(this).find('ul').stop(false, true).slideDown(st);
    }).mouseleave(function () {
        $(this).find('ul').stop(false, true).slideUp(st);
    });
});


  $(document).ready(function(){
	if ($.support.leadingWhitespace) {
		$(window).scroll(function(){
			var top = document.documentElement.scrollTop || window.pageYOffset || document.body.scrollTop;
			if(top>100){
		   var divcss = {
                position: 'fixed',
                top: '0',
              };
		   $(".container_left_cj").css(divcss);
			}
		});
	}
});

 $(document).ready(function(){
	if ($.support.leadingWhitespace) {
		$(window).scroll(function(){
			var top = document.documentElement.scrollTop || window.pageYOffset || document.body.scrollTop;
			if(top<99){
		   var divcss = {
                position: 'relative',
                top: '0',
              };
		   $(".container_left_cj").css(divcss);
			}
		});
	}
});

   
$(function() {
			$('.circle').each(function(index, el) {
				var num = $(this).find('span').text() * 3.6;
				if (num<=180) {
					$(this).find('.rightaa').css('transform', "rotate(" + num + "deg)");
				} else {
					$(this).find('.rightaa').css('transform', "rotate(180deg)");
					$(this).find('.leftaa').css('transform', "rotate(" + (num - 180) + "deg)");
				};
			});

});


$(function(){
$(".xh_lista li").click(function () {
                $(".xh_lista li").each(function () {
                    $(this).removeClass("current");
                });
                $(this).addClass("current");
});
$('.xh_lista li').click(function(){
  var index=$('.xh_lista li').index(this);
     if(index==0){
    $('.xh_nr1').show();
  $('.xh_nr2').hide();
 $('.xh_nr3').hide();
   }
   if(index==1){
   $('.xh_nr2').show();
  $('.xh_nr1').hide();
  $('.xh_nr3').hide();
  }
  if(index==2){
    $('.xh_nr3').show();
 $('.xh_nr2').hide();
  $('.xh_nr1').hide();
   }
  });
});

$(function(){
$(".haoma ul").mouseover(function () {
                $(".haoma ul").each(function () {
                    $(this).removeClass("hover_hm");
                });
                $(this).addClass("hover_hm");
});
});

$(function(){
$(".xuanh_C ul").mouseover(function () {
                $(".xuanh_C ul").each(function () {
                    $(this).removeClass("hove_a");
                });
                $(this).addClass("hove_a");
});
});
