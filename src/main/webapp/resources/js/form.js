$('.form-fieldset > input').blur(function (e) {
  $(this).toggleClass('filled', !!$(this).val());
});

function formClick(){
	document.getElementById("form").submit();
	return false;
}