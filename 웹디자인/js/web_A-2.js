$(function(){

    $(".popup").click(function(){
        $(".popup-view").show();
    });

    // hide() : 숨김
    $(".popup-close").click(function(){
        $(".popup-view").hide();
    })


    //메뉴 슬라이드
    $(".nav > ul > li").mouseover(function(){
        $(".nav > ul > li > ul").stop().slideDown(500);
        $(".header").addClass("on")
    })

    $(".nav > ul > li").mouseout(function(){
        $(".nav > ul > li > ul").stop().slideUp(200);
        $(".header").removeClass("on")
    })

    //이미지 슬라이드

        let cnt = 0;
        $(".sliderWrap").append($(".imgs").first().clone(true)); // 첫번째 이미지 복사, 마지막에 추가

        setInterval(function(){
            cnt++
            $(".sliderWrap").animate({marginLeft : -cnt * 100 + '%'},600) //이미지 애니메이션

            if(cnt == 3){
                setTimeout(function(){
                    $(".sliderWrap").animate({marginLeft:0},0);
                    cnt = 0
                },700)
            }
        },3000);
});