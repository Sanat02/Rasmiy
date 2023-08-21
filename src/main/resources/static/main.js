function saveUser(user){
    let userAsJson=JSON.stringify(user)
    localStorage.setItem('user',userAsJson)
}

function readUser(){
    let userAsJson=localStorage.getItem('user')
    let user=JSON.parse(userAsJson)
    return user
}

function makeHeaders(){
    let headers=new Headers()
    let user=readUser()
    headers.set('Content-Type','application/json')
    if(user){
        headers.set('Authorization','Basic'+btoa(user.email+":"+user.password))

    }
    return headers
}

const requestSettings={
    method:'GET',
    headers:makeHeaders()
}

