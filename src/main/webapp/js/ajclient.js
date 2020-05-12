//on crée un objet ajclient
let ajClient = new Vue({
    el: '#aj-client',
    data:{
        nn:"",
        name:"",
        firstname:"",
        mail:"",
        phone_number:"",
        rue:"",
        nr:"",
        codepostal:"",
        commune:""
    },
    methods: {
        close: function(){
            document.getElementById("aj-client").style.visibility = "hidden";
        },
        eraseFormData: function(){
            this.nn="";
            this.name="";
            this.firstname="";
            this.mail="";
            this.phone_number="";
            this.rue="";
            this.nr="";
            this.codepostal="";
            this.commune="";
        },
        send: function(){
            const adress = "";
            if(!(this.rue+this.nr+this.codepostal+this.commune).length===0){
                adress = `${this.rue}, ${this.nr} ${this.codepostal} ${this.commune}`;
            }
            const client = {
                nn: this.nn,
                name: this.name,
                firstname: this.firstname,
                mail: this.mail,
                adress: adress,
                phoneNumber: this.phone_number
            }
            ajaxPost(server_context_url+"ajclient",client,this.eraseFormData,function(resultReq,resultTxt){
                if(resultReq===true){
                    const resp = JSON.parse(resultTxt);
                    if(resp.result===true){
                        alert(resp.message);
                    } else {
                        alert(resp.message);
                    }
                } else {
                    alert(resultTxt);
                }
            })
        }
    },
    watch: {
        nn: function (val) {
            this.nn=toRegexChar(val,RegExp('\\d'));
        },
        nr: function (val) {
            this.nr=toRegexChar(val,RegExp('[\\w-]'));
        },
        codepostal: function (val) {
            this.codepostal=toRegexChar(val,RegExp('\\d'));
        }

    }
})
