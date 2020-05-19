//on crée un objet ajclient
let ajClient = new Vue({
    el: '#aj-client',
    data:{
        nom:"",
        prenom:"",
        numeroNational:"",
        handicap:"",
        adresse:"",
        telephone:"",
        email:""
    },
    methods: {
        close: function(){
            document.getElementById("aj-client").style.visibility = "hidden";
        },
        eraseFormData: function(){
            this.nom="";
            this.prenom="";
            this.numeroNational="";
            this.handicap="";
            this.adresse="";
            this.telephone="";
            this.email="";
        },
        send: function(){
            const client = {
                nom:this.nom,
                prenom:this.prenom,
                numeroNational:this.numeroNational,
                handicap:this.handicap,
                adresse: this.adresse,
                telephone: this.telephone,
                email: this.email
            }
            ajaxPost(server_context_url+"ajclient",client,this.eraseFormData,function(resultReq,resultTxt){
                if(resultReq===true){
                    const resp = JSON.parse(resultTxt);
                    if(resp.result===true){
                        alert(resp.message);
                        dashboardTable.update();
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
        numeroNational: function (val) {
            this.numeroNational=toRegexChar(val,RegExp('\\d'));
        },
        numeroNationalConjoint: function (val) {
            this.numeroNational=toRegexChar(val,RegExp('\\d'));
        }

    }
})
