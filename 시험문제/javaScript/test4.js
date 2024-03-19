function addRow(){
    const stuScore = document.querySelector('.stu_score');

    let str = '';

    str +='<tr class = "allScore">'
        str+='<td>'
        str+='    <input type="text" class="name">'
        str+='</td>'
        str+='<td>'
        str+='    <input type="text" class="score">'
        str+='</td>'
    str+= '</tr>'

    stuScore.insertAdjacentHTML("afterbegin",str);
}

function setData(){
    const stuNames = document.querySelectorAll('.name');
    const stuScores = document.querySelectorAll('.score');
    const allScores = document.querySelectorAll('.allScore')
    console.log(stuScores);

    //학생들 총점
    const totalScore = document.querySelector('#totalScore');
    const firstName = document.querySelector('#firstName');
    const names = document.querySelector('#names');

    let sum = 0;

    for(let i= 0; i < stuScores.length; i++){
       sum = sum + parseInt(stuScores[i].value);
       totalScore.value = sum;
    }
    console.log(totalScore);

    let name = "";
    let num = 0;

    for(let i = 0; i < allScores.length; i++){
        if(document.querySelectorAll('tr > td > .score' < i) ){
            for(let j = 0; j < stuScores.length; j++){
                name = document.querySelectorAll('tr > td > .name')[j].value;
            }
        }
        console.log(name);
        firstName.value = name;
    }

    let name2 = "";
    for(let i=0; i <allScores.length; i++){
        if(document.querySelectorAll('tr > td > .score' < 60) ){
            for(let j = 0; j < stuScores.length; j++)
            name2 = document.querySelectorAll('tr > td > .name')[j].value
        }
        console.log(name);
        names.value = name2;
    }
};




