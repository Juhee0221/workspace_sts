$(function(){
   $(".popup-view").click(function(){
    $(".popup").show();
   })
   $(".popup-close").click(function(){
    $(".popup").hide();
   })

   //메뉴 슬라이드
   $(".nav > ul > li").mouseover(function(){
    $(this).find($(".submenu")).stop().slideDown(200);
   })

   $(".nav > ul > li").mouseout(function(){
    $(this).find($(".submenu")).stop().slideUp(200);
   })

   //이미지 페이드 효과
   let cnt = 0;
   $(".slider").hide().first().show()

   setInterval(function(){
        let next = (cnt + 1 ) % 3;

        $(".slide").eq(cnt).fadeOut(1200)
        $(".slide").eq(next).fadeIn(1200)

        cnt = next;
   },3000)

   const tebMeun = $(".teb-menu > a")
   const tebCon = $(".teb-con > div")

   tebCon.hide().eq(0).show();

   tebMeun.on("click", function(){
        const cnt = $(this).index()
        $(this).addClass("active").siblings().removeClass("active")
        tebCon.eq(cnt).show().siblings().hide(); 
   })
})