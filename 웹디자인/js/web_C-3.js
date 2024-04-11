$(function(){
    $(".nav > ul > li").mouseover(function(){
        $(this).find($(".submenu")).stop().slideDown(200)
    })
    $(".nav > ul > li").mouseout(function(){
        $(this).find($(".submenu")).stop().slideUp(200)
    })

    $(".popup-view").click(function(){
        $(".popup").show()
    })
    $(".popup-close").click(function(){
        $(".popup").hide()
    })

    let cnt = 0;

    $(".sliderWrap").append($(".slide").first().clone(true))
    
    setInterval(function(){
        cnt++;

        $(".sliderWrap").animate({marginLeft: -cnt * 800 + "px"},600)

        if(cnt == 3){
            setTimeout(function(){
                $(".sliderWrap").animate({marginLeft:0},0)

                cnt = 0;
            },700)
        }

    },3000)
})