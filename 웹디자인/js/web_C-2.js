$(function(){
    $(".popup-view").click(function(){
        $(".popup").show();
    })

    $(".popup-close").click(function(){
        $(".popup").hide();
    })

    $(".nav > ul > li").mouseover(function(){
        $(this).find($(".submenu")).stop().slideDown(200)
    })

    $(".nav > ul > li").mouseout(function(){
        $(this).find($(".submenu")).stop().slideUp(200)
    })

    let cnt = 0;

    $(".slider").hide().first().show();

    setInterval(function(){
        let next = (cnt + 1) % 3 

        $(".slide").eq(cnt).fadeOut(1200)
        $(".slide").eq(next).fadeIn(1200)

        cnt = next;
    },3000)


})