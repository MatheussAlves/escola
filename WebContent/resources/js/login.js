var main = new Vue({
    el:"#main",
    data:{
        url: "http://localhost:8080/escola/rs/usuarios",
        usuario:{username: "",
                senha:"",
                tipo:"",
                isAuth:""},
        dismissSecs: 10,
        dismissCountDown: 0,
        showDismissibleAlert: false        
    },
    created: function(){
        let vm =  this;
        vm.usuario.isAuth = false;
    },
    methods:{
        logarUsuario: function(){
            const vm = this;
            var xhr = new XMLHttpRequest();
            var usuario = {}
             usuario.username = vm.usuario.username;
             usuario.senha = vm.usuario.password;
            var json = JSON.stringify(usuario)
            console.log(usuario)
            xhr.open('POST',vm.url+"/login",true);
            xhr.setRequestHeader('Content-type','application/json; charset=utf-8');
            xhr.onload = function(){
                var isLogin = JSON.parse(xhr.responseText);
                vm.isAuth = isLogin
                if (xhr.readyState == 4 && xhr.status == "201") {
                    console.table(isLogin);
                } else {
                    console.error(isLogin);
                }
                if(vm.isAuth){
                    document.location.href="/escola/index.html"
                }else{
                    alert("Usuario ou senha invalidos")
                }
            }
           
            xhr.send(json)
            this.showAlert();
        },
        showAlert() {
            this.dismissCountDown = this.dismissSecs
        }
    }
})