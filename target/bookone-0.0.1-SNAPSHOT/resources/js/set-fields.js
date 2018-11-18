function setFields(isbn, author, name, price, genre){
	console.log("here")
	$("input[name='isbn']").val(isbn);
	//$("input[name='isbn']").prop('disabled', true);
	$("input[name='author']").val(author);
	$("input[name='name']").val(name);
	$("input[name='price']").val(price);
	$("select[name='genreId']").val(genre);
	return false;
}