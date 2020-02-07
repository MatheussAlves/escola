var main = new Vue({
    el:"#main",
    data:{
        usuario:{username: "",
                password:"",
                tipo:"",
                isAuth:""}
    },
    created: function(){
        let vm =  this;
        vm.usuario.isAuth = false;
    }
})