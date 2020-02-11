var main = new Vue({
    el:"#main",
    data:{
        usuarioLogado:{username: "",
                password:"",
                tipo:"",
                isAuth:""},
    //usuario = require('../resources/js/login.js').usuario
    },
    created: function(){
        let vm =  this;
        vm.usuarioLogado = JSON.parse(localStorage.getItem('usuario'));
    },
    methods:{
        sair: function(){
            localStorage.clear();
            document.location.href="/escola/login.html"
        }
    }
})