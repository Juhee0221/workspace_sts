const btn = document.querySelector('#btn');

btn.addEventListener('click', function(){

    //세로 열 변수 value 값을 정수로 변환

    const rowCnt = parseInt(document.querySelector('#rowInput').value);

    //가로 행 변수 value 값을 정수로 변환 
    const colCnt = parseInt(document.querySelector('#colInput').value);

    console.log(rowCnt);
    console.log(colCnt);

    const table = document.querySelector('.table-div');

    table.innerHTML ='';
    let str = '';
    // <table>
	// 	<tr>
	// 		<td></td>
	// 	</tr>
	// </table>
    for(let b = 0; b < rowCnt; b++){
        str += '<table>';
        str += '<tr>';
        for(let i=0; i < colCnt; i++){
            str+= '<td>';
            str+= `${b+1}열 ${i+1}행 `;
            str+= '</td>';
        }
        str += '</tr>';
        str += '</table>';
    }

   

    table.insertAdjacentHTML('afterbegin', str);
})
