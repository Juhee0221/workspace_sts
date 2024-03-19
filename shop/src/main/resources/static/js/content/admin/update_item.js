 
 const updateItemCode = document.querySelector("#updateItemCode").value

if(updateItemCode !=0){
    selectItem(updateItemCode);
}

function selectItem(itemCode){
    fetch('/admin/update', { //요청경로
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
        },
        //컨트롤러로 전달할 데이터
        body: new URLSearchParams({
           // 데이터명 : 데이터값
           itemCode : itemCode
        })
    })
    .then((response) => {
        if(!response.ok){
            alert('fetch error!\n컨트롤러로 통신중에 오류가 발생했습니다.');
            return ;
        }
    
        // return response.text(); //컨트롤러에서 return하는 데이터가 없거나 int, String 일 때 사용
        return response.json(); //나머지 경우에 사용
    })
    //fetch 통신 후 실행 영역
    .then((data) => {//data -> controller에서 리턴되는 데이터!

        console.log(data)

        const selectMain = document.querySelector(".main")

        selectMain.innerHTML='';
        
        let str = '';

        str += `<form action="/admin/updateItem" method="post">
                <input type="hidden" name="itemCode" value="${data.itemList.itemCode}">
                    <h5>
                    상품 기본 정보
                    </h5>
                    <div class="row mt-3 mb-4" style="width: 100%; margin-left: 10px; align-items: center; border: 3px solid #ccc; border-radius: 25px;">
                        <div class="col-2 mb-4 mt-4">카테고리</div>
                            <div class="col-3 mb-4 mt-4">
                        <select class="form-select" style="width: 120px;" name="cateCode">`;

                            for(const category of data.cateList){
                                if(category.cateCode == data.itemList.cateCode){

                                    str += `<option name="cateCode" value="${category.cateCode}" selected>
                                            ${category.cateName}
                                            </option>`;
                                }
                                else{
                                    str += `<option name="cateCode" value="${category.cateCode}">
                                            ${category.cateName}
                                            </option>`;
                                }
                            }
            str+=`      </select>
                            </div>
                        <div class="col-2 mb-4 mt-4">
                            상품수량
                        </div>
                        <div class="col-4 mb-4 mt-4">
                            <input type="text" class="form-control" style="width: 120px;" name="itemStock" value="${data.itemList.itemStock}">
                        </div>
                        <div class="col-2 mb-4">
                            상품명
                        </div>
                        <div class="col-10 mb-4"> 
                            <input type="text" class="form-control" style="width: 342px;" name="itemName" value="${data.itemList.itemName}"> 
                        </div>
                        <div class="col-2 mb-4">
                            상품상태
                        </div>
                        <div class="col-6 mb-4">`;

                    if(data.itemList.itemStatus == 1){
                        str += `<input checked type="radio" name="itemStatus" value="1">`;
                    }else{
                        str += `<input type="radio" name="itemStatus" value="1">`;
                    }

             str +=  `<span style="margin-right: 20px;">준비 중</span>`
                    
                    if(data.itemList.itemStatus == 2){
                        str += `<input checked type="radio" name="itemStatus" value="2">`;
                    }else{
                        str += `<input type="radio" name="itemStatus" value="2">`;
                    }
            str += `<span style="margin-right: 20px;">판매 중</span>`

                    if(data.itemList.itemStatus == 3){
                        str += `<input checked type="radio" name="itemStatus" value="3">`
                    }else{
                        str += `<input type="radio" name="itemStatus" value="3">`
                    }
            str +=  `<span style="margin-right: 20px;">매진</span> 
                    </div>
                    </div>

                    <h5>상품이미지 정보</h5>

                    <div class="row mt-3 mb-4" style="border: 3px solid #ccc; border-radius: 20px; margin-left: 10px;">
                        <div class="col-3 mb-4">
                            <span>메인이미지</span>
                        </div>
                    <div class="col-8">`;
                       
                    for(const img of data.itemList.imgList){
                        if(img.isMain == 'Y'){
                            str += `<span onclick="showModal('${img.attachedFileName}')">${img.originFileName}</span>`;
                        }
                    }
        str +=     `</div>

                    <div class="col-3 mb-4"> 
                        <span>상세이미지</span>
                    </div>`;
                    
                    let cnt = 0;
                    data.itemList.imgList.forEach(function(img, idx){
                        if(img.isMain == 'N'){
                            if(cnt == 0){
                                str +=`
                                    <div class="col-8">
                                        <span onclick="showModal('${img.attachedFileName}')">${img.originFileName}</span>
                                    </div>
                                `;

                                cnt++;
                            }
                            else{
                                str +=`
                                    <div class="offset-3 col-9">
                                        <span onclick="showModal('${img.attachedFileName}')">${img.originFileName}</span>
                                    </div>
                                `;
                            }
                        }
                    });
        str+=   `</div>
                    <input type="submit" class="form-control" style="width: 150px;">
                </form>    
        `;

        selectMain.insertAdjacentHTML('afterbegin', str);
        
    })
    //fetch 통신 실패 시 실행 영역
    .catch(err=>{
        alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
        console.log(err);
    });
}

//이미지 모달창 띄우기
function showModal(attachedFileName){

    const img_modal = new bootstrap.Modal('#img-modal');

    const img_tag = document.querySelector('#img-modal img');

    img_tag.src = `/upload/${attachedFileName}`;

    img_modal.show();
}


