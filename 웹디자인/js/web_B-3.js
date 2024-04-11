$(function(){
    
    //팝업
    $(".popup-view").click(function(){
        $(".popup").show()
    })
    $(".popup-close").click(function(){
        $(".popup").hide()
    })

    //메뉴슬라이드
    $(".nav > ul > li").mouseover(function(){
        $(this).find($(".submenu")).stop().slideDown(200);
    })
    $(".nav > ul > li").mouseout(function(){
        $(this).find($(".submenu")).stop().slideUp(200);
    })

    //이미지 슬라이드 위아래

    let cnt = 0;
    $(".sliderWrap").append($(".slide").first().clone(true));

    setInterval(function(){
        cnt++;

        $(".sliderWrap").animate({marginTop: -cnt * 300 + "px"},600);

        if(cnt == 3){
            setTimeout(function(){
                $(".sliderWrap").animate({marginTop: 0},0)
                cnt = 0;
            },700)
        }
    },3000)
})