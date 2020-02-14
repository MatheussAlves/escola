var main = new Vue({
    el:"#main",
    data:{
        //DADOS
        usuarioLogado:{
            id:"",
            username: " ",
                password:" ",
                tipo:" ",
                isAuth:false},
        professores:[],
        alunos:[],
        aluno:{
            id:"",
            nome:"",
            idade: "",
            ensino:"",
            serie:"",
            usuario:""
        },
        professor:{
            id:"",
            nome:"",
            materia:"",
            turno:"",
            usuario:"",
        },
        usuario:{
            id:"",
            username:"",
            senha:"",
            tipo:""
        },
        //URLS
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
        console.log(userAux)
        
       switch(userAux.tipo){
           case 'DOCENTE':
                vm.getAlunos();          
           case 'ADMINISTRADOR':
               vm.getProfessores();
               vm.getAlunos();
            case 'ESTUDANTE':
                vm.getPerfilAluno();
       }
        
    },
    methods:{
        sair: function(){
            var vm = this;
            localStorage.clear();
            vm.userAux = null;
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
        },
        getDadosUsuario: function(){
            var xhr = new XMLHttpRequest();
            var vm = this;
            
        },
        getPerfilAluno: function(){
            var xhr = new XMLHttpRequest();
            var vm = this;
            xhr.open('GET',vm.urlAlunos+"/user?id="+vm.usuarioLogado.id,true)
            xhr.setRequestHeader('Content-type','application/json; charset=utf-8');
            xhr.onload = function(){
                if(xhr.readyState == 4 && xhr.status == "200"){
                    vm.aluno = JSON.parse(xhr.responseText);
                    //console.log(xhr.responseText,vm.aluno)
                    //console.log(vm.usuarioLogado.id)
                }else{
                    console.error("Erro ao recuperar usuario");
                }
            }
            xhr.send();
        }
    }
})