$(function(){
	
	//FECHA MENU.
	$('nav.navbar').find('a#fechaMenu').click(function(){
		$('form nav.menu-left').toggleClass('menu-abri')
		$('body div.main').toggleClass('conteudo')
		return false;
	});
	
});