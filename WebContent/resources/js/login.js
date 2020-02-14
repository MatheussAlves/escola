var main = new Vue({
    el:"#main",
    data:{
        url: "http://localhost:8080/escola/rs/usuarios",
        usuario:{
            id:"",
            username: "",
                senha:"",
                tipo:"",
                isAuth:""},
        dismissSecs: 10,
        dismissCountDown: 0,
        showDismissibleAlert: false        
    },
    created: function(){
        let vm =  this;
    },
    methods:{
        logarUsuario: function(){
            const vm = this;
            var xhr = new XMLHttpRequest();
            var user = {}
             user.username = vm.usuario.username;
             user.senha = vm.usuario.senha;
            var json = JSON.stringify(user)
            xhr.open('POST',vm.url+"/login",true);
            xhr.setRequestHeader('Content-type','application/json; charset=utf-8');
            xhr.onload = function(){
                vm.usuario = JSON.parse(xhr.responseText);
                vm.usuario.isAuth = true;
                //console.log(vm.usuario.tipo)
                if (xhr.readyState == 4 && xhr.status == "201") {
                    if(vm.usuario.tipo == null){
                        alert("Usuario ou senha invalidos!")
                    }else{
                        document.location.href="/escola/index.html"
                    }
                } else {
                    console.error("error -> ",vm.usuario);
                }
                if(vm.usuario.tipo == null){
                    alert("Usuario ou senha invalidos!")
                }else{
                    //console.log(Object.values(vm.usuario.tipo))
                    myStorage = window.localStorage;
                    myStorage.setItem('usuario',JSON.stringify(vm.usuario));
                    document.location.href="/escola/index.html"
                }
            }

            xhr.send(json)
        },
        logar: function(){
            const vm = this;
            var authUser;
            axios.post(vm.url+"/login",{username: vm.usuario.username,
                                        senha: vm.usuario.senha})
            .then(response => {
                authUser = response.data
                if(authUser.tipo == null){
                    alert("Usuario ou senha invalidos!")
                }
            }).catch(function(error){
                console.log(error)
            })
        },
        showAlert() {
            this.dismissCountDown = this.dismissSecs
        },
        returnUser(){
            const vm = this;
            return vm.usuario;
        }
    },
    

})