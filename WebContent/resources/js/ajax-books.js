function deleteBook(isbn){
	$.ajax({
        url: "books/" + isbn,
        type: "DELETE"
    }).then(function() {
    	var trId = "#tr" + isbn;
    	$(trId).css( "display", "none" )
    });
	return false;
}

function goToUserProfile(username) {
    //window.location.href = "users/"+username+"?r=" + (Math.random() * 10);
    window.location.replace("users/"+username);
}

function buyBook(username, isbn){
    $.ajax({
        url: "users/" + username + "/orders",
		data: {"isbn":isbn, "quantity": $("#quantity"+isbn).val()},
        type: "POST",
		success: goToUserProfile(username),
		error: goToUserProfile(username)
    });
}
function deleteOrder(username, orderNo){
    $.ajax({
        url: username + "/orders/" + orderNo,
        type: "DELETE"
    }).then(function() {
        var trId = "#tr" + orderNo;
        $(trId).css( "display", "none" )
    });
    return false;
}