function getScoreSum(){
    //변수 지정 
    const scores = document.querySelectorAll('.scoreTd');
    //태그들 내부에 들어가 있는 글자 빼는건 -> textcontent

    let sum = 0; 
    //반복문
    //문자열 -> 정수 : parseInt
    let num = 0;
    for(let i = 0; i <scores.length; i++){
        // console.log(scores[i].textContent)
        sum = sum + parseInt(scores[i].textContent);
         // console.log(num)
    }
    // scores.forEach(score => {
    //    sum = sum + parseInt((score.textContent)/scores.length);
    // });
    console.log(sum/scores.length);
 
    const result = document.querySelector('#resultTd');

    result.textContent = (sum/scores.length); 

}