function checkAllMenu(){
    const checks = document.querySelector('#chk_all');
    const menus = document.querySelectorAll('.menu');
    //checkAll이 체크됬을때 check 는 진실.
    const check = checks.checked;

    if(check){
        for(const menu of menus){
           menu.checked = true;
        }
    }
    else{
        for(const menu of menus){
            menu.checked = false;
            
         }
    }
    for(const che of menus){
        checkAllOption(che);
    }

}

function checkAllOption(check){

    const menus = check.nextElementSibling.children;
    
    const che = check.checked;

    //true
    if(che){

        for(let ch of menus){
            const checkbox = ch.firstElementChild;
            console.log(checkbox);
            checkbox.checked = true;
        }
    }
    else{
        
        for(let ch of menus){
            const checkbox = ch.firstElementChild;
            console.log(checkbox);
            checkbox.checked = false;
        }     
    }

}