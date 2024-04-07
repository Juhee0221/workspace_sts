$(function(){
    //popup
    $(".popup-view").click(function(){
        $(".popup").show();
    })
    $(".popup-close").click(function(){
        $(".popup").hide();
    })

    //menu slide
    $(".nav > ul > li").mouseover(function(){
        $(".submenu").stop().slideDown(200)
        $(".header").addClass("on");
    })
    $(".nav > ul > li").mouseout(function(){
        $(".submenu").stop().slideUp(200)
        $(".header").removeClass("on");
    })

    //img slide
    let cnt = 0;

    $(".sliderWrap").append($(".slide").first().clone(true));

    setInterval(function(){
        cnt++;

        $(".sliderWrap").animate({marginLeft: -cnt * 1200 + "px"},600)

        if(cnt == 3){
            setTimeout(function(){
                $(".sliderWrap").animate({marginLeft:0},0);
                cnt=0;
            },700)
        }
    },3000)
})