// ------------------- 첫번째 방식 ---------------//

//부트스트랩이 제공하는 모달 태그를 선택하는 방법
const buy_detail_modal = new bootstrap.Modal('#buy-detail-modal');

//모달 열기
// buy_detail_modal.show();

//모달 닫기
// buy_detail_modal.hide();

//행 클릭시 구매 상세 내역 조회 및 모달창 띄우기 
function selectDetail(buyCode){
    fetch('/admin/selectHistory', { //요청경로
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
        },
        //컨트롤러로 전달할 데이터
        body: new URLSearchParams({
           // 데이터명 : 데이터값
            buyCode : buyCode
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
        const trTag = document.querySelector(".select_buy")
        const tableTag = document.querySelector(".select_detail");

        console.log(data);

        trTag.innerHTML = '';
        tableTag.innerHTML = '';

        let str = '';
        

        str +=  '<colgroup>'
        str +=  '   <col width="20%">'
        str +=  '   <col width="18%">'
        str +=  '   <col width="20%">'
        str +=  '   <col width="*">'
        str +=  '</colgroup>'
        str +=  '<tr>'
        str +=      '<td>구매번호 | </td>'
        str +=      `<td>${data.buyCode}</td>`
        str +=      '<td>구매자ID |</td>'
        str +=      `<td>${data.memberId}</td>`
        str +=   '</tr>'
        str +=  '<tr>'
        str +=      '<td>구매금액 |</td>'
        str +=      `<td>${data.buyPrice}</td>`
        str +=      '<td>구매일시 |</td>'
        str +=      `<td>${data.buyDate}</td>`
        str +=  '</tr>'


        let strs = '';

      

        console.log(data.buyList);

            strs += `
                    <colgroup>
                        <col width="10%">
                        <col width="45%">
                        <col width="10%">
                        <col width="*">
                    </colgroup>
            `

        data.buyList.forEach((buy , i) => {

            console.log(buy);

            strs += `
                    <tr>
                        <td>NO</td>
                        <td>구매상품</td>
                        <td>수량</td>
                        <td>결제금액</td>
                    </tr>
                    <tr>
                        <td>${data.buyList.length - i}</td>
                        <td style="margin-left : 0;">
                            <span>
                                <img width="30%" src="/upload/${buy.itemVO.imgList[0].attachedFileName}">
                            </span>
                            <span>
                                ${buy.itemVO.itemName}
                            </span>
                        </td>
                        <td>
                            ${buy.buyCnt}
                        </td>
                        <td>
                            ${buy.totalPrice}
                        </td>
                    </tr>`
              });
        
      
        trTag.insertAdjacentHTML('afterbegin', str);
        tableTag.insertAdjacentHTML('afterbegin', strs);

        buy_detail_modal.show();
    })
    //fetch 통신 실패 시 실행 영역
    .catch(err=>{
        alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
        console.log(err);
    });

}
