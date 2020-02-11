var main = new Vue({
    el:"#main",
    data:{
        usuarioLogado:{username: " ",
                password:" ",
                tipo:" ",
                isAuth:false},
    //usuario = require('../resources/js/login.js').usuario
    },
    created: function(){
        let vm =  this;
        var userAux;
        userAux = JSON.parse(localStorage.getItem('usuario'));
        if(userAux == null){
            alert("Não foi possivel autenticar");
            document.location.href="/escola/login.html"
        }else{
            vm.usuarioLogado = userAux;
        }
    },
    methods:{
        sair: function(){
            localStorage.clear();
            document.location.href="/escola/login.html"
        }
    }
})