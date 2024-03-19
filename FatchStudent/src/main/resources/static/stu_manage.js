function goSelect(){
   const classCode = document.querySelector('.select').value;
   location.href= `/?classCode=${classCode}`;
}

function fetchSelect(){
    const classCode = document.querySelector('.select').value;

    // ------------------- 첫번째 방식 ---------------//
    fetch('/fetchSelect', { //요청경로
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
        },
        //컨트롤러로 전달할 데이터
        body: new URLSearchParams({
        // 데이터명 : 데이터값
            classCode : classCode
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
        //기존 테이블 내용 삭제
        // 삭제할 태그를 선택 
       const tbodyTag = document.querySelector('#table > tbody');

       // 삭제할 태그 안의 모든 내용을 삭제
       tbodyTag.innerHTML = '';
        
       //새로 조회한 데이터로 태그안의 내용을 채워 줌.
       let str = ``; 
    //    for(const stu of data){
    //         str += `<tr>
    //                     <td></td>
    //                     <td>${stu.className}</td>
    //                     <td>${stu.stuNum}</td>
    //                     <td>${stu.stuName}</td>
    //                 </tr>`;
    //    }
       //element = foreach 문에서 변수와 같은 역할
                        //( 변수이름 , 반복하는 횟수 )
       data.forEach(function(stu, i){
            str += `<tr>
                    <td>${data.length - i}</td>
                    <td>${stu.className}</td>
                    <td>${stu.stuNum}</td>
                    <td>
                        <span onclick="goDetail(${stu.stuNum})">
                            ${stu.stuName}
                        </span>
                    </td>
                </tr>`;
       });

       tbodyTag.insertAdjacentHTML('afterbegin', str);
       
    })
    //fetch 통신 실패 시 실행 영역
    .catch(err=>{
        alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
        console.log(err);
    });

}

function goDetail(stuNum){
    // ------------------- 첫번째 방식 ---------------//
    
    fetch('/stuSelect', { //요청경로
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
        },
        //컨트롤러로 전달할 데이터
        body: new URLSearchParams({
        // 데이터명 : 데이터값
            stuNum : stuNum
        })
    })
    .then((response) => {
        if(!response.ok){
            alert('fetch error!\n컨트롤러로 통신중에 오류가 발생했습니다.');
            return ;
        }

        //  return response.text(); //컨트롤러에서 return하는 데이터가 없거나 int, String 일 때 사용
         return response.json(); //나머지 경우에 사용
    })
    //fetch 통신 후 실행 영역
    .then((data) => {//data -> controller에서 리턴되는 데이터!

        console.log(data);
        //조회된 데이터로 그림 그리기
        const detail_div = document.querySelector('.container');
        detail_div.innerHTML = '';


            let str = `
                <div class="stu-detail">
                    <div class="text">
                        학생기본정보
                    </div>
                    <table id="table">
                        <thead>
                            <tr class="tr1">
                                <td>학번</td>
                                <td>소속반</td>
                                <td>학생명</td>
                            </tr>
                        </thead>
                        <tbody class="tr2">
                            <tr>
                                <td class="stuNumTd">${data.stuVO.stuNum}</td>
                                <td>${data.stuVO.className}</td>
                                <td>${data.stuVO.stuName}</td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                <div class="stu_score">
                    <div class="text">
                        학생 점수 정보
                    </div>
                    <table id="table">
                        <thead>
                            <tr class="tr1">
                                <td>국어점수</td>
                                <td>영어점수</td>
                                <td>수학점수</td>
                                <td>평균</td>
                            </tr>
                        </thead>
                        <tbody class="tbody">
                            <tr class="tr2">
                                <td class="td">${data.scoreVO == null ? 0 : data.scoreVO.korScore} </td>
                                <td class="td">${data.scoreVO == null ? 0 : data.scoreVO.engScore}</td>
                                <td class="td">${data.scoreVO == null ? 0 : data.scoreVO.mathScore}</td>
                                <td>${data.scoreVO == null ? 0.0 : 
                                    (data.scoreVO.korScore + data.scoreVO.engScore + data.scoreVO.mathScore)/3.0}</td>
                            </tr>
                        </tbody>
                    </table>
                    <div class="btn">
                       <input type="button" value="점수입력" onclick="goScore()">             
                    </div>
                </div>
            `;
        
        detail_div.insertAdjacentHTML('afterbegin', str);
           
    })
    //fetch 통신 실패 시 실행 영역
    .catch(err=>{
        alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
        console.log(err);
    });

}

function goScore(){
    const scoreInput = document.querySelectorAll('.tbody > .tr2 > .td')
    const button = document.querySelector('.btn')
    button.innerHTML = '';

    // for(let i = 0; i < scoreInput.length; i ++){
    //     scoreInput[i].textContent = '<input type="text">';
    // }
    // for(const e of tds){
    //     e.textContent = '<input type="text">'
    // }
    scoreInput.forEach(function(e, i){
        e.innerHTML = '<input type="text" class="input">'
    });

    // if(button.value =='점수입력'){
    //     const scoreInput = document.querySelectorAll('.tbody > .tr2 > .td')
    //     document.querySelector('.btn').value
    // }
    // confirm('입력한 정보로 점수를 등록할까요?')

    // let score = `
    //             <td>
    //                 <input type="text">
    //             </td>
    //             <td>
    //                 <input type="text">
    //             </td>
    //             <td>
    //                 <input type="text">
    //             </td>
    //             <td>
    //                 <input type="text" readonly>
    //             </td>
    //          `;
    let bnt = `
            <input type="button" value="저장" onclick="goInsert()">
    `;    
       
        
    // scoreInput.insertAdjacentHTML('afterbegin', score)
    button.insertAdjacentHTML('afterbegin', bnt)
}

function goInsert(){
    const inputs = document.querySelectorAll('.input');
    const stuNum = document.querySelector('.stuNumTd').textContent;

    confirm('입력한 정보로 점수를 등록할까요?')

    // ------------------- 첫번째 방식 ---------------//
    fetch('/scoreInsert', { //요청경로
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
        },
        //컨트롤러로 전달할 데이터
        body: new URLSearchParams({
        // 데이터명 : 데이터값
            korScore : inputs[0].value,
            engScore : inputs[1].value,
            mathScore : inputs[2].value,
            stuNum : stuNum
        })
    })
    .then((response) => {
        if(!response.ok){
            alert('fetch error!\n컨트롤러로 통신중에 오류가 발생했습니다.');
            return ;
        }

        return response.text(); //컨트롤러에서 return하는 데이터가 없거나 int, String 일 때 사용
        //return response.json(); //나머지 경우에 사용
    })
    //fetch 통신 후 실행 영역
    .then((data) => {//data -> controller에서 리턴되는 데이터!
        alert('점수가 등록되었습니다.');
        goDetail(stuNum);
    })
    //fetch 통신 실패 시 실행 영역
    .catch(err=>{
        alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
        console.log(err);
    });
}