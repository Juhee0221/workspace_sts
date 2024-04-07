$(function(){

    //단일 메뉴바 내리기
   $(".nav > ul >li").mouseover(function(){
    $(this).find(".subMenu").stop().slideDown(200);
   })
   $(".nav > ul >li").mouseout(function(){
    $(this).find(".subMenu").stop().slideUp(200);
   })

   //이미지 슬라이드 : 페이드 효과
   
   //변수하나 지정
   let cnt = 0;

   //슬라이드를 숨긴후 첫번째 슬라이드만 보여준다.
   $(".slider").hide().first().show();

   //이미지 애니메이션을 실행

   setInterval(function(){
    //변수하나 지정하는데 nextIndex = cnt + 1 % 3 
    let nextIndex = (cnt + 1) % 3;

    $(".slide").eq(cnt).fadeOut(1200);
    $(".slide").eq(nextIndex).fadeIn(1200);

    cnt = nextIndex;
   },3000)


   //탭 버튼 설정

   const tabBtn = $(".tab-manu > a");
   const tabCon = $(".tab-con > div");

   tabCon.hide().eq(0).show();
   
   tabBtn.on("click", function(){
        const cnt = $(this).index();

        $(this).addClass("active").siblings().removeClass("active");
        tabCon.eq(cnt).show().siblings().hide();
   })

})