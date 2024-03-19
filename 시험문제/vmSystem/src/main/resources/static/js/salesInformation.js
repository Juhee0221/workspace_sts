function goInsert(){
    const buyer = document.querySelector("#modelBuyer");

    if(buyer.value == ''){
        alert('구매자 명은 필수 사항입니다.');
        return ;
    }
    
    document.querySelector("#salesInsert").submit();
}