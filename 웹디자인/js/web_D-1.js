$(function(){
    $(".nav > ul > li").mouseover(function(){
        $(this).find($(".submenu")).stop().slideDown(200)
    })

    $(".nav > ul > li").mouseout(function(){
        $(this).find($(".submenu")).stop().slideUp(200)
    })

    let cnt = 0;
    $(".sliderWrap").append($(".slide").first().clone(true))
    

    setInterval(function(){
        cnt++;
        $(".sliderWrap").animate({marginTop: -cnt * 400 + "px"})

        if(cnt == 3){
            setTimeout(function(){
                $(".sliderWrap").animate({marginTop: 0},0)
            },600)
            cnt=0;
        }
        
    },3000)

    const tabMenu = $(".tabmenu > a")
    const tebCon = $(".tabcon >div")

    tebCon.hide().eq(0).show();

    tabMenu.on("click", function(){
        const cnt = $(this).index();

        $(this).addClass("active").siblings().removeClass("active")
        tebCon.eq(cnt).show().siblings().hide()
    })
})