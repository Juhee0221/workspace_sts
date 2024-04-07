$(function(){
    
    //팝업
    $(".popup-view").click(function(){
        $(".popup").show()
    })
    $(".popup-close").click(function(){
        $(".popup").hide()
    })

    //nav 슬라이드

    $(".nav > ul > li").mouseover(function(){
        $(".submenu").stop().slideDown(200);
    })
    $(".nav > ul > li").mouseout(function(){
        $(".submenu").stop().slideUp(200);
    })

    //이미지 슬라이드

    let cnt = 0;

    $(".sliderWrap").append($(".slide").first().clone(true))

    setInterval(function(){
        cnt++
        $(".sliderWrap").animate({marginLeft : -cnt * 1200 + 'px'},600)

        if(cnt == 3){
            setTimeout(function(){
                $(".sliderWrap").animate({marginLeft:0},0);
                cnt = 0;
            },700)
        }
    },3000)

      //탭 메뉴
    const tabMenu = $(".tab-menu > a")
    const tebCon = $(".tab-con > div")
  
      tebCon.hide().eq(0).show()
      
      tabMenu.on("click", function(){
          const cnt = $(this).index();
  
          $(this).addClass("active").siblings().removeClass("active")
          tebCon.eq(cnt).show().siblings().hide();
      })


})