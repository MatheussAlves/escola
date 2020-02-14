import * as dados from "./cadastro.js"
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
        toEdit:{},
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
        urlAlunos:"http://localhost:8080/escola/rs/alunos",
        //OPCOES
        graus:{},
        series:{},
        turnos:{},
        grauSelecionado:{}
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
                break;          
           case 'ADMINISTRADOR':
               vm.getProfessores();
               vm.getAlunos();
               break;
            case 'ESTUDANTE':
                vm.getPerfilAluno();
                break;
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
        },
        loadEdicao:function(object,num){
            this.$bvModal.show('modal-'+num);
            var vm = this;
            vm.toEdit = object;
            if(vm.toEdit.materia != null){
                vm.turnos = dados.optionsTurno;
            }else{
               vm.graus = dados.optionsGrau;
               switch(vm.grauSelecionado){
                   case 'MATERNAL':
                       vm.series = dados.optionsMaternal;
                       break;
                   case 'FUNDAMENTAL':
                       vm.series = dados.optionsFundamental;
                       break;
                   case 'FUNDAMENTAL2':
                       vm.series = dados.optionsFundamentalDois
                       break;
                   case 'MEDIO':
                       vm.series = dados.optionsMedio
                       break;
               }
               
            }
            
        },
        editAluno: function(){

        },
        editProfessor: function(){

        },
        deleteAluno: function(){

        },
        deleteProfessor: function(){

        }
    }
})