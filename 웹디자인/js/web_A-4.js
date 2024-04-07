$(function(){
    // 팝업창
    $(".popup_view").click(function(){
        $(".popup").show();
    })

    $(".popup_close").click(function(){
        $(".popup").hide();
    })

    //메뉴 슬라이드
    $(".nav > ul > li").mouseover(function(){
        $(".submenu").stop().slideDown(300);
        $(".header").addClass("on");
    })

    $(".nav > ul > li").mouseout(function(){
        $(".submenu").stop().slideUp(300);
        $(".header").removeClass("on");
    })

    //이미지 슬라이드 
    let cnt = 0;

    $(".slider").hide().first().show();

    setInterval(function(){
        let nextIndex = (cnt + 1) % 3

        $(".slide").eq(cnt).fadeOut(1200);
        $(".slide").eq(nextIndex).fadeIn(1200);

        cnt = nextIndex;
    },3000)

})