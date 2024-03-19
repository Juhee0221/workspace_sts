// 자바스크립트의 객체 (다수의 데이터를 저장하는 변수)
const name = '홍';

const stu = {
    'name' : '홍길동',
    'age' : 20
};

console.log(stu)
console.log(`name = ${stu.name}`)
console.log(`age = ${stu.age}`)

//자바스크립트 배열

const arr = [];
arr[0] = 20;
arr[1] = 30;
arr[2] = 'java';

console.log(arr)

const arr1 = [20, 30 , 'java'];
// push 배열에 값 밀어넣기. 
arr1.push(50);
arr1.push('홍');
console.log(arr1);
;
//객체 배열
const arr2 = [
    {
        'name' : '홍',
        'age' : 20
    }
    , {
        'name' : '김',
        'age' : 30
    }
    , {
        'name' : '이',
        'age' : 40
    }
]; 
console.log(arr2);
console.log(arr2[1].name)
function addRow(){
    const tbodyTag = document.querySelector('.stu-list > tbody');


    let str = ` <tr>
                <td><input type="text"></td>
                <td><input type="text"></td>
            </tr>`;

    tbodyTag.insertAdjacentHTML('beforeend',str )
}

function setData(){

    const trs = document.querySelectorAll('.stu-list > tbody > tr');

    //1.총점 구하기
    let sum = 0;

    for(const trTag of trs){
        sum = sum + parseInt(trTag.querySelectorAll('input')[1].value);
    }
    document.querySelector('#totalScore').value = sum;

    //1등 학생명 구하기
    //가장 높은 점수가 있는 tr 선택 

    /////////////////////////////////////////// 1등 학생명 구하기 첫번째 방식 /////////////////////////////////////////////
    // let highScoreTr = trs[0];

    // for(const trTag of trs){
    //     if(parseInt(highScoreTr.querySelector('input')[1].value) < parseInt(trTag.querySelectorAll('input')[1].value)){
    //         highScoreTr = trTag;
    //     }
    // }
    // const highScoreName = highScoreTr.querySelectorAll('input')[0].value;
    // document.querySelector('#firstName').value = highScoreName;
    /////////////////////////////////////////// 1등 학생명 구하기 첫번째 방식 끝!//////////////////////////////////////////

    /////////////////////////////////////////// 1등 학생명 구하기 두번째 방식 방식!//////////////////////////////////////////
    const stuList = [];

    for(const trTag of trs){
        const student = {
            'name' : trTag.querySelectorAll('input')[0].value,
            'score' : parseInt(trTag.querySelectorAll('input')[1].value)

        };   

        stuList.push(student);
    }
    // console.log(stuList);
    let maxStu = stuList[0];
    for(const stu of stuList){
        if(maxStu.score < stu.score){
            maxStu = stu;
        }
    }

    document.querySelector('#firstName').value = maxStu.name;
    /////////////////////////////////////////// 1등 학생명 구하기 두번째 방식 끝!//////////////////////////////////////////

    //3.보충학습대상자
    let names = '';
    for(const stu of stuList){
        if(stu.score < 60){
            names = names + stu.name + ' ';
        }
    }
    document.querySelector('#names').value = names;

}