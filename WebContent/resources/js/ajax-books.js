function deleteBook(isbn){
	$.ajax({
        url: "books/" + isbn,
        type: "DELETE"
    }).then(function(data) {
    	var trId = "#tr" + isbn;
    	console.log(data);
    	$(trId).css( "display", "none" )
    });
	return false;
}