$(function(){
  //팝업 오픈
  $(".popUp").click(function(){
    $(".popUp-view").show();
  })

  $(".pop-close").click(function(){
    $(".popUp-view").hide();
  })

  //메뉴슬라이드
  $(".nav > ul > li").mouseover(function(){
    $(this).find(".subMenu").stop().slideDown(200);
  })
  $(".nav > ul > li").mouseout(function(){
    $(this).find(".subMenu").stop().slideUp(200);
  })

  $(function(){
    //슬라이드 : 페이드 효과
    let currentIndex = 0;   //현재 이미지 설정
    $(".slider").hide().first().show();  //모든 이미지 숨기고 첫번째 이미지 나타남

    setInterval(function(){     //3초에 한번씩 실행
        let nextIndex = (currentIndex + 1) % 3;     //다음 이미지 설정

        $(".slide").eq(currentIndex).fadeOut(1200);    //첫 번재 이미지 안보이게 함
        $(".slide").eq(nextIndex).fadeIn(1200);    //두번째 이미지 보이게 함

        currentIndex = nextIndex;   //두번재 값을 현재 인덱스에 저장
    }, 3000);
});

})