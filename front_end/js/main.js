
var pesquisar = $("#ghsubmitbtn");
var search;


$(document).ready(function(){
  $("#not_found").hide();
  $("#found").hide();
});

pesquisar.click(pesquisarUsuario);

function pesquisarUsuario(){
	
  search = $("#ghusername").val();
	if(search==null || search=="" || search==undefined){
		alert("Informe um username");
	}else{
    $.get({
        url: "https://api.github.com/users/" +search,
        success: preencheDados
    }).fail(function(){
    
     $("#not_found").show();
     $("#found").hide();
    });
	}
}

function preencheDados(dados){
   
   $("#not_found").hide();
   $("#found").show();
   

   $("#name").text(dados.name);
   $("#name").append('(@<span class="smallname"><a class="html_url" href="'+dados.html_url+'" target="_blank">'+dados.login+'</a></span>)')
   $("#login").text(dados.login);
   $("#id").text(dados.id);
   $("#node_id").text(dados.node_id);
   $("#avatar_url").prop("src",dados.avatar_url);
   $("#gravatar_id").text(dados.gravatar_id);
   $(".html_url").prop("href",dados.html_url);
   $("#ffr").html('Followers: '+dados.followers+' - Following: '+dados.following+'<br> Repos:'+dados.public_repos);

   if( typeof dados.public_repos === 'number' && dados.public_repos >0){
    pesquisarRepositorios(dados.repos_url);
     
    $("#no_repo").hide();
    $("#repo").show();
   }else{
    $("#repo").hide();
    $("#no_repo").show();
    
   }
   
}

function pesquisarRepositorios(repos_url){
    $.get({
        url: repos_url,
        success: preecheListaRepositorios
    }).fail(function(){
     $("#found").hide();
     $("#not_found").show();
    });  
}

function preecheListaRepositorios(dados){
   $("#lisRepo").html("");
   var table = dados;
   for (var i = 0 ; i < table.length; i++) {
     $("#lisRepo").append('<a href="'+table[i].html_url+'" target="_blank">'+table[i].name+'</a>');   

   }

  
}


