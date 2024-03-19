function goInsertCar(){
    const modelName = document.querySelector(".modelName")
    const modelPrice = document.querySelector(".modelPrice")

    if(modelName.value == '' || modelPrice.value == ''){
        alert("모델명과 모델가격은 필수입력입니다.")
        return;
    }
    

    document.querySelector("#insertCarForm").submit();
}