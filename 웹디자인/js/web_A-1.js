window.onload=function(){

    //메뉴바 작동
    let navList = document.querySelector(".nav > ul");
    
    navList.addEventListener("mouseover", function(){
        navList.querySelectorAll(".subMenu").forEach(sub => {
            sub.style.height = "155px";
        });
    })

    navList.addEventListener("mouseout", function(){
        navList.querySelectorAll(".subMenu").forEach(sub => {
            sub.style.height = "0px";
        });
    })


    // let cnt = 0; 

    // const sliderWrap = document.querySelector(".sliderWrap");
    // const slider = document.querySelectorAll(".slider");

    // const sliderClone = sliderWrap.firstElementChild.cloneNode(true);

    // sliderWrap.appendChild(sliderClone);

    // setInterval(() => {
    //     cnt++

    //     sliderWrap.style.marginLeft = -cnt*100 +"%";
    //     sliderWrap.style.transition = "all 0.6s";

    //     if(cnt == 3){
    //         setTimeout(() => {
    //             sliderWrap.style.transition ="0s";
    //             sliderWrap.style.marginLeft ="0";
    //             cnt = 0;
    //         },700)
    //     }
    // },3000);


    //이미지 슬라이드
    let cnt = 0;
    
    const sliderWrap =document.querySelector(".sliderWrap");
    const slider = document.querySelectorAll(".slider");

    const sliderClone = sliderWrap.firstElementChild.cloneNode(true); //복사
    sliderWrap.appendChild(sliderClone); //붙여넣기

    setInterval(()=>{
        cnt++

        sliderWrap.style.marginLeft = (-cnt * 1200) + "px";
        sliderWrap.style.transition = "all 0.6s";

        if(cnt == 3 ){
            setTimeout(()=>{
                sliderWrap.style.transition = "0s";
                sliderWrap.style.marginLeft = "0";
                
                cnt = 0;
            },700)
        }

    },3000)

    //탭메뉴 만들기
    const tabBtn = document.querySelectorAll(".title > a") //버튼설정
    const tabCont = document.querySelectorAll(".info-cont > div") //탭 컨텐츠 설정

    tabCont.forEach(e => e.style.display = "none"); //컨텐츠 내용 숨기기 
    tabCont[0].style.display ="block"; //첫번째 컨텐츠 내용 보여주기

    tabBtn.forEach((tab, i)=>{
        tab.addEventListener("click", function(){
            tabBtn.forEach(tab => tab.classList.remove("active")); 
            tab.classList.add("active");

            tabCont.forEach(cont => cont.style.display="none");
            tabCont[i].style.display = "block";
        })
    })

    //팝업창 클릭

    const popupBtn = document.querySelector(".popup-btn");
    const popupView = document.querySelector(".popup-view");

    //팝업창 띄우기
    popupBtn.addEventListener("click", function(){
        popupView.style.display = "block";
    })

    //팝업창 닫기
    document.querySelector(".close").addEventListener("click", function(){
        popupView.style.display = "none";
    })

}