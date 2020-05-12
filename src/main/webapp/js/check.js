//PARAMETRES
const server_context_url = "http://localhost:8080/";
//METHOD AJAX
//envoi ajax
const ajaxPost = (url,obj,action,callback) => {
    const req = new XMLHttpRequest();
    req.open("POST",url);
    req.addEventListener("load",function(){
        if(req.status >=200 && req.status < 400){
            callback(true,req.responseText);
            action();
        } else {
            callback(false,"Erreur: échec de la requête");
        }
    })
    req.addEventListener("error", function(){
        callback(false,"Erreur: le serveur est injoignable.")
    })
    req.setRequestHeader('Content-Type','application/json');
    req.send(JSON.stringify(obj));
}
function status(response) {
    if (response.status >= 200 && response.status < 300) {
        return Promise.resolve(response)
    } else {
        return Promise.reject(new Error(response.statusText))
    }
}
function json(response) {
    return response.json()
}
//CONTRAINTES SUR LES INPUT
//Fonction qui fait une verif regex sur chaque caractére
const toRegexChar = (content,regex) => {
    let value = content;
    for(let i=0;i<content.length;++i){
        if(!regex.test(content[i])){
            value = value.replace(content[i],"");
        }
    }
    return value;
}