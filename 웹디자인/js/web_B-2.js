$(function(){

    //popup
   $(".popup-view").click(function(){
    $(".popup").show();
   })

   $(".popup-close").click(function(){
    $(".popup").hide();
   })

   //메뉴슬라이드
   $(".nav > ul > li").mouseover(function(){
    $(".submenu").stop().slideDown(200)
    $(".header").addClass("on");
   })

   $(".nav > ul > li").mouseout(function(){
    $(".submenu").stop().slideUp(200)
    $(".header").removeClass("on");
   })

   let cnt = 0;

   $(".sliderWrap").append($(".slide").first().clone(true));

   setInterval(function(){
        cnt++;

        $(".sliderWrap").animate({marginTop : -cnt * 300 + "px"},600)

        if(cnt == 3 ){
            setTimeout(function(){
                $(".sliderWrap").animate({marginTop:0},0)
            })
        }
    },3000)
})