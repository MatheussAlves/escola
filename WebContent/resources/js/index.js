var main = new Vue({
    el:"#main",
    data:{
        usuarioLogado:{username: " ",
                password:" ",
                tipo:" ",
                isAuth:false},
        professores:[],
        alunos:[],
        urlProfessores: "http://localhost:8080/escola/rs/professores",
        urlAlunos:"http://localhost:8080/escola/rs/alunos"
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
        vm.getProfessores();
        vm.getAlunos();
    },
    methods:{
        sair: function(){
            localStorage.clear();
            document.location.href="/escola/login.html"
        },
        getProfessores: function(){
            var xhr = new XMLHttpRequest();
            var vm = this;
            xhr.open('GET',vm.urlProfessores,true);
            xhr.setRequestHeader('Content-type','application/json; charset=utf-8');
            xhr.onload = function(){
                vm.professores = JSON.parse(xhr.responseText);
                if (xhr.readyState == 4 && xhr.status == "200") {
                    console.table(vm.professores);
                } else {
                    console.error(vm.professores);
                }
            }
            xhr.send();
        },
        getAlunos: function(){
            var xhr = new XMLHttpRequest();
            var vm = this;
            xhr.open('GET',vm.urlAlunos,true);
            xhr.setRequestHeader('Content-type','application/json; charset=utf-8');
            xhr.onload = function(){
                vm.alunos = JSON.parse(xhr.responseText);
                if (xhr.readyState == 4 && xhr.status == "200") {
                    console.table(vm.alunos);
                } else {
                    console.error(vm.alunos);
                }
            }
            xhr.send();
        }
    }
})