//création d'un object windows et de méthodes
let windows = {
    idTags: ["dashboard","aj-client","aj-decla"],
    closeAllWindow: function(){
        for(let i=1;i<this.idTags.length;i++){
            document.getElementById(this.idTags[i]).style.visibility = "hidden";
        }
    },
    setVisible: function(tagNb){
        this.closeAllWindow();
        document.getElementById(this.idTags[tagNb]).style.visibility = "visible";
    }
    
}
const link1 = new Vue({
    el: '#link1',
    data: {
        text: 'Dashboard',
    },
    methods:{
        menuEvent: function(){
            windows.setVisible(0);
        },
    }
})
const link2 = new Vue({
    el: '#link2',
    data: {
        text: 'Ajouter un déclarant',
    },
    methods:{
        menuEvent: function(){
            windows.setVisible(1);
        },
    }
})
const link3 = new Vue({
    el: '#link3',
    data: {
        text: 'Ajouter une déclaration'
    },
    methods:{
        menuEvent: function(){
            windows.setVisible(2);
        },
    }
})