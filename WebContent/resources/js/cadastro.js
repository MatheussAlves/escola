var main = new Vue({
	el:"#main",
	data:{
	//URLS
	url:"http://localhost:8080/escola/rs/usuarios",
	urlAlunos:"http://localhost:8080/escola/rs/alunos",
	urlProfessores:"http://localhost:8080/escola/rs/professores",
	//Alertas e loading
	mostrarAlerta: false,
	mostrarAlertaUsuarioExistente: false,
	loading: false,
	//FormValidation
	password:"",
	passwordToConfirm:"",
	//Entitys
		usuario:{
			id:"",
			username:"",
			senha:"",
			tipo:""
		},
		professor:{nome: "",
				   materia: "",
				   turno:"",},
		aluno:{nome:"",
				idade:"",
				serie:"",
				ensino:""},
	//Variaveis do select
		tipoSelecionado: null,
		grauSelecionado:null,
		serieSelecionada:null,
		turnoSelecionado: null,
		options:[{value: null, text:"Informe o tipo de acesso.", disabled: true},
				{value: "ESTUDANTE", text: "Estudante"},
				{value: "DOCENTE",text: "Professor"}],
		optionsTurno:[{value: null, text:"Informe o tipo de acesso.", disabled: true},
					  {value:"MATUTINO", text:"Matutino"},
					  {value:"VESPERTINO", text:"Vespertino"},
					  {value:"NOTURNO", text:"Noturno"}],
		optionsGrau:[{value: null, text:"Informe um grau", disabled: true},
					{value:"MATERNAL",text:"Maternal"},
					{value:"FUNDAMENTAL",text: "Ensino fundamental 1"},
					{value:"FUNDAMENTAL2",text: "Ensino fundamental 2"},
					{value:"MEDIO", text: "Ensino médio"}],
		optionsMaternal:[{value:null, text: "informe a serie", disabled: true},
						{value:"1", text: "Maternal 1"},
						{value:"2",text: "Maternal 2"}],
		optionsFundamental:[{value:null, text:"Informe o ano", disabled:true},
							{value:"1", text:"1º ano"},
							{value:"2", text:"2º ano"},
							{value:"3", text:"3º ano"},
							{value:"4", text:"4º ano"},
							{value:"5", text:"5º ano"}],
		optionsFundamentalDois:[{value: null, text:"Informe o ano", disabled:true},
							{value:"6", text:"6º ano"},
							{value:"7", text:"7º ano"},
							{value:"8", text:"8º ano"},
							{value:"9", text:"9º ano"}],							
		optionsMedio:[{value: null, text:"Informe o ano", disabled:true},
							{value:"1", text:"1º ano"},
							{value:"2", text:"2º ano"},
							{value:"3", text:"3º ano"}]
	},
	methods:{
		cadastrarUsuario: function(){
			var vm = this;
			var xhr = new XMLHttpRequest();
			if(!vm.validatePasswords()){
				xhr.open('POST',vm.url+"/createUsuario",true)
				vm.usuario.senha = vm.password;
				vm.usuario.tipo = vm.tipoSelecionado;
				console.log(vm.usuario.username,vm.usuario.senha,vm.usuario.tipo)
				var json = JSON.stringify(vm.usuario);
				xhr.setRequestHeader('Content-type','application/json; charset=utf-8');
				xhr.onprogress = function(){
					vm.loading = true;
				}
				xhr.onload = function(){
					if(xhr.status == 200){
						var usuarioCriado = JSON.parse(xhr.responseText);
						if(vm.tipoSelecionado=='DOCENTE'){
							vm.cadastrarProfessor(usuarioCriado);
						}else{
							vm.cadastrarAluno(usuarioCriado);
						}
					}else{
						console.log(xhr.status)
						vm.mostrarAlertaUsuarioExistente = true;
						//window.location.reload();
					}
				}
				xhr.send(json)
			}
		},
		cadastrarProfessor: function(usuario){
			var vm = this;
			var xhr = new XMLHttpRequest();
				
			vm.professor.usuario = usuario;
			console.log(usuario.id);
			xhr.open('POST',vm.urlProfessores,true)
			vm.loading = true;
			var json = JSON.stringify(vm.professor);
			xhr.setRequestHeader('Content-type','application/json; charset=utf-8');
			xhr.onload = function(){
				vm.loading=false;
				if(xhr.status == 200){
					alert("Professor cadastrado com sucesso")
					document.location.reload();
				}else{
					alert("Aconteceu algum erro e o usuario não foi cadastrado")
				}
			}
			xhr.send(json)
			
		},
		cadastrarAluno: function(usuario){
			var vm = this;
			var xhr = new XMLHttpRequest();

			vm.aluno.usuario = usuario;
			vm.aluno.serie = vm.serieSelecionada;
			vm.aluno.ensino = vm.grauSelecionado;
			xhr.open('POST',vm.urlAlunos,true)
			vm.loading = true;
			var json = JSON.stringify(vm.aluno);
			xhr.setRequestHeader('Content-type','application/json; charset=utf-8');
			xhr.onload = function(){
				vm.loading=false;
				if(xhr.status == 200){
					alert("Aluno cadastrado com sucesso")
					document.location.reload();
				}else{
					alert("Aconteceu algum erro e o usuario não foi cadastrado")
				}
			}
			xhr.send(json);
		},
		validatePasswords:function(){
			if(this.password != this.passwordToConfirm){
				this.mostrarAlerta = true;
			}
			return (this.password != this.passwordToConfirm);
		}
	}
})
	
